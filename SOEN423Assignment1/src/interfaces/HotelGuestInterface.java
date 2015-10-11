package interfaces;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.Date;

import server.misc.RoomType;

public interface HotelGuestInterface extends Remote{
	
	public void sayHiToGuest() throws RemoteException;
	public int getHotelId() throws RemoteException;
	public Remote testSendingRemote() throws RemoteException;
	
	// ACTUAL METHODS (i.e. reserveRooms, cancelRooms, etc)
	boolean reserveRoom(int guestId, int hotelId, RoomType roomType, Date checkIn, Date checkOut) throws RemoteException;
	boolean cancelRoom(int guestId, int hotelId, RoomType roomType, Date checkIn, Date checkOut) throws RemoteException;	
	boolean checkAvailability(int guestId, int preferredHotelId, RoomType roomType, Date checkIn, Date checkOut) throws RemoteException;
}
