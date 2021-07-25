package com.coffeehouse;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
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

	private ArrayList<ServerThread> activeThreads = new ArrayList<ServerThread>();

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
		this.serverName = serverName;

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

	/**
	 * Returns the discoverable name of the server
	 * 
	 * @return the discoverable name of the server
	 */
	public String getServerName() {
		return serverName;
	}
	
	public void removeThread(ServerThread e) {
		activeThreads.remove(e);
	}
	
	public ArrayList<ServerThread> getActiveThreads() {	
		return activeThreads;
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

		Thread mainThread = new Thread() {

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
						
						activeThreads.add(serverThread);
						
						serverThread.start();
					}
				}

			}

		};
		
		mainThread.start();

	}
	
	public void deactivate() {
		mainThread.stop();
	}

}
