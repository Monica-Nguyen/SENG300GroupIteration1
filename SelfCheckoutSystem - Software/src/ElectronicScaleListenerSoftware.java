import org.lsmr.selfcheckout.devices.AbstractDevice;
import org.lsmr.selfcheckout.devices.ElectronicScale;
import org.lsmr.selfcheckout.devices.listeners.AbstractDeviceListener;
import org.lsmr.selfcheckout.devices.listeners.ElectronicScaleListener;

public class ElectronicScaleListenerSoftware implements ElectronicScaleListener {
    @Override
    public void enabled(AbstractDevice<? extends AbstractDeviceListener> device) {

    }

    @Override
    public void disabled(AbstractDevice<? extends AbstractDeviceListener> device) {

    }

    @Override
    public void weightChanged(ElectronicScale scale, double weightInGrams) {

    }

    @Override
    public void overload(ElectronicScale scale) {

    }

    @Override
    public void outOfOverload(ElectronicScale scale) {

    }
}
