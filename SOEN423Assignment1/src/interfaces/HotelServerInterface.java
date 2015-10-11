package interfaces;

import java.rmi.Remote;
import java.rmi.RemoteException;

import servers.Hotel;

public interface HotelServerInterface extends Remote {

	public Hotel getHotel() throws RemoteException;
}
