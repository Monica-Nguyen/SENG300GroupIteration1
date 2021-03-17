

import org.lsmr.selfcheckout.Banknote;
import org.lsmr.selfcheckout.devices.AbstractDevice;
import org.lsmr.selfcheckout.devices.BanknoteDispenser;
import org.lsmr.selfcheckout.devices.BanknoteSlot;
import org.lsmr.selfcheckout.devices.BanknoteStorageUnit;
import org.lsmr.selfcheckout.devices.BanknoteValidator;
import org.lsmr.selfcheckout.devices.listeners.*;

import java.util.Currency;


public class BankNotePaymentListener implements BanknoteDispenserListener, BanknoteSlotListener, BanknoteValidatorListener, BanknoteStorageUnitListener {
	/**
	 * Announces that the indicated banknote dispenser is full of banknotes.
	 * 
	 * @param dispenser
	 *             The dispenser where the event occurred.
	 */
	
	public void banknotesFull(BanknoteDispenser dispenser)
	{
		if(dispenser.size() > 1000)
		{
			System.out.println("Banknote dispenser is full of banknotes (>1000)");
		}
	}

	/**
	 * Announces that the indicated banknote dispenser is empty of banknotes.
	 * 
	 * @param dispenser
	 *             The dispenser where the event occurred.
	 */
	public void banknotesEmpty(BanknoteDispenser dispenser)
	{
		if(dispenser.size() == 0)
		{
			System.out.println("Banknote dispenser empty");
		}
	}

	/**
	 * Announces that the indicated banknote has been added to the indicated banknote dispenser.
	 * 
	 * @param dispenser
	 *             The dispenser where the event occurred.
	 * @param banknote
	 *             The banknote that was added.
	 */
	public void banknoteAdded(BanknoteDispenser dispenser, Banknote banknote)
	{
		if(banknote.getValue() > 0)
		{
			System.out.println("Banknote added -  value: " + banknote.getValue() + "  currency: " + banknote.getCurrency());
		}
	}

	/**
	 * Announces that the indicated banknote has been added to the indicated banknote dispenser.
	 * 
	 * @param dispenser
	 *             The dispenser where the event occurred.
	 * @param banknote
	 *             The banknote that was removed.
	 */
	public void banknoteRemoved(BanknoteDispenser dispenser, Banknote banknote)
	{
		if(banknote.getValue() > 0)
		{
			System.out.println("Banknote added -  value: " + banknote.getValue() + "  currency: " + banknote.getCurrency());
	
		}
	}
	
	/**
	 * Announces that the indicated sequence of banknotes has been added to the
	 * indicated banknote dispenser. Used to simulate direct, physical loading of the dispenser.
	 * 
	 * @param dispenser
	 *              The dispenser where the event occurred.
	 * @param banknotes
	 *              The banknotes that were loaded.
	 */
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

	/**
	 * Announces that the indicated sequence of banknotes has been removed to the
	 * indicated banknote dispenser. Used to simulate direct, physical unloading of the dispenser.
	 * 
	 * @param dispenser
	 *              The dispenser where the event occurred.
	 * @param banknotes
	 *              The banknotes that were unloaded.
	 */
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
	
	/**
	 * An event announcing that a banknote has been inserted.
	 * 
	 * @param slot
	 *            The device on which the event occurred.
	 */
	public void banknoteInserted(BanknoteSlot slot)
	{
		System.out.println("Banknote has been inserted");
	}

	/**
	 * An event announcing that a banknote has been returned to the user, dangling
	 * from the slot.
	 * 
	 * @param slot
	 *            The device on which the event occurred.
	 */
	public void banknoteEjected(BanknoteSlot slot)
	{
		System.out.println("Banknote has been ejected");

	}

	/**
	 * An event announcing that a dangling banknote has been removed by the user.
	 * 
	 * @param slot
	 *            The device on which the event occurred.
	 */
	public void banknoteRemoved(BanknoteSlot slot)
	{
		System.out.println("Dangling banknote has been removed");

	}
	
	/**
	 * An event announcing that the indicated banknote has been detected and
	 * determined to be valid.
	 * 
	 * @param validator
	 *            The device on which the event occurred.
	 * @param currency
	 *            The kind of currency of the inserted banknote.
	 * @param value
	 *            The value of the inserted banknote.
	 */
	public void validBanknoteDetected(BanknoteValidator validator, Currency currency, int value)
	{
		System.out.println("Valid banknote detected - currency: " + currency + "   value: "+ value);
	}

	/**
	 * An event announcing that the indicated banknote has been detected and
	 * determined to be invalid.
	 * 
	 * @param validator
	 *            The device on which the event occurred.
	 */
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
