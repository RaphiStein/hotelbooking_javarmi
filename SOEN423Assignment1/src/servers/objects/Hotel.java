package servers.objects;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Calendar;

import interfaces.HotelInterface;
import servers.misc.Room;
import servers.misc.RoomType;

public class Hotel implements HotelInterface{

	private int hotelId;

	public ArrayList<Room> singleRoomList;
	public ArrayList<Room> doubleRoomList;
	public ArrayList<Room> familyRoomList;

	public int singleRoomPrice;
	public int doubleRoomPrice;
	public int familyRoomPrice;

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

		System.out.println("Hotel " + id + " being created.\nSingle Rooms: " + numberOfSingleRooms + ", $" + priceOfSingleRooms + "\nDouble Rooms: " + numberOfDoubleRooms + ", $" + priceOfDoubleRooms + "\nFamily Rooms: " + numberOfFamilyRooms + ", $" + priceOfFamilyRooms);
		// CREATE ROOMS
		// SINGLE ROOMS
		this.singleRoomPrice = priceOfSingleRooms;
		singleRoomList = new ArrayList<Room>(numberOfSingleRooms);
		for (int i = 0; i < numberOfSingleRooms; i++) {
			Room room = new Room(RoomType.SINGLE, priceOfSingleRooms);
			singleRoomList.add(room);
		}
		// DOUBLE ROOMS
		this.doubleRoomPrice = priceOfDoubleRooms;
		doubleRoomList = new ArrayList<Room>(numberOfDoubleRooms);
		for (int i = 0; i < numberOfDoubleRooms; i++) {
			Room room = new Room(RoomType.DOUBLE, priceOfDoubleRooms);
			doubleRoomList.add(room);
		}
		// FAMILY ROOMS
		this.familyRoomPrice = priceOfFamilyRooms;
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
	@Override
	public String checkAvailability(RoomType roomType, Calendar checkIn, Calendar checkOut) throws RemoteException {
		int count = 0;
		switch (roomType) {
		case SINGLE:
			count = 0;
			for (int i = 0; i < singleRoomList.size(); i++) {
				if (singleRoomList.get(i).roomIsAvailable(checkIn, checkOut)){
					count++;
				}
			}
			return count + " " + singleRoomPrice;
		case DOUBLE:
			count = 0;
			for (int i = 0; i < doubleRoomList.size(); i++) {
				if (doubleRoomList.get(i).roomIsAvailable(checkIn, checkOut)){
					count++;
				}
			}
			return count + " " + doubleRoomPrice;
		case FAMILY:
			count = 0;
			for (int i = 0; i < familyRoomList.size(); i++) {
				if (familyRoomList.get(i).roomIsAvailable(checkIn, checkOut)){
					count++;
				}
			}
			return count + " " + familyRoomPrice;
		default:
			System.out.println("ERROR with RoomType switch");
			return null;
		}
	}

}
