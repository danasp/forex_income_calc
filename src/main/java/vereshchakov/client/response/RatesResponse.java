package vereshchakov.client.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Map;

/**
 * Created by Danila Vereshchakov on 24.03.18.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class RatesResponse {

    //seconds from epoch
    @JsonProperty("timestamp")
    private long timestampSec;
    //base currency
    private String base;
    //quoted currency rates
    private Map<String, Double> rates;

    public long getTimestampSec() {
        return timestampSec;
    }

    public void setTimestampSec(long timestampSec) {
        this.timestampSec = timestampSec;
    }

    public String getBase() {
        return base;
    }

    public void setBase(String base) {
        this.base = base;
    }

    public Map<String, Double> getRates() {
        return rates;
    }

    public void setRates(Map<String, Double> rates) {
        this.rates = rates;
    }
}
