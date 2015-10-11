package client;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.Scanner;
import java.util.regex.Pattern;

import interfaces.HotelGuestHubInterface;
import interfaces.HotelGuestInterface;
import interfaces.HotelManagerHubInterface;

public class Client {

	private HotelGuestHubInterface guestHub;
	private HotelManagerHubInterface managerHub;
	
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
				String guestId = client.promptForGuestId();
				HotelGuestInterface guest = (HotelGuestInterface) client.guestHub.getGuestById(guestId);
				System.out.println("** You are logged in as Guest " + guestId);
				System.out.println("----------------------------------------");
				
				// LOGIN TO HOTEL
				int hotelId = client.promptForHotelId();
				guest.logInToHotel(hotelId);
				guest.sayHiToGuest();
				
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} 
		}
		else {
			System.out.println("ERROR Some strange problem has occured");
		}
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
		System.out.println("Which hotel would you like to log in to?");
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
}
