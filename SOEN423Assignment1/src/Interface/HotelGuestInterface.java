package Interface;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface HotelGuestInterface extends Remote{

	public void sayHiToGuest() throws RemoteException;
	public int getHotelId() throws RemoteException;
}
