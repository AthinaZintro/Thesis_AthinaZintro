package test;

import static org.junit.Assert.*;

//import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
//import org.junit.Assert;
//import static org.junit.Assert.assertEquals;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Scanner;

import org.apache.commons.io.FileUtils;


import register.FileManager;
import query.ExecuteQuery;
import query.QueryParsing;

public class UseCaseTest {
	private static FileManager tester;
	private static QueryParsing quer;
	private static ExecuteQuery executeQuer;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		tester = new FileManager();
		quer = new QueryParsing();
		executeQuer = new ExecuteQuery();
	}



	/*
	 * @BeforeEach 
	 * void setUp() throws Exception { }
	 */


	/*
	 * Calling the QueryParsing to split the query with parser method and the
	 * ExecuteQuery to execute it with execute method 
	 * Writing the results to a file 
	 * Calling the selectAll to execute the query with SQLite to check if the results are right
	 * Writing the results to a file
	 * comparing the two files 
	 *       
	 *       */
	@Test
	public final void testQueryFile1() {

		//INVOKE REGISTRATION
		
		// ???? CANNOT UNDERSTAND WHY WE NEED BOTH inputDataFilePath AND outputPath1 ???
		String inputDataFilePath = ".\\TestResources\\DownloadedData\\population_aged_20_39_years_male_percent.csv";
		String outputPath1 = ".\\TestResources\\DataForTest\\Datapopulation_aged_20_39_years_male_percent.csv";
		String outputTestPath1 = ".\\TestResources\\DataForTest\\testerForpopulation_aged_20_39_years_male_percent.txt";
		String[] columns1 = registerFile(inputDataFilePath, outputPath1, outputTestPath1);

		//QUERY
		File out1File=new File(".\\TestResources\\QueryResults\\Datapopulation_aged_20_39_years_male_percentResults.txt");
		File out11File=new File(".\\TestResources\\QueryResults\\Datapopulation_aged_20_39_years_male_percentSQL.txt");
		PrintStream out1 = null;
		try {
			out1 = new PrintStream(out1File);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.setOut(out1);
		try {
			quer.parser("Select country,rate,year from Datapopulation_aged_20_39_years_male_percent where 	rate>0.295 AND 	country=Afghanistan OR year<=1971 AND year>1960 OR			 rate<0.400 AND rate>0.100 OR country='Afghanistan' AND year<=2000 OR year>1990 AND rate<0.200 ");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		int check1 = 0;
		try {
			check1 = executeQuer.execute(tester, columns1, quer);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		out1.close();
		if(check1==0) {
			PrintStream out11 = null;
			try {
				out11 = new PrintStream(out11File);
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.setOut(out11);
			selectAll("Select country,rate,year from Datapopulation_aged_20_39_years_male_percent where rate>0.295 AND		 country='Afghanistan'   OR year<=1971 AND year>1960 OR 			rate<0.400 AND rate>0.100 OR country='Greece' AND year<=2000 OR year>1990 AND rate<0.200 ");
			out11.close();


			try {
				assertEquals(true,(FileUtils.contentEquals(out1File, out11File)));
			} catch (IOException e) {
				System.err.println("Assertion error: fileUtils.contentEquals crashes. \n\n");
				e.printStackTrace();
			}
		}
	}

	/**
	 * The NEW ONE !!!  TI KANEI H KA6E METABLHTH?
	 * 
	 * @param inputDataFilePath
	 * @param outputPath1
	 * @param outputTestPath1
	 * @return
	 */
	private static String[] registerFile(String inputDataFilePath, String outputPath1, String outputTestPath1  ) {

		
		File inputDataFile = new File(inputDataFilePath);
		File datafile1 = null;
		try {
			datafile1 = createDataFile(inputDataFile, outputPath1);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		File test1 = null;
		try {
			test1 = makeTesters(datafile1, outputTestPath1);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String[] columns1 = null;
		try {
			columns1 = readingTest(datafile1);
		} catch (FileNotFoundException e) {
			System.err.println("reading datafile fails. Exiting");
			System.exit(-1);
			e.printStackTrace();
		}
		String numberOfcolumns1 = "" + columns1.length + "";
		String metadataFilePath1 = tester.register(outputPath1, ",", columns1, numberOfcolumns1);
		File metadataFile1 = new File(metadataFilePath1);
		try {
			assertEquals(true, (FileUtils.contentEquals(metadataFile1, test1)));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return columns1;
	}
	
	
	/* Reading the Datatestfile and return name of columns */
	private static String[] readingTest(File file) throws FileNotFoundException {
		int j = 0;
		Scanner newReader = new Scanner(file);
		String[] columns = null;
		while (newReader.hasNextLine()) {
			String line = newReader.nextLine();
			if (j == 3) {
				String str = line.replaceAll("\\s", "");
				columns = str.split(",");
				// System.out.println(line);

				break;
			}
			j++;
		}
		newReader.close();
		return columns;

	}

	/* Creating a testerfile for the DataFileMetadata */
	private static File makeTesters(File test, String outputtestpath) throws IOException {
		File testerfile = new File(outputtestpath);
		FileWriter w = null;
		try {
			w = new FileWriter(outputtestpath);
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		w.write(test.getName()); /* Writing the lines that contains the info */
		w.write("\n");
		w.write(",\n");
		w.write("3");
		w.write("\n");
		w.write("year");
		w.write("\n");
		w.write("country");
		w.write("\n");
		w.write("rate");
		w.write("\n");
		w.close();
		return testerfile;

	}

	/* Write Data test file in the right format by reading the csv files */
	private static File createDataFile(File testfile, String outputFilePath) throws IOException {
		Scanner myReader = new Scanner(testfile);
		File file = new File(outputFilePath);
		ArrayList<String> years = new ArrayList<String>();
		FileWriter writer = null;
		try {
			writer = new FileWriter(outputFilePath);
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		int i = 0;

		writer.write(file.getName()); /* Writing the lines that contains the info */
		writer.write("\n");
		writer.write(",\n");
		writer.write("3");
		writer.write("\n");
		writer.write("year");
		writer.write(",");
		writer.write("country");
		writer.write(",");
		writer.write("rate");
		writer.write("\n");

		while (myReader.hasNextLine()) { /* Reading line by line the file to write Datatest in the right format */
			String data = myReader.nextLine();
			String[] labels = data.split(",");
			if (i != 0) {
				for (int k = 1; k < labels.length; k++) {
					// System.out.println(years.get(k-1)+","+labels[0]+","+labels[k]);
					writer.write(years.get(k - 1));
					writer.write(",");
					writer.write(labels[0]);
					writer.write(",");
					writer.write(labels[k]);
					writer.write("\n");
				}
				i++;
			} else {
				for (int j = 1; j < labels.length; j++) {
					// System.out.println(labels[j]);
					years.add(labels[j]);

				}
				i++;
			}

		}

		writer.close();
		myReader.close();
		return file;

	}

	private void selectAll(String sql){  
		Connection conn = null;
		try {
			Class.forName("org.sqlite.JDBC");
			conn = DriverManager.getConnection("jdbc:sqlite:QueryData.db");
		} catch (Exception e) {
			System.out.println(e);
		}


		try {  
			Statement stmt  = conn.createStatement();  
			ResultSet rs    = stmt.executeQuery(sql);  
			ResultSetMetaData rsmd = rs.getMetaData();
			String name = "";
			for(int i=1; i<=rsmd.getColumnCount();i++) {
				name = name+rsmd.getColumnName(i)+" ";


			}
			System.out.println(name);



			// loop through the result set 
			while (rs.next()) { 
				String sql_result="";
				for(int j=1;j<=rsmd.getColumnCount();j++) {
					sql_result = sql_result+rs.getString(j)+" "; 

				}
				System.out.println( sql_result);


			}  
			conn.close();
		} catch (SQLException e) {  
			System.out.println(e.getMessage());  
		}  
	}  

}//end class



/*

private static String[] registerFile1() {
	File file1 = new File(".\\TestResources\\DownloadedData\\population_aged_20_39_years_male_percent.csv");
	String outputPath1 = ".\\TestResources\\DataForTest\\Datapopulation_aged_20_39_years_male_percent.csv";
	File datafile1 = null;
	try {
		datafile1 = createDataFile(file1, outputPath1);
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	String outputTestPath1 = ".\\TestResources\\DataForTest\\testerForpopulation_aged_20_39_years_male_percent.txt";
	File test1 = null;
	try {
		test1 = makeTesters(datafile1, outputTestPath1);
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	String[] columns1 = null;
	try {
		columns1 = readingTest(datafile1);
	} catch (FileNotFoundException e) {
		System.err.println("reading datafile fails. Exiting");
		System.exit(-1);
		e.printStackTrace();
	}
	String numberOfcolumns1 = "" + columns1.length + "";
	String metadataFilePath1 = tester.register(outputPath1, ",", columns1, numberOfcolumns1);
	File metadataFile1 = new File(metadataFilePath1);
	try {
		assertEquals(true, (FileUtils.contentEquals(metadataFile1, test1)));
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	return columns1;
}
*/