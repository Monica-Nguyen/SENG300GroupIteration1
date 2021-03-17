import org.junit.Test;
import org.lsmr.selfcheckout.BarcodedItem;
import org.lsmr.selfcheckout.devices.SimulationException;

import static org.junit.Assert.*;

public class ScanItemTest {

    @Test(expected = SimulationException.class)
    public void testNullBarcode() {
        BarcodedItem barcoded = new BarcodedItem(null, 0.0);
    }

}
