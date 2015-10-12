package interfaces;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.Calendar;

import servers.misc.RoomType;

/**
 * Defines methods of the HotelHub RMI object.
 * The HotelHub maintains a list of Hotel RMI objects and serves them to clients, given an id. 
 * 
 * @author Raphi Stein 2015
 *
 */
public interface HotelHubInterface extends Remote {

	public HotelInterface getHotelById(int id) throws RemoteException;
	public String checkAvailability(String guestId, int preferredHotelId, RoomType roomType, Calendar checkIn, Calendar checkOut) throws RemoteException;
}
