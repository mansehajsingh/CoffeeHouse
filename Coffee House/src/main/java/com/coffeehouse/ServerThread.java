package com.coffeehouse;

import java.awt.Font;
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
			
			clientOut.write(("server: " + App.getServer().getServerName() + "\nusername: ").getBytes());
			
			String line = reader.readLine();
			
			boolean first = true;
			
			while(line != null) {
				
				if(line.equalsIgnoreCase("exit")) {
					break;
				}
				
				if(first == true) {
					user = new Account(line);
					App.getUserInterface().dynamicManager.populateSidePanel(
							App.getUserInterface().sideScrollPanel, 
							App.getServer().getActiveThreads(), 
							new Font(App.getUserInterface().light.getName(), Font.TRUETYPE_FONT, 28));
				}
				System.out.println(line);
				line = reader.readLine();
				first = false;
			}
			
			App.getServer().removeThread(this);
			
			App.getUserInterface().dynamicManager.populateSidePanel(
					App.getUserInterface().sideScrollPanel, 
					App.getServer().getActiveThreads(), 
					new Font(App.getUserInterface().light.getName(), Font.TRUETYPE_FONT, 28));
			
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
