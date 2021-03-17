



import org.lsmr.selfcheckout.Coin;
import org.lsmr.selfcheckout.devices.*;

import java.math.BigDecimal;

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

        int res = coin.getValue().compareTo(BigDecimal.ZERO);
        if(res == -1 || res == 0){
            throw new SimulationException("Coin cannot be less than or equal to 0");
        }

        try{
            station.coinSlot.accept(coin);
        }catch(DisabledException e){
            System.out.println("Disable Exception");
        }

        if(cls.isInsertCoin()){
            System.out.println("Coin insertion successful");
        }else{
            System.out.println("Coin insertion unsuccesssful");
        }

        try{
            station.coinValidator.accept(coin);
        }catch(DisabledException e){
            System.out.println("Disable Exception");
        }

        if(cls.isValidCoin()) {
            System.out.println("Coin validation successful");
        }else{
            System.out.println("Coin validation unsuccessful");
        }

        try{
            station.coinStorage.accept(coin);
        }catch(DisabledException e){
            System.out.println("Disabled Exception");
        }catch(OverloadException e) {
            System.out.println("Overload Exception");
        }

        if(cls.isAddCoin()) {
            System.out.println("Coin storage successful");
        }else{
            System.out.println("Coin storage unsuccessful");
        }

    }

}
