package com.coffeehouse;

import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JPanel;

public class DynamicGUI {
	
	public DynamicGUI() {
		
	}
	
	public void populateSidePanel(final JPanel insertPanel, ArrayList<ServerThread> activeThreads, final Font font) {
		
		insertPanel.removeAll();
		
		insertPanel.setPreferredSize(new Dimension(insertPanel.getWidth(), activeThreads.size() * 50));
		
		for(ServerThread thread : activeThreads) {	
			final JButton button = new JButton("to " + thread.getUser().getUsername());
			
			button.setForeground(Color.white);
			button.setBackground(insertPanel.getBackground());
			button.setFocusPainted(false);
			button.setAlignmentX(Component.CENTER_ALIGNMENT);
			button.setFont(font);
			button.setPreferredSize(new Dimension(335, 50));
			button.setBorder(BorderFactory.createEmptyBorder());
			button.setCursor(new Cursor(Cursor.HAND_CURSOR));
			
			button.addMouseListener(new MouseAdapter() {
				
				public void mouseEntered(MouseEvent e) {
					button.setBackground(Color.decode("#1c354d"));
					button.setFont(new Font(font.getName(), Font.BOLD, font.getSize()));
				}
				
				public void mouseExited(MouseEvent e) {
					button.setBackground(insertPanel.getBackground());
					button.setFont(font);
				}
				
			});
			
			insertPanel.add(button);
		}
		insertPanel.repaint();
		insertPanel.revalidate();
		
	}

}
