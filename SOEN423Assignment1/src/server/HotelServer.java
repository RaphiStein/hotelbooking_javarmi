package server;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

import Interface.HotelInterface;

public class HotelServer implements HotelInterface{
	
	private static int serverId;
	
	private Hotel hotel;
	
	public HotelServer(int id){
		serverId = id;
	}
	
	public void exportServer() throws Exception {
		// create Guest and Manager sections
		//HotelGuestServer hgs = new  HotelGuestServer();
		//HotelManagerServer hms = new HotelManagerServer();
		//Remote hgsRemote = UnicastRemoteObject.exportObject(hgs, port);
		//Remote hmsRemote = UnicastRemoteObject.exportObject(hms, port);
		
		Remote hotel = UnicastRemoteObject.exportObject(this, 2020);
		Registry registry = LocateRegistry.createRegistry(2020);
		//Registry registry = LocateRegistry.getRegistry(2020);
		registry.rebind("hotel-" + serverId, hotel);
		//registry.bind("guestRMI", hgsRemote);
		//registry.bind("managerRMI", hmsRemote);
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

	@Override
	public Guest getGuest() throws RemoteException {
		return new Guest();
	}
}
