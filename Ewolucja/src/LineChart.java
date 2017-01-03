import org.jfree.chart.ChartPanel; 
import org.jfree.chart.JFreeChart;  
import org.jfree.data.xy.XYSeries; 
import org.jfree.ui.ApplicationFrame;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import org.jfree.chart.ChartFactory; 
import org.jfree.chart.plot.PlotOrientation; 
import org.jfree.data.xy.XYSeriesCollection; 


public class LineChart extends ApplicationFrame 
{
	private static final long serialVersionUID = 1L;
	
	JFrame frame = new JFrame("Combined Plot Example");
	
	final XYSeries fitness = new XYSeries("Fitness");//jak chcesz dodac druga funkcje to tu 
	final XYSeries fitness2 = new XYSeries("Fitness 2");//jak chcesz dodac druga funkcje to tu 
	
	
	JLabel labelx = new JLabel("x");
    JLabel labely = new JLabel("y");
    JLabel labelz = new JLabel("z");
    JLabel labelf = new JLabel("f");
	
	LineChart(String applicationTitle, String chartTitle)
   {
      super(applicationTitle);
      final XYSeriesCollection dataset = new XYSeriesCollection();
      dataset.addSeries(fitness);
      
      JFreeChart xylineChart = ChartFactory.createXYLineChart(
         chartTitle ,
         "i",
         "f(i)",
         dataset,
         PlotOrientation.VERTICAL,
         true , true , false);
         
      ChartPanel chartPanelMiLambda = new ChartPanel(xylineChart);
      ChartPanel chartPanelJedenPlusJeden = new ChartPanel(xylineChart);
      
      textDecoration(16.0f, 5);
      //labelx.setMaximumSize(new Dimension(0, 200));
      //labelz.setMaximumSize(new Dimension(0, 200));
      
      JPanel textPanel = new JPanel();
      textPanel.setLayout(new BoxLayout(textPanel, BoxLayout.Y_AXIS));
      textPanel.add(labelx);
      textPanel.add(labely);
      textPanel.add(labelz);
      textPanel.add(labelf);
      
      
      JPanel chartPanel = new JPanel();
      chartPanel.setLayout(new BoxLayout(chartPanel, BoxLayout.Y_AXIS));
      
      JPanel controlPanel1 = new JPanel();
      controlPanel1.setLayout(new BoxLayout(controlPanel1, BoxLayout.Y_AXIS));
      controlPanel1.add(textPanel);
      controlPanel1.add(add(new JButton(new UpdateAction("(mi, lambda)"))));
      
      JPanel controlPanel2 = new JPanel();
      controlPanel2.setLayout(new BoxLayout(controlPanel2, BoxLayout.Y_AXIS));
      //controlPanel1.add(textPanel);
      controlPanel2.add(add(new JButton(new UpdateAction("(1+1) zrównoleglony"))));
      
      
      JPanel chartPanel1 = new JPanel();
      chartPanel1.setLayout(new BoxLayout(chartPanel1, BoxLayout.X_AXIS));
      chartPanel1.add(chartPanelMiLambda);
      //chartPanel1.add(textPanel);
      //chartPanel1.add(textPanel2);
      //chartPanel1.add(add(new JButton(new UpdateAction("(mi, lambda)"))));
      chartPanel1.add(controlPanel1);
      
      
      JPanel chartPanel2 = new JPanel();
      chartPanel2.setLayout(new BoxLayout(chartPanel2, BoxLayout.X_AXIS));
      chartPanel2.add(chartPanelJedenPlusJeden);
      //chartPanel2.add(add(new JButton(new UpdateAction("(1+1) zrównoleglony"))));
      //chartPanel2.add(textPanel);
      chartPanel2.add(controlPanel2);
      
      chartPanel.add(chartPanel1);
      chartPanel.add(chartPanel2);
      
      
      
      frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      frame.add(chartPanel, BorderLayout.CENTER);
      //frame.add(textPanel, BorderLayout.EAST);
      frame.pack();
      frame.setLocationRelativeTo(null);
      frame.setVisible(true);
   }
	
	protected void createDataset(int i, double fit)
    {          
       fitness.add(i , fit); //i fitness
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
	
	private static class UpdateAction extends AbstractAction{

	    public UpdateAction(String name) {
	    	super("Update  " + name);
	    }

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			
		}
	}
}