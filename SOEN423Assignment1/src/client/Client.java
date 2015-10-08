package client;

import java.util.Scanner;

public class Client {

	public Client(){};

	public static void main(String[] args) {
		Client client = new Client();

		System.out.println("Welcome Soen-423 Hotel Chain");
		System.out.println("Are you a guest or manager? (g/m)");

		String cORm = "";

		boolean valid = false;
		while (!valid){
			Scanner scanner = new Scanner(System.in);
			cORm = scanner.next();
			if (cORm.length() == 1 && cORm.equalsIgnoreCase("g") || cORm.equalsIgnoreCase("m")){
				System.out.println(cORm);
				valid = true;
			}
			else {
				System.out.println("Not valid input. Please enter the letter g or the letter m");
			}

		}

		// Launch the requested client
		if (cORm.equalsIgnoreCase("m")){
			System.out.println("NOT YET IMPLEMENTED");
		}
		else if (cORm.equalsIgnoreCase("g")){
			Guest guestClient = new Guest();
			System.out.println("Which hotel would you like to log in to?");
			int hotelId = client.promptForHotelId();
			guestClient.loginToHotel(hotelId);
			guestClient.getHotelId();
		}
		else {
			System.out.println("ERROR Some strange problem has occured");
		}
	}

	private int promptForHotelId(){
		boolean valid = false;
		int hotelId = 1; //default
		while (!valid){
			Scanner scanner = new Scanner(System.in);
			hotelId = scanner.nextInt();
			System.out.println(hotelId);
			valid = true;
			scanner.close();
		}

		return hotelId;
	}
}
