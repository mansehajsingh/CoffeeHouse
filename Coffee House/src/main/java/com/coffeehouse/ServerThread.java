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

	public Socket getClientSocket() {
		return clientSocket;
	}

	@Override
	public void run() {
		try {

			BufferedReader reader = new BufferedReader(new InputStreamReader(clientIn));

			user = new Account(reader.readLine());

			App.getUserInterface().dynamicManager.populateSidePanel(App.getUserInterface().sideScrollPanel,
					App.getServer().getActiveThreads(),
					new Font(App.getUserInterface().light.getName(), Font.TRUETYPE_FONT, 28));

			while (true) {

				String[] tokens = reader.readLine().split("<<separator>>");

				int messageType = Integer.parseInt(tokens[0]);

				boolean exited = false;

				switch (messageType) {

				case 0: // 0 is the exit code
					exited = true;
					break;
				case 1: // 1 is the message code

					System.out.println(user.getUsername() + " to " + tokens[1] + ": " + tokens[2]);

					for (ServerThread activeThread : App.getServer().getActiveThreads()) {
						if (activeThread.getUser().getUsername().equals(tokens[1])) {
							activeThread.clientOut.write((user.getUsername() + ": " + tokens[2] + "\n").getBytes());
						}
					}

					break;
				default:
					break;

				}

				if (exited == true)
					break;

			}

			App.getServer().removeThread(this);

			App.getUserInterface().dynamicManager.populateSidePanel(App.getUserInterface().sideScrollPanel,
					App.getServer().getActiveThreads(),
					new Font(App.getUserInterface().light.getName(), Font.TRUETYPE_FONT, 28));

		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
