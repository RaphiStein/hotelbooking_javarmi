package interfaces;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.Calendar;
import servers.misc.RoomType;

/**
 * Defines the methods of the Guest RMI objects. 
 * Essentially, these are the actions a guest client can execute on the server. 
 * @author Raphi
 *
 */
public interface HotelGuestInterface extends Remote{
	
	public void sayHiToGuest() throws RemoteException;
	public int getHotelId() throws RemoteException;
	public Remote testSendingRemote() throws RemoteException;
	
	// CUSTOM METHODS
	public boolean logInToHotel(int hotelId) throws RemoteException;
	// REQUIRED METHODS (i.e. reserveRooms, cancelRooms, etc)
	public boolean reserveRoom(int guestId, int hotelId, RoomType roomType, Calendar checkIn, Calendar checkOut) throws RemoteException;
	public boolean cancelRoom(int guestId, int hotelId, RoomType roomType, Calendar checkIn, Calendar checkOut) throws RemoteException;	
	public String checkAvailability(int guestId, int preferredHotelId, RoomType roomType, Calendar checkIn, Calendar checkOut) throws RemoteException;
}
