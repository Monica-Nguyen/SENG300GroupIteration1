import org.junit.Test;
import org.lsmr.selfcheckout.Barcode;
import org.lsmr.selfcheckout.BarcodedItem;
import org.lsmr.selfcheckout.devices.SelfCheckoutStation;
import org.lsmr.selfcheckoutsoftware.BanknotePaymentSoftware;
import org.lsmr.selfcheckoutsoftware.BarcodeScannerListenerSoftware;
import org.lsmr.selfcheckoutsoftware.CoinPaymentSoftware;
import org.lsmr.selfcheckoutsoftware.ScanItem;
import org.lsmr.selfcheckout.devices.SimulationException;

import java.math.BigDecimal;
import java.util.Currency;

import static org.junit.Assert.*;

public class ScanItemTest {

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
    BarcodeScannerListenerSoftware BarcodeScannerListener = new BarcodeScannerListenerSoftware();
    ScanItem scannedItem = new ScanItem(selfCheckoutStation, BarcodeScannerListener);
    BarcodedItem barcodedItem = new BarcodedItem(null, 0.0);

    // I need to leave the creation of the barcodedItem outside of this test because there is an exception thrown within BarcodedItem when I only want to test the ScanItem class
    @Test(expected = SimulationException.class)
    public void testScanItemNullBarcode() {
        scannedItem.scanningItem(barcodedItem, true);

    }

}
