package org.lsmr.selfcheckoutsoftware;

import org.lsmr.selfcheckout.Item;
import org.lsmr.selfcheckout.devices.*;
import org.lsmr.selfcheckout.devices.listeners.AbstractDeviceListener;
import org.lsmr.selfcheckout.devices.listeners.ElectronicScaleListener;

public class BagItem {

	private SelfCheckoutStation station;

	public double getCheckWeight() {
		return checkWeight;
	}


	private double checkWeight;
	
	
	public BagItem(SelfCheckoutStation s)
	{
		this.station = s;
		station.baggingArea.register(new ElectronicScaleListener() {

			private double prevWeight;

			@Override
			public void weightChanged(ElectronicScale scale, double weightInGrams) {
				double currentWeight = weightInGrams - prevWeight;

				System.out.println("The weight of this item is: " + currentWeight + " grams");

				prevWeight = weightInGrams;
			}

			@Override
			public void overload(ElectronicScale scale) {
				System.out.println("Object is too heavy. Remove it.");
			}

			@Override
			public void outOfOverload(ElectronicScale scale) {
				System.out.println("Excessive weight is gone, can weigh items again.");
			}

			@Override
			public void enabled(AbstractDevice<? extends AbstractDeviceListener> device) {

			}

			@Override
			public void disabled(AbstractDevice<? extends AbstractDeviceListener> device) {

			}
		});
	}


	public void baggingItem(Item item) throws OverloadException {
		if (item == null)
		{
			throw new SimulationException(new NullPointerException("item is null"));
		}
		else {
			double oldWeight = station.baggingArea.getCurrentWeight();
			station.baggingArea.add(item);
			double newWeight = station.baggingArea.getCurrentWeight();
			checkWeight = newWeight - oldWeight;
			if ((checkWeight == item.getWeight()))
			{
				System.out.println("Item can be bagged");
			}
			else
			{
				System.out.println("Item can not be bagged");
			}
		}
	}


}
