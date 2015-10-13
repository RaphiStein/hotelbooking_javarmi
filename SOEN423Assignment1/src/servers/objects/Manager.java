package servers.objects;

import java.io.IOException;
import java.io.Serializable;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.Date;
import java.util.logging.FileHandler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

import interfaces.HotelHubInterface;
import interfaces.HotelInterface;
import interfaces.HotelManagerInterface;
import servers.misc.Calendar;

public class Manager implements HotelManagerInterface, Serializable {

	private static final Logger LOGGER = Logger.getLogger(Manager.class.getName());
	
	private HotelHubInterface hotelHub;
	
	/**
	 *	Serialiazation 
	 */
	private static final long serialVersionUID = -5729480726400084600L;

	public Manager() {
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
		
		// Initialize Logger
		try {
			FileHandler fileHandler = new FileHandler("./src/servers/logs/manager.txt");
			fileHandler.setFormatter(new SimpleFormatter());
			LOGGER.setUseParentHandlers(false);
			LOGGER.addHandler(fileHandler);
			LOGGER.info("LOGGER Configured for Manager");
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public String serviceReport(int hotelId, Calendar serviceDate) throws RemoteException {
		HotelInterface hotel = hotelHub.getHotelById(hotelId);
		String report = "Service Report for Hotel-" + hotelId + " on " + serviceDate.getTime() + ":\n";
		report += hotel.serviceReport(serviceDate) + "\n";
		report += "END\n-->";
		LOGGER.info(report);
		return report;
	}
	@Override
	public String printStatus(int hotelId, Calendar date) throws RemoteException {
		HotelInterface hotel = hotelHub.getHotelById(hotelId);
		return hotel.printStatus(hotelId, date);
	}

}
