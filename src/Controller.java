import java.awt.Color;

import ShneyLuchot.OutState;
import ShneyLuchot.State;

public class Controller  extends Thread
{
	enum State {green0,green1_2,green2_3,turn_0_red,turn_3_red,turn1_2red};
	enum OutState {regularDay,Shabat};
	State state;
	OutState outState;
	
	public void run()
	{
		state=State.green0;
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
						case green0:
						//tm(15000)/evChengeRed(0,9,10,12,13,7,6);	
							evtm.waitEvent();
							//evChengeRed(0,9,10,12,13,7,6);//how we do it
							state=State.turn_0_red;
							break;
						case turn_0_red:
							//evack(0,9,10,12,7,6)/evChengeGreen(4,5,14,15,11,8,2,3)
							evack(0,9,10,12,7,6).whitEvent
							//evChengeGreen(4,5,14,15,11,8,2,3)//how we do it
							state=State.green2_3;
							break;
						case green2_3:
							//tm(15000)[else]/evChengeRed(3,11,8,14,15)
							evtm.waitEvent();
							//evChengeRed(3,11,8,14,15);//how we do it
							state=State.turn_3_red;
							break;
						case turn_3_red:
							//evack(3,11,8,14,15)/evChengeGreen(12,13,6,7,1)
							evack(3,11,8,14,15).whitEvent
							//evChengeGreen(12,13,6,7,1)//how we do it
							state=State.green1_2;
						case green1_2:
							//tm(15000)[else]/evChengeRed(4,5,1,2)
							evtm.waitEvent();
							//evChengeRed(4,5,1,2);//how we do it
							state=State.turn1_2red;
							break;
						case turn1_2red:
						//evack(4,5,1,2)/evChengeGreen(13,12,10,9,6,7,0)
							evack(4,5,1,2).whitEvent
							//evChengeGreen(13,12,10,9,6,7,0)//how we do it
							state=State.green0;
							break;
						}
					}
				case Shabat:
					
			}
			}
			
		} catch (InterruptedException e) {}

	}

}
