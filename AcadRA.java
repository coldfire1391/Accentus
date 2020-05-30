import java.util.*;
import java.awt.*;
import java.awt.image.*;
import javax.swing.*;

//for music
import java.io.*;
import java.net.URL;
import javax.sound.sampled.*;

// /*for .mp3*/
// import javafx.scene.media.Media;
// import javafx.scene.media.MediaPlayer;
// /*for .mp3*/

public class AcadRA extends GameObject
{
	BufferedImage img;
	int xc;	//x coordinate
	int yc;	//y coordinate
	int c;	//change in y
	int rover;			//used to change the head node 

	Node[] acad;
	Node head;			//used for visualizing scene of node
	boolean visualize;
	boolean reset;
	boolean exit;

	File path;
	
	/*for .wav*/
	AudioInputStream audio;
	Clip clip;
	/*for .wav*/
	
	// /*for .mp3*/
	// Media media;
	// MediaPlayer mediaplayer;
	// /*for .mp3*/

	public AcadRA()			//build the entire array here
	{
		visualize = false;
		reset = false;
		exit = false;

		img = MarioWindow.getImage("other/pointer.png");

		acad = new Node[12];				//size dependent on number of scenes
		Screen a1 = new Screen("acad","acad1.jpg");
		Node acad1 = new Node(a1,2,3,315,515,27);		//creating scene 1
		acad[1] = acad1;

		Screen a2 = new Screen("acad","acad2.jpg");
		Node acad2 = new Node(a2,4,9,270,513,28);		//creating scene 2
		acad[2] = acad2;
		
		Screen a3 = new Screen("acad","acad3.jpg");
		Node acad3 = new Node(a3,4,9,270,524,25);		//creating scene 3
		acad[3] = acad3;
		
		Screen a4 = new Screen("acad","acad4.jpg");
		Node acad4 = new Node(a4,6,5,240,513,28);		//creating scene 4
		acad[4] = acad4;

		Screen a5 = new Screen("acad","acad5.jpg");
		Node acad5 = new Node(a5,11,11,800,0,0);	//creating scene 5
		acad[5] = acad5;
		
		Screen a6 = new Screen("acad","acad6.jpg");
		Node acad6 = new Node(a6,7,8,250,516,28);		//creating scene 6
		acad[6] = acad6;

		Screen a7 = new Screen("acad","acad7.jpg");
		Node acad7 = new Node(a7,11,11,800,0,0);		//creating scene 7
		acad[7] = acad7;

		Screen a8 = new Screen("acad","acad8.jpg");
		Node acad8 = new Node(a8,7,5,130,513,27);		//creating scene 8
		acad[8] = acad8;

		Screen a9 = new Screen("acad","acad9.jpg");
		Node acad9 = new Node(a9,5,10,235,513,27);	//creating scene 9
		acad[9] = acad9;

		Screen a10 = new Screen("acad","acad10.jpg");
		Node acad10 = new Node(a10,11,11,800,0,0);	//creating scene 10
		acad[10] = acad10;

		Screen a11 = new Screen("other","end.jpg");
		Node acad11 = new Node(a11,-1,-1,260,397,47);	//creating scene 11
		acad[11] = acad11;
		
		head = acad[1];
		head.data.visualize = true;
		rover = 0;

		xc = head.pxc;
		yc = head.pyc;
		c = head.pca;

		path = new File("music/Acads.wav");

		/*for .wav*/
		audio = AudioSystem.getAudioInputStream(path);
		clip = AudioSystem.getClip();
		/*for .wav*/

		// /*For .mp3*/
		// media = new Media(path.toURI().toString());
		// mediaplayer = new MediaPlayer(media);
		// /*for .mp3*/
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
					head = acad[head.a];
					head.data.visualize = true;	
				}
				else if (rover == 1) 	//go to right choice
				{
					head = acad[head.b];
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
					head = acad[1];
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
		
		try
		{
			if (path.exists())
			{
				/*for .wav*/
				clip.open(audio);
				clip.start();
				clip.loop(Clip.LOOP_CONTINUOUSLY);
				/*for .wav*/

				// /*for .mp3*/
				// mediaplayer.setCycleCount(INDEFINITE);
				// mediaplayer.play();
				// /*for .mp3*/
			}
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}
	}
	public void stop_music()
	{
		/*for .wav*/
		clip.stop();
		/*for .wav*/

		// /*for .mp3*/
		// mediaplayer.stop();
		// /*for .mp3*/
	}
}