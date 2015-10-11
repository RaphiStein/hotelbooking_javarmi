package servers.objects;

import java.io.Serializable;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.Date;

import interfaces.HotelGuestInterface;
import interfaces.HotelHubInterface;
import interfaces.HotelInterface;
import servers.HotelHub;

public class Guest implements HotelGuestInterface, Serializable {

	private HotelInterface loggedInToHotel;
	private String guestId;
	/**
	 * 
	 */
	private static final long serialVersionUID = -4923801110152732444L;

	public Guest(HotelInterface hotel){
		this.loggedInToHotel = hotel;
	}
	public Guest(){
		System.out.println("Guest on Server created");
	}
	public Guest(String id){
		System.out.println("Guest " + id + "on Server created");
		this.guestId = id;
	}
	
	
	@Override
	public void sayHiToGuest() throws RemoteException {
		System.out.println(loggedInToHotel.getHotelGreeting());
	}

	@Override
	public int getHotelId() throws RemoteException {
		
		return loggedInToHotel.getHotelId();
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
	public boolean reserveRoom(int guestId, int hotelId, servers.misc.RoomType roomType, Date checkIn, Date checkOut)
			throws RemoteException {
		// TODO Auto-generated method stub
		return false;
	}
	@Override
	public boolean cancelRoom(int guestId, int hotelId, servers.misc.RoomType roomType, Date checkIn, Date checkOut)
			throws RemoteException {
		// TODO Auto-generated method stub
		return false;
	}
	@Override
	public boolean checkAvailability(int guestId, int preferredHotelId, servers.misc.RoomType roomType, Date checkIn,
			Date checkOut) throws RemoteException {
		// TODO Auto-generated method stub
		return false;
	}
	@Override
	public boolean logInToHotel(int hotelId) throws RemoteException {
		// Get Hotel from HotelHub
		try {
			HotelHubInterface hotelHub = (HotelHubInterface) Naming.lookup("rmi://localhost:2021/hotelHub");
			HotelInterface hotel = hotelHub.getHotelById(hotelId);
			loggedInToHotel = hotel;
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
