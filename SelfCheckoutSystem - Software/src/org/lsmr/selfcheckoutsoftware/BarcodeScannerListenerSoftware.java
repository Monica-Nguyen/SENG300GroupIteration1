package org.lsmr.selfcheckoutsoftware;

import org.lsmr.selfcheckout.Barcode;
import org.lsmr.selfcheckout.Item;
import org.lsmr.selfcheckout.devices.AbstractDevice;
import org.lsmr.selfcheckout.devices.BarcodeScanner;
import org.lsmr.selfcheckout.devices.listeners.AbstractDeviceListener;
import org.lsmr.selfcheckout.devices.listeners.BarcodeScannerListener;

public class BarcodeScannerListenerSoftware implements BarcodeScannerListener {

    private boolean scanned;
    private Item item;

    public BarcodeScannerListenerSoftware() {
        this.scanned = false;
    }

    public boolean getScanned() {
        return scanned;
    }

    public void setScanned(boolean scanned) {
        this.scanned = scanned;
    }


    @Override
    public void enabled(AbstractDevice<? extends AbstractDeviceListener> device) {

    }

    @Override
    public void disabled(AbstractDevice<? extends AbstractDeviceListener> device) {

    }

    @Override
    public void barcodeScanned(BarcodeScanner barcodeScanner, Barcode barcode) {
        setScanned(true);
        System.out.println("Item has been scanned, place it in bagging area.");
    }
}
