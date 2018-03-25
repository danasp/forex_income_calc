package vereshchakov.entity;

import vereshchakov.bl.Currency;
import vereshchakov.entity.impl.UsdForexRate;

import java.time.LocalDate;

/**
 * Created by Danila Vereshchakov on 24.03.18.
 */
public class ForexRateFactory {

    private ForexRateFactory(){
    }

    public static ForexRate getInstance(Currency currency, LocalDate date) {
        switch (currency) {
            case USD:
                return new UsdForexRate(date);
            default:
                throw new IllegalArgumentException("Not supported base currency: " + currency.name());
        }
    }

    public static ForexRate getInstance(String currency, LocalDate date) {
        try {
            Currency cur = Currency.valueOf(currency.toUpperCase());
            return getInstance(cur, date);
        } catch (IllegalArgumentException | NullPointerException e) {
            throw new IllegalArgumentException("Not supported base currency: " + currency);
        }
    }
}
