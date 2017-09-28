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
import java.util.ArrayList;
import java.util.Arrays;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import javax.swing.*;

public class MainActivity {
	
	private static JFrame mJFrame;
	private static JPanel mJPanel;
	private static JTextField mSearchBar;
	private static JButton mSearchButton;
	private static HashMap mHashMap;
	
	private static ThreadAction t1;
	private static ThreadAction t2;
	private static ThreadAction t3;
	private static ThreadAction t4;
	
	public static void main(String[] args) throws Exception {
		
		ArrayList<ThreadAction> ActionList = new ArrayList<ThreadAction>();
		ExecutorService es = Executors.newCachedThreadPool();
		
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
		
			t1 = new ThreadAction("Linear Probing: " + key, 1, mHashMap, key, type, def);
			es.execute(t1);
					
			t2 = new ThreadAction("Quadratic Probing: " + key, 2, mHashMap, key, type, def);
			es.execute(t2);
				
			t3 = new ThreadAction("Seperate Chaining: " + key, 3, mHashMap, key, type, def);
			es.execute(t3);
			
			t4 = new ThreadAction("Double Hashing: " + key, 4,  mHashMap, key, type, def);
			es.execute(t4);
		}
		
		es.shutdown();
		boolean finished = es.awaitTermination(2, TimeUnit.MINUTES);
		// All tasks should be done...
						
		System.out.println("---Investigation Complexity---");
		
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
		
		System.out.println("Total Linear-Probing Elements: " + mHashMap.getLinearProbing().length);
		System.out.println("Total Quadratic-Probing Elements: " + mHashMap.getQuadraticProbing().length);
		System.out.println("Total Seperate-Chaining Elements: " + mHashMap.getSeperateChaining().length);
		System.out.println("Total Double-Hashing Elements: " + mHashMap.getDoubleHashing().length);
		
		mBufferedReader.close();
		System.exit(0);
		
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

