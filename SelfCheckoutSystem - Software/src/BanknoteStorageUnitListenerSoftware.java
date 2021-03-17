import org.lsmr.selfcheckout.devices.AbstractDevice;
import org.lsmr.selfcheckout.devices.BanknoteStorageUnit;
import org.lsmr.selfcheckout.devices.listeners.AbstractDeviceListener;
import org.lsmr.selfcheckout.devices.listeners.BanknoteStorageUnitListener;

public class BanknoteStorageUnitListenerSoftware implements BanknoteStorageUnitListener {

    @Override
    public void enabled(AbstractDevice<? extends AbstractDeviceListener> device) {

    }

    @Override
    public void disabled(AbstractDevice<? extends AbstractDeviceListener> device) {

    }

    @Override
    public void banknotesFull(BanknoteStorageUnit unit) {
        if(unit.getCapacity() > 1000)
        {
            System.out.println("Banknote Storage Unit is full of banknotes (>1000)");
        }
    }

    @Override
    public void banknoteAdded(BanknoteStorageUnit unit) {
        System.out.println("Banknote added to Storage Unit");

    }

    @Override
    public void banknotesLoaded(BanknoteStorageUnit unit) {
        System.out.println("Banknote loaded to Storage Unit");

    }

    @Override
    public void banknotesUnloaded(BanknoteStorageUnit unit) {
        System.out.println("Banknote unloaded from Storage Unit");

    }
}
