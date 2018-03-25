package vereshchakov.entity;

import vereshchakov.bl.Currency;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Map;

/**
 * Created by Danila Vereshchakov on 24.03.18.
 */
public interface ForexRate {
    Currency getBase();
    Map<Currency, BigDecimal> getQuoteRates();
    void addQuoteRate(Currency currency, BigDecimal value);
    LocalDate getDate();
}
