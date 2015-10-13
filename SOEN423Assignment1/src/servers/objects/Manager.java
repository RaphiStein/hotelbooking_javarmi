package servers.objects;

import java.io.Serializable;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.Date;

import interfaces.HotelHubInterface;
import interfaces.HotelInterface;
import interfaces.HotelManagerInterface;
import servers.misc.Calendar;

public class Manager implements HotelManagerInterface, Serializable {

	private HotelHubInterface hotelHub;
	/**
	 * 
	 */
	private static final long serialVersionUID = -5729480726400084600L;

	public Manager(Remote hotel) {
		// Get Hotel from HotelHub
		try {
			hotelHub = (HotelHubInterface) Naming.lookup("rmi://localhost:2021/hotelHub");
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NotBoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	@Override
	public String sayHiToManager() throws RemoteException {
		return "Hi Manager";
	}
	
	public void serviceReport(int hotelId, Calendar serviceDate) throws RemoteException {
		HotelInterface hotel = hotelHub.getHotelById(hotelId);
		System.out.println("Service Report for Hotel-" + hotelId + " on " + serviceDate.getTime() + ":\n");
		System.out.println(hotel.serviceReport(serviceDate));

	}
	@Override
	public String printStatus(int hotelId, Calendar date) throws RemoteException {
		HotelInterface hotel = hotelHub.getHotelById(hotelId);
		return hotel.printStatus(hotelId, date);
	}


}
