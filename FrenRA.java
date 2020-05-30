import java.util.*;
import java.awt.*;
import java.awt.image.*;
import javax.swing.*;

//for music
import java.io.*;
import java.net.URL;
import javax.sound.sampled.*;

public class FrenRA extends GameObject
{
	BufferedImage img;
	int xc;	//x coordinate
	int yc;	//y coordinate
	int c;	//change in y
	int rover;			//used to change the head node 

	Node[] fren;
	Node head;			//used for visualizing scene of node
	boolean visualize;
	boolean reset;
	boolean exit;

	File path;
	AudioInputStream audio;
	Clip clip;
	public FrenRA()			//build the entire array here
	{
		visualize = false;
		reset = false;
		exit = false;

		img = MarioWindow.getImage("other/pointer.png");

		fren = new Node[24];				//size dependent on number of scenes
		Screen fr1 = new Screen("fren","fren1.jpg");
		Node fren1 = new Node(fr1,2,3,260,516,28);		//creating scene 1
		fren[1] = fren1;

		Screen fr2 = new Screen("fren","fren2.jpg");
		Node fren2 = new Node(fr2,4,4,270,513,27);		//creating scene 2
		fren[2] = fren2;
		
		Screen fr3 = new Screen("fren","fren3.jpg");
		Node fren3 = new Node(fr3,5,5,280,507,29);		//creating scene 3
		fren[3] = fren3;
		
		Screen fr4 = new Screen("fren","fren4.jpg");
		Node fren4 = new Node(fr4,6,20,209,512,28);	//creating scene 4
		fren[4] = fren4;

		Screen fr5 = new Screen("fren","fren5.jpg");
		Node fren5 = new Node(fr5,7,20,209,512,28);	//creating scene 5
		fren[5] = fren5;
		
		Screen fr6 = new Screen("fren","fren6.jpg");
		Node fren6 = new Node(fr6,8,8,192,508,28);		//creating scene 6
		fren[6] = fren6;

		Screen fr7 = new Screen("fren","fren7.jpg");
		Node fren7 = new Node(fr7,9,9,205,508,28);		//creating scene 7
		fren[7] = fren7;

		Screen fr8 = new Screen("fren","fren8.jpg");
		Node fren8 = new Node(fr8,10,12,288,517,27);	//creating scene 8
		fren[8] = fren8;

		Screen fr9 = new Screen("fren","fren9.jpg");
		Node fren9 = new Node(fr9,11,13,288,517,27);	//creating scene 9
		fren[9] = fren9;

		Screen fr10 = new Screen("fren","fren10.jpg");
		Node fren10 = new Node(fr10,23,23,800,0,0);	//creating scene 10
		fren[10] = fren10;

		Screen fr11 = new Screen("fren","fren11.jpg");
		Node fren11 = new Node(fr11,23,23,800,0,0);	//creating scene 11
		fren[11] = fren11;

		Screen fr12 = new Screen("fren","fren12.jpg");
		Node fren12 = new Node(fr12,14,16,275,513,28);	//creating scene 12
		fren[12] = fren12;

		Screen fr13 = new Screen("fren","fren13.jpg");
		Node fren13 = new Node(fr13,15,17,275,513,28);	//creating scene 13
		fren[13] = fren13;

		Screen fr14 = new Screen("fren","fren14.jpg");
		Node fren14 = new Node(fr14,23,23,800,0,0);	//creating scene 14
		fren[14] = fren14;

		Screen fr15 = new Screen("fren","fren15.jpg");
		Node fren15 = new Node(fr15,23,23,800,0,0);	//creating scene 15
		fren[15] = fren15;

		Screen fr16 = new Screen("fren","fren16.jpg");
		Node fren16 = new Node(fr16,14,18,205,506,28);	//creating scene 16
		fren[16] = fren16;

		Screen fr17 = new Screen("fren","fren17.jpg");
		Node fren17 = new Node(fr17,15,19,205,506,28);	//creating scene 17
		fren[17] = fren17;

		Screen fr18 = new Screen("fren","fren18.jpg");
		Node fren18 = new Node(fr18,23,23,800,0,0);	//creating scene 18
		fren[18] = fren18;

		Screen fr19 = new Screen("fren","fren19.jpg");
		Node fren19 = new Node(fr19,23,23,800,0,0);	//creating scene 19
		fren[19] = fren19;

		Screen fr20 = new Screen("fren","fren20.jpg");
		Node fren20 = new Node(fr20,22,21,285,507,27);	//creating scene 20
		fren[20] = fren20;

		Screen fr21 = new Screen("fren","fren21.jpg");
		Node fren21 = new Node(fr21,22,22,200,505,27);	//creating scene 21
		fren[21] = fren21;

		Screen fr22 = new Screen("fren","fren22.jpg");
		Node fren22 = new Node(fr22,23,23,800,0,0);	//creating scene 22
		fren[22] = fren22;

		Screen fr23 = new Screen("other","end.jpg");
		Node fren23 = new Node(fr23,-1,-1,260,397,47);	//creating end screen
		fren[23] = fren23;
		
		head = fren[1];
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
					head = fren[head.a];
					head.data.visualize = true;	
				}
				else if (rover == 1) 	//go to right choice
				{
					head = fren[head.b];
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
					head = fren[1];
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
		path = new File("music/Friends.wav");
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