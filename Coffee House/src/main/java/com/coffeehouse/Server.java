package com.coffeehouse;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;

/**
 * 
 * @author Mansehaj Singh
 * @since July 2021
 *
 */

public class Server {

	private int port;
	private String serverName;
	private ServerSocket serverSocket;

	private HashMap<String, ServerThread> activeThreads = new HashMap<String, ServerThread>();

	private Thread mainThread;
	
	private boolean isActive;

	/**
	 * Server object.
	 * 
	 * @param port the port on the localhost to which a ServerSocket will be
	 *             assigned
	 */
	public Server(int port, String serverName) {
		this.port = port;

		try {

			serverSocket = new ServerSocket(port);

		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	/**
	 * Returns the port that the server is operating at on the localhost.
	 * 
	 * @return the port number as a primitive integer
	 */
	public int getPort() {
		return port;
	}

	public String getServerName() {
		return serverName;
	}

	/**
	 * Returns the ServerSocket that the server is operating on.
	 * 
	 * @return a ServerSocket object
	 */
	public ServerSocket getServerSocket() {
		return serverSocket; // returns ServerSocket object that this server has initialized
	}

	public void activate() {
		
		isActive = true;

		mainThread = new Thread() {

			public void run() {

				while (true) { // infinite loop

					if (activeThreads.size() <= 30) { // we do not want to exceed a maximum of 30 threads on the server
						Socket clientSocket = null;
						try {
							clientSocket = serverSocket.accept();
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						ServerThread serverThread = new ServerThread(clientSocket);
						serverThread.start();
					}
				}

			}
		};

	}

}
