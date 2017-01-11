import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;

public class Controller extends JFrame implements ActionListener{

	private static final long serialVersionUID = 1L;
	Model model;
	Chart chart;
	int i, mi, lambda, m, numberOfPop;
	double c1, c2;
	
	Controller()
	{
		i = 0;
		mi = 20;
		lambda = 140;
		m = 10;
		c1 = 1.2;
		c2 = 0.8;
		numberOfPop = 50;
	}


	public void addView(Chart v){
		this.chart = v;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		/*przycisk (1+1)*/
		if(e.getSource() == chart.button1){
			chart.panel.setVisible(false);
			model = new Model(m, c1, c2);
			chart.create1Plus1();
			chart.drawChart1Plus1(model, m, c1, c2, numberOfPop);
		}
		/*przycisk (mi, labmda)*/
		if(e.getSource() == chart.button2){
			chart.panel.setVisible(false);
			model = new Model(mi, lambda);
			chart.createMiLambda();
			chart.drawChartMiLambda(model, i, mi, lambda, numberOfPop);
		}
		/*przycisk update (mi, lambda)*/
		if(e.getSource() == chart.bMiLambda){
			mi = chart.getMi(mi);
			lambda = chart.getLambda(lambda);
			numberOfPop = chart.getNumberOfPopulation(numberOfPop);
			if(i >= numberOfPop) i = numberOfPop;
			chart.updateMiLambda(mi, lambda);
			chart.drawChartMiLambda(model, i, mi, lambda, numberOfPop);
		}
		/*przycisk update (1+1)*/
		if(e.getSource() == chart.b1plus1){
			m = chart.getM(m);
			c1 = chart.getC1(c1);
			c2 = chart.getC2(c2);
			numberOfPop = chart.getNumberOfPopulation(numberOfPop);
			if(i >= numberOfPop) i = numberOfPop;
			chart.update1plus1(m, c1, c2);
			chart.drawChart1Plus1(model, m, c1, c2, numberOfPop);
		}
		/*nasteona populacja*/
		if(e.getSource() == chart.next){
			i++;
			if(i >= numberOfPop) i = numberOfPop;
			chart.setPop(model, i);
		}
		/*poprzednia populacja*/
		if(e.getSource() == chart.prev){
			i--;
			if(i <= 0) i = 0;
			chart.setPop(model, i);
		}
		/*i-ta populacja*/
		if(e.getSource() == chart.submit){
			i = chart.whichPop(i);
			if(i <= 0) i = 0;
			if(i >= numberOfPop) i = numberOfPop;
			chart.setPop(model, i);
		}

	}
}
