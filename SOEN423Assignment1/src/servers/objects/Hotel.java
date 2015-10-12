package servers.objects;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Map;

import interfaces.HotelInterface;
import servers.misc.Room;
import servers.misc.RoomList;
import servers.misc.RoomType;

public class Hotel implements HotelInterface{

	private int hotelId;

	public ArrayList<Room> singleRoomList;
	public ArrayList<Room> doubleRoomList;
	public ArrayList<Room> familyRoomList;


	public Hotel(int id){
		this.hotelId = id;
		// Initialize Room lists
		singleRoomList = new ArrayList<Room>();
		doubleRoomList = new ArrayList<Room>();
		familyRoomList = new ArrayList<Room>();
	}
	public Hotel(int id, int numberOfSingleRooms, int priceOfSingleRooms, int numberOfDoubleRooms, int priceOfDoubleRooms,
			int numberOfFamilyRooms, int priceOfFamilyRooms){
		this.hotelId = id;

		// CREATE ROOMS
		// SINGLE ROOMS
		singleRoomList = new ArrayList<Room>(numberOfSingleRooms);
		for (int i = 0; i < numberOfSingleRooms; i++) {
			Room room = new Room(RoomType.SINGLE, priceOfSingleRooms);
			singleRoomList.add(room);
		}
		// DOUBLE ROOMS
		doubleRoomList = new ArrayList<Room>(numberOfDoubleRooms);
		for (int i = 0; i < numberOfDoubleRooms; i++) {
			Room room = new Room(RoomType.DOUBLE, priceOfDoubleRooms);
			doubleRoomList.add(room);
		}
		// FAMILY ROOMS
		familyRoomList = new ArrayList<Room>(numberOfFamilyRooms);
		for (int i = 0; i < numberOfFamilyRooms; i++) {
			Room room = new Room(RoomType.FAMILY, priceOfFamilyRooms);
			familyRoomList.add(room);
		}
	}

	@Override
	public Guest getGuest() throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}

	public String getHotelGreeting(){
		return "Ola!";
	}

	@Override
	public int getHotelId() throws RemoteException {
		return hotelId;
	}
}
