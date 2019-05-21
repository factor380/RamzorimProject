import java.awt.Color;

import javax.swing.JPanel;

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
				//tm(300)/setLight(grey)
				sleep(300);
				setLight(1,Color.GRAY);
				//tm(300)/setLight(yellow)
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
