package vereshchakov.client;

import vereshchakov.bl.Currency;
import vereshchakov.client.response.RatesResponse;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.MediaType;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Client for https://openexchangerates.org
 */
public class OerForexRateClient implements ForexRateClient {

    private static final String APP_ID = "23d1003f347d408483eb93be949aee1e";
    private static final String SERVICE_URL = "https://openexchangerates.org/api/";
    private static final String LATEST_RATES_URL = "latest.json";
    private static final String HISTORICAL_RATES_URL = "historical/%s.json";

    public RatesResponse getLatestRate(Currency baseCurrency, Currency... specificCurrency) {
        Client client = ClientBuilder.newClient();
        RatesResponse response = client
                .target(SERVICE_URL)
                .path(LATEST_RATES_URL)
                .queryParam("app_id", APP_ID)
                .queryParam("symbols", formatSymbols(specificCurrency))
                .request(MediaType.APPLICATION_JSON_TYPE)
                .get(RatesResponse.class);

        return response;
    }

    public RatesResponse getHistoricalRate(LocalDate date, Currency... specificCurrency) {
        String historicalRatesUrl = String.format(HISTORICAL_RATES_URL, formatDate(date));
        Client client = ClientBuilder.newClient();
        RatesResponse response = client
                .target(SERVICE_URL)
                .path(historicalRatesUrl)
                .queryParam("app_id", APP_ID)
                .queryParam("symbols", formatSymbols(specificCurrency))
                .request(MediaType.APPLICATION_JSON_TYPE)
                .get(RatesResponse.class);

        return response;
    }

    private String formatDate(LocalDate date) {
        return date.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
    }

    private String formatSymbols(Currency...currencies) {
        StringBuilder sb = new StringBuilder();
        for (Currency currency : currencies) {
            sb.append(currency.name()).append(",");
        }
        return sb.toString();
    }
}
