import java.util.*;
import java.awt.*;
import java.awt.image.*;
import javax.swing.*;

//for music
import java.io.*;
import java.net.URL;
import javax.sound.sampled.*;

public class OtherRA extends GameObject	//Start game background
{
	Node[] other;
	Node head;
	int rover;
	boolean visualize;			//to display img or not

	File path;
	AudioInputStream audio;
	Clip clip;
	public OtherRA()			//s1 is foler in same directory as project												
	{
		visualize = true;

		other = new Node[3];
		Screen o1 = new Screen("other","flat.jpg");	//Start game screen
		Node other1 = new Node(o1,2,2,0,0,0);
		other[1] = other1;

		Screen o2 = new Screen("other","choose.PNG");	//choose screen
		Node other2 = new Node(o2,-1,-1,0,0,0);
		other[2] = other2;
		
		head = other[1];
		head.data.visualize = true;
		rover = 1;
	}
	public void paint(Graphics2D g)
	{
		if (visualize) 
		{
			head.data.paint(g);
		}
	}
	public void movenext()	//need to edit
	{
		if (visualize) 
		{
			head.data.visualize = false;
			head = other[head.a]; //head.b will also work in this case
			head.data.visualize = true;
			rover++;
		}
	}
	public void keyPressed(String key) 
	{
		if (visualize && rover == 1) 
		{
			if (key == "Enter") 
			{
				movenext();
			}
		}
    }
    public void mouseClicked(int xmouse, int ymouse, int button) 
    {
		if (visualize && rover == 2) 
		{
			visualize = false;
			if (0 < xmouse && xmouse < 400 && 0 < ymouse && ymouse < 300) //top left
			{
				rover = 1;
			}
			else if (0 < xmouse && xmouse < 400 && 300 < ymouse && ymouse < 600) //bottom left
			{
				rover = 2;
			}
			else if (400 < xmouse && xmouse < 800 && 0 < ymouse && ymouse < 300) //top right
			{
				rover = 3;
			}
			else 	//bottom right
			{
				rover = 4;
			}
		}
	}
	public void start_music()
	{
		path = new File("music/Title_End.wav");
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