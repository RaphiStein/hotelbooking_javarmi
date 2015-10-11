package interfaces;

import java.rmi.Remote;
import java.rmi.RemoteException;

import servers.objects.Manager;

/**
 * Defines the methods of the ManagerHub RMI Server. Essentially, this interface and
 * implementation serves a Manager object to a manager client. Typically there is only
 * one manager object in the system, but for the sake of organization and consistency
 * the hub was created to serve it to the client.
 * 
 * @author Raphi Stein 2015
 *
 */
public interface HotelManagerHubInterface extends Remote {

	public Manager getManager() throws RemoteException;
}
