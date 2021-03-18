package Iteration1Tests;

import org.junit.Test;
import org.lsmr.selfcheckout.Barcode;
import org.lsmr.selfcheckout.BarcodedItem;
import org.lsmr.selfcheckout.devices.SelfCheckoutStation;
import org.lsmr.selfcheckoutsoftware.ScanItem;
import org.lsmr.selfcheckout.devices.SimulationException;

import java.math.BigDecimal;
import java.util.Currency;
import java.util.List;

import static org.junit.Assert.*;

public class ScanItemTest {


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

    Barcode code = new Barcode("42");
    BarcodedItem bci;

    @Test
    public void testNullBarcode() {
        try
        {
            SelfCheckoutStation selfCheckoutStation = new SelfCheckoutStation(canadianDollars, bankNoteDenominations, coinDenominations, scaleMaximumWeight, scaleSensitivity);
            ScanItem scanItem = new ScanItem(selfCheckoutStation);
            bci = new BarcodedItem(null, 2.34);
            scanItem.scanningItem(bci);
        }
        catch (SimulationException e)
        {
            return;
        }
        fail("SimulationException expected");
    }

    @Test
    public void testScannedBarcode()
    {
        SelfCheckoutStation selfCheckoutStation = new SelfCheckoutStation(canadianDollars, bankNoteDenominations, coinDenominations, scaleMaximumWeight, scaleSensitivity);
        ScanItem scanItem = new ScanItem(selfCheckoutStation);
        bci = new BarcodedItem(code, 2.34);
        scanItem.scanningItem(bci);
        List<BarcodedItem> list = scanItem.getScannedItemsCart();
        assertTrue(list.get(0) == bci);
    }




}
