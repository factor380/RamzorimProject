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
	Event64 evack = new Event64(),evChengeGreen = new Event64(),evChengeRed = new Event64(),evShabat = new Event64(),stopEvShabat= new Event64();
	public ShneyLuchot( Ramzor ramzor,JPanel panel)
	{
		this.ramzor=ramzor;
		this.panel=panel;
	}
	public void init(Event64 evack,Event64 evChengeGreen,Event64 evChengeRed,Event64 evShabat,Event64 stopEvShabat) {
		this.evack = evack;
		this.evChengeGreen = evChengeGreen;
		this.evChengeRed = evChengeRed;
		this.evShabat=evShabat;
		this.stopEvShabat=stopEvShabat;
		start();
	}
	public void run()
	{
		state=State.red;
		outState=OutState.regularDay;
		boolean finishR=true;
		try 
		{
			while (true)
			{
				switch (outState)
				{
					case regularDay:
						setLight(1,Color.RED);
						setLight(2,Color.GRAY);
						evack.sendEvent();
					while(finishR) {
						switch(state)
						{
						case red:
							//evChengeGreen/sleep(1000),setLight(green)
							if(evChengeGreen.arrivedEvent())
							{
							evChengeGreen.waitEvent();
							sleep(1000);
							setLight(1,Color.GRAY);
							setLight(2,Color.GREEN);
							state=State.green;
							}
							else if(evShabat.arrivedEvent())
							{
								evShabat.waitEvent();
								setLight(1,Color.GRAY);
								setLight(2,Color.GRAY);
								finishR=false;
								outState=OutState.off;
							}
							else
								yield(); 
								
							break;
						case green:
							//evChengeRed/setLight(red),evack
							if(evChengeRed.arrivedEvent())
							{
							evChengeRed.waitEvent();
							setLight(1,Color.RED);
							setLight(2,Color.GRAY);
							evack.sendEvent();
							state=State.red;
							}
							else if(evShabat.arrivedEvent())
							{
								evShabat.waitEvent();
								setLight(1,Color.GRAY);
								setLight(2,Color.GRAY);
								finishR=false;
								outState=OutState.off;
							}
							else
								yield();
							break;
						}
					}
				case off:
					stopEvShabat.waitEvent();
					finishR=true;
					outState=OutState.regularDay;
					break;
					
					
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
