

import org.lsmr.selfcheckout.Barcode;
import org.lsmr.selfcheckout.BarcodedItem;
import org.lsmr.selfcheckout.devices.SelfCheckoutStation;
import org.lsmr.selfcheckout.devices.SimulationException;
import java.util.ArrayList;
import java.util.List;


public class ScanItem {
    /* If we have states:

    int checkoutState = 1;
    ... continuously change to 2,3...  for proceeding with subsequent use cases

    */

    // We will make many different selfCheckoutStations?
    private SelfCheckoutStation station;
    private BarcodeScannerListenerSoftware BarcodeScannerListener;
    private Boolean flag = true;
    private Barcode barcode;
    private int scannerVariable;
    List<Barcode> scannedItems = new ArrayList<>();


    // Scan item
    public ScanItem(SelfCheckoutStation s, BarcodeScannerListenerSoftware bi) {
        this.station = s;
        this.BarcodeScannerListener = bi;

        // There can be either a main scanner used or a handheld scanner used
        // This flag usage is wrong.. bare with me haha

        try {
            station.mainScanner.register(BarcodeScannerListener);
            station.handheldScanner.register(BarcodeScannerListener);
        } catch(Exception e) {
            flag = false;
            System.out.println("A scanner could not be registered.\n");
        }
    }

    public void scanningItem(BarcodedItem barcodedItem, Boolean flag){
        if (barcodedItem == null){
            throw new SimulationException(new NullPointerException("barcode is null"));
        }

        if(flag){ //&& state = 1){ // First we want to try scanning the item with the main scanner
            station.mainScanner.scan(barcodedItem);
            barcode = barcodedItem.getBarcode();
            scannedItems.add(barcode);

        }

        else{ // If not we can try the handheld scanner present in many checkouts
            station.handheldScanner.scan(barcodedItem);
        }
    }

    // I guess after a successful scan is where BagItem calls ScanItem?
    // BagItem.




}
