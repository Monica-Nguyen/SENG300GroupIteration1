
import org.lsmr.selfcheckout.Item;
import org.lsmr.selfcheckout.devices.ElectronicScale;
import org.lsmr.selfcheckout.Barcode;
import org.lsmr.selfcheckout.BarcodedItem;

import org.lsmr.selfcheckout.devices.SelfCheckoutStation;
import org.lsmr.selfcheckout.devices.listeners.BanknoteSlotListener;
import org.lsmr.selfcheckout.devices.listeners.BanknoteValidatorListener;
import org.lsmr.selfcheckout.devices.listeners.BanknoteStorageUnitListener;
import org.lsmr.selfcheckout.devices.listeners.CoinStorageUnitListener;
import org.lsmr.selfcheckout.devices.listeners.CoinSlotListener;
import org.lsmr.selfcheckout.devices.listeners.CoinTrayListener;
import org.lsmr.selfcheckout.devices.listeners.CoinValidatorListener;
import org.lsmr.selfcheckout.devices.SimulationException;

public class Main {

    private SelfCheckoutStation station;
    private ElectronicScaleListenerSoftware ElectronicScaleListener;
    private BarcodeScannerListenerSoftware BarcodeScannerListener;

    /*
    private BanknoteSlotListenerSoftware BanknoteSlotListener;
    private CoinSlotListenerSoftware CoinSlotListener;
    private CoinStorageUnitListenerSoftware CoinStorageUnitListener;
    private CoinTrayListenerSoftware CoinTrayListener;
    private CoinValidatorListenerSoftware CoinValidatorListener;
    private BanknoteSlotListenerSoftware BanknoteSlotListener;
    private BanknoteStorageUnitListenerSoftware BanknoteStorageUnitListener;
    private BanknoteValidatorListenerSoftware BanknoteValidatorListener;
    */

    //Am I correct to think that these have to be made too?


    public Main(SelfCheckoutStation s, ElectronicScaleListenerSoftware e, BarcodeScannerListenerSoftware bsl){
        // CoinSlotListenerSoftware csl, CoinStorageUnitListenerSoftware csul, CoinTrayListenerSoftware ctl, CoinValidatorListenerSoftware cvl,
        // BanknoteSlotListenerSoftware bsl, BanknoteStorageUnitListenerSoftware bsul, BanknoteValidatorListenerSoftware bvl){
        this.station = s;
        this.BarcodeScannerListener = bsl;
        this.ElectronicScaleListener = e;

        /*
        this.CoinSlotListener = csl;
        this.CoinStorageUnitListener = csul;
        this.CoinTrayListener = ctl;
        this.CoinValidatorListener = cvl;
        this.BanknoteSlotListener = bnsl;
        this.BanknoteStorageUnitListener = bnsul;
        this.BanknoteValidatorListener = bnvl;
        */

        station.mainScanner.register(BarcodeScannerListener);
        station.handheldScanner.register(BarcodeScannerListener);
        station.baggingArea.register(ElectronicScaleListener);

        /*
        station.banknoteInput.register(BanknoteSlotListener);
        station.banknoteValidator.register(BanknoteValidatorListener);
        station.banknoteStorage.register(BanknoteStorageUnitListener);
        station.coinSlot.register(CoinSlotListener);
        station.coinValidator.register(CoinValidatorListener);
        station.coinStorage.register(CoinStorageUnitListener);
        station.coinTray.register(CoinTrayListener);

         */

    }

}
