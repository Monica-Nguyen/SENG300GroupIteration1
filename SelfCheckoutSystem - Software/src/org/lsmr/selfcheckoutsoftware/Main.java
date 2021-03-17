package org.lsmr.selfcheckoutsoftware;

import org.lsmr.selfcheckout.devices.SelfCheckoutStation;
import java.math.BigDecimal;
import java.util.Currency;

public class Main {

    private ElectronicScaleListenerSoftware ElectronicScaleListener;
    private BarcodeScannerListenerSoftware BarcodeScannerListener;
    private BanknotePaymentSoftware BanknotePayment;
    private CoinPaymentSoftware CoinPayment;

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

    public Main(){
        SelfCheckoutStation selfCheckoutStation = new SelfCheckoutStation(canadianDollars, bankNoteDenominations,coinDenominations, scaleMaximumWeight, scaleSensitivity);
        ScanItem scanItem = new ScanItem(selfCheckoutStation, BarcodeScannerListener);
        BagItem bagItem = new BagItem(selfCheckoutStation, ElectronicScaleListener);
        BanknotePayment banknotePayment = new BanknotePayment(selfCheckoutStation, BanknotePayment);
        //org.lsmr.selfcheckoutsoftware.CoinPayment coinPayment = new org.lsmr.selfcheckoutsoftware.CoinPayment(selfCheckoutStation, org.lsmr.selfcheckoutsoftware.CoinPayment);

    }

}
