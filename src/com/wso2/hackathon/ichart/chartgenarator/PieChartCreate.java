package com.wso2.hackathon.ichart.chartgenarator;

import java.awt.Font;
import java.util.ArrayList;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PiePlot;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.data.general.PieDataset;
import org.jfree.ui.ApplicationFrame;

/**
 * A simple demonstration application showing how to create a pie chart using
 * data from a {@link DefaultPieDataset}.
 */
@SuppressWarnings("serial")
public class PieChartCreate extends ApplicationFrame {

	/**
	 * Default constructor.
	 * 
	 * @param title
	 *            the frame title.
	 */
	public PieChartCreate(String title) {
		super(title);		
	}

	/**
	 * Creates a sample dataset.
	 * 
	 * @return A sample dataset.
	 */
	public void createDataset(ArrayList<ArrayList<String>> list) {
		DefaultPieDataset dataset = new DefaultPieDataset();

		
		for (ArrayList<String> arrayList : list) {
			for (int i = 0; i < list.size(); i++) {
				dataset.setValue(arrayList.get(i), Double.parseDouble(arrayList.get(1)));
			}
		}
//		dataset.setValue("One", new Double(43.2));
//		dataset.setValue("Two", new Double(10.0));
//		dataset.setValue("Three", new Double(27.5));
//		dataset.setValue("Four", new Double(17.5));
//		dataset.setValue("Five", new Double(11.0));
//		dataset.setValue("Six", new Double(19.4));
		
		createChart(dataset);		
	}

	/**
	 * Creates a chart.
	 * 
	 * @param dataset
	 *            the dataset.
	 * 
	 * @return A chart.
	 */
	public static JFreeChart createChart(PieDataset dataset) {

		JFreeChart chart = ChartFactory.createPieChart("Pie Chart", // chart title
				dataset, // data
				true, // include legend
				true, false);

		PiePlot plot = (PiePlot) chart.getPlot();
		plot.setLabelFont(new Font("SansSerif", Font.PLAIN, 12));
		plot.setNoDataMessage("No data available");
		plot.setCircular(false);
		plot.setLabelGap(0.02);
		return chart;

	}
}