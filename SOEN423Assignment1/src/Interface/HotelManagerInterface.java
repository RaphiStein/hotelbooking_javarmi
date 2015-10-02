package Interface;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface HotelManagerInterface extends Remote {

	public String sayHiToManager() throws RemoteException;
}
