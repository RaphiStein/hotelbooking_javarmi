package registry;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;


/* NOTE: CURRENTLY NOT IN USE */

public class HotelSystemRegistry {
	
	private static Registry registry; 
	
	public static Registry getRegistry(){
		return registry;
	}
	public static void main(String[] args) {
		try {
			registry = LocateRegistry.createRegistry(2020);
			System.out.println("Registry is up and running");
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
