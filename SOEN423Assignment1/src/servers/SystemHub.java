package servers;

import java.rmi.Remote;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

import interfaces.HotelInterface;
import servers.objects.Guest;
import servers.objects.Hotel;
import servers.objects.Manager;

/**
 * CURRENTLY NOT IN USE
 * @author Raphi Stein 2015
 *
 */
public class SystemHub{
	
	/*
	private static int serverId;
	
	private Hotel hotel;
	
	// REMOTE OBJECTS HOSTED HERE
	private Remote guest;
	private Remote manager;
	
	public SystemHub(int id){
		serverId = id;
	}
	
	public void exportServer() throws Exception {
		// create Guest and Manager sections
		//HotelGuestServer hgs = new  HotelGuestServer();
		//HotelManagerServer hms = new HotelManagerServer();
		//Remote hgsRemote = UnicastRemoteObject.exportObject(hgs, port);
		//Remote hmsRemote = UnicastRemoteObject.exportObject(hms, port);
		

		Remote hotel = UnicastRemoteObject.exportObject(new Hotel(serverId), 2020);
		guest = UnicastRemoteObject.exportObject(new Guest((HotelInterface) hotel), 2020);
		manager = UnicastRemoteObject.exportObject(new Manager((HotelInterface) hotel), 2020);
		
		Registry registry = LocateRegistry.createRegistry(2020);
		registry.rebind("hotel-" + serverId, hotel);
		registry.rebind("hotelManager-" + serverId, manager);
		registry.rebind("hotelGuest-" + serverId, guest);
	}
	
	public void launch(int serverNumber) {
		try {
			(new SystemHub(serverNumber)).exportServer();
			System.out.println("Server " + serverNumber + " is up and running");
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}
*/
}
