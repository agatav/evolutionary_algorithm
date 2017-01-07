import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;

public class Controller extends AbstractAction{
	
	private Model model;
	private Chart chart;
	Controller()
	{
		chart = new Chart("Test", "(mi, lambda)","(mi, lambda)");
		model = new Model();
		chart.start(model);
	}
	
	public static void main(String[] args)
	{
		Controller controller = new Controller();
		return;
	}
	
	

		public Controller(String name) {
	    	super("Update  " + name);
	    }

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			
		}
}
