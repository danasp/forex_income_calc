package vereshchakov.dao;

import vereshchakov.bl.Currency;
import vereshchakov.entity.ForexRate;

import java.time.LocalDate;

/**
 * Created by Danila Vereshchakov on 24.03.18.
 */
public interface ForexRateDao {
    ForexRate getLatestForexRate(Currency baseCurrency, Currency...specificCurrency);
    ForexRate getUsdLatestForexRate();

    ForexRate getHistoricalForexRate(LocalDate date, Currency baseCurrency, Currency...specificCurrency);
    ForexRate getUsdHistoricalForexRate(LocalDate date);

}
