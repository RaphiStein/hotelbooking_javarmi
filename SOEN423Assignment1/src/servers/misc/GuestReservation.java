package servers.misc;

import java.util.Random;

public class GuestReservation implements Comparable<GuestReservation> {
	private Calendar checkIn;
	private Calendar checkOut;
	private Room room;
	
	
	public GuestReservation(Calendar checkIn, Calendar checkOut, Room room) {
		super();
		this.checkIn = checkIn;
		this.checkOut = checkOut;
		this.room = room;
	}
	public Calendar getCheckIn() {
		return checkIn;
	}
	public void setCheckIn(Calendar checkIn) {
		this.checkIn = checkIn;
	}
	public Calendar getCheckOut() {
		return checkOut;
	}
	public void setCheckOut(Calendar checkOut) {
		this.checkOut = checkOut;
	}
	public Room getRoom() {
		return room;
	}
	public void setRoom(Room room) {
		this.room = room;
	}
	@Override
	public int compareTo(GuestReservation o) {
		if (this.checkIn.equals(checkIn)){
			// If the checkIn dates are equal, randomly generate -1 or 1. Two Reservations cannot be equal
			double random = Math.random();
			if (random > 0.5) return 1;
			else return -1;
		}
		else if (this.checkIn.isEarlierThan(checkIn)){
			return -1;
		}
		
		return 1;
	}
	public void cancel() {
		// TODO Auto-generated method stub
		
	}
	
	
}
