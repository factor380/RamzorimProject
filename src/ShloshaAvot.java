import java.awt.Color;
import javax.swing.JPanel;



public class ShloshaAvot extends Thread
{
	enum State {red,green,yellow};
	enum OutState {regularDay,Shabat};
	Ramzor ramzor;
	JPanel panel;
	State state;
	OutState outState;
	Event64 evack,evChengeGreen,evChengeRed,evShabat,stopEvShabat;
	
	private boolean stop=true;
	public ShloshaAvot( Ramzor ramzor,JPanel panel,int key)
	{
		this.ramzor=ramzor;
		this.panel=panel;
		//new CarsMaker(panel,this,key);
	}
	public void init(Event64 evack,Event64 evChengeGreen,Event64 evChengeRed,Event64 evShabat,Event64 stopEvShabat) {
		this.evack = evack;
		this.evChengeGreen = evChengeGreen;
		this.evChengeRed = evChengeRed;
		this.evShabat = evShabat;
		this.stopEvShabat = stopEvShabat;
		start();
	}
	public void run()
	{
		state=State.red;
		outState=OutState.regularDay;
		setLight(1,Color.RED);
		evack.sendEvent();
		if(evShabat.arrivedEvent()) {
			evShabat.waitEvent();
			outState = OutState.Shabat;
		}
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
								//evChengeGreen/ sleep(1000),setLight(green)
								evChengeGreen.waitEvent();
								sleep(3000);
								setLight(1,Color.LIGHT_GRAY);
								setLight(2,Color.LIGHT_GRAY);
								setLight(3,Color.GREEN);
								state=State.green;
								break;
							case yellow:
								//tm(500)/setLight(red),evack
								sleep(500);
								setLight(1,Color.RED);
								setLight(2,Color.LIGHT_GRAY);
								setLight(3,Color.LIGHT_GRAY);
								evack.sendEvent();
								state=State.red;
								break;
							case green:
								//evChengeRed /setLight(yellow)
								evChengeRed.waitEvent();
								setLight(1,Color.LIGHT_GRAY);
								setLight(2,Color.YELLOW);
								setLight(3,Color.LIGHT_GRAY);
								state=State.yellow;
								break;
							
							}
						}
					case Shabat:
						sleep(300);
						setLight(2,Color.YELLOW);
						sleep(300);
						setLight(2,Color.LIGHT_GRAY);
						if(stopEvShabat.arrivedEvent())
						outState=OutState.regularDay;
						else
							outState = OutState.Shabat;
						break;
				}
//				sleep(1000);
//				setLight(2,Color.YELLOW);
//				sleep(1000);
//				setLight(1,Color.LIGHT_GRAY);
//				setLight(2,Color.LIGHT_GRAY);
//				setLight(3,Color.GREEN);
//				stop=false;
//				sleep(3000);
//				stop=true;
//				setLight(1,Color.LIGHT_GRAY);
//				setLight(2,Color.YELLOW);
//				setLight(3,Color.LIGHT_GRAY);
//				sleep(1000);
//				setLight(1,Color.RED);
//				setLight(2,Color.LIGHT_GRAY);
//				setLight(3,Color.LIGHT_GRAY);
			}
		} catch (InterruptedException e) {}
	}
	public void setLight(int place, Color color)
	{
		ramzor.colorLight[place-1]=color;
		panel.repaint();
	}

	public boolean isStop()
	{
		return stop;
	}
}
