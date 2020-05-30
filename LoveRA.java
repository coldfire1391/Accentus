import java.util.*;
import java.awt.*;
import java.awt.image.*;
import javax.swing.*;

//for music
import java.io.*;
import java.net.URL;
import javax.sound.sampled.*;

public class LoveRA extends GameObject
{
	BufferedImage img;
	int xc;	//x coordinate
	int yc;	//y coordinate
	int c;	//change in y
	int rover;			//used to change the head node 

	Node[] love;
	Node head;			//used for visualizing scene of node
	boolean visualize;
	boolean reset;
	boolean exit;

	File path;
	AudioInputStream audio;
	Clip clip;
	public LoveRA()			//build the entire array here
	{
		visualize = false;
		reset = false;
		exit = false;

		img = MarioWindow.getImage("other/pointer.png");

		love = new Node[20];			//size dependent on number of scenes
		Screen l1 = new Screen("love","love1.jpg");
		Node love1 = new Node(l1,2,19,320,514,24);		//creating scene 1
		love[1] = love1;

		Screen l2 = new Screen("love","love2.jpg");
		Node love2 = new Node(l2,3,11,40,509,24);		//creating scene 2
		love[2] = love2;
		
		Screen l3 = new Screen("love","love3.jpg");
		Node love3 = new Node(l3,4,4,200,524,22);		//creating scene 3
		love[3] = love3;
		
		Screen l4 = new Screen("love","love4.jpg");
		Node love4 = new Node(l4,5,8,260,527,22);		//creating scene 4
		love[4] = love4;

		Screen l5 = new Screen("love","love5.jpg");
		Node love5 = new Node(l5,6,7,330,514,24);		//creating scene 5
		love[5] = love5;
		
		Screen l6 = new Screen("love","love6.jpg");
		Node love6 = new Node(l6,19,19,800,0,0);	//creating scene 6
		love[6] = love6;

		Screen l7 = new Screen("love","love7.jpg");
		Node love7 = new Node(l7,19,19,800,0,0);	//creating scene 7
		love[7] = love7;

		Screen l8 = new Screen("love","love8.jpg");
		Node love8 = new Node(l8,9,10,330,521,31);		//creating scene 8
		love[8] = love8;

		Screen l9 = new Screen("love","love9.jpg");
		Node love9 = new Node(l9,5,5,800,0,0);		//creating scene 9
		love[9] = love9;

		Screen l10 = new Screen("love","love10.jpg");
		Node love10 = new Node(l10,19,19,800,0,0);	//creating scene 10
		love[10] = love10;

		Screen l11 = new Screen("love","love11.jpg");
		Node love11 = new Node(l11,12,18,150,511,24);	//creating scene 11
		love[11] = love11;

		Screen l12 = new Screen("love","love12.jpg");
		Node love12 = new Node(l12,13,15,110,511,24);	//creating scene 12
		love[12] = love12;

		Screen l13 = new Screen("love","love13.jpg");
		Node love13 = new Node(l13,14,18,80,524,25);	//creating scene 13
		love[13] = love13;

		Screen l14 = new Screen("love","love14.jpg");
		Node love14 = new Node(l14,19,19,800,0,0);	//creating scene 14
		love[14] = love14;

		Screen l15 = new Screen("love","love15.jpg");
		Node love15 = new Node(l15,18,16,30,518,24);	//creating scene 15
		love[15] = love15;

		Screen l16 = new Screen("love","love16.jpg");
		Node love16 = new Node(l16,17,19,75,514,25);	//creating scene 16
		love[16] = love16;

		Screen l17 = new Screen("love","love17.jpg");
		Node love17 = new Node(l17,19,19,800,0,0);	//creating scene 17
		love[17] = love17;

		Screen l18 = new Screen("love","love18.jpg");
		Node love18 = new Node(l18,3,3,800,0,0);	//creating scene 18
		love[18] = love18;

		Screen l19 = new Screen("other","end.jpg");
		Node love19 = new Node(l19,-1,-1,260,397,47);	//creating scene 19
		love[19] = love19;
		
		head = love[1];
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
					head = love[head.a];
					head.data.visualize = true;	
				}
				else if (rover == 1) 	//go to right choice
				{
					head = love[head.b];
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
					head = love[1];
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
		path = new File("music/Love.wav");
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