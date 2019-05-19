import java.awt.Color;

import javax.swing.JPanel;

import ShloshaAvot.OutState;
import ShloshaAvot.State;

/*
 * Created on Mimuna 5767  upDate on Tevet 5770 
 */



class ShneyLuchot extends Thread
{
	enum State {red,green};
	enum OutState {off, regularDay};
	Ramzor ramzor;
	JPanel panel;
	State state;
	OutState outState;
	public ShneyLuchot( Ramzor ramzor,JPanel panel)
	{
		this.ramzor=ramzor;
		this.panel=panel;
		start();
	}
	
	public void run()
	{
		state=State.red;
		outState=OutState.regularDay;
		try 
		{
			while (true)
			{
				switch (outState)
				{
					case regularDay:
					while(true) {
						switch(state)
						{
						case red:
							//evchengeRed/setLigt(green)
							evChengeGreen.waitEvent();
							sleep(1000);
							setLight(1,Color.GRAY);
							setLight(2,Color.GREEN);
							state=State.green;
							break;
						case green:
							//evchengeGreen/setLight(red),evack
							evChengeRed.waitEvent();
							setLight(1,Color.RED);
							setLight(2,Color.GRAY);
							evack.sendEvent();
							state=State.red;
							break;
						
						}
					}
				case off:
					
			}
			}
			
		} catch (InterruptedException e) {}

	}
	public void setLight(int place, Color color)
	{
		ramzor.colorLight[place-1]=color;
		panel.repaint();
	}
}
