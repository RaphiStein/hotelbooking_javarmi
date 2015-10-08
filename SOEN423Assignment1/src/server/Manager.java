package server;

import java.io.Serializable;
import java.rmi.Remote;
import java.rmi.RemoteException;

import interfaces.HotelInterface;
import interfaces.HotelManagerInterface;

public class Manager implements HotelManagerInterface, Serializable {

	private HotelInterface hotel;
	/**
	 * 
	 */
	private static final long serialVersionUID = -5729480726400084600L;

	public Manager(Remote hotel) {
		this.hotel = (HotelInterface) hotel;
	}
	@Override
	public String sayHiToManager() throws RemoteException {
		return "Hi Manager";
	}
	public String callHotel(){
		try {
			return hotel.getHotelGreeting();
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "ERROR";
	}

	
}
