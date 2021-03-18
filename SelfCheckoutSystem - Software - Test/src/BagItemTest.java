
import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.lsmr.selfcheckout.Item;
import org.lsmr.selfcheckout.devices.OverloadException;
import org.lsmr.selfcheckout.devices.SelfCheckoutStation;
import org.lsmr.selfcheckout.devices.SimulationException;
import org.lsmr.selfcheckoutsoftware.BagItem;
import org.lsmr.selfcheckoutsoftware.BanknotePayment;
import org.lsmr.selfcheckoutsoftware.ScanItem;

import java.math.BigDecimal;
import java.util.Currency;

public class BagItemTest {

    @Before
    public void setUp() throws Exception {
    }

    class Items extends Item
    {
        /**
         * Constructs an item with the indicated weight.
         *
         * @param weightInGrams The weight of the item.
         * @throws SimulationException If the weight is &le;0.
         */
        protected Items(double weightInGrams) {
            super(weightInGrams);
        }
    }

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


    //BagItem b = new BagItem(station);

    /*public BagItemTest()
    {
        SelfCheckoutStation selfCheckoutStation = new SelfCheckoutStation(canadianDollars, bankNoteDenominations,coinDenominations, scaleMaximumWeight, scaleSensitivity);
        BagItem bagItem = new BagItem(selfCheckoutStation, electListen);
        Items x = null;
        bagItem.baggingItem(x);
    }*/

    @Test
    public void nullItem()
    {
        try
        {
            SelfCheckoutStation selfCheckoutStation = new SelfCheckoutStation(canadianDollars, bankNoteDenominations,coinDenominations, scaleMaximumWeight, scaleSensitivity);
            BagItem bagItem = new BagItem(selfCheckoutStation);
            Items x = null;
            bagItem.baggingItem(x);
        }
        catch (SimulationException | OverloadException e)
        {
            return;
        }
        fail("SimulationException expected");
    }

    @Test
    public void testBaggingItem() throws OverloadException {
        SelfCheckoutStation selfCheckoutStation = new SelfCheckoutStation(canadianDollars, bankNoteDenominations,coinDenominations, scaleMaximumWeight, scaleSensitivity);
        BagItem bagItem = new BagItem(selfCheckoutStation);
        Item item = new Items(2.34);
        double itemWeight = item.getWeight();
        bagItem.baggingItem(item);
        assertTrue(bagItem.getCheckWeight() == itemWeight);
    }

    @Test
    public void testHeavyBaggingItem() {
        try {
            SelfCheckoutStation selfCheckoutStation = new SelfCheckoutStation(canadianDollars, bankNoteDenominations, coinDenominations, scaleMaximumWeight, scaleSensitivity);
            BagItem bagItem = new BagItem(selfCheckoutStation);
            Item item = new Items(1234.56);
            bagItem.baggingItem(item);
        }
        catch(OverloadException e)
        {
            return;
        }
        fail("OverloadException expected");
    }



    @After
    public void tearDown() throws Exception {
    }

}
