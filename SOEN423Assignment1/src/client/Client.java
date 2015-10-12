package client;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Scanner;
import java.util.regex.Pattern;

import interfaces.HotelGuestHubInterface;
import interfaces.HotelGuestInterface;
import interfaces.HotelManagerHubInterface;
import interfaces.HotelManagerInterface;
import servers.misc.RoomType;

public class Client {

	private HotelGuestHubInterface guestHub;
	private HotelManagerHubInterface managerHub;
	
	private HotelGuestInterface guest;
	private String guestId;
	private HotelManagerInterface manager;

	public Client(){};

	public static void main(String[] args) {
		Client client = new Client();

		System.out.println("Welcome Soen-423 Hotel Chain");
		System.out.println("Are you a guest or manager? (g/m)");

		String gORm = client.promptForClientType(); //guest or manager

		// Launch the requested client
		// MANAGER CLIENT SYSTEM REQUESTED
		if (gORm.equalsIgnoreCase("m")){
			System.out.println("NOT YET IMPLEMENTED");
		}
		// GUEST CLIENT SYSTEM REQUESTED
		else if (gORm.equalsIgnoreCase("g")){
			try {
				client.guestHub = (HotelGuestHubInterface) Naming.lookup("rmi://localhost:2020/guestHub");
				//client.guestId = client.promptForGuestId();
				client.guestId = "1234567890";
				client.guest = (HotelGuestInterface) client.guestHub.getGuestById(client.guestId);
				System.out.println("** You are logged in as Guest " + client.guestId);
				System.out.println("----------------------------------------");

				// LOGIN TO HOTEL
				int hotelId = client.promptForHotelId();
				System.out.println("Log into a hotel:");
				client.guest.logInToHotel(hotelId);

				int choice = client.promptForGuestAction();
				boolean success;
				switch (choice) {
				case 1:
					success = client.makeReservation();
					break;
				case 2:
					success = client.cancelReservation();
				case 3:
					success = client.checkAvailability();
				}

			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} 
		}
		else {
			System.out.println("ERROR Some strange problem has occured");
		}
	}

	private boolean checkAvailability() {
//		int preferredHotel = promptForHotelId();
//		RoomType roomType = promptForRoomType();
//		Calendar checkIn = promptForDate("Check In");
//		Calendar checkOut = promptForDate("Check Out");
		int preferredHotel = 0;
		RoomType roomType = RoomType.FAMILY;
		Calendar checkIn = new GregorianCalendar();
		checkIn.set(2015, 11, 10);
		Calendar checkOut = new GregorianCalendar();
		checkOut.set(2015, 11, 20);
		
		try {
			System.out.println(guest.checkAvailability(guestId, preferredHotel, roomType, checkIn, checkOut));
			return true;
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}

	private RoomType promptForRoomType() {
		System.out.println("What type of room would you like?");
		System.out.println("1) Single");
		System.out.println("2) Double");
		System.out.println("3) Family");
		boolean valid = false;
		int choice = 0; //default
		while (!valid){
			Scanner scanner = new Scanner(System.in);
			choice = scanner.nextInt();
			if (choice < 1 || choice > 3){
				System.out.println("Please enter 1, 2, or 3");
			}
			else {
				valid = true;
			}
		}
		switch (choice) {
		case 1:
			return RoomType.SINGLE;
		case 2:
			return RoomType.DOUBLE;
		case 3:
			return RoomType.FAMILY;
		default:
			return null;
		}
	}

	private boolean cancelReservation() {
		// TODO Auto-generated method stub
		return false;
	}

	private boolean makeReservation() {
		// TODO Auto-generated method stub
		return false;
	}

	private int promptForGuestAction() {
		System.out.println("What would you like to do?");
		System.out.println("1) Make a reservation");
		System.out.println("2) Cancel a reservtion");
		System.out.println("3) Check availabilty");
		boolean valid = false;
		int choice = 0; //default
		while (!valid){
			Scanner scanner = new Scanner(System.in);
			choice = scanner.nextInt();
			if (choice < 1 || choice > 3){
				System.out.println("Please enter 1, 2, or 3");
			}
			else {
				valid = true;
			}
		}
		return choice;
	}

	private String promptForClientType() {
		String gORm = "";
		boolean valid = false;
		while (!valid){
			Scanner scanner = new Scanner(System.in);
			gORm = scanner.next();
			if (gORm.length() == 1 && gORm.equalsIgnoreCase("g") || gORm.equalsIgnoreCase("m")){
				System.out.println(gORm);
				valid = true;
			}
			else {
				System.out.println("Not valid input. Please enter the letter g or the letter m");
			}
		}
		return gORm;
	}

	private String promptForGuestId() {
		System.out.println("What is your guest ID?");
		boolean valid = false;
		String guestId = ""; //default
		while (!valid){
			Scanner scanner = new Scanner(System.in);
			try {
				guestId = scanner.next(Pattern.compile("\\d{10}"));
				System.out.println(guestId);
				valid = true;
			}
			catch (Exception e){
				System.out.println("Your Guest ID should be 10 digits long");
			}
		}

		return guestId;
	}

	private int promptForHotelId(){
		System.out.println("Which hotel would you to select? (please enter a number)");
		boolean valid = false;
		int hotelId = 1; //default
		while (!valid){
			Scanner scanner = new Scanner(System.in);
			hotelId = scanner.nextInt();
			System.out.println(hotelId);
			valid = true;
		}

		return hotelId;
	}

	private Calendar promptForDate(String dateUse){
		Scanner scanner = new Scanner(System.in);
		int date = 1;
		int month = 1;
		int year = 2000;
		System.out.println(" | Date of " + dateUse + ":");

		// DATE (DAY OF MONTH)
		boolean dateValid = false;
		while (!dateValid){
			System.out.println(" | Please enter the day of the month:");
			date = scanner.nextInt();
			if (date > 0 && date < 32){
				dateValid = true;
			}
			else {
				System.out.println("Invalid. Please enter number between 1 and 31");
			}
		}

		// MONTH
		boolean monthValid = false;
		while (!monthValid){
			System.out.println(" | Please enter the month:");
			month = scanner.nextInt();
			if (month > 0 && month < 13){
				monthValid = true;
			}
			else {
				System.out.println("Invalid. Please enter number between 1 and 12");
			}
		}

		// YEAR	
		boolean yearValid = false;
		while (!yearValid){
			System.out.println(" | Please enter the year:");
			year = scanner.nextInt();
			if (year > 2014 && year < 2025){
				yearValid = true;
			}
			else {
				System.out.println("Invalid. Please enter a year between 2014 and 2025");
			}
		}

		Calendar calendar = new GregorianCalendar(year, month, date);
		
		return calendar;
		
	}
}
