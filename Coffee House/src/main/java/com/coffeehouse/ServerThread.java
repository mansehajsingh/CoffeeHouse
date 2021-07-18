package com.coffeehouse;

import java.io.IOException;
import java.net.Socket;

/**
 * 
 * @author Mansehaj Singh
 * @since July 2021
 *
 */

public class ServerThread extends Thread {
	
	private Socket clientSocket;
	
	public ServerThread(Socket clientSocket) {
		this.clientSocket = clientSocket;
	}
	
	@Override
	public void run() {
		try {
			clientSocket.getOutputStream().write("Welcome bro".getBytes());
			clientSocket.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
