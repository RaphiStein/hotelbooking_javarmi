package interfaces;

import java.rmi.Remote;
import java.rmi.RemoteException;
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
}
