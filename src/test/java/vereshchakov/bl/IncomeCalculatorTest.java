package vereshchakov.bl;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import vereshchakov.dao.ForexRateDao;
import vereshchakov.dao.ForexRateDaoImpl;
import vereshchakov.entity.ForexRate;
import vereshchakov.entity.impl.UsdForexRate;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.Month;

import static org.mockito.Matchers.anyObject;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Created by Danila Vereshchakov on 24.03.18.
 */
public class IncomeCalculatorTest {

    private ForexRate currentForexRate;
    private ForexRate histForexRate;

    @Before
    public void initForexRate() {
        currentForexRate = new UsdForexRate(LocalDate.of(2018, Month.MARCH, 24));
        currentForexRate.addQuoteRate(Currency.RUB, new BigDecimal("57.2334"));

        histForexRate = new UsdForexRate(LocalDate.of(2017, Month.MARCH, 24));
        histForexRate.addQuoteRate(Currency.RUB, new BigDecimal("56.9488"));
    }


    @Test
    public void incomeCalcTest() {
        ForexRateDao forexRateDao = mock(ForexRateDaoImpl.class);
        when(forexRateDao.getUsdLatestForexRate()).thenReturn(currentForexRate);
        when(forexRateDao.getUsdHistoricalForexRate(anyObject())).thenReturn(histForexRate);

        IncomeCalculator calculator = new IncomeCalculator(forexRateDao);

        BigDecimal result = calculator.incomeCalc(LocalDate.of(2017, Month.MARCH, 24), new BigDecimal(1000));

        Assert.assertEquals(new BigDecimal("-0.14"), result);

    }
}
