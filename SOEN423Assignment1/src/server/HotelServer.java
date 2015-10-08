package server;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

import Interface.HotelInterface;

public class HotelServer{
	
	private static int serverId;
	
	private Hotel hotel;
	
	// REMOTE OBJECTS HOSTED HERE
	private Remote guest;
	private Remote manager;
	
	public HotelServer(int id){
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
			(new HotelServer(serverNumber)).exportServer();
			System.out.println("Server " + serverNumber + " is up and running");
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}

}
