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


	public void addView(Chart v){
		this.chart = v;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == chart.button1){
			chart.panel.setVisible(false);
			model = new Model(10,1.2,0.8);
			chart.start(model);
			chart.create1Plus1();
		}
		if(e.getSource() == chart.button2){
			chart.panel.setVisible(false);
			model = new Model(20, 140);
			chart.start(model);
			chart.createMiLambda();
		}
		if(e.getSource() == chart.bMiLambda){
			chart.update(20, 140);
			chart.start(model);
		}
		if(e.getSource() == chart.b1plus1){
			chart.update(10,1.2,0.8);
			chart.start(model);
		}

	}
}
