//	Matthew McMillian
//	mgm160130@utdallas.edu
//	CS 3345 - Data Structures and Algorithms

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.text.DecimalFormat;
import javax.swing.*;

public class MainActivity {
	
	// Frame and Panels for GUI
	private static JFrame mJFrame;
	private static JLayeredPane mJLayeredPane;
	
	// Grey Background Panels
	private static JLabel BackgroundTop;
	private static JLabel BackgroundMiddle;
	private static JLabel BackgroundBottom;
	
	// TextBoxes and Output Areas
	private static JTextPane mJWordSearchPane;
	private static JTextPane mJTextStatusPane;
	private static JTextPane mJOutputTextPane;
	
	// Data Pane(s)
	private static JTextPane mJLabelPane;
	private static JTextPane mJLinearOutputPane;
	private static JTextPane mJQuadraticOutputPane;
	private static JTextPane mJChainingOutputPane;
	private static JTextPane mJDoubleOutputPane;
	
	// User Input
	private static JTextField mSearchBar;
	private static JButton mSearchButton;
	
	// The Hash Map
	private static HashMap mHashMap;
	
	// Formatting for the Data Output
	private static DecimalFormat df2 = new DecimalFormat("0.####");
	
	public static void main(String[] args) throws Exception {
		long startTime = System.nanoTime();
		
		// Variable Setup
		mHashMap = new HashMap();
		
		// HASHING SETUP		
		InputStream input = MainActivity.class.getResourceAsStream("/dictionary.txt");		
		BufferedReader mBufferedReader = new BufferedReader(new InputStreamReader(input));
		String mLine = "";
		
		/* Pareses through file and hashes each word by
		 *	a. Linear Probing
		 *	b. Quadratic Probing
		 *	c. Seperate Chaining
		 *	d. Double Hashing
		*/
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
		
		long endTime = System.nanoTime();
	
		System.out.println("\nTotal Hashing Completed in: " + (endTime-startTime)/1000000 + "ms");
		
		// All tasks should be done by this point
		
		// For Additional Information, outprints Different data comparrisons
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
		
		System.out.println("Total Linear-Probing Elements: " + mHashMap.LinearSize + "/" + mHashMap.getLinearProbing().length);
		System.out.println("Total Quadratic-Probing Elements: " + mHashMap.QuadraticSize + "/" + mHashMap.getQuadraticProbing().length);
		System.out.println("Total Seperate-Chaining Elements: " + mHashMap.ChainingSize + "/" + mHashMap.getSeperateChaining().length);
		System.out.println("Total Double-Hashing Elements: " + mHashMap.DoubleSize + "/" + mHashMap.getDoubleHashing().length);
		
		mBufferedReader.close();		

		// JAVA.SWING ELEMENTS	
		
		// Main Windows, titled "dictionary.exe"
		 mJFrame = new JFrame("dictionary.exe");
			mJFrame.setResizable(false);			
			mJFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			mJFrame.setPreferredSize(new Dimension(640,370));
		
		// Pane to fit all of the elemnets inside
		mJLayeredPane = mJFrame.getLayeredPane();
			mJLayeredPane.setOpaque(true);
			mJLayeredPane.setBackground(Color.GRAY);
			mJLayeredPane.setLayout(null);			
			mJLayeredPane.setPreferredSize(new Dimension(640,370));	
		
		// TOP Grey Background
		BackgroundTop = makeColoredLabel(5, 5, 625, 75, Color.LIGHT_GRAY, Color.BLACK);
		mJLayeredPane.add(BackgroundTop, new Integer(0));
		
		// MIDDLE Grey Background
		BackgroundMiddle = makeColoredLabel(5, 85, 625, 120, Color.LIGHT_GRAY, Color.BLACK);
	    mJLayeredPane.add(BackgroundMiddle, new Integer(0));
	    
	    // BOTTOM Grey Background
	    BackgroundBottom = makeColoredLabel(5, 210, 625, 125, Color.LIGHT_GRAY, Color.BLACK);
	    mJLayeredPane.add(BackgroundBottom, new Integer(0));
		
	    // Searchbar for user to input data. User can also press 'ENTER' to search
		mSearchBar = makeTextField(140, (BackgroundTop.getY()+BackgroundTop.getHeight())/2 - 10, 170, 25);
		mJLayeredPane.add(mSearchBar, new Integer(2));
		
		// Pane that displays what word you ware searching for
		mJWordSearchPane = makeTextPane(10, mSearchBar.getY(), 130, mSearchBar.getHeight(), Color.BLACK, Color.LIGHT_GRAY, "Word to search for:", "Arial", Font.BOLD, 13);
		mJLayeredPane.add(mJWordSearchPane, new Integer(2));
		
		// Button that when pressed, searches for the word in the searchbox
		mSearchButton = makeButton(mSearchBar.getX() + mSearchBar.getWidth() + 10, mSearchBar.getY(), 85, mSearchBar.getHeight(), "SEARCH", "Arial", Font.BOLD, 12, 0);		
		mJLayeredPane.add(mSearchButton, new Integer(2));
		
		// Status Pane that detects if hashmaps have been loaded correctly
		mJTextStatusPane = makeHashTextPane();
		mJLayeredPane.add(mJTextStatusPane, new Integer(3));
		
		// Data Ouput on 3rd Pane
		mJLabelPane = makeTextPane(10, 220, 610, 20, Color.BLACK, Color.LIGHT_GRAY, "Data Structure\tTable Size\tLambda\tSuccess\tItems Investigated", "Arial", Font.BOLD, 12);
		mJLayeredPane.add(mJLabelPane, new Integer(3));	
		
		// Linear Probing Data Output
		mJLinearOutputPane = makeTextPane(10, 240, 610, 20, Color.BLACK, Color.LIGHT_GRAY, "Linear Probing:\t" 
				+ mHashMap.LinearSize + "\t" 
				+ "λ=" + df2.format((double)(mHashMap.LinearSize)/(mHashMap.getLinearProbing().length)) + "\t"
				+ "Yes\t"
				+ mHashMap.LinearInvestigation + " Items", "Arial", Font.PLAIN, 12);
		mJLayeredPane.add(mJLinearOutputPane, new Integer(3));	
		
		// Quadratic Probing Data Output
		mJQuadraticOutputPane = makeTextPane(10, 260, 610, 20, Color.BLACK, Color.LIGHT_GRAY, "Quadratic Probing:\t" 
				+ mHashMap.QuadraticSize + "\t" 
				+ "λ=" + df2.format((double)(mHashMap.QuadraticSize)/(mHashMap.getQuadraticProbing().length)) + "\t"
				+ "Yes\t"
				+ mHashMap.QuadraticInvestigation + " Items", "Arial", Font.PLAIN, 12);
		mJLayeredPane.add(mJQuadraticOutputPane, new Integer(3));
		
		// Seperate-Chaining Data Output
		mJChainingOutputPane = makeTextPane(10, 280, 610, 20, Color.BLACK, Color.LIGHT_GRAY, "Seperate Chaining:\t" 
				+ mHashMap.ChainingSize + "\t" 
				+ "λ=" + df2.format((double)(mHashMap.ChainingSize)/(mHashMap.getSeperateChaining().length)) + "\t"
				+ "Yes\t"
				+ mHashMap.ChainingInvestigation + " Items", "Arial", Font.PLAIN, 12);
		mJLayeredPane.add(mJChainingOutputPane, new Integer(3));
		
		// Double Hashing Data Output
		mJDoubleOutputPane = makeTextPane(10, 300, 610, 20, Color.BLACK, Color.LIGHT_GRAY, "Double Hashing:\t" 
				+ mHashMap.DoubleSize + "\t" 
				+ "λ=" + df2.format((double)(mHashMap.DoubleSize)/(mHashMap.getDoubleHashing().length)) + "\t"
				+ "Yes\t"
				+ mHashMap.DoubleInvestigation + " Items", "Arial", Font.PLAIN, 12);
		mJLayeredPane.add(mJDoubleOutputPane, new Integer(3));
				
		mJOutputTextPane = new JTextPane();	
		mJOutputTextPane.setBackground(Color.LIGHT_GRAY);
		mJOutputTextPane.setFont(new Font("Arial", Font.BOLD, 12));
		mJOutputTextPane.setBounds(15, 105, 605, 90);
		mJOutputTextPane.setEditable(false);			
		
		mJLayeredPane.add(mJOutputTextPane, new Integer(3));
		
		// Final Frame Settings
		mJFrame.setLocationByPlatform(true);
		mJFrame.pack();
		mJFrame.setVisible(true);

	}
	
	// Creates a JTextPane with specific instructions
	private static JTextPane makeHashTextPane() {
		JTextPane tmp = new JTextPane();
			if(mHashMap.LinearSize >= 155285) {
				tmp.setForeground(Color.GREEN);
				tmp.setText("HASHMAPS LOADED");
			}
			else {
				tmp.setForeground(Color.RED);
				tmp.setText("HASHMAPS FAILED");
			}
			tmp.setBackground(Color.BLACK);
			tmp.setFont(new Font("Arial", Font.BOLD, 12));
			tmp.setBounds(500, 10, 122, 20);
			tmp.setEditable(false);			
		return tmp;
	}
	
	// Creates a JTextPane with specific instructions
	private static JTextPane makeTextPane(int xpos, int ypos, int width, int height, Color foregroundColor, Color backgroundColor, String text, String font_style, int font_type, int font_size) {
		JTextPane tmp = new JTextPane();
			tmp.setBounds(xpos, ypos, width, height);
			tmp.setForeground(foregroundColor);
			tmp.setBackground(backgroundColor);
			tmp.setText(text);
			tmp.setFont(new Font(font_style, font_type, font_size));
			tmp.setEditable(false);
		return tmp;
	}
	
	// Creates a JLabel with specific instructions
	private static JLabel makeColoredLabel(int xpos, int ypos, int width, int height, Color backgroundColor, Color borderColor) {
		JLabel label = new JLabel();
			label.setOpaque(true);
	        label.setBackground(backgroundColor);
	        label.setForeground(borderColor);
	        label.setBorder(BorderFactory.createLineBorder(borderColor));
	        label.setBounds(xpos, ypos, width, height);
	        
		return label;
	}
	
	// Creates a JTextField with specific instructions
	private static JTextField makeTextField(int xpos, int ypos, int width, int height) {
		JTextField tmp = new JTextField();				
			tmp.setBounds(xpos, ypos, width, height);
			tmp.addKeyListener(new KeyListener() {

				@Override
				public void keyTyped(KeyEvent e) {
					// Do Nothing					
				}

				@Override
				public void keyPressed(KeyEvent e) {					
					if(e.getKeyCode() == KeyEvent.VK_ENTER) {
						selectionButtonPressed(0);
					}
				}

				@Override
				public void keyReleased(KeyEvent e) {
					// Do Nothing				
				}
				
			});
			
		return tmp;
	}
	
	// Creates a JButton with specific instructions
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

	// Determines what happens when the button is pressed
	private static void selectionButtonPressed(int listener_type) {		
		switch(listener_type) {
			case 0:
				String UserText = mSearchBar.getText();	
				UserText = UserText.replaceAll(" ", "_");
				UserText = UserText.toLowerCase();				
				if(UserText != "" && mHashMap.getKeySet().contains(UserText)) {					
					UpdateDefinition(UserText.toUpperCase(),mHashMap.find(UserText, 3));
					mSearchBar.setText("");
				} else {
					UpdateDefinition(":ERROR:","The word/phrase you inputted isnt not a word, or I cannot find the word in my dictionary. Please enter another word or try again.");
				}
				break;
			default:
				break;
				
		}		
	}
	
	// Updates the definition when a new phrase is searched
	private static void UpdateDefinition(String key, String def) {
		if(mJOutputTextPane != null) {
			mJOutputTextPane.setText(key + ": \n\n" + def);
		}
	}

}

