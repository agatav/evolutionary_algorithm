import org.jfree.chart.ChartPanel; 
import org.jfree.chart.JFreeChart;
import org.jfree.data.xy.XYSeries; 
import org.jfree.ui.ApplicationFrame;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.xy.XYSeriesCollection; 

public class Chart extends ApplicationFrame 
{
	private static final long serialVersionUID = 1L;
	
	Model model;
	Controller controller;
	
	JFrame frame = new JFrame("Coœ tam");
	JPanel panel = new JPanel();
	JPanel chartPanelMiLambda = new JPanel();
	JButton button1, button2, bMiLambda, b1plus1;
	
	final XYSeries fitness = new XYSeries("Fitness");//jak chcesz dodac druga funkcje to tu 
	final XYSeries seriesX = new XYSeries("XY");
	final XYSeries seriesZ = new XYSeries("ZY");

	/*pole do tekstu x, y, z, funkcji*/
	JLabel labelx = new JLabel("x");
    JLabel labely = new JLabel("y");
    JLabel labelz = new JLabel("z");
    JLabel labelf = new JLabel("f");
	
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
		
		button1.addActionListener(controller);	//need controller before adding it as a listener 
		button2.addActionListener(controller);
	}

	public void update(int mi, int lambda){
		if (model.update(mi, lambda)) System.out.println("true");
	}
	public void update(int between, double multiplier1, double multiplier2){
		if (model.update(between, multiplier1, multiplier2)) System.out.println("true");
	}
	
	public JFreeChart createLineChart(String chartTitle, String x, String y, XYSeries series){
		final XYSeriesCollection dataset = new XYSeriesCollection();
	      dataset.addSeries(series);
	      
	      JFreeChart xylineChart = ChartFactory.createXYLineChart(//parametry wykresu
	         chartTitle, x, y, dataset, PlotOrientation.VERTICAL, true, true, false);
	      return xylineChart;
	}
	public JFreeChart createScatterPlot(String chartTitle, String x, String y, XYSeries series){
		final XYSeriesCollection dataset = new XYSeriesCollection();
	      dataset.addSeries(series);

	      
	      JFreeChart xylineChart = ChartFactory.createScatterPlot(//parametry wykresu
	         chartTitle, x, y, dataset, PlotOrientation.VERTICAL, true, true, false);
	      return xylineChart;
	}
	private void CreateFrame(JFreeChart xylineChart, JFreeChart chartX, JFreeChart chartZ, JPanel panel, Controller controller, JButton b){
		  this.controller = controller;
		  b.addActionListener(controller);
		  
	      JPanel textPanel = new JPanel();
	      textPanel.setLayout(new BoxLayout(textPanel, BoxLayout.Y_AXIS));
	      textPanel.add(labelx);
	      textPanel.add(labely);
	      textPanel.add(labelz);
	      textPanel.add(labelf);
	      
	      panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
	      
	      JPanel controlPanel = new JPanel();
	      controlPanel.setLayout(new BoxLayout(controlPanel, BoxLayout.Y_AXIS));
	      controlPanel.add(textPanel);
	      controlPanel.add(b);

	      panel.add(ChartPanel(xylineChart, controlPanel));
	      panel.add(XYZChartPanel(chartX));
	      panel.add(XYZChartPanel(chartZ));
	      
	      textDecoration(16.0f, 5);
	}
	
	public void createMiLambda(){
		  bMiLambda = new JButton("Update (mi, lambda)");
		CreateFrame(createLineChart("(mi, lambda)", "i", "f(i)", fitness), 
				  createScatterPlot("", "Y", "X", seriesX), 
				  createScatterPlot("", "Y", "Z", seriesZ),
				  chartPanelMiLambda, controller, bMiLambda);
		frame.add(chartPanelMiLambda, BorderLayout.CENTER);
		frame.pack();
		frame.setLocation(100, 0);
		
	}
	public void create1Plus1(){
		b1plus1 = new JButton("update (1+1)");
		CreateFrame(createLineChart("(1+1)", "i", "f(i)", fitness), 
				  createScatterPlot("", "Y", "X", seriesX), 
				  createScatterPlot("", "Y", "Z", seriesZ), 
				  chartPanelMiLambda, controller, b1plus1);
		frame.add(chartPanelMiLambda, BorderLayout.CENTER);
		frame.pack();
		frame.setLocation(100, 0);
	}
	
	
	private void Frame(JFrame frame, JPanel chartPanel){
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    frame.add(chartPanel, BorderLayout.CENTER);
	    frame.pack();
	    frame.setLocationRelativeTo(null);
	    frame.setVisible(true);
	}
	
	private JPanel ChartPanel(JFreeChart p, JPanel controlPanel){//p-wykres z konkretnymi parametrami
		ChartPanel chart = new ChartPanel(p);
		JPanel panel = new JPanel();
		panel.setLayout(new BoxLayout(panel, BoxLayout.X_AXIS));
	    panel.add(chart);
	    panel.add(controlPanel);
		return panel;
	}
	
	private JPanel XYZChartPanel(JFreeChart p){//p-wykres z konkretnymi parametrami
		ChartPanel chart = new ChartPanel(p);
		JPanel panel = new JPanel();
		panel.setLayout(new BoxLayout(panel, BoxLayout.X_AXIS));
	    panel.add(chart);
		return panel;
	}
	
	protected void start(Model model)
	{ 
		this.model = model;
		fitness.clear();
		seriesX.clear();
		seriesZ.clear();
		for (int i=0;i<100;i++)
			{
				model.addGen();
			}
			int i=0;
			for (Evolving<ProjectEvolvingArgs> e : model.getBests())
			{				
				createDataset(i++, e.getFitness());			
			}
			i=0;
			for (Evolving<ProjectEvolvingArgs> e : model.getPop(50))//0 zmienia sie
			{				
				//dla i=0 inny kolor
				createDatasetXYZ(e.getArgs().getY(), e.getArgs().getX(), seriesX);//model.getPop()
				createDatasetXYZ(e.getArgs().getY(), e.getArgs().getZ(), seriesZ);	//e.getArgs().getX() (Y, Z)	
			}
			setText("x", model.getBests().get(model.getBests().size()-1).getArgs().getX(), labelx);
			setText("y", model.getBests().get(model.getBests().size()-1).getArgs().getY(), labely);
			setText("z", model.getBests().get(model.getBests().size()-1).getArgs().getZ(), labelz);
			setText("Funkcja", model.getBests().get(model.getBests().size()-1).getFitness(), labelf);
	}
		
	private void createDataset(int i, double fit)
    {          
       fitness.add(i , fit); //i fitness
    }
	private void createDatasetXYZ(double i, double fit, XYSeries series)
    {          
       series.add(i , fit); //i fitness
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
		
		labelx.setFont(labelx.getFont().deriveFont(font));
		labely.setFont(labely.getFont().deriveFont(font));
		labelz.setFont(labelz.getFont().deriveFont(font));
		labelf.setFont(labelf.getFont().deriveFont(font));
	}
}