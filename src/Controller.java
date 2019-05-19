import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

public class Controller  extends Thread
{
	enum State {green0,green1_2,green2_3,turn_0_red,turn_3_red,turn1_2red};
	enum OutState {regularDay,Shabat};
	State state;
	List listThree = new ArrayList();
	List listTwo = new ArrayList();
	OutState outState;
	Event64 evack0,evack1,evack2,evack3,evack4,evack5,evack6,evack7,evack8,evack9
	,evack10,evack11,evack12,evack13,evack14,evack15,evChengeGreen0,evChengeGreen1,evChengeGreen2,evChengeGreen3,evChengeGreen4,evChengeGreen5,evChengeGreen6,evChengeGreen7,evChengeGreen8,evChengeGreen9
	,evChengeGreen10,evChengeGreen11,evChengeGreen12,evChengeGreen13,evChengeGreen14,evChengeGreen15,evChangeRed0,evChangeRed1,evChangeRed2,evChangeRed3,evChangeRed4,evChangeRed5,evChangeRed6,evChangeRed7,evChangeRed8,evChangeRed9
	,evChangeRed10,evChangeRed11,evChangeRed12,evChangeRed13,evChangeRed14,evChangeRed15;
	public Controller (List listThree,List listTwo) {
		this.listThree = listThree;
		this.listTwo = listTwo;
		
		((ShneyLuchot) listTwo.get(4)).init(evack4,evChengeGreen4,evChangeRed4);
		((ShneyLuchot) listTwo.get(5)).init(evack5,evChengeGreen5,evChangeRed5);
		((ShneyLuchot) listTwo.get(6)).init(evack6,evChengeGreen6,evChangeRed6);
		((ShneyLuchot) listTwo.get(7)).init(evack7,evChengeGreen7,evChangeRed7);
		((ShneyLuchot) listTwo.get(8)).init(evack8,evChengeGreen8,evChangeRed8);
		((ShneyLuchot) listTwo.get(9)).init(evack9,evChengeGreen9,evChangeRed9);
		((ShneyLuchot) listTwo.get(10)).init(evack10,evChengeGreen10,evChangeRed10);
		((ShneyLuchot) listTwo.get(11)).init(evack11,evChengeGreen11,evChangeRed11);
		((ShneyLuchot) listTwo.get(12)).init(evack12,evChengeGreen12,evChangeRed12);
		((ShneyLuchot) listTwo.get(13)).init(evack13,evChengeGreen13,evChangeRed13);
		((ShneyLuchot) listTwo.get(14)).init(evack14,evChengeGreen14,evChangeRed14);
		((ShneyLuchot) listTwo.get(15)).init(evack15,evChengeGreen15,evChangeRed15);
		
		((ShloshaAvot) listThree.get(0)).init(evack0,evChengeGreen0,evChangeRed0);
		((ShloshaAvot) listThree.get(1)).init(evack1,evChengeGreen1,evChangeRed1);
		((ShloshaAvot) listThree.get(2)).init(evack2,evChengeGreen2,evChangeRed2);
		((ShloshaAvot) listThree.get(3)).init(evack3,evChengeGreen3,evChangeRed3);
		start();
	}
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
						//evack(0-15)/evChengeGreen(13,12,10,9,6,7,0)
						evack0.waitEvent();evack1.waitEvent();evack2.waitEvent();evack3.waitEvent();evack4.waitEvent();
						evack5.waitEvent();evack6.waitEvent();evack7.waitEvent();evack8.waitEvent();
						evack9.waitEvent();evack10.waitEvent();evack11.waitEvent();evack12.waitEvent();
						evack13.waitEvent();evack14.waitEvent();evack15.waitEvent();
						evChengeGreen13.sendEvent();evChengeGreen12.sendEvent();evChengeGreen10.sendEvent();
						evChengeGreen9.sendEvent();evChengeGreen6.sendEvent();evChengeGreen7.sendEvent();evChengeGreen0.sendEvent();
					while(true) {
						switch(state)
						{
						case green0:
						//tm(15000)/evChengeRed(0,9,10,12,13,7,6);	
							sleep(15000);
							//evChengeRed(0,9,10,12,13,7,6);//how we do it
							evChangeRed13.sendEvent();evChangeRed12.sendEvent();evChangeRed10.sendEvent();
							evChangeRed9.sendEvent();evChangeRed6.sendEvent();evChangeRed7.sendEvent();evChangeRed0.sendEvent();
							state=State.turn_0_red;
							break;
						case turn_0_red:
							//evack(0,9,10,12,7,6)/evChengeGreen(4,5,14,15,11,8,2,3)
							evack0.waitEvent();
							evack6.waitEvent();evack7.waitEvent();
							evack9.waitEvent();evack10.waitEvent();evack12.waitEvent();
							evChengeGreen4.sendEvent();evChengeGreen5.sendEvent();evChengeGreen14.sendEvent();
							evChengeGreen15.sendEvent();evChengeGreen11.sendEvent();evChengeGreen8.sendEvent();
							evChengeGreen2.sendEvent();evChengeGreen3.sendEvent();
							state=State.green2_3;
							break;
						case green2_3:
							//tm(15000)[else]/evChengeRed(3,11,8,14,15)
							sleep(15000);
							evChangeRed3.sendEvent();evChangeRed11.sendEvent();evChangeRed8.sendEvent();
							evChangeRed14.sendEvent();evChangeRed15.sendEvent();
							state=State.turn_3_red;
							break;
						case turn_3_red:
							//evack(3,11,8,14,15)/evChengeGreen(12,13,6,7,1)
							evack3.waitEvent();evack8.waitEvent();evack11.waitEvent();
							evack14.waitEvent();evack15.waitEvent();
							evChengeGreen12.sendEvent();evChengeGreen13.sendEvent();evChengeGreen6.sendEvent();
							evChengeGreen7.sendEvent();evChengeGreen1.sendEvent();
							state=State.green1_2;
						case green1_2:
							//tm(15000)[else]/evChengeRed(4,5,1,2)
							sleep(15000);
							evChangeRed4.sendEvent();evChangeRed5.sendEvent();evChangeRed1.sendEvent();
							evChangeRed2.sendEvent();
							state=State.turn1_2red;
							break;
						case turn1_2red:
						//evack(4,5,1,2)/evChengeGreen(13,12,10,9,6,7,0)
							evack4.waitEvent();evack5.waitEvent();
							evack1.waitEvent();evack2.waitEvent();
							evChengeGreen12.sendEvent();evChengeGreen13.sendEvent();evChengeGreen10.sendEvent();
							evChengeGreen7.sendEvent();evChengeGreen9.sendEvent();evChengeGreen6.sendEvent();
							evChengeGreen0.sendEvent();
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
