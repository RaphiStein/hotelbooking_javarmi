package servers;

import java.rmi.RemoteException;
import java.util.HashMap;

import interfaces.HotelHubInterface;
import servers.objects.Hotel;

public class HotelHub implements HotelHubInterface{

	private HashMap<String, Hotel> hotels;

	@Override
	public Hotel getHotelById(int id) throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}
}
