package Interface;

import java.rmi.Remote;
import java.rmi.RemoteException;

import server.Guest;

public interface HotelInterface extends Remote {
	
	public Guest getGuest() throws RemoteException;
}
