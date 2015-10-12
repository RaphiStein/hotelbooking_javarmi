package servers;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Random;

import interfaces.HotelHubInterface;
import interfaces.HotelInterface;
import servers.misc.RoomType;
import servers.objects.Hotel;

public class HotelHub implements HotelHubInterface{

	private HashMap<Integer, HotelInterface> hotels;

	/**
	 * Creates the HotelHub and inserts ${numOfHotels} hotels in it. 
	 * @param numOfHotels
	 */
	public HotelHub(int numOfHotels, boolean shouldPopulate) {
		hotels = new HashMap<Integer, HotelInterface>(numOfHotels);
		try {
			for (int i = 0; i < numOfHotels; i++) {
				Hotel hotel;
				if (shouldPopulate){
					Random random = new Random();
					hotel = new Hotel(i, random.nextInt(10), random.nextInt(100)+100, random.nextInt(10), random.nextInt(100)+100, random.nextInt(10), random.nextInt(100)+100);					
				}
				else 
					hotel = new Hotel(i);					
				HotelInterface hotelInterface = (HotelInterface) UnicastRemoteObject.exportObject(hotel, 2021);
				hotels.put(i, hotelInterface);
			}
		}
		catch (Exception e){
			e.printStackTrace();
		}
	}
	public HotelHub(int numOfHotels) {
		new HotelHub(numOfHotels, false);
	}
	@Override
	public HotelInterface getHotelById(int id) throws RemoteException {
		if (hotels.containsKey(id)){
			return hotels.get(id);
		}
		return null;
	}
	@Override
	public String checkAvailability(String guestId, int preferredHotelId, RoomType roomType, Calendar checkIn,
			Calendar checkOut) throws RemoteException {
		String result = "";
		// First, find preferred hotel
		HotelInterface hotel = hotels.get(preferredHotelId);
		return "Preferred Hotel: " + hotel.checkAvailability(roomType, checkIn, checkOut);
		// Iterate through all hotels
	}
}
