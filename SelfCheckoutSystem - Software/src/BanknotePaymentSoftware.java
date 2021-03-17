

import org.lsmr.selfcheckout.Banknote;
import org.lsmr.selfcheckout.devices.AbstractDevice;
import org.lsmr.selfcheckout.devices.BanknoteDispenser;
import org.lsmr.selfcheckout.devices.BanknoteSlot;
import org.lsmr.selfcheckout.devices.BanknoteStorageUnit;
import org.lsmr.selfcheckout.devices.BanknoteValidator;
import org.lsmr.selfcheckout.devices.listeners.*;

import java.util.Currency;


public class BanknotePaymentSoftware implements BanknoteDispenserListener, BanknoteSlotListener, BanknoteValidatorListener, BanknoteStorageUnitListener {

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
	}


	@Override
	public void banknoteRemoved(BanknoteDispenser dispenser, Banknote banknote)
	{
		if(banknote.getValue() > 0)
		{
			System.out.println("Banknote added -  value: " + banknote.getValue() + "  currency: " + banknote.getCurrency());
	
		}
	}
	

	@Override
	public void banknotesLoaded(BanknoteDispenser dispenser, Banknote... banknotes)
	{
		if(banknotes.length <= 0)
		{
			System.out.println("Loading length 0");
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
			System.out.println("Loading length 0");
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
	}


	@Override
	public void banknoteEjected(BanknoteSlot slot)
	{
		System.out.println("Banknote has been ejected");

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
	}


	@Override
	public void invalidBanknoteDetected(BanknoteValidator validator)
	{
		System.out.println("Invalid banknote detected");

	}

	@Override
	public void enabled(AbstractDevice<? extends AbstractDeviceListener> device) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void disabled(AbstractDevice<? extends AbstractDeviceListener> device) {
		// TODO Auto-generated method stub
		
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
