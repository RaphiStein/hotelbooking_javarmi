package server;

import java.io.Serializable;
import java.rmi.RemoteException;

import Interface.HotelManagerInterface;

public class Manager implements HotelManagerInterface, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5729480726400084600L;

	@Override
	public String sayHiToManager() throws RemoteException {
		return "Hi Manager";
	}

	
}
