import org.lsmr.selfcheckout.Coin;
import org.lsmr.selfcheckout.devices.AbstractDevice;
import org.lsmr.selfcheckout.devices.CoinSlot;
import org.lsmr.selfcheckout.devices.CoinStorageUnit;
import org.lsmr.selfcheckout.devices.CoinValidator;
import org.lsmr.selfcheckout.devices.listeners.AbstractDeviceListener;
import org.lsmr.selfcheckout.devices.listeners.CoinSlotListener;
import org.lsmr.selfcheckout.devices.listeners.CoinStorageUnitListener;
import org.lsmr.selfcheckout.devices.listeners.CoinValidatorListener;

import java.math.BigDecimal;

public class CoinListenerSoftware implements CoinSlotListener, CoinValidatorListener, CoinStorageUnitListener {

    private boolean insertCoin;
    private Coin coin;
    private boolean validCoin;

    public CoinListenerSoftware(){

        this.insertCoin= false;
        this.validCoin = false;

    }

    @Override
    public void enabled(AbstractDevice<? extends AbstractDeviceListener> device) {

    }

    @Override
    public void disabled(AbstractDevice<? extends AbstractDeviceListener> device) {

    }

    @Override
    public void coinInserted(CoinSlot slot) {
        insertCoin = true;
        System.out.println("Coin has been inserted");
    }

    @Override
    public void coinsFull(CoinStorageUnit unit) {
        if(unit.getCoinCount()>1000){
            System.out.println("Coin storage is full");
        }
    }

    @Override
    public void coinAdded(CoinStorageUnit unit) {

        System.out.println("Coin has been added");
    }

    @Override
    public void coinsLoaded(CoinStorageUnit unit) {

    }

    @Override
    public void coinsUnloaded(CoinStorageUnit unit) {

    }

    @Override
    public void validCoinDetected(CoinValidator validator, BigDecimal value) {
            validCoin = true;
         System.out.println("Coin is valid");


    }

    @Override
    public void invalidCoinDetected(CoinValidator validator) {

        System.out.println("Coin is invalid");

    }
}
