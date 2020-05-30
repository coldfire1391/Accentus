import java.util.*;
import java.lang.*;
public class Accentus
{
	public static void main(String[] args)throws InterruptedException
	{
		FamRA famra = new FamRA();
		LoveRA lovera = new LoveRA();
		FrenRA frenra = new FrenRA();
		AcadRA acadra = new AcadRA();
		OtherRA otherra = new OtherRA();

		final MarioWindow mw = new MarioWindow();

		mw.add(famra);
		mw.add(lovera);
		mw.add(frenra);
		mw.add(acadra);
		mw.add(otherra);

		(new Thread()
		{
			public void run()
			{
				mw.startGame();
			}
		}).start();

		reset:
		while(true)
		{
			famra.reset = false;
			frenra.reset = false;
			acadra.reset = false;
			lovera.reset = false;

			otherra.visualize = true;
			otherra.head = otherra.other[1];
			otherra.head.data.visualize = true;
			otherra.rover = 1;

			otherra.start_music();
			while(otherra.visualize)
			{
				MarioWindow.delay(100);
			}
			otherra.stop_music();
			switch(otherra.rover)
			{
				case 1:
				{
					frenra.visualize = true;
					frenra.start_music();
					break;
				}
				case 2:
				{
					lovera.visualize = true;
					lovera.start_music();
					break;
				}
				case 3:
				{
					famra.visualize = true;
					famra.start_music();
					break;
				}
				case 4:
				{
					acadra.visualize = true;
					acadra.start_music();
					break;
				}
			}
			while(true)
			{
				if (famra.reset || frenra.reset || acadra.reset || lovera.reset) 
				{
					if (famra.reset) 
					{
						famra.stop_music();	
					}
					else if (frenra.reset) 
					{
						frenra.stop_music();	
					}
					else if (acadra.reset) 
					{
						acadra.stop_music();
					}
					else if (lovera.reset) 
					{
						lovera.stop_music();
					}
					continue reset;
				}
				if (famra.exit || frenra.exit || acadra.exit || lovera.exit) 
				{
					System.exit(0);	
				}
				MarioWindow.delay(100);
			}
		}
	}
}