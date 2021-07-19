package com.coffeehouse;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.GraphicsEnvironment;
import java.awt.Image;
import java.awt.Toolkit;
import java.io.File;
import java.io.IOException;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.BadLocationException;

public class UserInterface implements DocumentListener {
	
	// Primary Components
	public JFrame frame;
	public JPanel mainPanel;
	
	// Start Up Menu Components
	public JPanel startPanel;
	
	public JTextField portField;
	public JLabel portLabel;
	
	public UserInterface() {
	}
	
	public void mainMenu() {
		
		UIManager.put("Button.select", new Color(0f, 0f, 0f, 0f)); // Gets rid of button select color.
		
		// Instantiating components
		frame = new JFrame("Coffee House (Server Edition)");
		mainPanel = new JPanel(new BorderLayout());
		
		startPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
		
		portField = new JTextField();
		portLabel = new JLabel("port");
		
		// Setting appearance
		Dimension size = Toolkit.getDefaultToolkit().getScreenSize();
		frame.setSize((int) size.getWidth() - 50, (int) size.getHeight() - 50); // Setting size of frame
		
		mainPanel.setBackground(Color.decode("#191F24")); // other color: #1C5AC4
		
		startPanel.setBackground(Color.decode("#191F24"));
		
		portField.setPreferredSize(new Dimension(frame.getWidth()/10, frame.getHeight()/10));
		portField.setBorder(BorderFactory.createMatteBorder(0, 0, 4, 0, Color.decode("#1C5AC4")));
		portField.setForeground(Color.white);
		portField.setBackground(Color.decode("#191F24"));
		portField.setHorizontalAlignment(JTextField.CENTER);
		portField.setColumns(5);
		portField.getDocument().addDocumentListener(this);
		GraphicsEnvironment ge = 
		         GraphicsEnvironment.getLocalGraphicsEnvironment();
		
		Font jetBrainsMonoMed = null;
		
		try {
			jetBrainsMonoMed = Font.createFont(Font.TRUETYPE_FONT, new File("fonts/jetbrainsmono/JetBrainsMono-Medium.ttf"));
			ge.registerFont(Font.createFont(Font.TRUETYPE_FONT, new File("fonts/jetbrainsmono/JetBrainsMono-Medium.ttf")));
			portField.setFont(new Font(jetBrainsMonoMed.getFontName(), Font.TRUETYPE_FONT, 32));
		} catch (FontFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		portLabel.setForeground(Color.white);
		portLabel.setFont(new Font(jetBrainsMonoMed.getFontName(), Font.TRUETYPE_FONT, 28));
		
		// Adding components to containers
		startPanel.add(portField);
		startPanel.add(portLabel);
		
		mainPanel.add(startPanel, BorderLayout.CENTER);
		
		frame.add(mainPanel);
		
		// Necessary handles
		frame.setLocationRelativeTo(null);
		frame.setIconImage(new ImageIcon("images/coffee-light.png").getImage());
		frame.setVisible(true);
		frame.setDefaultCloseOperation(frame.EXIT_ON_CLOSE);
		
	}

	public void insertUpdate(DocumentEvent e) {
		if(e.getDocument() == portField.getDocument()) {
			
		}
	}

	public void removeUpdate(DocumentEvent e) {
		if(e.getDocument() == portField.getDocument()) {

		}
	}

	public void changedUpdate(DocumentEvent e) {
		// TODO Auto-generated method stub
		
	}
	
}
