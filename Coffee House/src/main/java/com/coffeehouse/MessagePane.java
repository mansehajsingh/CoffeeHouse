package com.coffeehouse;

import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Insets;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.text.Highlighter;

public class MessagePane extends JPanel {

	public String username;

	public MessagePane(String username) {
		this.username = username;
		this.setBackground(Color.decode("#191F24"));
		BoxLayout layout = new BoxLayout(this, BoxLayout.Y_AXIS);
		this.setLayout(layout);

		ArrayList<Message> msgs = new ArrayList<Message>();

		msgs.add(new Message("Hi",
				new Account("Joan"), new Account("Margaret")));
		msgs.add(new Message("This is some dummy text.", new Account("Joan"),
				new Account("Margaret")));
		msgs.add(new Message("Oh... well this is some more dummy text.", new Account("Ela"),
				new Account("Margaret")));
		msgs.add(new Message("I think you should relax with the dummy text.", new Account("Joan"),
				new Account("Margaret")));
		msgs.add(new Message("Really?", new Account("Ela"),
				new Account("Margaret")));
		msgs.add(new Message("Yea", new Account("Joan"),
				new Account("Margaret")));

		this.populate(msgs);

		this.setVisible(true);

	}

	public void populate(ArrayList<Message> messages) {
		
		JPanel buffer = new JPanel();
		buffer.setBackground(Color.decode("#191F24"));
		buffer.setMaximumSize(new Dimension(App.getUserInterface().frame.getWidth(), 20));
		this.add(buffer);

		for (Message message : messages) {
			JLabel messageLabel = createMessageLabel(message.getSender().getUsername(), message.getMessageBody());
			this.add(messageLabel);
		
			buffer = new JPanel();
			buffer.setBackground(Color.decode("#191F24"));
			buffer.setMaximumSize(new Dimension(App.getUserInterface().frame.getWidth(), 30));
			this.add(buffer);
		}

	}

	public JLabel createMessageLabel(String senderName, String message) {

		JLabel messageLabel = new JLabel();
		messageLabel.setText("<html><b><i>" + senderName  + "</i></b><br>" + message + "</html>");
		messageLabel.setForeground(Color.white);
		messageLabel.setOpaque(true);
		messageLabel.setFont(new Font(App.getUserInterface().light.getName(), Font.TRUETYPE_FONT, 20));
		messageLabel.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 10));

		if (senderName.equals(username)) {
			messageLabel.setText("<html><b><i>You</i></b><br>" + message + "</html>");
			messageLabel.setBackground(Color.decode("#1C5AC4"));
			messageLabel.setAlignmentX(0);
		} else {
			messageLabel.setBackground(Color.decode("#858786"));
			messageLabel.setAlignmentX(1);
		}

		return messageLabel;

	}

}
