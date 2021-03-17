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
    private Barcode barcode;
    private int scanDevice;
    List<Barcode> scannedItems = new ArrayList<>();
    boolean scanFlag = false;

    // Scan item
    public ScanItem(SelfCheckoutStation s, BarcodeScannerListenerSoftware bsl){
        this.station = s;
        this.BarcodeScannerListener = bsl;

        try{
            station.mainScanner.register(BarcodeScannerListener);
            scanDevice = 1;
        }
        // If we cannot register the main scanner, we should try the handheld next
        catch(Exception e){
            System.out.println("Main scanner could not be registered");
            station.handheldScanner.register(BarcodeScannerListener);
            scanDevice = 2;
        }
    }

    public void scanningItem(BarcodedItem barcodedItem){
        // if (state == 1){}?

        if (barcodedItem == null){
            throw new SimulationException(new NullPointerException("barcode is null"));
        }

        if(scanDevice == 1){
            station.mainScanner.scan(barcodedItem);
            barcode = barcodedItem.getBarcode();
            scannedItems.add(barcode);
            scanFlag = true;
        }

        else if (scanDevice == 2){
            station.handheldScanner.scan(barcodedItem);
            barcode = barcodedItem.getBarcode();
            scannedItems.add(barcode);
            scanFlag = true;
        }

        // After a successful scan is where BagItem calls ScanItem when the flag == true?
        if (scanFlag){
            System.out.println("Barcode was added to a list.");
        }
        else{
            System.out.println("Barcode was not added to a list.");
        }

    }

}
