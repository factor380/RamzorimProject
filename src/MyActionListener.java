import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JRadioButton;





public class MyActionListener implements ActionListener
{
	Event64 buttonRegel9_10,buttonRegel11_8_15_14,stamButtonRegel,evShabat,stopEvShabat;
	public void init(Event64 buttonRegel9_10, Event64 buttonRegel11_8_15_14,Event64 stamButtonRegel,Event64 evShabat,Event64 stopEvShabat) {
		this.buttonRegel9_10 = buttonRegel9_10;
		this.buttonRegel11_8_15_14 = buttonRegel11_8_15_14;
		this.stamButtonRegel = stamButtonRegel;
		this.evShabat = evShabat;
		this.stopEvShabat = stopEvShabat;
	}
	public void actionPerformed(ActionEvent e) 
	{
		JRadioButton butt=(JRadioButton)e.getSource();
		System.out.println(butt.getName());
		if(butt.getName().equals("9")||butt.getName().equals("10"))
		{
			buttonRegel9_10.sendEvent(butt);
		}
		else if(butt.getName().equals("11")||butt.getName().equals("8")||butt.getName().equals("15")||butt.getName().equals("14"))
		{
			buttonRegel11_8_15_14.sendEvent(butt);
		}
		
		else if(butt.getName().equals("16"))//לשבת
		{
			if(!butt.isSelected())
				stopEvShabat.sendEvent();
			else
				evShabat.sendEvent();
		}
		else {
			stamButtonRegel.sendEvent(butt);
		}
	}

}
