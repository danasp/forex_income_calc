package vereshchakov.dao;

import vereshchakov.bl.Currency;
import vereshchakov.entity.ForexRate;
import vereshchakov.entity.ForexRateFactory;
import vereshchakov.client.ForexRateClient;
import vereshchakov.client.response.RatesResponse;

import java.math.BigDecimal;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Map;

/**
 * Created by Danila Vereshchakov on 24.03.18.
 */
public class ForexRateDaoImpl implements ForexRateDao {

    private ForexRateClient forexRateClient;

    public ForexRateDaoImpl(ForexRateClient forexRateClient) {
        this.forexRateClient = forexRateClient;
    }

    @Override
    public ForexRate getLatestForexRate(Currency baseCurrency, Currency... specificCurrency) {
        RatesResponse latestRates = forexRateClient.getLatestRate(baseCurrency, specificCurrency);
        return responseMapper(latestRates);
    }

    @Override
    /**
     * get all supported pair for USD
     */
    public ForexRate getUsdLatestForexRate() {
        return getLatestForexRate(Currency.USD, Currency.values());
    }

    @Override
    public ForexRate getHistoricalForexRate(LocalDate date, Currency baseCurrency, Currency... specificCurrency) {
        RatesResponse historicalRates = forexRateClient.getHistoricalRate(date);
        return responseMapper(historicalRates);
    }

    /**
     * get all supported historical pair for USD
     */
    @Override
    public ForexRate getUsdHistoricalForexRate(LocalDate date) {
        return getHistoricalForexRate(date, Currency.USD, Currency.values());
    }

    private ForexRate responseMapper(RatesResponse rawResponse) {
        LocalDate date = Instant.ofEpochSecond(rawResponse.getTimestampSec())
                .atZone(ZoneId.of("UTC"))
                .toLocalDate();

        ForexRate forexRate = ForexRateFactory.getInstance(rawResponse.getBase(), date);

        Map<String, Double> quotedRates = rawResponse.getRates();
        for (Map.Entry<String, Double> pair : quotedRates.entrySet()) {
            String currencyStr = pair.getKey();
            if (isCurrencySupport(currencyStr)) {
                BigDecimal value = new BigDecimal(pair.getValue());
                forexRate.addQuoteRate(Currency.valueOf(currencyStr), value);
            }
        }

        return forexRate;
    }

    private boolean isCurrencySupport(String currency) {
        try {
            Currency.valueOf(currency);
            return true;
        } catch (RuntimeException e) {
            return false;
        }
    }
}
