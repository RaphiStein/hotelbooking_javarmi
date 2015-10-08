package client;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

import interfaces.HotelGuestInterface;
import interfaces.HotelInterface;
import server.HotelServer;

public class Guest {

	private String guestId;
	
	private HotelGuestInterface hotelGuestInterface;
	
	public Guest(String id) {
		this.guestId = id;
		System.out.println("--------------------------------");
		System.out.println("  Hotel Guest Client System...  ");
		System.out.println("--------------------------------\n\n");
		
		/*
		try {
			//System.setSecurityManager(new RMISecurityManager());
			System.setSecurityManager(System.getSecurityManager());
			HotelGuestInterface hotel = (HotelGuestInterface) Naming.lookup("rmi://localhost:2020/hotelGuest1");
			
			System.out.println("saying hi...");
			//HotelGuestInterface hotelGuest = hotel.getGuest();
			//hotelGuest.sayHiToGuest();
		}
		catch (Exception e){
			e.printStackTrace();
		}
		*/
	}
	
	public HotelGuestInterface accessHotel(int hotelNumber){
		try {
			return (HotelGuestInterface) Naming.lookup("rmi://localhost:2020/hotelGuest-" + hotelNumber);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	public void loginToHotel(int hotelId){
		// If logged in to another hotel, first log out
		if (hotelGuestInterface != null){
			logoutOfHotel();
		}
		try {
			hotelGuestInterface = (HotelGuestInterface) Naming.lookup("rmi://localhost:2020/hotelGuest-" + hotelId);
			System.out.println("-- You are now logged in as guest to Hotel-" + hotelId + " --");
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NotBoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	public void logoutOfHotel(){
		hotelGuestInterface = null;
	}
	
	public void getHotelId(){
		try {
			System.out.println("The currnet hotelId is " + hotelGuestInterface.getHotelId());
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
