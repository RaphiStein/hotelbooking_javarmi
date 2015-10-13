package interfaces;

import java.rmi.Remote;
import java.rmi.RemoteException;

import servers.misc.Calendar;
import servers.misc.Room;
import servers.misc.RoomType;
import servers.objects.Guest;

/**
 * Defines the methods of an individual Hotel RMI Server object
 * @author Raphi Stein 2015
 *
 */
public interface HotelInterface extends Remote {
	
	// ADDED
	public int getHotelId() throws RemoteException;
	public String serviceReport(Calendar date);
	
	//REQUIRED
	public String checkAvailability(RoomType roomType, Calendar checkIn, Calendar checkOut) throws RemoteException;
	public Room reserveRoom(String guestId, int hotelId, RoomType roomType, Calendar checkIn, Calendar checkOut) throws RemoteException;
	public String printStatus(int hotelId, Calendar date);
	

}
