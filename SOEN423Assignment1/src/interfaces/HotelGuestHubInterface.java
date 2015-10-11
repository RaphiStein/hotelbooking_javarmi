package interfaces;

import java.rmi.Remote;
import java.rmi.RemoteException;

import servers.objects.Guest;


/**
 * Defines the methods provided by the GuestHub RMI Server object.
 * The GuestHub serves Guest RMI objects to clients or creates and sends them for 
 * first time guests (id not yet registered).
 * The GuestHub maintains a list of registered guests.
 *  
 * @author Raphi Stein
 *
 */
public interface HotelGuestHubInterface extends Remote {

	public Remote getGuestById(String guestId) throws RemoteException;
}
