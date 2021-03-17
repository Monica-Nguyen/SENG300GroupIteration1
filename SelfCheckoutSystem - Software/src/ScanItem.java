

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
    private Boolean flag = false;
    private Barcode barcode;
    private int scanDevice;
    List<Barcode> scannedItems = new ArrayList<>();


    // Scan item
    public ScanItem(SelfCheckoutStation s, BarcodeScannerListenerSoftware bi) {
        this.station = s;
        this.BarcodeScannerListener = bi;

        // There can be either a main scanner used or a handheld scanner used
        try{
            station.mainScanner.register(BarcodeScannerListener);
            scanDevice = 1;
        }
        catch(Exception e){
            System.out.println("Main scanner could not be registered");
            station.handheldScanner.register(BarcodeScannerListener);
            scanDevice = 2;
        }


    }

    public void scanningItem(BarcodedItem barcodedItem){
        if (barcodedItem == null){
            throw new SimulationException(new NullPointerException("barcode is null"));
        }

        if(scanDevice == 1){ //&& state = 1){ // First we want to try scanning the item with the main scanner
            station.mainScanner.scan(barcodedItem);
            barcode = barcodedItem.getBarcode();
            scannedItems.add(barcode);
            flag = true;

        }

        else if (scanDevice == 2){ // If not we can try the handheld scanner present in many checkouts
            station.handheldScanner.scan(barcodedItem);
            barcode = barcodedItem.getBarcode();
            scannedItems.add(barcode);
            flag = true;
        }
    }

    /*
    if (flag){
        // I guess after a successful scan is where BagItem calls ScanItem when the flag == true?
    }

    */




}
