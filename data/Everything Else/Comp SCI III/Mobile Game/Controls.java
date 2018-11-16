import java.awt.*;
import java.awt.image.*;
import java.util.*;
import java.io.*;
import java.lang.*;
import java.lang.System.*;
import javax.swing.*;
import java.awt.event.*;

public class Controls implements MouseListener
{

    public Controls(MobileGame mg)
    {
		mg.addMouseListener(this);
    }

    public void mouseClicked(MouseEvent e)
    {

    }
    public void mousePressed(MouseEvent e)
    {
		int x = e.getX();
		int y = e.getY();
		System.out.println ("POS X:: " + x + " | " +"POS Y:: " + y);
    }
    public void mouseReleased(MouseEvent e)
    {

    }
    public void mouseEntered(MouseEvent e)
    {

    }
    public void mouseExited(MouseEvent e)
    {

    }

}