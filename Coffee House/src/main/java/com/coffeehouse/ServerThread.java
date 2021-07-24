package com.coffeehouse;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.Socket;

/**
 * 
 * @author Mansehaj Singh
 * @since July 2021
 *
 */

public class ServerThread extends Thread {

	private Socket clientSocket;
	private Account user;

	private InputStream clientIn;
	private OutputStream clientOut;

	public ServerThread(Socket clientSocket) {
		this.clientSocket = clientSocket;

		try {
			this.clientIn = clientSocket.getInputStream();
			this.clientOut = clientSocket.getOutputStream();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
	
	public Account getUser() {
		return this.user;
	}

	@Override
	public void run() {
		try {

			BufferedReader reader = new BufferedReader(new InputStreamReader(clientIn));

			String line;

			while ((line = reader.readLine()) != null) {
				
				String[] tokens = line.split("<<separator>>");
				
			}

			clientSocket.close();
			App.getServer().removeThread(this);
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
