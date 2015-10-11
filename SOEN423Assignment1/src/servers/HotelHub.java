package servers;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.HashMap;

import interfaces.HotelHubInterface;
import interfaces.HotelInterface;
import servers.objects.Hotel;

public class HotelHub implements HotelHubInterface{

	private HashMap<Integer, HotelInterface> hotels;

	/**
	 * Creates the HotelHub and inserts ${numOfHotels} hotels in it. 
	 * @param numOfHotels
	 */
	public HotelHub(int numOfHotels) {
		hotels = new HashMap<Integer, HotelInterface>(numOfHotels);
		try {
			for (int i = 0; i < numOfHotels; i++) {
				HotelInterface hotel = (HotelInterface) UnicastRemoteObject.exportObject(new Hotel(i), 2021);
				hotels.put(i, hotel);
			}
		}
		catch (Exception e){
			
		}
	}
	@Override
	public HotelInterface getHotelById(int id) throws RemoteException {
		if (hotels.containsKey(id)){
			return hotels.get(id);
		}
		return null;
	}
}
