package client;

import java.util.Scanner;

public class Client {
	public static void main(String[] args) {
		System.out.println("Welcome Soen-423 Hotel Chain");
		System.out.println("Are you a guest or manager? (c/m)");
		
		String cORm = "";
		
		boolean valid = false;
		while (!valid){
			Scanner scanner = new Scanner(System.in);
			cORm = scanner.next();
			if (cORm.length() == 1 && cORm.equalsIgnoreCase("g") || cORm.equalsIgnoreCase("m")){
				System.out.println(cORm);
				valid = true;
				scanner.close();
			}
			else {
				System.out.println("Not valid input. Please enter the letter g or the letter m");
			}
				
		}
		
		// Launch the requested client
		if (cORm.equalsIgnoreCase("m")){
			
		}
		else if (cORm.equalsIgnoreCase("g")){
			Guest guestClient = new Guest();
		}
		else {
			System.out.println("ERROR Some strange problem has occured");
		}
	}
}
