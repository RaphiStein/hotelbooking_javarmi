package servers.misc;

import java.util.Calendar;
import java.util.Map;
import java.util.SortedSet;
import java.util.TreeMap;
import java.util.TreeSet;

public class Room {
	private static int roomIdCounter = 0;

	private int roomId;
	private RoomType roomtype;
	private int price;
	
	private SortedSet<Calendar> occupiedDates;
	private Map<Calendar, Calendar> checkInCheckOutPairs;
	
	public Room(RoomType roomType, int price) {
		this.roomtype = roomType;
		this.price = price;
		// Data structures
		occupiedDates = new TreeSet<Calendar>();
		checkInCheckOutPairs = new TreeMap<Calendar, Calendar>();
		this.roomId = ++Room.roomIdCounter;
	}
	
	public void bookRoom(Calendar checkIn, Calendar checkout){
		// Add to occupy dates
		int daysBetween = (int) (checkIn.getTimeInMillis() - checkout.getTimeInMillis()) / (1000 * 60 * 60 * 24);
		occupiedDates.add(checkIn);
		for (int i = 0; i < daysBetween-1; i++) { // don't put checkOut date
			Calendar addDate = (Calendar) checkIn.clone();
			addDate.add(Calendar.DATE, i);
			occupiedDates.add(addDate);
		}
	}
	public boolean roomIsAvailable(Calendar checkIn, Calendar checkout){
		int daysBetween = (int) (checkIn.getTimeInMillis() - checkout.getTimeInMillis()) / (1000 * 60 * 60 * 24);
		for (int i = 0; i < daysBetween; i++) {
			Calendar addDate = (Calendar) checkIn.clone();
			addDate.add(Calendar.DATE, i);
			if (occupiedDates.contains(addDate)){
				return false;
			}
		}
		return true;
	}
}
