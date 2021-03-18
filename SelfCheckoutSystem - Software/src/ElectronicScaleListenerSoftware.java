import org.lsmr.selfcheckout.devices.AbstractDevice;
import org.lsmr.selfcheckout.devices.ElectronicScale;
import org.lsmr.selfcheckout.devices.listeners.AbstractDeviceListener;
import org.lsmr.selfcheckout.devices.listeners.ElectronicScaleListener;

public class ElectronicScaleListenerSoftware implements ElectronicScaleListener {

    private double prevWeight;

    public ElectronicScaleListenerSoftware() {
        this.prevWeight = 0;
    }

    public double getPrevWeight()
    {
        return this.prevWeight;
    }

    @Override
    public void enabled(AbstractDevice<? extends AbstractDeviceListener> device) {

    }

    @Override
    public void disabled(AbstractDevice<? extends AbstractDeviceListener> device) {

    }

    @Override
    public void weightChanged(ElectronicScale scale, double weightInGrams) {
        double currentWeight = weightInGrams - prevWeight;

        System.out.println("The weight of this item is: " + currentWeight + " grams");

        prevWeight = weightInGrams;

    }

    @Override
    public void overload(ElectronicScale scale) {
        System.out.println("Object is too heavy. Remove it.");

    }

    @Override
    public void outOfOverload(ElectronicScale scale) {
        System.out.println("Excessive weight is gone, can weigh items again.");

    }
}