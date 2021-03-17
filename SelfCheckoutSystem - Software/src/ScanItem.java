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

    // Will we make many different selfCheckoutStations?
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
        station.mainScanner.register(BarcodeScannerListener);
        station.handheldScanner.register(BarcodeScannerListener);
    }

    public void scanningItem(BarcodedItem barcodedItem, Boolean mainScanner){
        // if (state == 1){}?

        if (barcodedItem == null){
            throw new SimulationException(new NullPointerException("barcode is null"));
        }

        if(mainScanner){
            station.mainScanner.scan(barcodedItem);
        }

        else{
            station.handheldScanner.scan(barcodedItem);
        }

        if(BarcodeScannerListener.getScanned()) {
            barcode = barcodedItem.getBarcode();
            scannedItems.add(barcode);
            System.out.println("Barcode was added to a list.");
        }

        else{
            System.out.println("Barcode was not added to a list.");
        }

    }

}
