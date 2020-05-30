public class Node
{
	Screen data;
	int a;
	int b;
	int pxc;	//pointer x-coord
	int pyc;	//pointer y-coord
	int pca;	//pointer change amount
	public Node(Screen s, int x, int y, int px, int py, int pc)
	{
		data = s;
		a = x;
		b = y;
		pxc = px;
		pyc = py;
		pca = pc;
	}
}