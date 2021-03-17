package org.lsmr.selfcheckoutsoftware;

import org.lsmr.selfcheckout.Banknote;
import org.lsmr.selfcheckout.devices.*;
import org.lsmr.selfcheckout.devices.listeners.*;

import java.util.Currency;


public class BanknotePaymentSoftware implements BanknoteDispenserListener, BanknoteSlotListener, BanknoteValidatorListener, BanknoteStorageUnitListener {

	private boolean banknoteInserted = false;
	private boolean banknoteIsValid = false;
	private boolean banknoteStored = false;
	private boolean suIsFull = false;

	public boolean getInserted()
	{
		return banknoteInserted;
	}

	public void toggleInserted()
	{
		banknoteInserted = false;
	}

	public boolean getValidation()
	{
		return banknoteIsValid;
	}

	public void toggleValidation()
	{
		banknoteIsValid = false;
	}


	public boolean getStore()
	{
		return banknoteStored;
	}

	public void toggleStored()
	{
		banknoteStored = false;
	}

	public boolean isFull()
	{
		return suIsFull;
	}


	@Override
	public void banknotesFull(BanknoteDispenser dispenser)
	{
		if(dispenser.size() > 1000)
		{
			System.out.println("Banknote dispenser is full of banknotes (>1000)");

		}
	}


	@Override
	public void banknotesEmpty(BanknoteDispenser dispenser)
	{
		if(dispenser.size() == 0)
		{
			System.out.println("Banknote dispenser empty");
		}
	}


	@Override
	public void banknoteAdded(BanknoteDispenser dispenser, Banknote banknote)
	{
		if(banknote.getValue() > 0)
		{
			System.out.println("Banknote added -  value: " + banknote.getValue() + "  currency: " + banknote.getCurrency());
		}
		else{
			System.out.println("ERROR: Adding banknote of value <= 0");
		}
	}


	@Override
	public void banknoteRemoved(BanknoteDispenser dispenser, Banknote banknote)
	{
		if(banknote.getValue() > 0)
		{
			System.out.println("Banknote added -  value: " + banknote.getValue() + "  currency: " + banknote.getCurrency());
		}
		else{
			System.out.println("ERROR: Removing banknote of value <= 0");
		}
	}
	

	@Override
	public void banknotesLoaded(BanknoteDispenser dispenser, Banknote... banknotes)
	{
		if(banknotes.length <= 0)
		{
			System.out.println("ERROR: Loading banknotes of length 0");
		}
		for(Banknote i : banknotes)
		{
			System.out.println("Banknote loaded -  value: " + i.getValue() + "  currency: " + i.getCurrency());

		}
	}


	@Override
	public void banknotesUnloaded(BanknoteDispenser dispenser, Banknote... banknotes) 
	{
		if(banknotes.length <= 0)
		{
			System.out.println("ERROR: Unloading banknotes of length 0");
		}
		for(Banknote i : banknotes)
		{
			System.out.println("Banknote unloaded -  value: " + i.getValue() + "  currency: " + i.getCurrency());

		}
	}
	

	@Override
	public void banknoteInserted(BanknoteSlot slot)
	{
		System.out.println("Banknote has been inserted");
		this.banknoteInserted = true;
	}


	@Override
	public void banknoteEjected(BanknoteSlot slot)
	{
		System.out.println("Banknote has been ejected");
		banknoteInserted = false;
	}


	@Override
	public void banknoteRemoved(BanknoteSlot slot)
	{
		System.out.println("Dangling banknote has been removed");


	}
	

	@Override
	public void validBanknoteDetected(BanknoteValidator validator, Currency currency, int value)
	{
		System.out.println("Valid banknote detected - currency: " + currency + "   value: "+ value);
		banknoteIsValid = true;
	}


	@Override
	public void invalidBanknoteDetected(BanknoteValidator validator)
	{
		System.out.println("Invalid banknote detected");
		banknoteIsValid = false;

	}



	@Override
	public void banknotesFull(BanknoteStorageUnit unit) {
		if(unit.getBanknoteCount() >= 1000)
		{
			System.out.println("Banknote Storage Unit is full of banknotes (>1000)");
		}

		suIsFull = true;
	}

	@Override
	public void banknoteAdded(BanknoteStorageUnit unit) {
		System.out.println("Banknote added to Storage Unit");
		banknoteStored = true;
	}

	@Override
	public void banknotesLoaded(BanknoteStorageUnit unit) {
		System.out.println("Banknote loaded to Storage Unit");
	}

	@Override
	public void banknotesUnloaded(BanknoteStorageUnit unit) {
		System.out.println("Banknote unloaded from Storage Unit");
		suIsFull = false;
	}

	@Override
	public void enabled(AbstractDevice<? extends AbstractDeviceListener> device) {

	}

	@Override
	public void disabled(AbstractDevice<? extends AbstractDeviceListener> device) {

	}
}
