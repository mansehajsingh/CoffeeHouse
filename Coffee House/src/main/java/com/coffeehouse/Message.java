package com.coffeehouse;

public class Message {
	
	private String body;
	private Account sender;
	private Account recipient;
	
	public Message(String msgBody, Account sender, Account recipient) {
		this.body = msgBody;
		this.sender = sender;
		this.recipient = recipient;
	}
	
	public String getMessageBody() {
		return body;
	}
	
	public Account getSender() {
		return sender;
	}
	
	public Account getRecipient() {
		return recipient;
	}

}
