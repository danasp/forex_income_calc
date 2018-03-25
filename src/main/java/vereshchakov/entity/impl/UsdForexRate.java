package vereshchakov.entity.impl;

import vereshchakov.bl.Currency;
import vereshchakov.entity.ForexRate;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Collections;
import java.util.EnumMap;
import java.util.Map;

/**
 * Created by Danila Vereshchakov on 24.03.18.
 */
public class UsdForexRate implements ForexRate {
    private Currency base = Currency.USD;
    private Map<Currency, BigDecimal> quoteRates = new EnumMap<>(Currency.class);
    private LocalDate date;

    public UsdForexRate(LocalDate date) {
        this.date = date;
    }

    public Currency getBase() {
        return base;
    }

    public Map<Currency, BigDecimal> getQuoteRates() {
        return Collections.unmodifiableMap(quoteRates);
    }

    @Override
    public void addQuoteRate(Currency currency, BigDecimal value) {
        if(currency == null || value == null) {
            throw new IllegalArgumentException("Currency or value is null");
        }

        quoteRates.put(currency, value);
    }

    @Override
    public LocalDate getDate() {
        return date;
    }

}
