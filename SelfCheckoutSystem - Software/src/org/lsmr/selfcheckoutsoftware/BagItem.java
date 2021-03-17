package org.lsmr.selfcheckoutsoftware;

import org.lsmr.selfcheckout.Item;
import org.lsmr.selfcheckout.devices.SelfCheckoutStation;
import org.lsmr.selfcheckout.devices.SimulationException;

public class BagItem {

	private SelfCheckoutStation station;
	private ElectronicScaleListenerSoftware electListen;
	
	
	public BagItem(SelfCheckoutStation s,ElectronicScaleListenerSoftware e)
	{
		this.station = s;
		this.electListen = e;
		station.baggingArea.register(electListen);
	}

	public void baggingItem(Item item)
	{
		if (item == null)
		{
			throw new SimulationException(new NullPointerException("item is null"));
		}
		else {
			double oldWeight = electListen.getPrevWeight();
			station.baggingArea.add(item);
			double newWeight = electListen.getPrevWeight();
			double checkWeight = newWeight - oldWeight;
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
