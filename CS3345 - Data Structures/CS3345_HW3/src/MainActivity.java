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
import java.text.DecimalFormat;

import javax.swing.*;

public class MainActivity {
	
	private static JFrame mJFrame;
	private static JLayeredPane mJLayeredPane;
	
	private static JLabel BackgroundTop;
	private static JLabel BackgroundMiddle;
	private static JLabel BackgroundBottom;
	
	private static JTextPane mJWordSearchPane;
	private static JTextPane mJTextStatusPane;
	private static JTextPane mJOutputTextPane;
	
	private static JTextPane mJLabelPane;
	private static JTextPane mJLinearOutputPane;
	private static JTextPane mJQuadraticOutputPane;
	private static JTextPane mJChainingOutputPane;
	private static JTextPane mJDoubleOutputPane;
	
	private static JTextField mSearchBar;
	private static JButton mSearchButton;
	
	private static HashMap mHashMap;
	
	private static DecimalFormat df2 = new DecimalFormat("0.####");
	
	public static void main(String[] args) throws Exception {
		long startTime = System.nanoTime();
		
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
		
		long endTime = System.nanoTime();
	
		System.out.println("\nTotal Hashing Completed in: " + (endTime-startTime)/1000000 + "ms");
		
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
		
		 mJFrame = new JFrame("dictionary.exe");
			mJFrame.setResizable(false);			
			mJFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			mJFrame.setPreferredSize(new Dimension(640,370));
					
		mJLayeredPane = mJFrame.getLayeredPane();
			mJLayeredPane.setOpaque(true);
			mJLayeredPane.setBackground(Color.GRAY);
			mJLayeredPane.setLayout(null);			
			mJLayeredPane.setPreferredSize(new Dimension(640,370));	
			
		BackgroundTop = makeColoredLabel(5, 5, 625, 75, Color.LIGHT_GRAY, Color.BLACK);
		mJLayeredPane.add(BackgroundTop, new Integer(0));
			  
		BackgroundMiddle = makeColoredLabel(5, 85, 625, 120, Color.LIGHT_GRAY, Color.BLACK);
	    mJLayeredPane.add(BackgroundMiddle, new Integer(0));
	    
	    BackgroundBottom = makeColoredLabel(5, 210, 625, 125, Color.LIGHT_GRAY, Color.BLACK);
	    mJLayeredPane.add(BackgroundBottom, new Integer(0));
			
		mSearchBar = makeTextField(140, (BackgroundTop.getY()+BackgroundTop.getHeight())/2 - 10, 170, 25);
		mJLayeredPane.add(mSearchBar, new Integer(2));
		
		mJWordSearchPane = makeTextPane(10, mSearchBar.getY(), 130, mSearchBar.getHeight(), Color.BLACK, Color.LIGHT_GRAY, "Word to search for:", "Arial", Font.BOLD, 13);
		mJLayeredPane.add(mJWordSearchPane, new Integer(2));
				
		mSearchButton = makeButton(mSearchBar.getX() + mSearchBar.getWidth() + 10, mSearchBar.getY(), 85, mSearchBar.getHeight(), "SEARCH", "Arial", Font.BOLD, 12, 0);		
		mJLayeredPane.add(mSearchButton, new Integer(2));
		
		mJTextStatusPane = makeHashTextPane();
		mJLayeredPane.add(mJTextStatusPane, new Integer(3));
		
		mJLabelPane = makeTextPane(10, 220, 610, 20, Color.BLACK, Color.LIGHT_GRAY, "Data Structure\tTable Size\tLambda\tSuccess\tItems Investigated", "Arial", Font.BOLD, 12);
		mJLayeredPane.add(mJLabelPane, new Integer(3));	
		
		mJLinearOutputPane = makeTextPane(10, 240, 610, 20, Color.BLACK, Color.LIGHT_GRAY, "Linear Probing:\t" 
				+ mHashMap.LinearSize + "\t" 
				+ "λ=" + df2.format((double)(mHashMap.LinearSize)/(mHashMap.getLinearProbing().length)) + "\t"
				+ "Yes\t"
				+ mHashMap.LinearInvestigation + " Items", "Arial", Font.PLAIN, 12);
		mJLayeredPane.add(mJLinearOutputPane, new Integer(3));	
		
		mJQuadraticOutputPane = makeTextPane(10, 260, 610, 20, Color.BLACK, Color.LIGHT_GRAY, "Quadratic Probing:\t" 
				+ mHashMap.QuadraticSize + "\t" 
				+ "λ=" + df2.format((double)(mHashMap.QuadraticSize)/(mHashMap.getQuadraticProbing().length)) + "\t"
				+ "Yes\t"
				+ mHashMap.QuadraticInvestigation + " Items", "Arial", Font.PLAIN, 12);
		mJLayeredPane.add(mJQuadraticOutputPane, new Integer(3));
		
		mJChainingOutputPane = makeTextPane(10, 280, 610, 20, Color.BLACK, Color.LIGHT_GRAY, "Seperate Chaining:\t" 
				+ mHashMap.ChainingSize + "\t" 
				+ "λ=" + df2.format((double)(mHashMap.ChainingSize)/(mHashMap.getSeperateChaining().length)) + "\t"
				+ "Yes\t"
				+ mHashMap.ChainingInvestigation + " Items", "Arial", Font.PLAIN, 12);
		mJLayeredPane.add(mJChainingOutputPane, new Integer(3));
		
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
						
		mJFrame.setLocationByPlatform(true);
		mJFrame.pack();
		mJFrame.setVisible(true);

	}
	
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
				UserText = UserText.replaceAll(" ", "_");
				UserText = UserText.toLowerCase();				
				if(UserText != "" && mHashMap.getKeySet().contains(UserText)) {					
					UpdateDefinition(UserText.toUpperCase(),mHashMap.find(UserText, 1));
				} else {
					UpdateDefinition(":ERROR:","The word/phrase you inputted isnt not a word, or I cannot find the word in my dictionary. Please enter another word or try again.");
				}
				break;
			default:
				break;
				
		}		
	}
	
	private static void UpdateDefinition(String key, String def) {
		if(mJOutputTextPane != null) {
			mJOutputTextPane.setText(key + ": \n\n" + def);
		}
	}

}

