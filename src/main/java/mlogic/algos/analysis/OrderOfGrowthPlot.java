package mlogic.algos.analysis;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.List;

import javafx.application.Application;
import javafx.beans.binding.Bindings;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import mlogic.algos.analysis.OrderOfGrowthEstimator.GrowthClass;

/**
 * 
 * Uses the JavaFx api to plot growth charts for elapsed time (or memory
 * utilized) by various operations of a set of algorithm implementations.
 * 
 * @author Rajaram G
 *
 */
public class OrderOfGrowthPlot extends Application {

	/**
	 * Performance Test object with methods to execute tests and generate usable
	 * results
	 */
	private PerformanceTest pt;

	/**
	 * Loads the Performance Test class name from command-line arguments
	 */
	@Override
	public void init() throws Exception {
		super.init();
		Parameters parameters = getParameters();
		List<String> arguments = parameters.getRaw();
		if (arguments.isEmpty())
			throw new IllegalArgumentException("PT Class name must be provided");
		createPTObject(arguments.get(0));
	}

	/**
	 * Dynamically creates an instance of the Performance Test class
	 * 
	 * @param ptClassName
	 *            Name of the performance test class
	 */
	private void createPTObject(String ptClassName) {
		ClassLoader classLoader = OrderOfGrowthPlot.class.getClassLoader();
		Class<PerformanceTest> aClass;
		try {
			aClass = (Class<PerformanceTest>) classLoader.loadClass(ptClassName);
			Constructor<PerformanceTest> ctor = aClass.getConstructor();
			this.pt = ctor.newInstance();
		} catch (ClassNotFoundException | NoSuchMethodException | SecurityException | InstantiationException
				| IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * Executes performance tests and presents data in charts
	 */
	@Override
	public void start(Stage stage) {
		pt.execute();

		ScrollPane scroll = getScrollableGridWithCharts();

		Scene scene = new Scene(scroll);
		scene.getStylesheets().add("analysis.css");

		stage.setTitle("Order of Growth Plots for " + pt.algorithmClass() + " Implementations");
		stage.setScene(scene);
		stage.show();
	}

	/**
	 * Creates a 3-column grid to display the y chart, log(y) chart and growth
	 * classification table. Each row represents an operation. Each series in
	 * the charts represents performance data for various algorithms that
	 * implement the operation
	 * 
	 * @return a scrollable pane with embedded grid
	 */
	private ScrollPane getScrollableGridWithCharts() {
		GridPane grid = new GridPane();
		for (int operationType = 0; operationType < pt.operationTypes().length; operationType++)
			addGrowthCharts(grid, operationType);

		ScrollPane scroll = new ScrollPane();
		scroll.setContent(grid);
		return scroll;
	}

	/**
	 * Adds growth charts and growth class table for the given operation type to
	 * a row in the grid
	 * 
	 * @param grid
	 * @param operationType
	 */
	private void addGrowthCharts(GridPane grid, int operationType) {
		System.out.println(
				"Creating growth charts for " + pt.algorithmClass() + "." + pt.operationTypes()[operationType]);

		final LineChart<Number, Number> lineChart = createLineChartObject(operationType);
		grid.add(lineChart, 0, operationType);

		final LineChart<Number, Number> logChart = createLogChartObject(operationType);
		grid.add(logChart, 1, operationType);

		final VBox vbox = getVBoxWithGrowthClassificationTable(operationType);
		// grid.add(vbox, 2, operationType);

	}

	/**
	 * Creates the Log Y Chart object
	 * 
	 * @param operationType
	 * @return
	 */
	private LineChart<Number, Number> createLogChartObject(int operationType) {
		final NumberAxis xAxisForLogPlot = new NumberAxis();
		xAxisForLogPlot.setLabel(pt.inputSizeLabel());

		final NumberAxis yAxisForLogPlot = new NumberAxis();

		final LineChart<Number, Number> logChart = new LineChart<Number, Number>(xAxisForLogPlot, yAxisForLogPlot);
		logChart.setTitle("Log of Elapsed Times (ns) for " + pt.operationTypes()[operationType]);
		logChart.setCreateSymbols(false);

		for (int algorithm = 0; algorithm < pt.algorithms().length; algorithm++) {

			XYChart.Series logOfySeries = new XYChart.Series();
			logOfySeries.setName(pt.algorithms()[algorithm]);

			for (int x = 2; x <= pt.inputSizes().length; x++) {
				logOfySeries.getData().add(
						new XYChart.Data(pt.inputSizes()[x - 1], pt.logElapsed()[algorithm][operationType][x - 1]));
			}
			logChart.getData().add(logOfySeries);
		}
		logChart.setMaxHeight(400);
		return logChart;
	}

	/**
	 * Creates the Y chart object
	 * 
	 * @param operationType
	 * @return
	 */
	private LineChart<Number, Number> createLineChartObject(int operationType) {
		final NumberAxis xAxis = new NumberAxis();
		xAxis.setLabel(pt.inputSizeLabel());

		final NumberAxis yAxis = new NumberAxis();

		final LineChart<Number, Number> lineChart = new LineChart<Number, Number>(xAxis, yAxis);
		lineChart.setTitle("Elapsed Times (ns) for " + pt.operationTypes()[operationType]);
		lineChart.setCreateSymbols(false);
		for (int algorithm = 0; algorithm < pt.algorithms().length; algorithm++) {
			XYChart.Series ySeries = new XYChart.Series();
			ySeries.setName(pt.algorithms()[algorithm]);

			for (int x = 2; x <= pt.inputSizes().length; x++)
				ySeries.getData()
						.add(new XYChart.Data(pt.inputSizes()[x - 1], pt.elapsed()[algorithm][operationType][x - 1]));
			lineChart.getData().add(ySeries);
		}

		lineChart.setMaxHeight(400);
		return lineChart;
	}

	/**
	 * Creates a Vertical Column Box with a table showing the growth class of
	 * the operation under each algorithm
	 * 
	 * @param operationType
	 * @return
	 */
	private VBox getVBoxWithGrowthClassificationTable(int operationType) {
		final Label label = new Label("Growth Classification");
		label.setFont(new Font("Arial", 16));

		TableView<BigORecord> table = new TableView<BigORecord>();
		table.setEditable(true);
		TableColumn impl = new TableColumn("Implementation");
		impl.setCellValueFactory(new PropertyValueFactory<BigORecord, String>("algo"));
		TableColumn clazz = new TableColumn("Classification");
		clazz.setCellValueFactory(new PropertyValueFactory<BigORecord, String>("clazz"));
		impl.setMinWidth(200);
		clazz.setMinWidth(100);
		ObservableList<BigORecord> data = FXCollections.observableArrayList();

		for (int algorithm = 0; algorithm < pt.algorithms().length; algorithm++) {
			GrowthClass g = OrderOfGrowthEstimator.estimate(pt.inputSizes(), pt.elapsed()[algorithm][operationType]);
			data.add(new BigORecord(pt.algorithms()[algorithm], g.label()));
		}

		table.setItems(data);
		table.setFixedCellSize(25);
		table.prefHeightProperty().bind(Bindings.size(table.getItems()).multiply(table.getFixedCellSize()).add(30));

		table.getColumns().addAll(impl, clazz);
		final VBox vbox = new VBox();
		vbox.setSpacing(5);
		vbox.setPadding(new Insets(5, 0, 0, 10));
		vbox.getChildren().addAll(label, table);
		vbox.setMaxHeight(400);
		return vbox;
	}

	public static void main(String[] args) {
		launch(args);

	}
}