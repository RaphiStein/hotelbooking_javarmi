package client;

import java.rmi.Naming;

import Interface.HotelGuestInterface;
import Interface.HotelInterface;
import server.HotelServer;

public class Guest {

	public Guest() {
		System.out.println("Hotel Guest Client System...\n\n");
		
		try {
			//System.setSecurityManager(new RMISecurityManager());
			System.setSecurityManager(System.getSecurityManager());
			HotelInterface hotel = (HotelInterface) Naming.lookup("rmi://localhost:2020/hotel1");
			
			System.out.println("saying hi...");
			HotelGuestInterface hotelGuest = hotel.getGuest();
			hotelGuest.sayHiToGuest();
		}
		catch (Exception e){
			e.printStackTrace();
		}
	}

}
