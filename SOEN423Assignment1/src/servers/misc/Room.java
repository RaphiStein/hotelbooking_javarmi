package servers.misc;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Map;
import java.util.SortedSet;
import java.util.TreeMap;
import java.util.TreeSet;

public class Room implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -6795914098104059358L;

	private static int roomIdCounter = 0;

	private int roomId;
	private RoomType roomtype;
	private int price;
	
	private SortedSet<servers.misc.Calendar> occupiedDates;
	private Map<servers.misc.Calendar, servers.misc.Calendar> checkInCheckOutPairs;
	
	public Room(RoomType roomType, int price) {
		this.roomtype = roomType;
		this.price = price;
		// Data structures
		occupiedDates = new TreeSet<servers.misc.Calendar>();
		checkInCheckOutPairs = new TreeMap<servers.misc.Calendar, servers.misc.Calendar>();
		this.roomId = ++Room.roomIdCounter;
	}
	
	public void bookRoom(Calendar checkIn, Calendar checkout){
		// Add to occupy dates
		int daysBetween = (int) (checkout.getTimeInMillis() - checkIn.getTimeInMillis()) / (1000 * 60 * 60 * 24);
		for (int i = 0; i < daysBetween-1; i++) { // don't put checkOut date
			servers.misc.Calendar addDate = (servers.misc.Calendar) checkIn.clone();
			addDate.add(Calendar.DAY_OF_MONTH, i);
			occupiedDates.add(addDate);
		}
	}
	public boolean roomIsAvailable(Calendar checkIn, Calendar checkout){
		int daysBetween = (int) (checkout.getTimeInMillis() - checkIn.getTimeInMillis()) / (1000 * 60 * 60 * 24);
		for (int i = 0; i < daysBetween; i++) {
			Calendar addDate = (servers.misc.Calendar) checkIn.clone();
			addDate.add(Calendar.DAY_OF_MONTH, i);
			if (occupiedDates.contains(addDate)){
				return false;
			}
		}
		return true;
	}
}
