package servers;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.HashMap;
import java.util.Random;

import interfaces.HotelHubInterface;
import interfaces.HotelInterface;
import servers.misc.Calendar;
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
					hotel = new Hotel(i, random.nextInt(10)+10, random.nextInt(100)+100, random.nextInt(10)+5, random.nextInt(100)+200, random.nextInt(10)+1, random.nextInt(100)+300);					
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
	public HotelHub(int numOfHotels, int[][] numberOfEachRoomType) {
		hotels = new HashMap<Integer, HotelInterface>(numOfHotels);
		Hotel hotel;
		for (int i = 0; i < numberOfEachRoomType.length; i++) {
			try {
				hotel = new Hotel(i, numberOfEachRoomType[i][0], 100, numberOfEachRoomType[i][1], 200, numberOfEachRoomType[i][2], 300);
				HotelInterface hotelInterface = (HotelInterface) UnicastRemoteObject.exportObject(hotel, 2021);
				hotels.put(i, hotelInterface);
			}
			catch (Exception e){
				e.printStackTrace();
			}
		}
	}
	@Override
	public HotelInterface getHotelById(int id) throws RemoteException {
		if (hotels.containsKey(id)){
			return hotels.get(id);
		}
		else {
			try {
				throw new Exception("No such hotel found");
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
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
