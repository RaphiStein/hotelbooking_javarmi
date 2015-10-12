package servers.objects;

import java.io.Serializable;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.Calendar;

import interfaces.HotelGuestInterface;
import interfaces.HotelHubInterface;
import interfaces.HotelInterface;
import servers.HotelHub;

public class Guest implements HotelGuestInterface, Serializable {
	private HotelHubInterface hotelHub; // reference to the common HotelHub 
	private HotelInterface currentHotel; // the hotel the user is currently logged into
	private String guestId;
	/**
	 * 
	 */
	private static final long serialVersionUID = -4923801110152732444L;

	public Guest(HotelInterface hotel){
		this.currentHotel = hotel;
	}
	public Guest(){
		System.out.println("Guest on Server created");
	}
	public Guest(String id){
		System.out.println("Guest-" + id + " on Server created");
		this.guestId = id;
	}
	
	
	@Override
	public void sayHiToGuest() throws RemoteException {
		System.out.println(currentHotel.getHotelGreeting());
	}

	@Override
	public int getHotelId() throws RemoteException {
		
		return currentHotel.getHotelId();
	}

	
	public Remote testSendingRemote(){
		try {
			return UnicastRemoteObject.exportObject(new Guest(), 2020);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	@Override
	public boolean reserveRoom(int guestId, int hotelId, servers.misc.RoomType roomType, Calendar checkIn, Calendar checkOut)
			throws RemoteException {
		// TODO Auto-generated method stub
		return false;
	}
	@Override
	public boolean cancelRoom(int guestId, int hotelId, servers.misc.RoomType roomType, Calendar checkIn, Calendar checkOut)
			throws RemoteException {
		// TODO Auto-generated method stub
		return false;
	}
	@Override
	public String checkAvailability(int guestId, int preferredHotelId, servers.misc.RoomType roomType, Calendar checkIn,
			Calendar checkOut) throws RemoteException {
		String availability = hotelHub.checkAvailability(guestId, preferredHotelId, roomType, checkIn, checkOut);
		//System.out.println(availability);
		return availability;
	}
	@Override
	public boolean logInToHotel(int hotelId) throws RemoteException {
		// Get Hotel from HotelHub
		try {
			hotelHub = (HotelHubInterface) Naming.lookup("rmi://localhost:2021/hotelHub");
			currentHotel = hotelHub.getHotelById(hotelId);
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NotBoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// TODO Auto-generated method stub
		return false;
	}
	
	
	

}
