public class Run {
	public Run() {

		//create Model and View
		//Model model = new Model();
		Chart chart = new Chart("test","test");
		Controller controller = new Controller();
		//controller.addModel(model);
		controller.addView(chart);

		chart.addController(controller);
		//chart.addModel(model);
	}
	
	public static void main(String[] args){
		new Run();
	}
}