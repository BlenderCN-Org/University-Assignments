//	Matthew McMillian
//	mgm160130@utdallas.edu
//	CS 3345 - Data Structures and Algorithms

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import javax.swing.*;

public class MainActivity {
	
	private static JFrame mJFrame;
	private static JLayeredPane mJLayeredPane;
	
	private static JLabel BackgroundTop;
	private static JLabel BackgroundBottom;
	
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
		
			mHashMap.insertLinearProbing(key, type, def);
			mHashMap.insertQuadraticProbing(key, type, def);
			mHashMap.insertSeperateChaining(key, type, def);
			mHashMap.insertDoubleHashing(key, type, def);
		}
		// All tasks should be done...
		
		System.out.println("\n--Investigation Complexity--");
		
		System.out.println("avg. Linear-Probing Investigations: " + mHashMap.LinearInvestigation);
		System.out.println("avg. Quadratic-Probing Investigations: " + mHashMap.QuadraticInvestigation);
		System.out.println("avg. Seperate-Chaining Investigations: " + mHashMap.ChainingInvestigation);
		System.out.println("avg. Double-Hashing Investigations: " + mHashMap.DoubleInvestigation);
		
		System.out.println("\n--Time Complexity--");
		
		System.out.println("Total Linear-Probing Insertion Time: " + mHashMap.LinearTime + "ms");
		System.out.println("Total Quadratic-Probing Insertion Time: " + mHashMap.QuadraticTime + "ms");
		System.out.println("Total Seperate-Chaining Insertion Time: " + mHashMap.ChainingTime + "ms");
		System.out.println("Total Double-Hashing Insertion Time: " + mHashMap.DoubleTime + "ms");
		
		System.out.println("\n--Space Complexity--");
		
		System.out.println("Total Linear-Probing Elements: " + mHashMap.LinearSize);
		System.out.println("Total Quadratic-Probing Elements: " + mHashMap.QuadraticSize);
		System.out.println("Total Seperate-Chaining Elements: " + mHashMap.ChainingSize);
		System.out.println("Total Double-Hashing Elements: " + mHashMap.DoubleSize);
		
		mBufferedReader.close();
		
		// JAVA.SWING ELEMENTS	
		
		 mJFrame = new JFrame("Dictionary.exe");
			mJFrame.setResizable(false);			
			mJFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			mJFrame.setPreferredSize(new Dimension(640,240));
					
		mJLayeredPane = mJFrame.getLayeredPane();
			mJLayeredPane.setOpaque(true);
			mJLayeredPane.setBackground(Color.GRAY);
			mJLayeredPane.setLayout(null);			
			mJLayeredPane.setPreferredSize(new Dimension(640,240));	
			
		BackgroundTop = makeColoredLabel(5, 5, 625, 75, Color.LIGHT_GRAY, Color.BLACK);
		mJLayeredPane.add(BackgroundTop, new Integer(0));
			  
		BackgroundBottom = makeColoredLabel(5, 85, 625, 105, Color.LIGHT_GRAY, Color.BLACK);
	    mJLayeredPane.add(BackgroundBottom, new Integer(0));
			
		mSearchBar = makeTextField(40, (BackgroundTop.getY()+BackgroundTop.getHeight())/2 - 10, 170, 25);
		mJLayeredPane.add(mSearchBar, new Integer(2));
				
		mSearchButton = makeButton(mSearchBar.getX() + mSearchBar.getWidth() + 10, mSearchBar.getY(), 85, mSearchBar.getHeight(), "SEARCH", "Arial", Font.BOLD, 12, 0);		
		mJLayeredPane.add(mSearchButton, new Integer(2));
		
		JTextPane tmp = new JTextPane();
		if(mHashMap.LinearSize == 155285) {
			tmp.setForeground(Color.GREEN);
			tmp.setText("HASHMAPS LOADED");
		}
		else {
			tmp.setForeground(Color.RED);
			tmp.setText("HASHMAPS FAILED");
		}
		tmp.setBackground(Color.LIGHT_GRAY);
		tmp.setFont(new Font("Arial", Font.BOLD, 12));
		tmp.setBounds(500, 10, 125, 20);
		mJLayeredPane.add(tmp, new Integer(3));
						
		mJFrame.setLocationByPlatform(true);
		mJFrame.pack();
		mJFrame.setVisible(true);

	}
	
	private static JLabel makeColoredLabel(int xpos, int ypos, int width, int height, Color backgroundColor, Color borderColor) {
		JLabel label = new JLabel();
			label.setOpaque(true);
	        label.setBackground(backgroundColor);
	        label.setForeground(borderColor);
	        label.setBorder(BorderFactory.createLineBorder(borderColor));
	        label.setBounds(xpos, ypos, width, height);
	        
		return label;
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
				String UserText = mSearchBar.getText();
				// Search Here
				break;
			default:
				break;
				
		}
		
	}

}

