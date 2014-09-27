package com.wso2.hackathon.ichart.imageprocessor;

import java.util.ArrayList;

public class Main {

	public static void main(String[] args) {

		String filePath = "E:\\WSO2 Hackathon\\Test13.jpg";
		try {
			
			ArrayList<ArrayList<String>> table = new CharRecognizer().getTableData(filePath);
			for(ArrayList<String> row : table) {
				for(String column : row) {
					System.out.println(column);
				}
			}
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}