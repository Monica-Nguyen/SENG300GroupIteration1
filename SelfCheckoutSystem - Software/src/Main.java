import org.lsmr.selfcheckout.BarcodedItem;
import org.lsmr.selfcheckout.devices.SelfCheckoutStation;
import java.math.BigDecimal;
import java.util.Currency;

public class Main {

    private ElectronicScaleListenerSoftware ElectronicScaleListener;
    private BarcodeScannerListenerSoftware BarcodeScannerListener;
    private BanknotePaymentSoftware BanknotePayment;
    private CoinPaymentSoftware CoinPayment;

    private Currency canadianDollars = Currency.getInstance("CAD");
    private BigDecimal nickel = new BigDecimal(0.05);
    private BigDecimal dime = new BigDecimal(.1);
    private BigDecimal quarter = new BigDecimal(.25);
    private BigDecimal loonie = new BigDecimal(1.00);
    private BigDecimal toonie = new BigDecimal(2.00);
    private BigDecimal[] coinDenominations = {nickel, dime, quarter, loonie, toonie};
    private int[] bankNoteDenominations = {5, 10, 20, 50, 100};
    private int scaleMaximumWeight = 100; // Assuming kilograms
    private int scaleSensitivity = 1; // In grams

    public Main() {
        SelfCheckoutStation selfCheckoutStation = new SelfCheckoutStation(canadianDollars, bankNoteDenominations, coinDenominations, scaleMaximumWeight, scaleSensitivity);
        ScanItem scanItem = new ScanItem(selfCheckoutStation, BarcodeScannerListener);
        BagItem bagItem = new BagItem(selfCheckoutStation, ElectronicScaleListener);
        BanknotePayment banknotePayment = new BanknotePayment(selfCheckoutStation, BanknotePayment);
        // coinPayment = new CoinPayment(selfCheckoutStation, CoinPayment);


        //  Pass in true and false from the tests for the boolean mainScanner
        /*
        public void scanningAndBagging(BarcodedItem bi, Boolean r){
            // get barcoded item to scan and then get list to bag items

        }

        public void coinPaymentCall(){

        }

        public void banknotePaymentCall(){

        }
        */

    }
}
