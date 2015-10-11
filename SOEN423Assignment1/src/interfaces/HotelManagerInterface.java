package interfaces;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.Date;

public interface HotelManagerInterface extends Remote {

	public String sayHiToManager() throws RemoteException;
	
	public void serviceReport(int hotelId, Date serviceDate) throws RemoteException;
	public void printStatus(int hotelId, Date date) throws RemoteException;
}
