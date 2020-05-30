import java.util.*;
import java.awt.*;
import java.awt.image.*;
import javax.swing.*;

public class Screen extends GameObject
{
	BufferedImage img;
	boolean visualize;			//to display img or not
	public Screen(String s1, String s2)			//s1 is foler in same directory as project												
	{											//s2 is name of img file with extension
		img = MarioWindow.getImage(s1+"/"+s2);
		visualize = false;
	}
	public void paint(Graphics2D g)
	{
		if (visualize) 
		{
			g.drawImage(img,0,0,null);
			// g.setFont(new Font("Power Clear", Font.PLAIN, 14)); //if text is needed, use this font
		}
	}
}