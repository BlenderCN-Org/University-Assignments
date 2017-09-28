//	Matthew McMillian
//	mgm160130@utdallas.edu
//	CS 3345 - Data Structures and Algorithms

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Arrays;

import javax.swing.*;

public class MainActivity {
	
	private static JFrame mJFrame;
	private static JPanel mJPanel;
	private static JTextField mSearchBar;
	private static JButton mSearchButton;
	private static HashMap mHashMap;
	
	public static void main(String[] args) throws Exception {
		
		// Variable Setup
		mHashMap = new HashMap();
		
		// HASHING SETUP
		BufferedReader mBufferedReader = new BufferedReader(new FileReader("dictionary.txt"));
		String mLine = "";
		
		
		String key; String type; String def;
		while((mLine = mBufferedReader.readLine()) != null) {
			key = mLine.substring(0, mLine.indexOf("|")); mLine = mLine.substring(mLine.indexOf("|")+1);
			type = mLine.substring(0,mLine.indexOf("|")); mLine = mLine.substring(mLine.indexOf("|")+1);
			def = mLine;
			System.out.println(key + ", " + type + ", " + def);
			mHashMap.insert(key, type, def);
		}
		
		mBufferedReader.close();	
		
		System.out.println(Arrays.toString(mHashMap.getDoubleHashing()));
		
		// JAVA.SWING ELEMENTS
		/*
			 mJFrame = new JFrame("Dictionary.exe");
				mJFrame.setResizable(false);			
				mJFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);		
					
			mJPanel = new JPanel();
				mJPanel.setOpaque(true);
				mJPanel.setBackground(Color.GRAY);
				mJPanel.setLayout(null);			
				mJPanel.setPreferredSize(new Dimension(640,240));		
			
			mSearchBar = makeTextField(40, 80, 170, 25);
			mJPanel.add(mSearchBar);
				
			mSearchButton = makeButton(mSearchBar.getX() + mSearchBar.getWidth() + 10, mSearchBar.getY(), 85, mSearchBar.getHeight(), "SEARCH", "Arial", Font.BOLD, 12, 0);		
			mJPanel.add(mSearchButton);					
				
			mJFrame.getContentPane().add(mJPanel);		
			mJFrame.setLocationByPlatform(true);
			mJFrame.pack();
			mJFrame.setVisible(true);
		 */
	}
	
	private static JTextField makeTextField(int xpos, int ypos, int width, int height) {
		JTextField tmp = new JTextField();				
			tmp.setBounds(xpos, ypos, width, height);
			
		return tmp;
	}
	
	private static JButton makeButton(int xpos, int ypos, int width, int height, String text, String font_style, int font_type, int font_size, int listener_type) {
		JButton tmp = new JButton(text);
			tmp.setSize(width,height);
			tmp.setLocation(xpos, ypos);
			tmp.setFont(new Font(font_style, font_type, font_size));		
			tmp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
					selectionButtonPressed(listener_type);
			}
		});
			
		return tmp;
	}

	private static void selectionButtonPressed(int listener_type) {
		
		switch(listener_type) {
		
			case 0:
				break;
			default:
				break;
				
		}
		
	}

}

