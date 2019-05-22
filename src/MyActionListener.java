import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JRadioButton;





public class MyActionListener implements ActionListener
{
	boolean shabat;
	Event64 ButtonRegel,evShabat,stopEvShabat;
	static boolean flag=false; 
	public void init(Event64 ButtonRegel,Event64 evShabat,Event64 stopEvShabat) {
		this.ButtonRegel = ButtonRegel;
		this.evShabat = evShabat;
		this.stopEvShabat = stopEvShabat;
	}
	public void actionPerformed(ActionEvent e) 
	{
		JRadioButton butt=(JRadioButton)e.getSource();
		System.out.println(butt.getName());
		if(flag&&!butt.getName().equals("16"))
		{
			butt.setSelected(false);
			return;
		}
			
		if(shabat && !(butt.getName().equals("16"))) {
			butt.setSelected(false);
		}
		else if(butt.getName().equals("16"))//לשבת
		{
			if(!butt.isSelected()) {
				stopEvShabat.sendEvent();
				shabat = false;
			}
			else {
				evShabat.sendEvent();
				shabat = true;
			}
		}
		else {
			ButtonRegel.sendEvent(butt);
			flag=true;
		}
	}

}
