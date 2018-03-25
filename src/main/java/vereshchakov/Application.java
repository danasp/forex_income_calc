package vereshchakov;

import vereshchakov.bl.IncomeCalculator;
import vereshchakov.client.ForexRateClient;
import vereshchakov.client.OerForexRateClient;
import vereshchakov.dao.ForexRateDao;
import vereshchakov.dao.ForexRateDaoImpl;
import vereshchakov.gui.ForexIncomeCalc;

import javax.swing.*;

/**
 * Created by Danila Vereshchakov on 24.03.18.
 */
public class Application {
    public static void main(String[] args) {

        ForexRateClient forexRateClient = new OerForexRateClient();
        ForexRateDao forexRateDao = new ForexRateDaoImpl(forexRateClient);
        IncomeCalculator calculator = new IncomeCalculator(forexRateDao);

        SwingUtilities.invokeLater(new Runnable(){
            @Override
            public void run() {
                JFrame frame = new JFrame("Forex Income Calculator");
                frame.setContentPane(new ForexIncomeCalc(calculator).getMainPanel());
                frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
                frame.pack();
                frame.setVisible(true);

            }
        });
    }
}
