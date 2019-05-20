import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JRadioButton;





public class MyActionListener implements ActionListener
{
	Event64 evButtonRegel_9_10,evButtonRegel_11_8_15_14;
	public void init(Event64 evButtonRegel_9_10, Event64 evButtonRegel_11_8_15_14) {
		this.evButtonRegel_9_10=evButtonRegel_9_10;
		this.evButtonRegel_11_8_15_14=evButtonRegel_11_8_15_14;
	}
	public void actionPerformed(ActionEvent e) 
	{
		JRadioButton butt=(JRadioButton)e.getSource();
		System.out.println(butt.getName());
		if(butt.getName().equals(9)||butt.getName().equals(10))
		{
			evButtonRegel_9_10.sendEvent();
		}
		else if(butt.getName().equals(11)||butt.getName().equals(8)||butt.getName().equals(15)||butt.getName().equals(14))
		{
			evButtonRegel_11_8_15_14.sendEvent();
		}
		
		else if(butt.getName().equals(16))//לשבת
		{}
		
	}

}
