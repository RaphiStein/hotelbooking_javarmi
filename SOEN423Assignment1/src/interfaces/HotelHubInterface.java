package interfaces;

import java.rmi.Remote;
import java.rmi.RemoteException;

import servers.objects.Hotel;

/**
 * Defines methods of the HotelHub RMI object.
 * The HotelHub maintains a list of Hotel RMI objects and serves them to clients, given an id. 
 * 
 * @author Raphi Stein 2015
 *
 */
public interface HotelHubInterface extends Remote {

	public Hotel getHotelById(int id) throws RemoteException;
}
