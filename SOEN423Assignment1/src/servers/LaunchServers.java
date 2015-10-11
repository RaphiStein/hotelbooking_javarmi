package servers;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

import interfaces.HotelGuestHubInterface;
import interfaces.HotelManagerHubInterface;

public class LaunchServers {
	private static final int NUMBER_OF_SERVERS = 1;
	
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
		
		/*
		for (int i = 0; i < NUMBER_OF_SERVERS; i++) {
			SystemHub hotelServer = new SystemHub(i);
			hotelServer.launch(i);
		}
		*/
	}
}
