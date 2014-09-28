package com.wso2.hackathon.ichart.imageprocessor;

import java.io.BufferedInputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.Arrays;

import com.wso2.hackathon.ichart.ocrsdk.Client;
import com.wso2.hackathon.ichart.ocrsdk.ProcessingSettings;
import com.wso2.hackathon.ichart.ocrsdk.Task;

public class CharRecognizer {

	private static final String APPLICATION_ID = "ichart generator";
	private static final String PASSWORD = "DMDrWwdljhE0uB6ervs2Kp3c";

	public CharRecognizer() {
		super();
	}

	public ArrayList<ArrayList<String>> getTableData(byte [] file) throws Exception {

		Client restClient = new Client();

		restClient.serverUrl = "http://cloud.ocrsdk.com";
		restClient.applicationId = CharRecognizer.APPLICATION_ID;
		restClient.password = CharRecognizer.PASSWORD;
		
		return this.performRecognition(restClient, file);
	}

	private ArrayList<ArrayList<String>> performRecognition(Client restClient, byte [] file) throws Exception {

		String language = "English";

		ProcessingSettings settings = new ProcessingSettings();
		settings.setLanguage(language);
		settings.setOutputFormat(ProcessingSettings.OutputFormat.txt);

		Task task = null;
		task = restClient.processImage(file, settings);

		while (task.isTaskActive()) {

			Thread.sleep(5000);
			task = restClient.getTaskStatus(task.Id);
		}

		ArrayList<ArrayList<String>> table = new ArrayList<ArrayList<String>>();

		if (task.Status == Task.TaskStatus.Completed) {
			URL url = new URL(task.DownloadUrl);
			URLConnection connection = url.openConnection();

			BufferedInputStream reader = new BufferedInputStream(connection.getInputStream());

			byte[] contents = new byte[1024];

			int bytesRead = 0;
			String strFileContents = "";
			while ((bytesRead = reader.read(contents)) != -1) {
				strFileContents = new String(contents, 0, bytesRead);
			}
			
			String[] rows = strFileContents.split("\\r?\\n");
			
			for(String row : rows) {
				
				String[] columns = row.split("\t");
				ArrayList<String> clmns = new ArrayList<String>(Arrays.asList(columns));
				
				table.add(clmns);
			}			

		} else if (task.Status == Task.TaskStatus.NotEnoughCredits) {
			// TODO: Handle this error
		} else {
			// TODO: Handle this error
		}
		
		return table;
	}
}