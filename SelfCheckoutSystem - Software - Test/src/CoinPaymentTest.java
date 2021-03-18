import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.lsmr.selfcheckout.Coin;
import org.lsmr.selfcheckout.Item;
import org.lsmr.selfcheckout.devices.DisabledException;
import org.lsmr.selfcheckout.devices.OverloadException;
import org.lsmr.selfcheckout.devices.SelfCheckoutStation;
import org.lsmr.selfcheckout.devices.SimulationException;
import org.lsmr.selfcheckoutsoftware.BagItem;
import org.lsmr.selfcheckoutsoftware.CoinPayment;

import java.math.BigDecimal;
import java.math.MathContext;
import java.util.Currency;

public class CoinPaymentTest {

    SelfCheckoutStation station;
    Currency canadianDollars = Currency.getInstance("CAD");
    BigDecimal nickel = new BigDecimal(0.05);
    BigDecimal dime = new BigDecimal(.1);
    BigDecimal quarter = new BigDecimal(.25);
    BigDecimal loonie = new BigDecimal(1.00);
    BigDecimal toonie = new BigDecimal(2.00);
    BigDecimal[] coinDenominations = {nickel, dime, quarter, loonie, toonie};
    int[] bankNoteDenominations = {5, 10, 20, 50, 100};
    int scaleMaximumWeight = 100; // Assuming kilograms
    int scaleSensitivity = 1; // In grams
    Coin coin;

    @Before
    public void setUp() throws Exception {
    }

    @Test
    public void testBadCoin()
    {
        try
        {
            SelfCheckoutStation selfCheckoutStation = new SelfCheckoutStation(canadianDollars, bankNoteDenominations, coinDenominations, scaleMaximumWeight, scaleSensitivity);
            CoinPayment coinPayment = new CoinPayment(selfCheckoutStation);
            coin = new Coin(BigDecimal.ZERO, canadianDollars);
            coinPayment.pay(coin);
        }
        catch (SimulationException | DisabledException e)
        {
            return;
        }
        fail("Simulation Exception expected");
    }

    @Test
    public void testCoinPayment() throws DisabledException {
        SelfCheckoutStation selfCheckoutStation = new SelfCheckoutStation(canadianDollars, bankNoteDenominations, coinDenominations, scaleMaximumWeight, scaleSensitivity);
        CoinPayment coinPayment = new CoinPayment(selfCheckoutStation);
        coin = new Coin(nickel, canadianDollars);
        coinPayment.pay(coin);
        MathContext m = new MathContext(1); // 4 precision
        assertEquals(coinPayment.getTotalPaid().round(m), nickel.round(m));
    }

    @After
    public void tearDown() throws Exception {
    }
}
