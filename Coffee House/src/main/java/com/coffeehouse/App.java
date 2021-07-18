package com.coffeehouse;

import java.io.IOException;

public class App {
	
	public static void main(String[] args) {

		Server server = new Server(8880);
		try {
			server.activate();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
