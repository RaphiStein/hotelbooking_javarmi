package interfaces;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.Calendar;

import servers.misc.RoomType;
import servers.objects.Guest;

/**
 * Defines the methods of an individual Hotel RMI Server object
 * @author Raphi Stein 2015
 *
 */
public interface HotelInterface extends Remote {
	
	public Guest getGuest() throws RemoteException;
	public String getHotelGreeting() throws RemoteException;
	public int getHotelId() throws RemoteException;
	
	public String checkAvailability(RoomType roomType, Calendar checkIn, Calendar checkOut) throws RemoteException;
}
