import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;

public class Controller extends JFrame implements ActionListener{

	private static final long serialVersionUID = 1L;
	Model model;
	Chart chart;
	
	Controller()
	{

	}

	public Controller(String name) {
	    super("Update  " + name);
	}

	public void addModel(Model m){
		this.model = m;
	}

	public void addView(Chart v){
		this.chart = v;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == chart.button1){
			chart.panel.setVisible(false);
			chart.start(model);
			chart.createJedenPludJeden();
		}
		if(e.getSource() == chart.button2){
			chart.panel.setVisible(false);
			chart.start(model);
			chart.createMiLambda();
		}
		if(e.getSource() == chart.b){
			chart.update();
		}
	}
}
