import java.util.*;
import java.awt.*;
import java.awt.image.*;
import javax.swing.*;

//for music
import java.io.*;
import java.net.URL;
import javax.sound.sampled.*;

public class FamRA extends GameObject
{
	BufferedImage img;
	int xc;	//x coordinate
	int yc;	//y coordinate
	int c;	//change in y
	int rover;	//used to change the head node 

	Node[] family;
	Node head;			//used for visualizing scene of node
	boolean visualize;
	boolean reset;
	boolean exit;

	File path;
	AudioInputStream audio;
	Clip clip;
	public FamRA()			//build the entire array here
	{
		visualize = false;
		reset = false;
		exit = false;

		img = MarioWindow.getImage("other/pointer.png");

		family = new Node[14];			//size dependent on number of scenes
		Screen f1 = new Screen("fam","fam1.jpg");
		Node fam1 = new Node(f1,2,2,160,510,27);		//creating scene 1
		family[1] = fam1;

		Screen f2 = new Screen("fam","fam2.jpg");
		Node fam2 = new Node(f2,3,8,200,514,28);		//creating scene 2
		family[2] = fam2;
		
		Screen f3 = new Screen("fam","fam3.jpg");
		Node fam3 = new Node(f3,4,6,150,514,29);		//creating scene 3
		family[3] = fam3;
		
		Screen f4 = new Screen("fam","fam4.jpg");
		Node fam4 = new Node(f4,5,5,220,517,29);		//creating scene 4
		family[4] = fam4;

		Screen f5 = new Screen("fam","fam5.jpg");
		Node fam5 = new Node(f5,13,13,800,0,0);		//creating scene 5
		family[5] = fam5;
		
		Screen f6 = new Screen("fam","fam6.jpg");
		Node fam6 = new Node(f6,3,7,260,508,28);		//creating scene 6
		family[6] = fam6;

		Screen f7 = new Screen("fam","fam7.jpg");
		Node fam7 = new Node(f7,8,8,800,0,0);		//creating scene 7
		family[7] = fam7;

		Screen f8 = new Screen("fam","fam8.jpg");
		Node fam8 = new Node(f8,9,10,230,524,25);		//creating scene 8
		family[8] = fam8;

		Screen f9 = new Screen("fam","fam9.jpg");
		Node fam9 = new Node(f9,13,13,800,0,0);		//creating scene 9
		family[9] = fam9;

		Screen f10 = new Screen("fam","fam10.jpg");
		Node fam10 = new Node(f10,11,12,190,517,26);	//creating scene 10
		family[10] = fam10;

		Screen f11 = new Screen("fam","fam11.jpg");
		Node fam11 = new Node(f11,13,13,800,0,0);	//creating scene 11
		family[11] = fam11;

		Screen f12 = new Screen("fam","fam12.jpg");
		Node fam12 = new Node(f12,13,13,800,0,0);	//creating scene 12
		family[12] = fam12;

		Screen f13 = new Screen("other","end.jpg");
		Node fam13 = new Node(f13,-1,-1,260,397,47);	//creating end screen
		family[13] = fam13;
		
		head = family[1];
		head.data.visualize = true;
		rover = 0;

		xc = head.pxc;
		yc = head.pyc;
		c = head.pca;
	}
	public void paint(Graphics2D g)
	{
		if (visualize) 
		{
			head.data.paint(g);
			g.drawImage(img,xc,yc,null);
		}
	}
	public void movenext()
	{
		if (visualize) 
		{
			head.data.visualize = false;		
			if (head.a != -1) 
			{
				if (rover == 0)			//go to left choice 
				{
					head = family[head.a];
					head.data.visualize = true;	
				}
				else if (rover == 1) 	//go to right choice
				{
					head = family[head.b];
					head.data.visualize = true;	
				}
				xc = head.pxc;
				yc = head.pyc;
				c = head.pca;
				rover = 0;
			}
			else
			{
				if (rover == 0) //play again
				{
					visualize = false;
					head = family[1];
					xc = head.pxc;
					yc = head.pyc;
					c = head.pca;
					head.data.visualize = true;
					rover = 0;
					reset = true;
				}
				else if (rover == 1) //exit
				{
					exit = true;
				}
			}
		}
	}
	public void keyPressed(String key) 
	{
		if (visualize) 
		{
			if (key == "Enter") 
			{
				movenext();
			}
			else if(key == "Up" && rover != 0)
			{
				rover = 0;
				yc-=c;
				// System.out.println(choice);
			}
			else if(key == "Down" && rover != 1)
			{
				rover = 1;
				yc+=c;
				// System.out.println(choice);
			}
		}
	}
	public void start_music()
	{
		path = new File("music/Family.wav");
		try
		{
			if (path.exists())
			{
				audio = AudioSystem.getAudioInputStream(path);
				clip = AudioSystem.getClip();
				clip.open(audio);
			}
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}
		clip.start();
		clip.loop(Clip.LOOP_CONTINUOUSLY);
	}
	public void stop_music()
	{
		clip.stop();
	}
}