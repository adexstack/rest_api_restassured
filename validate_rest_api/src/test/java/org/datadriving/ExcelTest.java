package org.datadriving;

import java.io.IOException;
import java.util.ArrayList;

public class ExcelTest {

	// Customize and Use this to get Data from Excel as required for test
	public static void main(String[] args) throws IOException {

		ExcelDataDriven d = new ExcelDataDriven();
		ArrayList<Object> data1 = d.getData("src/test/resources/demodata.xlsx", "testdata", "TestCases", "Add Profile");
		ArrayList<Object> data2 = d.getData("src/test/resources/demodata.xlsx", "testdata", "Data1", "Adex");

		// getting data as excel poi data
		System.out.println(data1.get(0));
		System.out.println(data1.get(1));
		System.out.println(data1.get(2));
		System.out.println(data1.get(3));

		// getting data as string
		System.out.println(data1.get(0).toString());
		System.out.println(data1.get(1).toString());
		System.out.println(data1.get(2).toString());
		System.out.println(data1.get(3).toString());

		// getting list of row data at once, returning list of all without index getting a specific row cell data
		ArrayList<Object> rowData = new ArrayList<>();
		rowData.add(data1);
		rowData.add(data2);

		for (Object data : rowData) {
			System.out.println(data);
			System.out.println(data.toString());
		}
	}
}
