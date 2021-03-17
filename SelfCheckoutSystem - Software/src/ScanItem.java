import org.lsmr.selfcheckout.BarcodedItem;
import org.lsmr.selfcheckout.devices.SelfCheckoutStation;
import org.lsmr.selfcheckout.devices.SimulationException;
import org.lsmr.selfcheckout.products.Product;

import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;
import java.util.Map;

public class ScanItem {
    /* If we have states:

    int checkoutState = 1;
    ... continuously change to 2,3...  for proceeding with subsequent use cases

    */

    // Will we make many different selfCheckoutStations?
    private SelfCheckoutStation station;
    private BarcodeScannerListenerSoftware BarcodeScannerListener;
    List<BarcodedItem> scannedItemsCart = new ArrayList<>();

    boolean scanFlag = false;

    // Scan item
    public ScanItem(SelfCheckoutStation s, BarcodeScannerListenerSoftware bsl){
        this.station = s;
        this.BarcodeScannerListener = bsl;
        station.mainScanner.register(BarcodeScannerListener);
        station.handheldScanner.register(BarcodeScannerListener);
    }

    public void scanningItem(BarcodedItem barcodedItem, Boolean mainScanner){
        // if (state == 1){}?

        if (barcodedItem.getBarcode() == null){
            throw new SimulationException(new NullPointerException("Barcode is null.\n"));
        }

        if(mainScanner){
            station.mainScanner.scan(barcodedItem);
        }

        else{
            station.handheldScanner.scan(barcodedItem);
        }

        if(BarcodeScannerListener.getScanned()) {
            // Map<Product, Integer> thing = new HashMap<>();
            // make a key with the barcode
            // that key corresponds to an item from the productDatabases
            scannedItemsCart.add(barcodedItem);
            System.out.println("Barcode was added to a list.\n");
            BarcodeScannerListener.setScanned(false);
        }

        else{
            System.out.println("Barcode could not be scanned.\n");
        }

        // state = 2;

    }

}
