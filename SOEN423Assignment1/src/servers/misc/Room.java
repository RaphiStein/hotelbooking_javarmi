package servers.misc;

import java.io.Serializable;
import java.util.Iterator;
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
	/**
	 * The hotel this Room belongs to
	 */
	private int hotelId;
	
	private SortedSet<Calendar> occupiedDates;
	private Map<Calendar, Calendar> checkInCheckOutPairs;
	
	public Room(RoomType roomType, int price, int hotelId) {
		this.roomtype = roomType;
		this.price = price;
		// Data structures
		occupiedDates = new TreeSet<Calendar>();
		checkInCheckOutPairs = new TreeMap<Calendar, Calendar>();
		this.roomId = ++Room.roomIdCounter;
		this.hotelId = hotelId;
	}
	
	public void bookRoom(Calendar checkIn, Calendar checkout){
		// Add to occupy dates
		int daysBetween = (int) (checkout.getTimeInMillis() - checkIn.getTimeInMillis()) / (1000 * 60 * 60 * 24);
		for (int i = 0; i < daysBetween; i++) { // don't put checkOut date
			servers.misc.Calendar addDate = (servers.misc.Calendar) checkIn.clone();
			addDate.add(Calendar.DAY_OF_MONTH, i);
			occupiedDates.add(addDate);
		}
		// Add to CheckInCheckOut set
		checkInCheckOutPairs.put(checkIn, checkout);
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
	public Calendar getCheckoutDate(Calendar checkin){
		return checkInCheckOutPairs.get(checkin);
	}
	@Override
	public boolean equals(Object obj) {
		Room room = (Room) obj;
		if (roomId == room.getRoomId()){
			return true;
		}
		else {
			return false;
		}
	}
	public int getRoomId(){
		return roomId;
	}
	
	public void cancel(Calendar checkIn, Calendar checkOut){
		// TWO PARTS:
		// 1) Remove checkInCheckOut pair
		checkInCheckOutPairs.remove(checkIn);
		// 2) Update occupiedDates set
		Iterator<Calendar> iter = occupiedDates.iterator();
		while (iter.hasNext()){
			Calendar cal = iter.next();
			if (cal.equals(checkIn) || (cal.isLaterThan(checkIn) && cal.isEarlierThan(checkOut))){
				iter.remove();
			}
		}
		
	}

	public boolean hasCheckout(Calendar date) {
		for (Calendar checkout : checkInCheckOutPairs.values()) {
		    if (checkout.equals(date)){
		    	return true;
		    }
		} 
		return false;
	}

	public RoomType getRoomType() {
		return roomtype;
	}

	public String getStatusOnDate(Calendar date) {
		if (occupiedDates.contains(date)){
			return "OCCUPIED";
		}
		else
			return "VACANT";
	}
}
