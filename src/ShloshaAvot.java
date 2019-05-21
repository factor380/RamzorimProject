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
		new CarsMaker(panel,this,key);
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
		boolean finishR=true;
		boolean finishS=false;
		if(evShabat.arrivedEvent()) {
			evShabat.waitEvent();
			outState = OutState.Shabat;
			finishS=true;
		}
		try 
		{
				while (true)
				{
					switch (outState)
					{
						case regularDay:
							setLight(1,Color.RED);
							setLight(2,Color.GRAY);
							setLight(3,Color.GRAY);
							evack.sendEvent();
						while(finishR) {
							switch(state)
							{
							case red:
								//evChengeGreen/ sleep(1000),setLight(green)
								if(evChengeGreen.arrivedEvent())
								{
								evChengeGreen.waitEvent();
								sleep(1000);
								setLight(1,Color.GRAY);
								setLight(2,Color.GRAY);
								setLight(3,Color.GREEN);
								stop=false;
								state=State.green;
								}
								else if(evShabat.arrivedEvent())
								{
									evShabat.waitEvent();
									setLight(1,Color.GRAY);
									setLight(2,Color.GRAY);
									setLight(3,Color.GRAY);
									outState=outState.Shabat;
									finishR=false;
									finishS=true;
								}
								else
									yield(); 
								break;
							case yellow:
								//tm(500)/setLight(red),evack
								if(evShabat.arrivedEvent())
								{
									evShabat.waitEvent();
									setLight(1,Color.GRAY);
									setLight(2,Color.GRAY);
									setLight(3,Color.GRAY);
									outState=outState.Shabat;
									finishR=false;
									finishS=true;
								}
								else {
								sleep(500);
								setLight(1,Color.RED);
								setLight(2,Color.GRAY);
								setLight(3,Color.GRAY);
								evack.sendEvent();
								state=State.red;
								}
								break;
							case green:
								//evChengeRed /setLight(yellow)
								if(evChengeRed.arrivedEvent())
								{
								evChengeRed.waitEvent();
								stop = true;
								setLight(1,Color.GRAY);
								setLight(2,Color.YELLOW);
								setLight(3,Color.GRAY);
								state=State.yellow;
								}
								else if(evShabat.arrivedEvent())
								{
									evShabat.waitEvent();
									setLight(1,Color.GRAY);
									setLight(2,Color.GRAY);
									setLight(3,Color.GRAY);
									outState=outState.Shabat;
									finishR=false;
									finishS=true;
								}
								else
									yield(); 
								break;
							
							}
						}
					case Shabat:
						while(finishS)
						{
						//tm(500)/setLight(grey)
						sleep(500);
						setLight(2,Color.GRAY);
						//tm(500)/setLight(yellow)
						sleep(500);
						setLight(2,Color.YELLOW);
							if(stopEvShabat.arrivedEvent())
							{
							stopEvShabat.waitEvent();
							outState=OutState.regularDay;
							state=State.red;
							finishR=true;
							finishS=false;
							}
						
						}
						
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

	public boolean isStop()
	{
		return stop;
	}
}
