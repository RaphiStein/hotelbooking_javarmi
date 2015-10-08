package server;

import java.io.Serializable;
import java.rmi.Remote;
import java.rmi.RemoteException;

import interfaces.HotelGuestInterface;
import interfaces.HotelInterface;

public class Guest implements HotelGuestInterface, Serializable {

	private HotelInterface hotel;
	/**
	 * 
	 */
	private static final long serialVersionUID = -4923801110152732444L;

	public Guest(HotelInterface hotel){
		this.hotel = hotel;
	}
	
	@Override
	public void sayHiToGuest() throws RemoteException {
		System.out.println("Hi Guest");
	}

	@Override
	public int getHotelId() throws RemoteException {
		return hotel.getHotelId();
	}

}
