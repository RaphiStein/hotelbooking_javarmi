package server;

public class LaunchServers {
	private static final int NUMBER_OF_SERVERS = 1;
	
	public static void main(String[] args) {
		for (int i = 0; i < NUMBER_OF_SERVERS; i++) {
			HotelServer hotelServer = new HotelServer(i);
			hotelServer.launch(i);
		}
	}
}
