import org.lsmr.selfcheckout.devices.AbstractDevice;
import org.lsmr.selfcheckout.devices.BanknoteValidator;
import org.lsmr.selfcheckout.devices.listeners.AbstractDeviceListener;
import org.lsmr.selfcheckout.devices.listeners.BanknoteValidatorListener;

import java.util.Currency;

public class BanknoteValidatorListenerSoftware implements BanknoteValidatorListener {
    @Override
    public void enabled(AbstractDevice<? extends AbstractDeviceListener> device) {

    }

    @Override
    public void disabled(AbstractDevice<? extends AbstractDeviceListener> device) {

    }

    @Override
    public void validBanknoteDetected(BanknoteValidator validator, Currency currency, int value) {
        System.out.println("Valid banknote detected - currency: " + currency + "   value: "+ value);

    }

    @Override
    public void invalidBanknoteDetected(BanknoteValidator validator) {
        System.out.println("Invalid banknote detected");
    }
}
