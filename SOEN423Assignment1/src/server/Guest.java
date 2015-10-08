package server;

import java.io.Serializable;
import java.rmi.Remote;
import java.rmi.RemoteException;

import Interface.HotelGuestInterface;

public class Guest implements HotelGuestInterface, Serializable {

	private Remote hotel;
	/**
	 * 
	 */
	private static final long serialVersionUID = -4923801110152732444L;

	public Guest(){
		
	}
	
	@Override
	public void sayHiToGuest() throws RemoteException {
		System.out.println("Hi Guest");
	}

}
