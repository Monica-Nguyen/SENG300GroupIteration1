package org.lsmr.selfcheckoutsoftware;

import org.lsmr.selfcheckout.Banknote;
import org.lsmr.selfcheckout.devices.*;

/*
 * Devices:
 *		BanknoteDispenser, BanknoteSlot, BanknoteStorageUnit, BanknoteValidator
 *
 */

/* Listeners:
 *		banknoteDispenserListener, BanknoteSlotListener, BanknoteStorageUnitListener, banknoteValidatorListener
 *		 
 */


public class BanknotePayment {
	
	
	private SelfCheckoutStation station;
	private BanknotePaymentSoftware bnps;

	private int totalPaid = 0;
	private boolean hasRemainder = false;
	private boolean needToDispenseChange = false;
	private boolean successfulStorage = true;
	public BanknotePayment(SelfCheckoutStation scs, BanknotePaymentSoftware paymentSoftware){
		this.station = scs;
		this.bnps = paymentSoftware;

		station.banknoteInput.register(bnps);
		station.banknoteStorage.register(bnps);
		station.banknoteValidator.register(bnps);

	}
	public void pay(Banknote bnote, int total)
	{

		if(bnote == null)
			throw new SimulationException(new NullPointerException("Banknote is null"));
		if(bnote.getValue() <= 0)
			throw new SimulationException("Banknote can't be value <= 0");
		if(total <= 0)
			throw new SimulationException("Total need to pay cannot be <= 0!");

		// Start by trying to accept an input of banknote
		try {
			station.banknoteInput.accept(bnote);
		} catch(OverloadException e)
		{
			System.out.println("Overload Exception");
		} catch (DisabledException e) 
		{
			System.out.println("Disabled Exception");
		}
		if(bnps.getStore()) {
			System.out.print("\nStoring banknote of " + bnote.getValue() + " was successful\n");
			totalPaid += bnote.getValue();
			successfulStorage = true;
			if(total - bnote.getValue() == 0)
			{
				hasRemainder = false;
				needToDispenseChange = false;
				System.out.println("Total of " + total + " was paid entirely");
			}
			else if(total - bnote.getValue() > 0)
			{
				hasRemainder = true;
				System.out.println("Need to pay: " + (total - bnote.getValue()));
			}
			else if(total - bnote.getValue() < 0)
			{
				needToDispenseChange = true;
				System.out.println("Dispense change: " + (bnote.getValue() - total));
			}
		}
		else {
			successfulStorage = false;
			System.out.println("Storing banknote was unsuccessful");
		}
		bnps.toggleStored();			// Banknote stored, reset getStore boolean parameter.



		System.out.println("----------------");

	}

	public int getTotalPaid()
	{
		return totalPaid;
	}
	public boolean getHasRemainder() {
		return hasRemainder;
	}
	public boolean getNeedToDispenseChange(){
		return needToDispenseChange;
	}
	public boolean getSuccessfulStorage()
	{
		return successfulStorage;
	}


}
