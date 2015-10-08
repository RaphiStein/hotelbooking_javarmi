package interfaces;

import java.rmi.Remote;
import java.rmi.RemoteException;

import server.Guest;

public interface HotelInterface extends Remote {
	
	public Guest getGuest() throws RemoteException;
	public String getHotelGreeting() throws RemoteException;
	public int getHotelId() throws RemoteException;
}