package servers;

import java.rmi.RemoteException;

import interfaces.HotelManagerHubInterface;
import servers.objects.Manager;

public class ManagerHub implements HotelManagerHubInterface{

	private Manager manager;

	@Override
	public Manager getManager() throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}
}
