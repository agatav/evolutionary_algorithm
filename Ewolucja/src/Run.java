public class Run {
	public Run() {
		Chart chart = new Chart("test","test");
		Controller controller = new Controller();
		controller.addView(chart);
		chart.addController(controller);
	}
	
	public static void main(String[] args){
		new Run();
	}
}