import org.lsmr.selfcheckout.devices.AbstractDevice;
import org.lsmr.selfcheckout.devices.BanknoteSlot;
import org.lsmr.selfcheckout.devices.listeners.AbstractDeviceListener;
import org.lsmr.selfcheckout.devices.listeners.BanknoteSlotListener;

public class BanknoteSlotListenerSoftware implements BanknoteSlotListener {


    @Override
    public void banknoteInserted(BanknoteSlot slot) {
        System.out.println("Banknote has been inserted");

    }

    @Override
    public void banknoteEjected(BanknoteSlot slot) {
        System.out.println("Banknote has been ejected");
    }

    @Override
    public void banknoteRemoved(BanknoteSlot slot) {
        System.out.println("Dangling banknote has been removed");
    }

    @Override
    public void enabled(AbstractDevice<? extends AbstractDeviceListener> device) {

    }

    @Override
    public void disabled(AbstractDevice<? extends AbstractDeviceListener> device) {

    }
}
