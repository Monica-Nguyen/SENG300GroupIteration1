package Iteration1Tests;

import java.util.Currency;
import java.math.BigDecimal;

import org.junit.Test;
import org.lsmr.selfcheckout.Banknote;
import org.lsmr.selfcheckout.devices.SelfCheckoutStation;
import org.lsmr.selfcheckout.devices.SimulationException;
import org.lsmr.selfcheckoutsoftware.BanknotePayment;
import org.lsmr.selfcheckoutsoftware.BanknotePaymentSoftware;

import static org.junit.Assert.*;

public class BanknotePaymentTest {
//    Initialize SelfCheckoutSystem
    Currency canadianDollars = Currency.getInstance("CAD");
    BigDecimal nickel = new BigDecimal(0.05);
    BigDecimal dime = new BigDecimal(.1);
    BigDecimal quarter = new BigDecimal(.25);
    BigDecimal loonie = new BigDecimal(1.00);
    BigDecimal toonie = new BigDecimal(2.00);
    BigDecimal[] coinDenominations = {nickel, dime, quarter, loonie, toonie};
    int[] bankNoteDenominations = {5, 10, 20, 50, 100};
    int scaleMaximumWeight = 100;
    int scaleSensitivity = 1;

    SelfCheckoutStation selfCheckoutStation = new SelfCheckoutStation(canadianDollars, bankNoteDenominations,coinDenominations, scaleMaximumWeight, scaleSensitivity);
    BanknotePaymentSoftware software = new BanknotePaymentSoftware();

    //Test with a normal banknote
    @Test
    public void testBankNote() {

        Banknote bn = new Banknote(10, canadianDollars);
        BanknotePayment t1 = new BanknotePayment(selfCheckoutStation, software);

        t1.pay(bn, 10);

    }
    //Test with a null banknote
    @Test
    public void testNullNote()
    {
        Banknote bn = null;
        BanknotePayment t1 = new BanknotePayment(selfCheckoutStation, software);

        try {
            t1.pay(bn, 10);
        }catch(SimulationException e)
        {
            assertTrue(true);
        }
    }

    //Test with a zero valued banknote
    @Test
    public void testZeroValue()
    {
        BanknotePayment t1 = new BanknotePayment(selfCheckoutStation, software);
        try {
            Banknote bn = new Banknote(0, canadianDollars);
            t1.pay(bn, 10);
        }catch(SimulationException e)
        {
            assertTrue(true);
        }
    }

    //Test total paid
    @Test
    public void testTotalPaid()
    {
        SelfCheckoutStation selfCheckoutStation1 = new SelfCheckoutStation(canadianDollars, bankNoteDenominations,coinDenominations, scaleMaximumWeight, scaleSensitivity);
        BanknotePayment t1 = new BanknotePayment(selfCheckoutStation1, software);
        Banknote bn = new Banknote(10, canadianDollars);

        t1.pay(bn, 10);
        assertEquals(10, t1.getTotalPaid());
    }

    //Test whether there are remainders
    @Test
    public void testHasRemainder()
    {
        SelfCheckoutStation selfCheckoutStation1 = new SelfCheckoutStation(canadianDollars, bankNoteDenominations,coinDenominations, scaleMaximumWeight, scaleSensitivity);
        BanknotePayment t1 = new BanknotePayment(selfCheckoutStation1, software);
        Banknote bn = new Banknote(10, canadianDollars);

        t1.pay(bn, 100);
        assertTrue(t1.getHasRemainder());

    }

    //Test whether there is a need to dispense change
    @Test
    public void testNeedToDispenseChange()
    {
        SelfCheckoutStation selfCheckoutStation1 = new SelfCheckoutStation(canadianDollars, bankNoteDenominations,coinDenominations, scaleMaximumWeight, scaleSensitivity);
        BanknotePayment t1 = new BanknotePayment(selfCheckoutStation1, software);
        Banknote bn = new Banknote(10, canadianDollars);
        t1.pay(bn, 1);

        assertTrue(t1.getNeedToDispenseChange());
    }

    //Test whether the banknote was successfully stored
    @Test
    public void testSuccessfulStorage()
    {
        SelfCheckoutStation selfCheckoutStation1 = new SelfCheckoutStation(canadianDollars, bankNoteDenominations,coinDenominations, scaleMaximumWeight, scaleSensitivity);
        BanknotePayment t1 = new BanknotePayment(selfCheckoutStation1, software);
        Banknote bn = new Banknote(10, canadianDollars);
        t1.pay(bn, 1);

        assertTrue(t1.getSuccessfulStorage());
    }






}
