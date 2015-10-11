package servers.objects;

import java.rmi.RemoteException;

import interfaces.HotelInterface;
import servers.misc.RoomList;

public class Hotel implements HotelInterface{

	private int hotelId;
	private RoomList roomList;
	
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
