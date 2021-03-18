package org.lsmr.selfcheckoutsoftware;


import org.lsmr.selfcheckout.Coin;
import org.lsmr.selfcheckout.devices.*;
import org.lsmr.selfcheckout.devices.listeners.*;

import java.math.BigDecimal;
import java.math.MathContext;

public class CoinPayment {


    private SelfCheckoutStation station;

    MathContext m = new MathContext(1); // 4 precision
    private boolean insertCoin = false;
    private Coin coin2;

    public boolean isValidCoin() {
        return validCoin;
    }

    private boolean validCoin = false;
    private boolean addCoin = false;

    public boolean isFull() {
        return full;
    }

    int counter = 0;

    private boolean full = false;
    BigDecimal totalPaid = BigDecimal.valueOf(0.00).round(m);

    public BigDecimal getTotalPaid()
    {
        return totalPaid;
    }

    public CoinPayment(SelfCheckoutStation station) {
        this.station = station;
        station.coinSlot.register(new CoinSlotListener() {


            @Override
            public void coinInserted(CoinSlot slot) {
                insertCoin = true;
                System.out.println("Coin has been inserted");
            }

            @Override
            public void enabled(AbstractDevice<? extends AbstractDeviceListener> device) {

            }

            @Override
            public void disabled(AbstractDevice<? extends AbstractDeviceListener> device) {

            }
        });
        station.coinValidator.register(new CoinValidatorListener() {
            @Override
            public void validCoinDetected(CoinValidator validator, BigDecimal value) {
                validCoin = true;
                System.out.println("Coin is valid");
            }

            @Override
            public void invalidCoinDetected(CoinValidator validator) {
                validCoin = false;
                System.out.println("Coin is invalid");

            }

            @Override
            public void enabled(AbstractDevice<? extends AbstractDeviceListener> device) {

            }

            @Override
            public void disabled(AbstractDevice<? extends AbstractDeviceListener> device) {

            }
        });
        station.coinStorage.register(new CoinStorageUnitListener() {
            @Override
            public void coinsFull(CoinStorageUnit unit) {
                if(unit.getCoinCount()>=unit.getCapacity()) {
                    full = true;
                    System.out.println("Coin storage is full");
                }
            }

            @Override
            public void coinAdded(CoinStorageUnit unit) {
                addCoin = true;
                System.out.println("Coin has been added");

            }

            @Override
            public void coinsLoaded(CoinStorageUnit unit) {
            }

            @Override
            public void coinsUnloaded(CoinStorageUnit unit) {
            }

            @Override
            public void enabled(AbstractDevice<? extends AbstractDeviceListener> device) {

            }

            @Override
            public void disabled(AbstractDevice<? extends AbstractDeviceListener> device) {

            }
        });
    }


    public void pay(Coin coin, CoinStorageUnit unit) throws DisabledException, OverloadException {

        int res = coin.getValue().compareTo(BigDecimal.ZERO);
        if(res == -1 || res == 0){
            throw new SimulationException("Coin cannot be less than or equal to 0");
        }

        else {
            station.coinSlot.accept(coin);
            if(insertCoin){
                System.out.println("Coin insertion successful");
                if (!validCoin)
                {
                    System.out.println("Coin validation unsuccessful");
                }
                else
                {
                    System.out.println("Coin is valid");
                    if (full)
                    {
                        System.out.println("Storage is full");
                    }
                    else
                    {
                        unit.accept(coin);
                        System.out.println("Storage has space");
                        BigDecimal validatedCoin = coin.getValue().round(m);
                        totalPaid = totalPaid.add(validatedCoin).round(m);
                        System.out.println("You paid: " + totalPaid + " dollars");
                    }
                }
            }
            else {
                System.out.println("Coin insertion unsuccessful");
            }
        }
    }

}
