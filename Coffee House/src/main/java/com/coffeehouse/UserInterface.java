package com.coffeehouse;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.GraphicsEnvironment;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.BindException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.plaf.basic.BasicScrollBarUI;
import javax.swing.text.BadLocationException;

public class UserInterface implements DocumentListener, ActionListener {

	DynamicGUI dynamicManager = new DynamicGUI();

	// Primary Components
	public JFrame frame;
	public JPanel mainPanel;

	// Start Up Menu Components
	public JPanel startPanel;

	public JLabel logo;

	public JPanel brandPanel;
	public JLabel firstWord;
	public JLabel secondWord;

	public JLabel editionLabel;

	public JTextField portField;
	public JLabel portLabel;
	public JLabel portError;

	public JTextField nameField;
	public JLabel nameLabel;
	public JLabel nameError;

	public JButton startButton;

	Font medium = null;
	Font extraBold = null;
	Font light = null;

	// Server Menu Components
	public JPanel serverPanel;

	public JPanel navigationPanel;
	public JPanel centerPanel;
	public JPanel sidePanel;

	public JPanel navLeft;
	public JPanel navRight;

	public JButton serverStartButton;
	public JButton serverStopButton;

	// Side Panel Components
	public JScrollPane sideScroll;
	public JPanel sideScrollPanel;

	public JPanel serverInfoPanel;
	public JLabel infoPortLabel;
	public JLabel infoNameLabel;

	public UserInterface() {
	}

	public void setView(JPanel panel) {
		mainPanel.removeAll();
		mainPanel.add(panel);
		mainPanel.repaint();
		mainPanel.revalidate();
	}

	public void startMenu() {

		UIManager.put("Button.select", new Color(0f, 0f, 0f, 0f)); // Gets rid of button select color.

		// Instantiating components
		frame = new JFrame("Coffee House (Server Edition)");
		mainPanel = new JPanel(new BorderLayout());

		startPanel = new JPanel();
		BoxLayout startLayout = new BoxLayout(startPanel, BoxLayout.Y_AXIS);
		startPanel.setLayout(startLayout);

		logo = new JLabel();

		brandPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
		firstWord = new JLabel("coffee");
		secondWord = new JLabel("house");

		editionLabel = new JLabel("Server Edition");

		portField = new JTextField();
		portLabel = new JLabel("localhost port");
		portError = new JLabel("");

		nameField = new JTextField();
		nameLabel = new JLabel("discoverable name");
		nameError = new JLabel("");

		startButton = new JButton("get started");

		// Setting appearance
		Dimension size = Toolkit.getDefaultToolkit().getScreenSize();
		frame.setSize((int) size.getWidth() - 50, (int) size.getHeight() - 50); // Setting size of frame

		mainPanel.setBackground(Color.decode("#191F24")); // other color: #1C5AC4

		startPanel.setBackground(Color.decode("#191F24"));

		try {
			logo.setIcon(new ImageIcon(ImageIO.read(new File("images/coffee-light.png"))
					.getScaledInstance(frame.getWidth() / 8, frame.getWidth() / 8, Image.SCALE_SMOOTH)));
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		logo.setAlignmentX(Component.CENTER_ALIGNMENT);
		logo.setPreferredSize(new Dimension(20, 20));
		logo.setBorder(BorderFactory.createEmptyBorder(frame.getHeight() / 10, 0, 20, 0));

		portField.setAlignmentX(Component.CENTER_ALIGNMENT);
		portField.setMaximumSize(new Dimension(frame.getWidth() / 10, frame.getHeight() / 12));
		portField.setBorder(BorderFactory.createMatteBorder(0, 0, 4, 0, Color.decode("#1C5AC4")));
		portField.setForeground(Color.white);
		portField.setBackground(Color.decode("#191F24"));
		portField.setHorizontalAlignment(JTextField.CENTER);
		portField.setColumns(5);
		portField.getDocument().addDocumentListener(this);
		GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();

		try {
			medium = Font.createFont(Font.TRUETYPE_FONT, new File("fonts/jetbrainsmono/JetBrainsMono-Medium.ttf"));
			ge.registerFont(
					Font.createFont(Font.TRUETYPE_FONT, new File("fonts/jetbrainsmono/JetBrainsMono-Medium.ttf")));

			extraBold = Font.createFont(Font.TRUETYPE_FONT,
					new File("fonts/jetbrainsmono/JetBrainsMono-ExtraBold.ttf"));
			ge.registerFont(
					Font.createFont(Font.TRUETYPE_FONT, new File("fonts/jetbrainsmono/JetBrainsMono-ExtraBold.ttf")));

			light = Font.createFont(Font.TRUETYPE_FONT, new File("fonts/jetbrainsmono/JetBrainsMono-Light.ttf"));
			ge.registerFont(
					Font.createFont(Font.TRUETYPE_FONT, new File("fonts/jetbrainsmono/JetBrainsMono-Light.ttf")));

			portField.setFont(new Font(medium.getFontName(), Font.TRUETYPE_FONT, 32));
		} catch (FontFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		firstWord.setForeground(Color.white);
		firstWord.setFont(new Font(light.getFontName(), Font.TRUETYPE_FONT, 28));
		firstWord.setAlignmentX(Component.CENTER_ALIGNMENT);

		secondWord.setForeground(Color.decode("#1C5AC4"));
		secondWord.setFont(new Font(extraBold.getFontName(), Font.TRUETYPE_FONT, 28));
		secondWord.setAlignmentX(Component.CENTER_ALIGNMENT);

		brandPanel.setBackground(Color.decode("#191F24"));
		brandPanel.setAlignmentX(Component.CENTER_ALIGNMENT);
		brandPanel.setMaximumSize(new Dimension(frame.getWidth(), firstWord.getHeight() + 50));

		editionLabel.setFont(new Font(light.getFontName(), Font.TRUETYPE_FONT, 16));
		editionLabel.setForeground(Color.white);
		editionLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

		portLabel.setForeground(Color.LIGHT_GRAY);
		portLabel.setFont(new Font(light.getFontName(), Font.TRUETYPE_FONT, 20));
		portLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

		portError.setForeground(Color.red);
		portError.setAlignmentX(Component.CENTER_ALIGNMENT);
		portError.setBorder(BorderFactory.createEmptyBorder(0, 0, 10, 0));
		portError.setFont(new Font(light.getName(), Font.TRUETYPE_FONT, 12));

		nameField.setAlignmentX(Component.CENTER_ALIGNMENT);
		nameField.setMaximumSize(new Dimension(frame.getWidth() / 5, frame.getHeight() / 12));
		nameField.setBorder(BorderFactory.createMatteBorder(0, 0, 4, 0, Color.decode("#1C5AC4")));
		nameField.setForeground(Color.white);
		nameField.setBackground(Color.decode("#191F24"));
		nameField.setHorizontalAlignment(JTextField.CENTER);
		nameField.setColumns(15);
		nameField.setFont(new Font(medium.getFontName(), Font.TRUETYPE_FONT, 32));
		nameField.getDocument().addDocumentListener(this);

		nameLabel.setForeground(Color.LIGHT_GRAY);
		nameLabel.setFont(new Font(light.getFontName(), Font.TRUETYPE_FONT, 20));
		nameLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

		nameError.setForeground(Color.red);
		nameError.setAlignmentX(Component.CENTER_ALIGNMENT);
		nameError.setBorder(BorderFactory.createEmptyBorder(0, 0, 50, 0));
		nameError.setFont(new Font(light.getName(), Font.TRUETYPE_FONT, 12));

		startButton.setFont(new Font(medium.getFontName(), Font.TRUETYPE_FONT, 28));
		startButton.setFocusPainted(false);
		startButton.setBackground(Color.white);
		startButton.setForeground(Color.decode("#191F24"));
		startButton.setBorder(BorderFactory.createEmptyBorder());
		startButton.setMaximumSize(new Dimension(frame.getWidth() / 8, frame.getHeight() / 20));
		startButton.setAlignmentX(Component.CENTER_ALIGNMENT);
		startButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
		startButton.addActionListener(this);
		startButton.addMouseListener(new MouseAdapter() {

			public void mouseEntered(MouseEvent e) {
				startButton.setForeground(Color.decode("#1C5AC4"));
			}

			public void mouseExited(MouseEvent e) {
				startButton.setForeground(Color.decode("#191F24"));
			}

		});

		// Adding components to containers
		brandPanel.add(firstWord);
		brandPanel.add(secondWord);

		startPanel.add(logo);
		startPanel.add(brandPanel);
		startPanel.add(editionLabel);
		startPanel.add(portField);
		startPanel.add(portLabel);
		startPanel.add(portError);
		startPanel.add(nameField);
		startPanel.add(nameLabel);
		startPanel.add(nameError);
		startPanel.add(startButton);

		mainPanel.add(startPanel, BorderLayout.CENTER);

		frame.add(mainPanel);

		// Necessary handles
		frame.setLocationRelativeTo(null);
		frame.setIconImage(new ImageIcon("images/coffee-icon.jpg").getImage());
		frame.setVisible(true);
		frame.setDefaultCloseOperation(frame.EXIT_ON_CLOSE);

	}

	public JPanel getServerPanel() {
		// Instantiating Components
		serverPanel = new JPanel(new BorderLayout());

		navigationPanel = new JPanel(new GridLayout(1, 2));
		centerPanel = new JPanel(new BorderLayout());
		sidePanel = new JPanel(new GridLayout(2, 1));

		navLeft = new JPanel(new FlowLayout(FlowLayout.LEFT));
		navRight = new JPanel(new FlowLayout(FlowLayout.RIGHT));

		serverStartButton = new JButton("▶️");
		serverStopButton = new JButton("■");

		sideScrollPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
		sideScroll = new JScrollPane(sideScrollPanel, ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED,
				ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

		serverInfoPanel = new JPanel();
		BoxLayout infoLayout = new BoxLayout(serverInfoPanel, BoxLayout.Y_AXIS);
		serverInfoPanel.setLayout(infoLayout);

		infoPortLabel = new JLabel("port: " + App.getServer().getPort());
		infoNameLabel = new JLabel("name: " + App.getServer().getServerName());
		
		JPanel bufferLeft = new JPanel();
		JPanel bufferRight = new JPanel();

		// Setting Appearance
		navigationPanel.setBackground(Color.decode("#15293C"));
		navigationPanel.setPreferredSize(new Dimension(frame.getWidth(), 50));

		sidePanel.setBackground(Color.decode("#0f1b26"));
		sidePanel.setPreferredSize(new Dimension(335, frame.getHeight() - navigationPanel.getHeight()));

		sideScrollPanel.setBackground(sidePanel.getBackground());

		sideScroll.getVerticalScrollBar().setUI(new BasicScrollBarUI() {
			@Override
			protected void configureScrollBarColors() {
				this.thumbColor = Color.decode("#a3a3a3");
				this.trackColor = Color.white;
			}

			@Override
			protected JButton createDecreaseButton(int orientation) {
				JButton noButton = new JButton();
				noButton.setVisible(false);
				noButton.setPreferredSize(new Dimension(0, 0));
				return noButton;
			}

			protected JButton createIncreaseButton(int orientation) {
				JButton noButton = new JButton();
				noButton.setVisible(false);
				noButton.setPreferredSize(new Dimension(0, 0));
				return noButton;
			}

		});
		sideScroll.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, Color.white));
		sideScroll.getVerticalScrollBar().setUnitIncrement(15);

		serverInfoPanel.setBackground(sidePanel.getBackground());

		infoPortLabel.setForeground(Color.white);
		infoPortLabel.setFont(new Font(medium.getName(), Font.TRUETYPE_FONT, 28));
		infoPortLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
		infoPortLabel.setBorder(BorderFactory.createEmptyBorder(50, 0, 20, 0));

		infoNameLabel.setForeground(Color.white);
		infoNameLabel.setFont(new Font(medium.getName(), Font.TRUETYPE_FONT, 28));
		infoNameLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

		centerPanel.setBackground(Color.decode("#191F24"));

		navLeft.setBackground(Color.decode("#15293C"));

		navRight.setBackground(Color.decode("#15293C"));

		serverStartButton.setForeground(Color.decode("#A2FAA3"));
		serverStartButton.setFont(new Font("temp", Font.PLAIN, 28));
		serverStartButton.setBorder(BorderFactory.createEmptyBorder(3, 0, 0, 0));
		serverStartButton.setBackground(Color.decode("#15293C"));
		serverStartButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
		serverStartButton.setFocusPainted(false);
		serverStartButton.addActionListener(this);
		serverStartButton.setToolTipText("start server");

		serverStopButton.setForeground(Color.gray);
		serverStopButton.setFont(new Font("temp", Font.PLAIN, 28));
		serverStopButton.setBorder(BorderFactory.createEmptyBorder(3, 0, 0, 20));
		serverStopButton.setBackground(Color.decode("#15293C"));
		serverStopButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
		serverStopButton.setFocusPainted(false);
		serverStopButton.addActionListener(this);
		serverStopButton.setToolTipText("stop server");
		
		bufferRight.setBackground(mainPanel.getBackground());
		bufferRight.setPreferredSize(new Dimension(30, frame.getHeight()));
		
		bufferLeft.setBackground(mainPanel.getBackground());
		bufferLeft.setPreferredSize(new Dimension(30, frame.getHeight()));

		// Adding Components to their Containers
		navRight.add(serverStartButton);
		navRight.add(serverStopButton);

		serverInfoPanel.add(infoPortLabel);
		serverInfoPanel.add(infoNameLabel);

		navigationPanel.add(navLeft);
		navigationPanel.add(navRight);

		sidePanel.add(sideScroll);
		sidePanel.add(serverInfoPanel);
		
		MessagePane pane = new MessagePane("Joan");
		
		centerPanel.add(pane, BorderLayout.CENTER);
		centerPanel.add(bufferLeft, BorderLayout.EAST);
		centerPanel.add(bufferRight, BorderLayout.WEST);

		serverPanel.add(navigationPanel, BorderLayout.NORTH);
		serverPanel.add(centerPanel, BorderLayout.CENTER);
		serverPanel.add(sidePanel, BorderLayout.WEST);

		// Finishing Statements

		return serverPanel;

	}

	public void insertUpdate(DocumentEvent e) {
		if (e.getDocument() == portField.getDocument()) {

		}
	}

	public void removeUpdate(DocumentEvent e) {
		if (e.getDocument() == portField.getDocument()) {

		}
	}

	public void changedUpdate(DocumentEvent e) {
		// TODO Auto-generated method stub

	}

	public void actionPerformed(ActionEvent e) {

		if (e.getSource() == startButton) {

			boolean canSet = true;
			int tempPort = -1;

			try { // Checking to see if the entered port is numerical
				tempPort = Integer.parseInt(portField.getText());
			} catch (NumberFormatException ex) {
				portError.setText("Invalid Port");
				canSet = false;
			}

			if (tempPort > 65535 || tempPort < 1024) { // Checking to see if the port is within valid range
				portError.setText("Invalid Port");
				canSet = false;
			} else {
				portError.setText("");
			}

			if (nameField.getText().isEmpty()) { // Checking to see if the discoverable name is empty
				nameError.setText("Invalid Name");
				canSet = false;
			} else {
				nameError.setText("");
			}

			if (canSet == true) { // if all conditions are met, move forward

				try {
					App.setServer(Integer.parseInt(portField.getText()), nameField.getText());
					setView(getServerPanel());
				} catch (Throwable t) {
					t.printStackTrace();
					portError.setText("Port may already be in use. Try another."); // sometimes a port may already be in
																					// use
					canSet = false;
				}

				if (canSet)
					setView(getServerPanel());

			}
		} else if (e.getSource() == serverStartButton) {

			serverStartButton.setForeground(Color.gray);
			serverStopButton.setForeground(Color.decode("#cc2e23"));
			
			App.getServer().activate();
			
		} else if (e.getSource() == serverStopButton) {
			
			serverStartButton.setForeground(Color.decode("#A2FAA3"));
			serverStopButton.setForeground(Color.gray);
			
			App.getServer().deactivate();
			
		}

	}

}
