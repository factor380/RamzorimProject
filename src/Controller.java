import javax.swing.JRadioButton;

public class Controller  extends Thread
{
	enum State {green0,green1_2,green2_3,turn_0_red,turn_3_red,turn1_2red,from12_to_23,from23_to_0};
	enum OutState {regularDay,Shabat};
	State state;
	boolean out = false;
	ShloshaAvot listThree[] = new ShloshaAvot[4];
	ShneyLuchot listTwo[] = new ShneyLuchot[11]; 
	OutState outState;
	Object data,data2;
	Event64 buttonRegel9_10 = new Event64(),buttonRegel11_8_15_14 = new Event64(),stamButtonRegel = new Event64(),evShabat = new Event64(),stopEvShabat = new Event64();
	Event64 evack0 = new Event64(),evack1 = new Event64(),evack2 = new Event64(),evack3 = new Event64(),evack4 = new Event64(),evack5 = new Event64(),evack6 = new Event64(),evack7 = new Event64(),evack8 = new Event64(),evack9 = new Event64()
	,evack10 = new Event64(),evack11 = new Event64(),evack12 = new Event64(),evack13 = new Event64(),evack14 = new Event64(),evack15 = new Event64(),evChengeGreen0 = new Event64(),evChengeGreen1 = new Event64(),evChengeGreen2 = new Event64(),evChengeGreen3 = new Event64(),evChengeGreen4 = new Event64(),evChengeGreen5 = new Event64(),evChengeGreen6 = new Event64(),evChengeGreen7 = new Event64(),evChengeGreen8 = new Event64()
	,evChengeGreen9 = new Event64(),evChengeGreen10 = new Event64(),evChengeGreen11 = new Event64(),evChengeGreen12 = new Event64(),evChengeGreen13 = new Event64(),evChengeGreen14 = new Event64(),evChengeGreen15 = new Event64(),evChangeRed0 = new Event64(),evChangeRed1 = new Event64(),evChangeRed2 = new Event64(),evChangeRed3 = new Event64(),evChangeRed4 = new Event64(),evChangeRed5 = new Event64(),evChangeRed6 = new Event64()
	,evChangeRed7 = new Event64(),evChangeRed8 = new Event64(),evChangeRed9 = new Event64(),evChangeRed10 = new Event64(),evChangeRed11 = new Event64(),evChangeRed12 = new Event64(),evChangeRed13 = new Event64(),evChangeRed14 = new Event64(),evChangeRed15 = new Event64();
	public Controller (ShloshaAvot listThree[] ,ShneyLuchot listTwo[],MyActionListener myListener) {
		this.listThree = listThree;
		this.listTwo = listTwo;
		
		myListener.init(buttonRegel9_10, buttonRegel11_8_15_14,stamButtonRegel,evShabat,stopEvShabat);
		
		 listTwo[0].init(evack4,evChengeGreen4,evChangeRed4);
		 listTwo[1].init(evack5,evChengeGreen5,evChangeRed5);
		 listTwo[2].init(evack6,evChengeGreen6,evChangeRed6);
		 listTwo[3].init(evack7,evChengeGreen7,evChangeRed7);
		 listTwo[4].init(evack8,evChengeGreen8,evChangeRed8);
		 listTwo[5].init(evack9,evChengeGreen9,evChangeRed9);
		 listTwo[6].init(evack10,evChengeGreen10,evChangeRed10);
		 listTwo[7].init(evack11,evChengeGreen11,evChangeRed11);
		 listTwo[8].init(evack12,evChengeGreen12,evChangeRed12);
		 listTwo[9].init(evack13,evChengeGreen13,evChangeRed13);
		 listTwo[10].init(evack14,evChengeGreen14,evChangeRed14);
		 listTwo[11].init(evack15,evChengeGreen15,evChangeRed15);
		
		listThree[0].init(evack0,evChengeGreen0,evChangeRed0,evShabat,stopEvShabat);
		listThree[1].init(evack1,evChengeGreen1,evChangeRed1,evShabat,stopEvShabat);
		listThree[2].init(evack2,evChengeGreen2,evChangeRed2,evShabat,stopEvShabat);
		listThree[3].init(evack3,evChengeGreen3,evChangeRed3,evShabat,stopEvShabat);
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
					while(!out) {
						switch(state)
						{
						case green0:
							if(buttonRegel11_8_15_14.arrivedEvent()) {
								data = buttonRegel11_8_15_14.waitEvent();
							}
							if(data != null)
							    ((JRadioButton) data).setSelected(false);
							if(evShabat.arrivedEvent()) {
								evShabat.waitEvent();
								out=true;
								outState=OutState.Shabat;
							}
							else {
						//tm(7000)/evChengeRed(0,9,10,12,13,7,6);	
							sleep(7000);
							//evChengeRed(0,9,10,12,13,7,6);//how we do it
							evChangeRed13.sendEvent();evChangeRed12.sendEvent();evChangeRed10.sendEvent();
							evChangeRed9.sendEvent();evChangeRed6.sendEvent();evChangeRed7.sendEvent();evChangeRed0.sendEvent();
							state=State.turn_0_red;
							}
							break;
						case turn_0_red:
							if(evShabat.arrivedEvent()) {
								evShabat.waitEvent();
								out=true;
								outState=OutState.Shabat;
							}
							else {
							//evack(0,9,10,12,13,7,6)/evChengeGreen(4,5,14,15,11,8,2,3)
							evack0.waitEvent();evack13.waitEvent();
							evack6.waitEvent();evack7.waitEvent();
							evack9.waitEvent();evack10.waitEvent();evack12.waitEvent();
							evChengeGreen4.sendEvent();evChengeGreen5.sendEvent();evChengeGreen14.sendEvent();
							evChengeGreen15.sendEvent();evChengeGreen11.sendEvent();evChengeGreen8.sendEvent();
							evChengeGreen2.sendEvent();evChengeGreen3.sendEvent();
							if(stamButtonRegel.arrivedEvent()) {
								data2 = stamButtonRegel.waitEvent();
							((JRadioButton) data2).setSelected(false);
							}
							state=State.green2_3;
							}
							break;
						case green2_3:
							if(buttonRegel11_8_15_14.arrivedEvent()) {
								data = buttonRegel11_8_15_14.waitEvent();
							}
							if(data != null)
							    ((JRadioButton) data).setSelected(false);
							if(evShabat.arrivedEvent()) {
								evShabat.waitEvent();
								out=true;
								outState=OutState.Shabat;
								break;
							}
							//tm(7000)[button_regel=9,10]/evChengeRed(4,5,14,15,11,8,2,3)
							sleep(7000);
							if(buttonRegel9_10.arrivedEvent()) {
								data = buttonRegel9_10.waitEvent();
								evChangeRed4.sendEvent();evChangeRed5.sendEvent();evChangeRed14.sendEvent();evChangeRed15.sendEvent();
								evChangeRed11.sendEvent();evChangeRed8.sendEvent();evChangeRed2.sendEvent();evChangeRed3.sendEvent();
								state = State.from23_to_0;
							}
							//tm(7000)[else]/evChengeRed(3,11,8,14,15)
							else {
							evChangeRed3.sendEvent();evChangeRed11.sendEvent();evChangeRed8.sendEvent();
							evChangeRed14.sendEvent();evChangeRed15.sendEvent();
							state=State.turn_3_red;
							}
							break;
						case turn_3_red:
							if(stamButtonRegel.arrivedEvent()) {
								data2 = stamButtonRegel.waitEvent();
							((JRadioButton) data2).setSelected(false);
							}
							if(evShabat.arrivedEvent()) {
								evShabat.waitEvent();
								out=true;
								outState=OutState.Shabat;
							}
							else {
							//evack(3,11,8,14,15)/evChengeGreen(12,13,6,7,1)
							evack3.waitEvent();evack8.waitEvent();evack11.waitEvent();
							evack14.waitEvent();evack15.waitEvent();
							evChengeGreen12.sendEvent();evChengeGreen13.sendEvent();evChengeGreen6.sendEvent();
							evChengeGreen7.sendEvent();evChengeGreen1.sendEvent();
							state=State.green1_2;
							}
							break;
						case green1_2:
							if(evShabat.arrivedEvent()) {
								evShabat.waitEvent();
								out=true;
								outState=OutState.Shabat;
								break;
							}
							sleep(7000);
							//tm(7000)[button_regel=11,8,15,14]/evChengeRed(13,12,7,6,1)
							if(buttonRegel11_8_15_14.arrivedEvent()) {
								data = buttonRegel11_8_15_14.waitEvent();
								evChangeRed13.sendEvent();evChangeRed12.sendEvent();
								evChangeRed7.sendEvent();evChangeRed6.sendEvent();evChangeRed1.sendEvent();
								state = State.from12_to_23;
							}
							//tm(7000)[else]/evChengeRed(4,5,1,2)
							else {
							evChangeRed4.sendEvent();evChangeRed5.sendEvent();evChangeRed1.sendEvent();
							evChangeRed2.sendEvent();
							state=State.turn1_2red;
							}
							break;
						case turn1_2red:
							if(buttonRegel9_10.arrivedEvent()) {
								data = buttonRegel9_10.waitEvent();
								((JRadioButton) data).setSelected(false);
							}
							if(stamButtonRegel.arrivedEvent()) {
								data2 = stamButtonRegel.waitEvent();
							((JRadioButton) data2).setSelected(false);
							}
							if(evShabat.arrivedEvent()) {
								evShabat.waitEvent();
								out=true;
								outState=OutState.Shabat;
							}
							else {
						  //evack(4,5,1,2)/evChengeGreen(10,9,0)
							evack4.waitEvent();evack5.waitEvent();
							evack1.waitEvent();evack2.waitEvent();
							evChengeGreen10.sendEvent();evChengeGreen9.sendEvent();evChengeGreen0.sendEvent();
							state=State.green0;
							}
							break;
						case from23_to_0:
							if(evShabat.arrivedEvent()) {
								evShabat.waitEvent();
								out=true;
								outState=OutState.Shabat;
							}
							else {
							//evack(4,5,14,15,11,8,2,3)/evChengeGreen(13,12,10,9,6,7,0)
							evack4.waitEvent();evack5.waitEvent();evack14.waitEvent();evack15.waitEvent();
						    evack11.waitEvent();evack8.waitEvent();evack2.waitEvent();evack3.waitEvent();
						    evChengeGreen13.sendEvent();evChengeGreen12.sendEvent();evChengeGreen10.sendEvent();evChengeGreen9.sendEvent();
						    evChengeGreen6.sendEvent();evChengeGreen7.sendEvent();evChengeGreen0.sendEvent();
						    state = State.green0;
							}
							break;
						case from12_to_23:
							if(evShabat.arrivedEvent()) {
								evShabat.waitEvent();
								out=true;
								outState=OutState.Shabat;
							}
							else {
							//evack(13,12,7,6,1)/evChengeGreen(14,15,11,8,3)
							evack13.waitEvent();evack12.waitEvent();evack7.waitEvent();
							evack6.waitEvent();evack1.waitEvent();
							evChengeGreen14.sendEvent();evChengeGreen15.sendEvent();evChengeGreen11.sendEvent();
							evChengeGreen8.sendEvent();evChengeGreen3.sendEvent();
							state = State.green2_3;
							}
							break;
						
						}
					}
				case Shabat:
					evChangeRed0.sendEvent();evChangeRed1.sendEvent();evChangeRed2.sendEvent();evChangeRed3.sendEvent();
					evChangeRed4.sendEvent();evChangeRed5.sendEvent();evChangeRed6.sendEvent();evChangeRed7.sendEvent();
					evChangeRed8.sendEvent();evChangeRed9.sendEvent();evChangeRed10.sendEvent();evChangeRed11.sendEvent();
					evChangeRed12.sendEvent();evChangeRed13.sendEvent();evChangeRed14.sendEvent();evChangeRed15.sendEvent();
					evack0.waitEvent();evack1.waitEvent();evack2.waitEvent();evack3.waitEvent();
					evack4.waitEvent();evack5.waitEvent();evack6.waitEvent();evack7.waitEvent();
					evack8.waitEvent();evack9.waitEvent();evack10.waitEvent();evack11.waitEvent();
                    evack12.waitEvent();evack13.waitEvent();evack14.waitEvent();evack15.waitEvent();
					stopEvShabat.waitEvent();
					out=false;
					outState=OutState.regularDay;
					state = State.green0;
					break;
			}
		}
			
		} catch (InterruptedException e) {}

	}

}
