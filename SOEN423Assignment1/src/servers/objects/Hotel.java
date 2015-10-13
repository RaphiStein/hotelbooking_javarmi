package servers.objects;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Iterator;

import interfaces.HotelInterface;
import servers.misc.Calendar;
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
			Room room = new Room(RoomType.SINGLE, priceOfSingleRooms, hotelId);
			singleRoomList.add(room);
		}
		// DOUBLE ROOMS
		this.doubleRoomPrice = priceOfDoubleRooms;
		doubleRoomList = new ArrayList<Room>(numberOfDoubleRooms);
		for (int i = 0; i < numberOfDoubleRooms; i++) {
			Room room = new Room(RoomType.DOUBLE, priceOfDoubleRooms, hotelId);
			doubleRoomList.add(room);
		}
		// FAMILY ROOMS
		this.familyRoomPrice = priceOfFamilyRooms;
		familyRoomList = new ArrayList<Room>(numberOfFamilyRooms);
		for (int i = 0; i < numberOfFamilyRooms; i++) {
			Room room = new Room(RoomType.FAMILY, priceOfFamilyRooms, hotelId);
			familyRoomList.add(room);
		}
	}


	@Override
	public int getHotelId() throws RemoteException {
		return hotelId;
	}
	/**
	 * Returns a String in the form "int1 int2" where int1 represents the number of available rooms, and int2
	 * the price of such a room
	 */
	@Override
	public String checkAvailability(RoomType roomType, Calendar checkIn, Calendar checkOut) throws RemoteException {
		int count = 0;
		switch (roomType) {
		case SINGLE:
			return getAvailableRoomsData(singleRoomList, checkIn, checkOut) + " " + singleRoomPrice;
		case DOUBLE:
			return getAvailableRoomsData(doubleRoomList, checkIn, checkOut) + " " + doubleRoomPrice;
		case FAMILY:
			return getAvailableRoomsData(familyRoomList, checkIn, checkOut) + " " + familyRoomPrice;
		default:
			System.out.println("ERROR with RoomType switch");
			return null;
		}
	}
	/**
	 * Called by objects.Guest on specific hotel to book a room at said hotel 
	 */
	@Override
	public Room reserveRoom(String guestId, int hotelId, RoomType roomType, Calendar checkIn, Calendar checkOut)
			throws RemoteException {
		// Find available room
		Room room = getAvailableRoom(roomType, checkIn, checkOut);
		if (room != null) {
			room.bookRoom(checkIn, checkOut);			
			return room;
		}
		return null; // no available room found
	}
	
	
	private int getAvailableRoomsData(ArrayList<Room> roomList, Calendar checkIn, Calendar checkOut){
		int count = 0;
		for (int i = 0; i < roomList.size(); i++) {
			if (roomList.get(i).roomIsAvailable(checkIn, checkOut)){
				count++;
			}
		}
		return count;
	}
	private Room getAvailableRoom(RoomType roomType, Calendar checkIn, Calendar checkOut){
		switch (roomType) {
		case SINGLE:
			for (int i = 0; i < singleRoomList.size(); i++) {
				if (singleRoomList.get(i).roomIsAvailable(checkIn, checkOut)){
					return singleRoomList.get(i);
				}
			}
		case DOUBLE:
			for (int i = 0; i < doubleRoomList.size(); i++) {
				if (doubleRoomList.get(i).roomIsAvailable(checkIn, checkOut)){
					return doubleRoomList.get(i);
				}
			}
		case FAMILY:
			for (int i = 0; i < familyRoomList.size(); i++) {
				if (familyRoomList.get(i).roomIsAvailable(checkIn, checkOut)){
					return familyRoomList.get(i);
				}
			}
		default:
			return null;
		}
	}
	@Override
	public String serviceReport(Calendar date) {
		ArrayList<Room> roomsBeingCheckedOutOfOnDate = new ArrayList<Room>();
		ArrayList<ArrayList<Room>> allRoomLists = new ArrayList<ArrayList<Room>>();
		allRoomLists.add(singleRoomList);
		allRoomLists.add(doubleRoomList);
		allRoomLists.add(familyRoomList);
		
		for (int i = 0; i < allRoomLists.size(); i++) {
			ArrayList<Room> currentRoomList = allRoomLists.get(i);
			for (int j = 0; j < currentRoomList.size(); j++) {
				Room currentRoom = currentRoomList.get(j);
				if (currentRoom.hasCheckout(date)){
					roomsBeingCheckedOutOfOnDate.add(currentRoom);
				}
			}
		}
		//String report = "Service report for Hotel-" + date.getTime() + "\n";
		String report = "-->\n";
		for (int i = 0; i < roomsBeingCheckedOutOfOnDate.size(); i++) {
			Room currentRoom = roomsBeingCheckedOutOfOnDate.get(i);
			report +=  "\nRoom: " + currentRoom.getRoomId();
		}
		return report;
	}
	@Override
	public String printStatus(int hotelId, Calendar date) {
		ArrayList<ArrayList<Room>> allRoomLists = new ArrayList<ArrayList<Room>>();
		allRoomLists.add(singleRoomList);
		allRoomLists.add(doubleRoomList);
		allRoomLists.add(familyRoomList);
		
		String report = "";
		for (int i = 0; i < allRoomLists.size(); i++) {
			ArrayList<Room> currentRoomList = allRoomLists.get(i);
			for (int j = 0; j < currentRoomList.size(); j++) {
				Room currentRoom = currentRoomList.get(j);
				report += "\nRoom " + currentRoom.getRoomId() + ":   " + currentRoom.getRoomType() + "   " + currentRoom.getStatusOnDate(date);
			}
		}
		
		return report;
		
	}

}
