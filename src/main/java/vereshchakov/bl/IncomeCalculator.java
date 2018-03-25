package vereshchakov.bl;

import vereshchakov.dao.ForexRateDao;
import vereshchakov.entity.ForexRate;
import vereshchakov.exception.BusinessLogicException;

import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * Created by Danila Vereshchakov on 24.03.18.
 */
public class IncomeCalculator {

    private static final BigDecimal SPREAD = new BigDecimal(0.005);

    private ForexRateDao forexRateDao;

    public IncomeCalculator(ForexRateDao forexRateDao) {
        this.forexRateDao = forexRateDao;
    }

    public BigDecimal incomeCalc(LocalDate histDate, BigDecimal usdAmount) {
        ForexRate currentRate = forexRateDao.getUsdLatestForexRate();
        ForexRate historicalRate = forexRateDao.getUsdHistoricalForexRate(histDate);

        if (currentRate.getBase() != historicalRate.getBase()) {
            throw new BusinessLogicException("Only the same currency can be compared");
        }

        BigDecimal histQuotedRate = historicalRate.getQuoteRates().get(Currency.RUB);

        //usdAmount * rate + (usdAmount * rate * spread)
        BigDecimal spreadValue = usdAmount.multiply(histQuotedRate).multiply(SPREAD);
        BigDecimal ask = usdAmount.multiply(histQuotedRate).add(spreadValue);

        BigDecimal bid = usdAmount.multiply(currentRate.getQuoteRates().get(Currency.RUB));

        return bid.subtract(ask).setScale(2, BigDecimal.ROUND_HALF_EVEN);

    }
}
