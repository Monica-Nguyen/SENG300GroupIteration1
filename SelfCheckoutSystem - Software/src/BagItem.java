import java.util.ArrayList;

import org.lsmr.selfcheckout.Item;
import org.lsmr.selfcheckout.devices.ElectronicScale;
import org.lsmr.selfcheckout.devices.SelfCheckoutStation;

public class BagItem {
	
	private ElectronicScale scale;
	private SelfCheckoutStation station;
	public int maxWeight;
	public int maxSensitivity;
	
	
	public BagItem(ElectronicScale scale, SelfCheckoutStation station)
	{
		this.scale = scale;
		this.station = station;
	}
	
	

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
