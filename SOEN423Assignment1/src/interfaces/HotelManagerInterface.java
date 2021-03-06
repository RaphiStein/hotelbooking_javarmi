package interfaces;

import java.rmi.Remote;
import java.rmi.RemoteException;

import servers.misc.Calendar;

public interface HotelManagerInterface extends Remote {

	
	public String serviceReport(int hotelId, Calendar serviceDate) throws RemoteException;
	public String printStatus(int hotelId, Calendar date) throws RemoteException;
}
