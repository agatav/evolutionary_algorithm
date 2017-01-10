import org.jfree.chart.ChartPanel; 
import org.jfree.chart.JFreeChart;
import org.jfree.data.xy.XYSeries; 
import org.jfree.ui.ApplicationFrame;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYItemRenderer;
import org.jfree.data.xy.XYSeriesCollection; 

public class Chart extends ApplicationFrame 
{
	private static final long serialVersionUID = 1L;
	
	Model model;
	Controller controller;
	JFreeChart chartX, chartZ;
	
	JFrame frame = new JFrame("Evolution algorithm");
	JPanel panel = new JPanel();
	JPanel chartPanelMiLambda = new JPanel();
	JButton button1, button2, bMiLambda, b1plus1, prev, next, submit;
	
	/*funkcje na wykresach-> fitness, punkty populacji, punkt najlepszy, empty->ustawia skale wykresu [-20,20]*/
	final XYSeries fitness = new XYSeries("Fitness"); 
	final XYSeries seriesX = new XYSeries("XY");
	final XYSeries seriesZ = new XYSeries("ZY");
	final XYSeries bestX = new XYSeries("best");
	final XYSeries bestZ = new XYSeries("best");
	final XYSeries emptyX = new XYSeries("");
	final XYSeries emptyZ = new XYSeries("");

	/*pola do tekstu x, y, z, funkcji, populacji, parametrow funkcji*/
	JLabel labelx = new JLabel("x");
    JLabel labely = new JLabel("y");
    JLabel labelz = new JLabel("z");
    JLabel labelf = new JLabel("f");
    JLabel labelg = new JLabel("");
    JLabel labelAlg = new JLabel("");
	JLabel labelEmpty = new JLabel(" ");
	JLabel labelEmpty2 = new JLabel(" ");
	/*pole do wpisywania populacji i parametrow*/
    JTextField whichPop, mi, lambda, c1, c2, m;
	
	Chart(String applicationTitle, String chartTitle)
   {
      super(applicationTitle);
      
      textDecoration(16.0f, 5);
      
      button1 = new JButton("1+1");
      button2 = new JButton("(mi, lambda)");
      
      JPanel textPanel = new JPanel();
      textPanel.setLayout(new BoxLayout(textPanel, BoxLayout.Y_AXIS));
      
      panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
      
      JPanel controlPanel = new JPanel();
      controlPanel.setLayout(new FlowLayout());    
      controlPanel.add(button1);
      controlPanel.add(button2);
      
      panel.add(textPanel);
      panel.add(controlPanel);      
      Frame(frame, panel);
   }
	
	public void addController(Controller controller){
		this.controller = controller;
		
		button1.addActionListener(controller);
		button2.addActionListener(controller);
	}

	public void updateMiLambda(int mi, int lambda){
		if (model.update(mi, lambda));
	}
	public void update1plus1(int between, double multiplier1, double multiplier2){
		if (model.update(between, multiplier1, multiplier2));
	}
	
	public JFreeChart createLineChart(String chartTitle, String x, String y, XYSeries series){
		final XYSeriesCollection dataset = new XYSeriesCollection();
	      dataset.addSeries(series);
	      
	      JFreeChart xylineChart = ChartFactory.createXYLineChart(//parametry wykresu
	         chartTitle, x, y, dataset, PlotOrientation.VERTICAL, true, true, false);
	      return xylineChart;
	}
	/*panel z przyciskami next prev*/
	private JPanel nextPrevPanel(boolean miLambda, Controller controller){
		this.controller = controller;
		if(miLambda){
			  whichPop = new JTextField("");
			  whichPop.setPreferredSize(new Dimension( 50, 24 ));
			  JPanel nextPrevPanel = new JPanel();
		      nextPrevPanel.setLayout(new FlowLayout(2, 15, 5));
		      nextPrevPanel.add(prev);
		      nextPrevPanel.add(next);
		      nextPrevPanel.add(whichPop);
		      nextPrevPanel.add(submit);
		      prev.addActionListener(controller);
			  next.addActionListener(controller);
			  submit.addActionListener(controller);
		      return nextPrevPanel;
	      }
		return new JPanel();
	}
	/*panel z tekstem - parametry algorytmu, x, y, z, funkcja, populacja*/
	private JPanel textPanel(){
		JPanel textPanel = new JPanel();
	      textPanel.setLayout(new BoxLayout(textPanel, BoxLayout.Y_AXIS));
	      textPanel.add(labelAlg);
	      textPanel.add(labelg);
	      textPanel.add(labelEmpty);
	      textPanel.add(labelx);
	      textPanel.add(labely);
	      textPanel.add(labelz);
	      textPanel.add(labelf);
	      textPanel.add(labelEmpty2);
	      return textPanel;
	}
	/*pola do wpisywania parametry funkcji*/
	private JPanel paramPanel(boolean miLambda){
		JPanel panel = new JPanel();
		panel.setMaximumSize(new Dimension(300, 30));
		if(miLambda){
			mi = new JTextField("mi");
			lambda = new JTextField("lambda");
			mi.setPreferredSize(new Dimension(50, 24));
			lambda.setPreferredSize(new Dimension(50, 24));
			panel.add(mi);
			panel.add(lambda);
		}
		else{
			m = new JTextField("m");
			c1 = new JTextField("c1");
			c2 = new JTextField("c2");
			m.setPreferredSize(new Dimension(50, 24));
			c1.setPreferredSize(new Dimension(50, 24));
			c2.setPreferredSize(new Dimension(50, 24));
			panel.add(m);
			panel.add(c1);
			panel.add(c2);
		}
	      return panel;
	}
	private void CreateFrame(JFreeChart xylineChart, JFreeChart chartX, JFreeChart chartZ, JPanel panel, Controller controller, JButton b, boolean miLambda){
		  this.controller = controller;
		  b.addActionListener(controller);
	      	      
	      panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
	      
	      JPanel controlPanel = new JPanel();
	      controlPanel.setLayout(new BoxLayout(controlPanel, BoxLayout.Y_AXIS));
	      controlPanel.add(textPanel());
	      controlPanel.add(paramPanel(miLambda));
	      controlPanel.add(b);

	      panel.add(ChartPanel(xylineChart, controlPanel));
	      panel.add(nextPrevPanel(miLambda, controller));
	      panel.add(XYZChartPanel(chartX));
	      panel.add(XYZChartPanel(chartZ));
	}
	/*okno z wykresem (mi,lambda)*/
	public void createMiLambda(){
		  bMiLambda = new JButton("Update (mi, lambda)");
		  prev = new JButton("Prev");
		  next = new JButton ("Next");
		  submit = new JButton("Submit");
		  
		  final XYSeriesCollection datasetX = new XYSeriesCollection();
		  datasetX.addSeries(bestX);  
		  datasetX.addSeries(seriesX);
		  datasetX.addSeries(emptyX);
	      chartX = ChartFactory.createScatterPlot("", "X", "Y", datasetX, PlotOrientation.VERTICAL, true, true, false);
	      
	      final XYSeriesCollection datasetZ = new XYSeriesCollection();
		  datasetZ.addSeries(bestZ);  
		  datasetZ.addSeries(seriesZ);
		  datasetZ.addSeries(emptyZ);
	      chartZ = ChartFactory.createScatterPlot("", "X", "Y", datasetZ, PlotOrientation.VERTICAL, true, true, false);
	          
		CreateFrame(createLineChart("(mi, lambda)", "i", "f(i)", fitness),chartX, chartZ,
				  		chartPanelMiLambda, controller, bMiLambda, true);
		frame.add(chartPanelMiLambda, BorderLayout.CENTER);
		frame.setPreferredSize(new Dimension(800, 750));
		frame.pack();
		frame.setLocation(100, 0);
		
	}
	/*okno z wykresem (1+1)*/
	public void create1Plus1(){
		b1plus1 = new JButton("update (1+1)");
		
		final XYSeriesCollection datasetX = new XYSeriesCollection();
		  datasetX.addSeries(bestX);  
		  datasetX.addSeries(seriesX);
		  datasetX.addSeries(emptyX);
	      chartX = ChartFactory.createScatterPlot("", "X", "Y", datasetX, PlotOrientation.VERTICAL, true, true, false);
	      
	      final XYSeriesCollection datasetZ = new XYSeriesCollection();
		  datasetZ.addSeries(bestZ);  
		  datasetZ.addSeries(seriesZ);
		  datasetZ.addSeries(emptyZ);
	      chartZ = ChartFactory.createScatterPlot("", "X", "Y", datasetZ, PlotOrientation.VERTICAL, true, true, false);
	     
		CreateFrame(createLineChart("(1+1)", "i", "f(i)", fitness), chartX, chartZ,
				  chartPanelMiLambda, controller, b1plus1, false);
		frame.add(chartPanelMiLambda, BorderLayout.CENTER);
		frame.setPreferredSize(new Dimension(800, 750));
		frame.pack();
		frame.setLocation(100, 0);
	}
	
	/*tworzy okno*/
	private void Frame(JFrame frame, JPanel chartPanel){
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    frame.add(chartPanel, BorderLayout.CENTER);
	    frame.pack();
	    frame.setLocationRelativeTo(null);
	    frame.setVisible(true);
	}
	/**/
	private JPanel ChartPanel(JFreeChart p, JPanel controlPanel){
		ChartPanel chart = new ChartPanel(p);
		JPanel panel = new JPanel();
		panel.setLayout(new BoxLayout(panel, BoxLayout.X_AXIS));
	    panel.add(chart);
	    panel.add(controlPanel);
		return panel;
	}
	/**/
	private JPanel XYZChartPanel(JFreeChart p){
		ChartPanel chart = new ChartPanel(p);
		JPanel panel = new JPanel();
		panel.setLayout(new BoxLayout(panel, BoxLayout.X_AXIS));
	    panel.add(chart);
		return panel;
	}
	/*rusuje wykres (1+1) i XY ZX*/
	protected void drawChart1Plus1(Model model, int m, double c1, double c2)
	{ 
		this.model = model;
		fitness.clear();
		seriesX.clear();
		seriesZ.clear();
		bestX.clear();
		bestZ.clear();
		for (int i=0;i<100;i++)
			{
				model.addGen();
			}
			int i=0;
			for (Evolving<ProjectEvolvingArgs> e : model.getBests())
			{				
				fitness.add(i++, e.getFitness());	
				seriesX.add(e.getArgs().getY(), e.getArgs().getX());
				seriesZ.add(e.getArgs().getY(), e.getArgs().getZ());
			}

			bestX.add(model.getBests().get(model.getBests().size()-1).getArgs().getY(), model.getBests().get(model.getBests().size()-1).getArgs().getX());
			bestZ.add(model.getBests().get(model.getBests().size()-1).getArgs().getY(), model.getBests().get(model.getBests().size()-1).getArgs().getZ());
			emptyX.add(-20, -20); emptyX.add(20, 20);
			emptyZ.add(-20, -20); emptyZ.add(20, 20);
			
			XYPlot plotX = (XYPlot) chartX.getPlot();
			XYItemRenderer rendererX = plotX.getRenderer();
			rendererX.setSeriesVisible(2, false);
			
			XYPlot plotZ = (XYPlot) chartZ.getPlot();
			XYItemRenderer rendererZ = plotZ.getRenderer();
			rendererZ.setSeriesVisible(2, false);
			
			setText("x", model.getBests().get(model.getBests().size()-1).getArgs().getX(), labelx);
			setText("y", model.getBests().get(model.getBests().size()-1).getArgs().getY(), labely);
			setText("z", model.getBests().get(model.getBests().size()-1).getArgs().getZ(), labelz);
			setText("Function", model.getBests().get(model.getBests().size()-1).getFitness(), labelf);
			labelAlg.setText("m = "+m+",   c1 = "+c1+",   c2 = "+c2);
	}
	/*rysuje (mi,lambda) i XY ZY*/
	protected void drawChartMiLambda(Model model, int pop, int mi, int lambda)
	{ 
		this.model = model;
		fitness.clear();
		seriesX.clear();
		seriesZ.clear();
		bestX.clear();
		bestZ.clear();
		for (int i=0;i<100;i++)
			{
				model.addGen();
			}
			int i=0;
			for (Evolving<ProjectEvolvingArgs> e : model.getBests())
			{				
				fitness.add(i++, e.getFitness());			
			}
			for (Evolving<ProjectEvolvingArgs> e : model.getPop(pop))
			{				
				seriesX.add(e.getArgs().getY(), e.getArgs().getX());
				seriesZ.add(e.getArgs().getY(), e.getArgs().getZ());
			}
			bestX.add(model.getPop(pop).get(0).getArgs().getY(), model.getPop(pop).get(0).getArgs().getX());
			bestZ.add(model.getPop(pop).get(0).getArgs().getY(), model.getPop(pop).get(0).getArgs().getZ());
			emptyX.add(-20, -20); emptyX.add(20, 20);
			emptyZ.add(-20, -20); emptyZ.add(20, 20);
			
			XYPlot plotX = (XYPlot) chartX.getPlot();
			XYItemRenderer rendererX = plotX.getRenderer();
			rendererX.setSeriesVisible(2, false);
			
			XYPlot plotZ = (XYPlot) chartZ.getPlot();
			XYItemRenderer rendererZ = plotZ.getRenderer();
			rendererZ.setSeriesVisible(2, false);
			
			setText("x", model.getBests().get(model.getBests().size()-1).getArgs().getX(), labelx);
			setText("y", model.getBests().get(model.getBests().size()-1).getArgs().getY(), labely);
			setText("z", model.getBests().get(model.getBests().size()-1).getArgs().getZ(), labelz);
			setText("Function", model.getBests().get(model.getBests().size()-1).getFitness(), labelf);
			labelg.setText("Population = "+pop);
			labelAlg.setText("mi = "+mi+",   lambda = "+lambda);
	}
	/*ustawia dla XY i ZY populacje o indeksie pop*/
	protected void setPop(Model model, int pop){
		this.model = model;
		bestX.clear();
		bestZ.clear();
		seriesX.clear();
		seriesZ.clear();
		for (Evolving<ProjectEvolvingArgs> e : model.getPop(pop))
		{				
			seriesX.add(e.getArgs().getY(), e.getArgs().getX());
			seriesZ.add(e.getArgs().getY(), e.getArgs().getZ());
		}
		bestX.add(model.getPop(pop).get(0).getArgs().getY(), model.getPop(pop).get(0).getArgs().getX());
		bestZ.add(model.getPop(pop).get(0).getArgs().getY(), model.getPop(pop).get(0).getArgs().getZ());
		
		XYPlot plotX = (XYPlot) chartX.getPlot();
		XYItemRenderer rendererX = plotX.getRenderer();
		rendererX.setSeriesVisible(2, false);
		
		XYPlot plotZ = (XYPlot) chartZ.getPlot();
		XYItemRenderer rendererZ = plotZ.getRenderer();
		rendererZ.setSeriesVisible(2, false);
		
		labelg.setText("Population = "+pop);
	}
	protected void setText(String s, double x, JLabel name)
    {          
		name.setText(s+" = "+x);
    }
	
	private void textDecoration(float size, int font)
	{
		labelx.setFont(labelx.getFont().deriveFont(size));
		labely.setFont(labely.getFont().deriveFont(size));
		labelz.setFont(labelz.getFont().deriveFont(size));
		labelf.setFont(labelf.getFont().deriveFont(size));
		labelg.setFont(labelg.getFont().deriveFont(size));
		labelAlg.setFont(labelAlg.getFont().deriveFont(size));
		
		labelx.setFont(labelx.getFont().deriveFont(font));
		labely.setFont(labely.getFont().deriveFont(font));
		labelz.setFont(labelz.getFont().deriveFont(font));
		labelf.setFont(labelf.getFont().deriveFont(font));
		labelg.setFont(labelg.getFont().deriveFont(font));
		labelAlg.setFont(labelAlg.getFont().deriveFont(font));
	}
	/*do pobierania liczb z pola tekstowego*/
	int whichPop(int i){
		 try {	
			i = Integer.parseInt(whichPop.getText());
		 } catch (NumberFormatException e) {
	    }
		whichPop.setText("");
		return i;
	}
	int getMi(int i){
		 try {	
			i = Integer.parseInt(mi.getText());
		 } catch (NumberFormatException e) {
	    }
		mi.setText("mi");
		return i;
	}
	int getLambda(int i){
		 try {	
			i = Integer.parseInt(lambda.getText());
		 } catch (NumberFormatException e) {
	    }
		lambda.setText("lambda");
		return i;
	}
	int getM(int i){
		 try {	
			i = Integer.parseInt(m.getText());
		 } catch (NumberFormatException e) {
	    }
		m.setText("m");
		return i;
	}
	double getC1(double i){
		 try {	
			i = Double.parseDouble(c1.getText());
		 } catch (NumberFormatException e) {
	    }
		c1.setText("c1");
		return i;
	}
	double getC2(double i){
		 try {	
			i = Double.parseDouble(c2.getText());
		 } catch (NumberFormatException e) {
	    }
		c2.setText("c2");
		return i;
	}
	
}