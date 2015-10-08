package server;

import java.rmi.RemoteException;

import interfaces.HotelInterface;

public class Hotel implements HotelInterface{

	private int hotelId;
	
	public Hotel(int id){
		this.hotelId = id;
	}

	@Override
	public Guest getGuest() throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}
	
	public String getHotelGreeting(){
		return "Ola!";
	}

	@Override
	public int getHotelId() throws RemoteException {
		return hotelId;
	}
}
