package com.wso2.hackathon.ichart.chartgenarator;

import java.awt.Font;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JPanel;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PiePlot;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.data.general.PieDataset;
import org.jfree.ui.ApplicationFrame;
import org.jfree.ui.RefineryUtilities;

import com.wso2.hackathon.ichart.imageprocessor.CharRecognizer;

/**
 * A simple demonstration application showing how to create a pie chart using
 * data from a {@link DefaultPieDataset}.
 */
public class PieChartCreate extends ApplicationFrame {

	/**
	 * Default constructor.
	 * 
	 * @param title
	 *            the frame title.
	 */
	public PieChartCreate(String title) {
		super(title);
		setContentPane(createDemoPanel());
		BufferedImage bi = ScreenImage.createImage(createDemoPanel());
		try {
			ScreenImage.writeImage(bi, "panel.png");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * Creates a sample dataset.
	 * 
	 * @return A sample dataset.
	 */
	private static PieDataset createDataset(ArrayList<ArrayList<String>> list) {
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
		return dataset;
	}

	/**
	 * Creates a chart.
	 * 
	 * @param dataset
	 *            the dataset.
	 * 
	 * @return A chart.
	 */
	private static JFreeChart createChart(PieDataset dataset) {

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

	/**
	 * Creates a panel for the demo (used by SuperDemo.java).
	 * 
	 * @return A panel.
	 */
	public static JPanel createDemoPanel() {
		CharRecognizer charRecognizer = new CharRecognizer();
		ArrayList<ArrayList<String>> list = null;
		try {
			list = charRecognizer.getTableData("path");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		JFreeChart chart = createChart(createDataset(list));
		return new ChartPanel(chart);
	}

	/**
	 * Starting point for the demonstration application.
	 * 
	 * @param args
	 *            ignored.
	 */
	public static void main(String[] args) {

		PieChartCreate demo = new PieChartCreate("Pie Chart");
		demo.pack();
		RefineryUtilities.centerFrameOnScreen(demo);
		demo.setVisible(true);

	}

}