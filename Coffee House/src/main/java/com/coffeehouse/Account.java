package com.coffeehouse;

import java.io.Serializable;
import java.util.ArrayList;

public class Account implements Serializable {

	private String username;
	private ArrayList<ArrayList<Message>> messageLogs;

	public Account(String username) {
		this.username = username;
		this.messageLogs = new ArrayList<ArrayList<Message>>();
	}

	public String getUsername() {
		return username;
	}

	public ArrayList<ArrayList<Message>> getMessageLogs() {
		return messageLogs;
	}

}
