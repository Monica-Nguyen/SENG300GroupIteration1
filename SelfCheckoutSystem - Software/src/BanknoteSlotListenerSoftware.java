import org.lsmr.selfcheckout.devices.AbstractDevice;
import org.lsmr.selfcheckout.devices.BanknoteSlot;
import org.lsmr.selfcheckout.devices.listeners.AbstractDeviceListener;
import org.lsmr.selfcheckout.devices.listeners.BanknoteSlotListener;

public class BanknoteSlotListenerSoftware implements BanknoteSlotListener {


    @Override
    public void banknoteInserted(BanknoteSlot slot) {

    }

    @Override
    public void banknoteEjected(BanknoteSlot slot) {

    }

    @Override
    public void banknoteRemoved(BanknoteSlot slot) {

    }

    @Override
    public void enabled(AbstractDevice<? extends AbstractDeviceListener> device) {

    }

    @Override
    public void disabled(AbstractDevice<? extends AbstractDeviceListener> device) {

    }
}
