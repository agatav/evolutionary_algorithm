import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;

public class Controller extends JFrame implements ActionListener{

	private static final long serialVersionUID = 1L;
	Model model;
	Chart chart;
	int i, mi, lambda, m;
	double c1, c2;
	
	Controller()
	{
		i = 0;
		mi = 20;
		lambda = 140;
		m = 10;
		c1 = 1.2;
		c2 = 0.8;
	}


	public void addView(Chart v){
		this.chart = v;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == chart.button1){
			chart.panel.setVisible(false);
			model = new Model(m, c1, c2);
			chart.create1Plus1();
			chart.drawChart1Plus1(model, m, c1, c2);
		}
		if(e.getSource() == chart.button2){
			chart.panel.setVisible(false);
			model = new Model(mi, lambda);
			chart.createMiLambda();
			chart.drawChartMiLambda(model, i, mi, lambda);
		}
		if(e.getSource() == chart.bMiLambda){
			mi = chart.getMi(mi);
			lambda = chart.getLambda(lambda);
			chart.updateMiLambda(mi, lambda);
			chart.drawChartMiLambda(model, i, mi, lambda);
		}
		if(e.getSource() == chart.b1plus1){
			m = chart.getM(m);
			c1 = chart.getC1(c1);
			c2 = chart.getC2(c2);
			chart.update1plus1(m, c1, c2);
			chart.drawChart1Plus1(model, m, c1, c2);
		}
		if(e.getSource() == chart.next){
			i++;
			if(i >= 100) i = 100;
			chart.setPopGen(model, i);
		}
		if(e.getSource() == chart.prev){
			i--;
			if(i <= 0) i = 0;
			chart.setPopGen(model, i);
		}
		if(e.getSource() == chart.submit){
			i = chart.whichGen(i);
			if(i <= 0) i = 0;
			if(i >= 100) i = 100;
			chart.setPopGen(model, i);
		}

	}
}
