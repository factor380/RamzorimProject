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
	private boolean stop=true;
	public ShloshaAvot( Ramzor ramzor,JPanel panel,int key)
	{
		this.ramzor=ramzor;
		this.panel=panel;
//		new CarsMaker(panel,this,key);
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
								//evChengeGreen/ sleep(1000),setLight(green)
								evChengeGreen.waitEvent();
								sleep(1000);
								setLight(1,Color.LIGHT_GRAY);
								setLight(2,Color.LIGHT_GRAY);
								setLight(3,Color.GREEN);
								state=State.green;
							case yellow:
								//tm(500)/setLight(red),evack
								sleep(500);
								setLight(1,Color.RED);
								setLight(2,Color.LIGHT_GRAY);
								setLight(3,Color.LIGHT_GRAY);
								evack.sendEvent();
								state=State.yellow;
								break;
							case green:
								//evChengeRed /setLight(yellow)
								evChengeRed.waitEvent();
								setLight(1,Color.LIGHT_GRAY);
								setLight(2,Color.YELLOW);
								setLight(3,Color.LIGHT_GRAY);
								state=State.red;
								break;
							
							}
						}
					case Shabat:
						
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
