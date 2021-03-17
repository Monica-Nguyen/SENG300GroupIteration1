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

	public BanknotePayment(SelfCheckoutStation scs, BanknotePaymentSoftware paymentSoftware){
		this.station = scs;
		this.bnps = paymentSoftware;

		station.banknoteInput.register(bnps);
		station.banknoteStorage.register(bnps);
		station.banknoteValidator.register(bnps);

	}
	public void takeBanknotePayment(Banknote bnote)
	{

		if(bnote == null)
			throw new SimulationException(new NullPointerException("Banknote is null"));
		if(bnote.getValue() <= 0)
			throw new SimulationException("Banknote can't be value <= 0");
		
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

		if(bnps.getInserted())
			System.out.println("Banknote insertion successful");
		else
			System.out.println("Banknote insertion unsuccessful");

		bnps.toggleInserted();			//banknoteSlot is now empty


		// Try to validate the banknote
		try {
			station.banknoteValidator.accept(bnote);
		} catch(DisabledException e)
		{
			System.out.println("Disabled Exception");
		}

		if(bnps.getValidation())
			System.out.println("Banknote validation successful");
		else
			System.out.println("Banknote validation unsuccessful");

		bnps.toggleValidation();		//bankNoteValidator now empty


		// Try to store the banknote
		try {
			station.banknoteStorage.accept(bnote);
		} catch(OverloadException e)
		{
			System.out.println("Overload Exception");
		} catch (DisabledException e) 
		{
			System.out.println("Disabled Exception");
		}

		if(bnps.getStore())
			System.out.println("Banknote storage successful");
		else
			System.out.println("Banknote storage unsuccessful");

		bnps.toggleStored();			// Banknote stored, reset getStore boolean parameter.


	}

}
