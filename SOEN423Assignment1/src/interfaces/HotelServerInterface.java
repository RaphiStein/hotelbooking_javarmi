package interfaces;

import java.rmi.Remote;
import java.rmi.RemoteException;

import server.Hotel;

public interface HotelServerInterface extends Remote {

	public Hotel getHotel() throws RemoteException;
}
