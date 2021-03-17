import org.lsmr.selfcheckout.devices.AbstractDevice;
import org.lsmr.selfcheckout.devices.CoinStorageUnit;
import org.lsmr.selfcheckout.devices.listeners.AbstractDeviceListener;
import org.lsmr.selfcheckout.devices.listeners.CoinStorageUnitListener;

public class CoinStorageUnitListenerSoftware implements CoinStorageUnitListener {
    @Override
    public void enabled(AbstractDevice<? extends AbstractDeviceListener> device) {

    }

    @Override
    public void disabled(AbstractDevice<? extends AbstractDeviceListener> device) {

    }

    @Override
    public void coinsFull(CoinStorageUnit unit) {

    }

    @Override
    public void coinAdded(CoinStorageUnit unit) {

    }

    @Override
    public void coinsLoaded(CoinStorageUnit unit) {

    }

    @Override
    public void coinsUnloaded(CoinStorageUnit unit) {

    }
}
