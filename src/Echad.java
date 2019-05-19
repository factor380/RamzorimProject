import java.awt.Color;

import javax.swing.JPanel;

/*
 * Created on Mimuna 5767  upDate on Tevet 5770 
 */

/**
 * @author �����
 */
class Echad extends Thread
{
	Ramzor ramzor;
	JPanel panel;
	public Echad( Ramzor ramzor,JPanel panel)
	{
		this.ramzor=ramzor;
		this.panel=panel;
		start();
	}

	public void run()
	{
		try 
		{
			while (true)
			{
				sleep(300);
				setLight(1,Color.GRAY);
				sleep(300);
				setLight(1,Color.YELLOW);
			}
		} catch (InterruptedException e) {}

	}
	public void setLight(int place, Color color)
	{
		ramzor.colorLight[place-1]=color;
		panel.repaint();
	}
}