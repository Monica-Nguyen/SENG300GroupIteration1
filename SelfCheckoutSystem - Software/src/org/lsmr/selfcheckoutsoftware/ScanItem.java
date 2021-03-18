package org.lsmr.selfcheckoutsoftware;

import org.lsmr.selfcheckout.Barcode;
import org.lsmr.selfcheckout.BarcodedItem;
import org.lsmr.selfcheckout.Item;
import org.lsmr.selfcheckout.devices.AbstractDevice;
import org.lsmr.selfcheckout.devices.BarcodeScanner;
import org.lsmr.selfcheckout.devices.SelfCheckoutStation;
import org.lsmr.selfcheckout.devices.SimulationException;
import org.lsmr.selfcheckout.devices.listeners.AbstractDeviceListener;
import org.lsmr.selfcheckout.devices.listeners.BarcodeScannerListener;
import org.lsmr.selfcheckout.devices.listeners.ElectronicScaleListener;

import java.util.ArrayList;
import java.util.List;

public class ScanItem {
    /* If we have states:

    int checkoutState = 1;
    ... continuously change to 2,3...  for proceeding with subsequent use cases

    */

    // Will we make many different selfCheckoutStations?
    private SelfCheckoutStation station;
    private Barcode barcode;
    private int scanDevice;
    List<BarcodedItem> scannedItemsCart = new ArrayList<>();

    public List<BarcodedItem> getScannedItemsCart()
    {
        return scannedItemsCart;
    }

    boolean scanFlag = false;

    private boolean scanned = false;

    // Scan item
    public ScanItem(SelfCheckoutStation s){
        this.station = s;
        station.mainScanner.register(new BarcodeScannerListener() {
            //private boolean scanned = false;
            //private Item item;

            /*public boolean getScanned() {
                return scanned;
            }

            public void setScanned(boolean scanned) {
                this.scanned = scanned;
            }*/


            @Override
            public void enabled(AbstractDevice<? extends AbstractDeviceListener> device) {

            }

            @Override
            public void disabled(AbstractDevice<? extends AbstractDeviceListener> device) {

            }

            @Override
            public void barcodeScanned(BarcodeScanner barcodeScanner, Barcode barcode) {
                scanned = true;
                System.out.println("Item has been scanned, place it in bagging area.");
            }
        });
        //station.handheldScanner.register(BarcodeScannerListener);
    }

    public void scanningItem(BarcodedItem barcodedItem){
        // if (state == 1){}?

        if (barcodedItem.getBarcode() == null){
            throw new SimulationException(new NullPointerException("Barcode is null.\n"));
        }

        /*if(mainScanner){
            station.mainScanner.scan(barcodedItem);
        }*/

        /*else{
            station.handheldScanner.scan(barcodedItem);
        }*/

        else {
            station.mainScanner.scan(barcodedItem);
        }
        if(scanned) {
            // Map<Product, Integer> thing = new HashMap<>();
            // make a key with the barcode
            // that key corresponds to an item from the productDatabases
            scannedItemsCart.add(barcodedItem);
            System.out.println("Barcoded item was added to a list.\n");
            scanned = false;
        }

        else{
            System.out.println("Barcode could not be scanned.\n");
        }

        // state = 2;

    }

}
