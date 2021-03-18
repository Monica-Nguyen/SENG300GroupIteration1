import static org.junit.jupiter.api.Assertions.*;
import java.util.Currency;
import java.math.BigDecimal;

import org.junit.jupiter.api.Test;
import org.lsmr.selfcheckout.Banknote;
import org.lsmr.selfcheckout.devices.SelfCheckoutStation;
import org.lsmr.selfcheckout.devices.SimulationException;

class BanknotePaymentTest {

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

    SelfCheckoutStation selfCheckoutStation = new SelfCheckoutStation(canadianDollars, bankNoteDenominations,coinDenominations, scaleMaximumWeight, scaleSensitivity);
    BanknotePaymentSoftware software = new BanknotePaymentSoftware();
    @Test
    void testBankNote() {

        Banknote bn = new Banknote(10, canadianDollars);
        BanknotePayment t1 = new BanknotePayment(selfCheckoutStation, software);

        t1.pay(bn, 10);

    }

    @Test
    void testNullNote()
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

    @Test
    void testZeroValue()
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

    @Test
    void testTotalPaid()
    {
        SelfCheckoutStation selfCheckoutStation1 = new SelfCheckoutStation(canadianDollars, bankNoteDenominations,coinDenominations, scaleMaximumWeight, scaleSensitivity);
        BanknotePayment t1 = new BanknotePayment(selfCheckoutStation1, software);
        Banknote bn = new Banknote(10, canadianDollars);

        t1.pay(bn, 10);
        assertEquals(10, t1.getTotalPaid());
    }

    @Test
    void testHasRemainder()
    {
        SelfCheckoutStation selfCheckoutStation1 = new SelfCheckoutStation(canadianDollars, bankNoteDenominations,coinDenominations, scaleMaximumWeight, scaleSensitivity);
        BanknotePayment t1 = new BanknotePayment(selfCheckoutStation1, software);
        Banknote bn = new Banknote(10, canadianDollars);

        t1.pay(bn, 100);
        assertEquals(true, t1.getHasRemainder());

    }

    @Test
    void testNeedToDispenseChange()
    {
        SelfCheckoutStation selfCheckoutStation1 = new SelfCheckoutStation(canadianDollars, bankNoteDenominations,coinDenominations, scaleMaximumWeight, scaleSensitivity);
        BanknotePayment t1 = new BanknotePayment(selfCheckoutStation1, software);
        Banknote bn = new Banknote(10, canadianDollars);
        t1.pay(bn, 1);

        assertEquals(true, t1.getNeedToDispenseChange());
    }

    @Test
    void testSuccessfulStorage()
    {
        SelfCheckoutStation selfCheckoutStation1 = new SelfCheckoutStation(canadianDollars, bankNoteDenominations,coinDenominations, scaleMaximumWeight, scaleSensitivity);
        BanknotePayment t1 = new BanknotePayment(selfCheckoutStation1, software);
        Banknote bn = new Banknote(10, canadianDollars);
        t1.pay(bn, 1);

        assertEquals(true, t1.getSuccessfulStorage());
    }



//    @Test
//    void




}
