import org.lsmr.selfcheckout.Coin;
import org.lsmr.selfcheckout.devices.CoinSlot;
import org.lsmr.selfcheckout.devices.DisabledException;
import org.lsmr.selfcheckout.devices.SelfCheckoutStation;

public class CoinPayment {


    private SelfCheckoutStation station;
    private CoinSlot slot;
    private Coin coin;
    private CoinListenerSoftware cls;

    public CoinPayment(SelfCheckoutStation station, CoinListenerSoftware cls) {
        this.station = station;
        this.cls = cls;
        station.coinSlot.register(cls);
        station.coinValidator.register(cls);
        station.coinStorage.register(cls);
    }


    public void pay(Coin coin) throws DisabledException {

            station.coinSlot.accept(coin);



    }



}
