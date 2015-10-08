package client;

import java.rmi.Naming;

import Interface.HotelGuestInterface;
import Interface.HotelInterface;
import server.HotelServer;

public class Guest {

	public Guest() {
		System.out.println("--------------------------------");
		System.out.println("  Hotel Guest Client System...  ");
		System.out.println("--------------------------------\n\n");
		
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
	}
	
	public HotelGuestInterface accessHotel(int hotelNumber){
		try {
			return (HotelGuestInterface) Naming.lookup("rmi://localhost:2020/hotelGuest-" + hotelNumber);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

}
