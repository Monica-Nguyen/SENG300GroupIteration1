import org.lsmr.selfcheckout.devices.AbstractDevice;
import org.lsmr.selfcheckout.devices.CoinValidator;
import org.lsmr.selfcheckout.devices.listeners.AbstractDeviceListener;
import org.lsmr.selfcheckout.devices.listeners.CoinValidatorListener;

import java.math.BigDecimal;

public class CoinValidatorListenerSoftware implements CoinValidatorListener {
    @Override
    public void enabled(AbstractDevice<? extends AbstractDeviceListener> device) {

    }

    @Override
    public void disabled(AbstractDevice<? extends AbstractDeviceListener> device) {

    }

    @Override
    public void validCoinDetected(CoinValidator validator, BigDecimal value) {

    }

    @Override
    public void invalidCoinDetected(CoinValidator validator) {

    }
}
