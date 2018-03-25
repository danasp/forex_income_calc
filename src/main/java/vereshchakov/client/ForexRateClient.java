package vereshchakov.client;

import vereshchakov.bl.Currency;
import vereshchakov.client.response.RatesResponse;

import java.time.LocalDate;

/**
 * Created by Danila Vereshchakov on 24.03.18.
 */
public interface ForexRateClient {
    RatesResponse getLatestRate(Currency baseCurrency, Currency... specificCurrency);
    RatesResponse getHistoricalRate(LocalDate date, Currency... specificCurrency);
}
