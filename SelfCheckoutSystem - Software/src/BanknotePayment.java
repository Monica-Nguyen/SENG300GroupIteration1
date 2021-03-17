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
	
	public BanknotePayment(SelfCheckoutStation scs){
		this.station = scs;
		
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
		System.out.println("Banknote accepted");
		
		// Try to validate the banknote
		try {
			station.banknoteValidator.accept(bnote);
		} catch(DisabledException e)
		{
			System.out.println("Disabled Exception");
		}


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
		
	}

}
