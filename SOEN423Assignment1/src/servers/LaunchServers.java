package servers;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

import interfaces.HotelGuestHubInterface;
import interfaces.HotelHubInterface;
import interfaces.HotelManagerHubInterface;

public class LaunchServers {
	private static final int NUMBER_OF_HOTELS = 1;
	private static final boolean SHOULD_POPULATE = true;
	
	public static void main(String[] args) {
		// Launch Hubs
		System.out.println("Launching Remote objects: GuestHub and ManagerHub ");
		try {
			HotelGuestHubInterface guestHub = (HotelGuestHubInterface) UnicastRemoteObject.exportObject(new GuestHub(), 2020);
			HotelManagerHubInterface managerHub = (HotelManagerHubInterface) UnicastRemoteObject.exportObject(new ManagerHub(), 2020);
			
			Registry registry = LocateRegistry.createRegistry(2020);
			registry.rebind("guestHub", guestHub);
			registry.rebind("managerHub", managerHub);
			
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		// Launch HotelHub. This Remote object is not intended to be accessed by clients directly, but rather by the GuestHub and ManagerHub
		try {
			HotelHubInterface hotelHub = (HotelHubInterface) UnicastRemoteObject.exportObject(new HotelHub(NUMBER_OF_HOTELS, SHOULD_POPULATE), 2021);
			Registry registry = LocateRegistry.createRegistry(2021);
			registry.rebind("hotelHub", hotelHub);
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		System.out.println("Hubs Created.");
	}
}
