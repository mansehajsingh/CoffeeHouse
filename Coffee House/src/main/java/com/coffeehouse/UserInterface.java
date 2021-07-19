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
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
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

	public JLabel logo;

	public JPanel brandPanel;
	public JLabel firstWord;
	public JLabel secondWord;
	
	public JLabel editionLabel;

	public JTextField portField;
	public JLabel portLabel;

	public JTextField nameField;
	public JLabel nameLabel;
	
	public JButton startButton;

	public UserInterface() {
	}

	public void mainMenu() {

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

		nameField = new JTextField();
		nameLabel = new JLabel("discoverable name");
		
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
		logo.setBorder(BorderFactory.createEmptyBorder(frame.getHeight()/10, 0, 20, 0));

		portField.setAlignmentX(Component.CENTER_ALIGNMENT);
		portField.setMaximumSize(new Dimension(frame.getWidth() / 10, frame.getHeight() / 12));
		portField.setBorder(BorderFactory.createMatteBorder(0, 0, 4, 0, Color.decode("#1C5AC4")));
		portField.setForeground(Color.white);
		portField.setBackground(Color.decode("#191F24"));
		portField.setHorizontalAlignment(JTextField.CENTER);
		portField.setColumns(5);
		portField.getDocument().addDocumentListener(this);
		GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();

		Font medium = null;
		Font extraBold = null;
		Font light = null;

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
		portLabel.setBorder(BorderFactory.createEmptyBorder(0, 0, 10, 0));

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
		nameLabel.setBorder(BorderFactory.createEmptyBorder(0, 0, 50, 0));
		
		startButton.setFont(new Font(medium.getFontName(), Font.TRUETYPE_FONT, 28));
		startButton.setFocusPainted(false);
		startButton.setBackground(Color.white);
		startButton.setForeground(Color.decode("#191F24"));
		startButton.setBorder(BorderFactory.createEmptyBorder());
		startButton.setMaximumSize(new Dimension(frame.getWidth()/ 8, frame.getHeight() / 20));
		startButton.setAlignmentX(Component.CENTER_ALIGNMENT);
		startButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
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
		startPanel.add(nameField);
		startPanel.add(nameLabel);
		startPanel.add(startButton);

		mainPanel.add(startPanel, BorderLayout.CENTER);

		frame.add(mainPanel);

		// Necessary handles
		frame.setLocationRelativeTo(null);
		frame.setIconImage(new ImageIcon("images/coffee-icon.jpg").getImage());
		frame.setVisible(true);
		frame.setDefaultCloseOperation(frame.EXIT_ON_CLOSE);

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

}
