import java.awt.Color;

import javax.swing.JPanel;

class ShneyLuchot extends Thread
{
	enum State {red,green};
	enum OutState {off, regularDay};
	Ramzor ramzor;
	JPanel panel;
	State state;
	OutState outState;
	Event64 evack = new Event64(),evChengeGreen = new Event64(),evChengeRed = new Event64(),evShabat = new Event64();
	public ShneyLuchot( Ramzor ramzor,JPanel panel)
	{
		this.ramzor=ramzor;
		this.panel=panel;
	}
	public void init(Event64 evack,Event64 evChengeGreen,Event64 evChengeRed,Event64 evShabat) {
		this.evack = evack;
		this.evChengeGreen = evChengeGreen;
		this.evChengeRed = evChengeRed;
		this.evShabat=evShabat;
		start();
	}
	public void run()
	{
		state=State.red;
		outState=OutState.regularDay;
		boolean finishR=true;
		setLight(1,Color.RED);
		evack.sendEvent();
		try 
		{
			while (true)
			{
				switch (outState)
				{
					case regularDay:
					while(finishR) {
						switch(state)
						{
						case red:
							//evchengeGreen/setLigt(green)
							if(evChengeGreen.arrivedEvent())
							{
							evChengeGreen.waitEvent();
							sleep(1000);
							setLight(1,Color.GRAY);
							setLight(2,Color.GREEN);
							state=State.green;
							}
							else if(evChengeRed.arrivedEvent())
							{
								evChengeRed.waitEvent();
								evack.sendEvent();
								state=State.red;
							}
							else if(evShabat.arrivedEvent())
							{
								evShabat.waitEvent();
								setLight(1,Color.GRAY);
								setLight(2,Color.GRAY);
								finishR=false;
							}
							else
								yield(); 
								
							break;
						case green:
							//evchengeRed/setLight(red),evack
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
