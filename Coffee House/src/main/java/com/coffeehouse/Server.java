package com.coffeehouse;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
	
	private int port;
	private ServerSocket serverSocket;
	
	/**
	 * Server object.
	 * @param port the port on the localhost to which a ServerSocket will be assigned
	 */
	public Server(int port) {
		this.port = port;
		
		try {
			
			serverSocket = new ServerSocket(port);
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	/**
	 * Returns the port that the server is operating at on the localhost.
	 * @return the port number as a primitive integer
	 */
	public int getPort() {
		return port;
	}
	
	/**
	 * Returns the ServerSocket that the server is operating on.
	 * @return a ServerSocket object
	 */
	public ServerSocket getServerSocket() {
		return serverSocket; //returns ServerSocket object that this server has initialized
	}
	
	public void start() throws IOException {
		
		while(true) {
			
			Socket clientSocket = serverSocket.accept();
			
		}
		
	}

}
