import org.lsmr.selfcheckout.Coin;
import org.lsmr.selfcheckout.devices.AbstractDevice;
import org.lsmr.selfcheckout.devices.CoinSlot;
import org.lsmr.selfcheckout.devices.listeners.AbstractDeviceListener;
import org.lsmr.selfcheckout.devices.listeners.CoinSlotListener;

public class CoinSlotListenerSoftware implements CoinSlotListener {


    private boolean insertCoin;
    private Coin coin;

    public CoinSlotListenerSoftware(){
        this.insertCoin= false;
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

    }
}
