package servers;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.HashMap;
import java.util.Map;

import interfaces.HotelGuestHubInterface;
import interfaces.HotelGuestInterface;
import servers.objects.Guest;

public class GuestHub implements HotelGuestHubInterface{

	private Map<String, Remote> guests;

	public GuestHub() {
		// Initialize guests Map
		guests = new HashMap<String, Remote>(10);
	}

	@Override
	public Remote getGuestById(String guestId) throws RemoteException {
		if (guests.containsKey(guestId)){
			return guests.get(guestId);
		}
		else {
			// Create new Guest, add to Map, and return
			Remote guest = new Guest(guestId);
			guests.put(guestId, guest);
			return guest;
		}
	}	
	
}
