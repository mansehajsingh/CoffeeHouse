package com.coffeehouse;

import java.io.IOException;

public class App {
	
	private static UserInterface UI;
	private static Server server;
	
	public static void main(String[] args) {

		UI = new UserInterface();
		UI.startMenu();
		
		server = null;

	}
	
	public static void setServer(int serverPort, String serverName) {
		server = new Server(serverPort, serverName);
	}
	
	public static Server getServer() {
		return server;
	}

}
