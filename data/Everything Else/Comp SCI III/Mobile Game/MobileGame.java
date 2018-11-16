import java.awt.*;
import java.awt.image.*;
import java.util.*;
import java.io.*;
import java.lang.*;
import java.lang.System.*;
import javax.swing.*;

public class MobileGame extends JFrame implements Runnable
{
	public static int width = 480;
	public static int height = 800;
	private boolean isOn; // Is the game on?
	Controls c;

	public Color bgColor = new Color(118,210,255); // Light blue

	Thread gthread;

	BufferedImage img = new BufferedImage(width,height,BufferedImage.TYPE_INT_RGB); // Screenshots the app Width/Height so it doenst flicker

    public MobileGame()
    {
		setTitle("Mobile Game"); // Title
		setSize(width,height); // Size
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Click on X it actaully closes
		setLocationRelativeTo(null); // Centers it
		setVisible(true); //Makes it Visible
		setResizable(false); // Not resizeable

		c = new Controls(this);
		addMouseListener(c);
    }

    public void run()
    {
    	while(isOn)
    	{
    		try
    		{
				Update();
				Render();
    		}
    		catch(Exception e)
    		{
    			e.printStackTrace(); // Prints out whats wrong
    		}
    	}
    }

    public synchronized void start()
    {
		gthread = new Thread(this); // Create's thread
		isOn = true;

		gthread.start(); // Starts the game
    }

     public synchronized void stop()
    {
		isOn = false; // Turns it off
		System.exit(0); // Closes the game
    }

    public void Update()
    {

    }

    public void Render()
    {
		BufferStrategy bs = getBufferStrategy(); // Stops flickers

		if(bs == null)
		{
			createBufferStrategy(3); // How many times it buffers
			return;
		}

		Graphics g = bs.getDrawGraphics(); //Allows you to use graphics "g"
		g.drawImage(img,0,0,width,height,null); //(Screenshot,Pos x,Pos y,width,height,null)
		g.setColor(bgColor);
		g.fillRect(0,0,width,height);




		///////////////////////////////////////////////--------------NOTHING BELOW HERE
		g.dispose(); // Gets rid of the last screenshot
		bs.show();	// Shows the last screenshot
    }

    public static void main(String [] args)
    {
    	MobileGame m = new MobileGame(); // Makes the mobile game
    	m.start(); // Starts the game
    }

}