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
import java.util.Scanner;

import org.apache.commons.io.FileUtils;


import register.FileManager;
import query.ExecuteQuery;
import query.QueryParsing;

public class QueryFileTest {
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

		//Register File
		String inputputPath1 = ".\\TestResources\\DataForTest\\Datapopulation_aged_20_39_years_male_percent.csv";
		String outputTestPath1 = ".\\TestResources\\DataForTest\\testerForpopulation_aged_20_39_years_male_percent.txt";
		registerFile(inputputPath1, outputTestPath1);

		//Query
		File out1File=new File(".\\TestResources\\QueryResults\\Datapopulation_aged_20_39_years_male_percentResults.txt");
		File out11File=new File(".\\TestResources\\QueryResults\\Datapopulation_aged_20_39_years_male_percentSQL.txt");
		/*PrintStream out1 = null;
		try {
			out1 = new PrintStream(out1File);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.setOut(out1);*/
		try {
			quer.parser("Select country,rate,year from Datapopulation_aged_20_39_years_male_percent where 	rate>0.295 AND 	country=Afghanistan OR year<=1971 AND year>1960 OR			 rate<0.400 AND rate>0.100 OR country='Afghanistan' AND year<=2000 OR year>1990 AND rate<0.200 ");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		int check1 = 0;
		try {
			check1 = executeQuer.execute(tester, quer);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		/*out1.close();*/
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
	
	
	@Test
	public final void testQueryFile2() {

		//Register File
		String inputputPath2 = ".\\TestResources\\DataForTest\\Datacell_phones_total.csv";
		String outputTestPath2 =".\\TestResources\\DataForTest\\testerForcell_phones_total.txt";
		registerFile(inputputPath2, outputTestPath2);

		//Query
		File out2File=new File(".\\TestResources\\QueryResults\\Datacell_phones_totalResults.txt");
		File out22File=new File(".\\TestResources\\QueryResults\\Datacell_phones_totalSQL.txt");
		/*PrintStream out1 = null;
		try {
			out1 = new PrintStream(out1File);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.setOut(out1);*/
		try {
			quer.parser("Select country,rate from Datacell_phones_total where country=Grenada");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		int check2 = 0;
		try {
			check2 = executeQuer.execute(tester, quer);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		/*out1.close();*/
		if(check2==0) {
		
			PrintStream out22 = null;
			try {
				out22 = new PrintStream(out22File);
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.setOut(out22);
			selectAll("Select country,rate from Datacell_phones_total where country='Grenada'");
			out22.close();


			try {
				assertEquals(true,(FileUtils.contentEquals(out2File, out22File)));
			} catch (IOException e) {
				System.err.println("Assertion error: fileUtils.contentEquals crashes. \n\n");
				e.printStackTrace();
			}
		}
	}
	@Test
	public final void testQueryFile3() {

		//Register File
		String inputputPath3 = ".\\TestResources\\DataForTest\\Dataco2_emissions_tonnes_per_person.csv";
		String outputTestPath3 = ".\\TestResources\\DataForTest\\testerForco2_emissions_tonnes_per_person.txt";
		registerFile(inputputPath3, outputTestPath3);

		//Query

		File out3File=new File(".\\TestResources\\QueryResults\\Dataco2_emissions_tonnes_per_personResults.txt");
		File out33File=new File(".\\TestResources\\QueryResults\\Dataco2_emissions_tonnes_per_personSQL.txt");
		/*PrintStream out1 = null;
		try {
			out1 = new PrintStream(out1File);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.setOut(out1);*/
		try {
			quer.parser("Select country from Dataco2_emissions_tonnes_per_person where year=1970 ");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		int check3 = 0;
		try {
			check3 = executeQuer.execute(tester, quer);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		/*out1.close();*/
		if(check3==0) {
		
			PrintStream out33 = null;
			try {
				out33 = new PrintStream(out33File);
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.setOut(out33);
			selectAll("Select country from Dataco2_emissions_tonnes_per_person where year=1970 ");
			out33.close();


			try {
				assertEquals(true,(FileUtils.contentEquals(out3File, out33File)));
			} catch (IOException e) {
				System.err.println("Assertion error: fileUtils.contentEquals crashes. \n\n");
				e.printStackTrace();
			}
		}
	}
	@Test
	public final void testQueryFile4() {

		//Register File
		String inputputPath4 =".\\TestResources\\DataForTest\\Datadata_quality_life_expectancy.csv";
		String outputTestPath4 = ".\\TestResources\\DataForTest\\testerFordata_quality_life_expectancy.txt";
		registerFile(inputputPath4, outputTestPath4);

		//Query

		File out4File=new File(".\\TestResources\\QueryResults\\Datadata_quality_life_expectancyResults.txt");
		File out44File=new File(".\\TestResources\\QueryResults\\Datadata_quality_life_expectancySQL.txt");
		/*PrintStream out1 = null;
		try {
			out1 = new PrintStream(out1File);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.setOut(out1);*/
		try {
			quer.parser("Select rate,country from Datadata_quality_life_expectancy where country=Niger AND rate<80");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		int check4 = 0;
		try {
			check4 = executeQuer.execute(tester, quer);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		/*out1.close();*/
		if(check4==0) {
		
			PrintStream out44 = null;
			try {
				out44 = new PrintStream(out44File);
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.setOut(out44);
			selectAll("Select rate,country from Datadata_quality_life_expectancy where country='Niger' AND rate<80");
			out44.close();


			try {
				assertEquals(true,(FileUtils.contentEquals(out4File, out44File)));
			} catch (IOException e) {
				System.err.println("Assertion error: fileUtils.contentEquals crashes. \n\n");
				e.printStackTrace();
			}
		}
	}
	@Test
	public final void testQueryFile5() {

		//Register File
		String inputputPath5= ".\\TestResources\\DataForTest\\Dataenergy_production_total.csv";
		String outputTestPath5 = ".\\TestResources\\DataForTest\\testerForenergy_production_total.txt";
		registerFile(inputputPath5, outputTestPath5);

		//Query

		File out5File=new File(".\\TestResources\\QueryResults\\Dataenergy_production_totalResults.txt");
		File out55File=new File(".\\TestResources\\QueryResults\\Dataenergy_production_totalSQL.txt");
		/*PrintStream out1 = null;
		try {
			out1 = new PrintStream(out1File);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.setOut(out1);*/
		try {
			quer.parser("Select rate from Dataenergy_production_total where year=2000 AND country=Greece ");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		int check5 = 0;
		try {
			check5 = executeQuer.execute(tester, quer);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		/*out1.close();*/
		if(check5==0) {
		
			PrintStream out55 = null;
			try {
				out55 = new PrintStream(out55File);
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.setOut(out55);
			selectAll("Select rate from Dataenergy_production_total where year='2000' AND country='Greece'");
			out55.close();


			try {
				assertEquals(true,(FileUtils.contentEquals(out5File, out55File)));
			} catch (IOException e) {
				System.err.println("Assertion error: fileUtils.contentEquals crashes. \n\n");
				e.printStackTrace();
			}
		}
	}
	@Test
	public final void testQueryFile6() {

		//Register File
		String inputputPath6= ".\\TestResources\\DataForTest\\Datafemales_aged_15_24_employment_rate_percent.csv";
		String outputTestPath6 = ".\\TestResources\\DataForTest\\testerForfemales_aged_15_24_employment_rate_percent.txt";
		registerFile(inputputPath6, outputTestPath6);

		//Query

		File out6File=new File(".\\TestResources\\QueryResults\\Datafemales_aged_15_24_employment_rate_percentResults.txt");
		File out66File=new File(".\\TestResources\\QueryResults\\Datafemales_aged_15_24_employment_rate_percentSQL.txt");
		/*PrintStream out1 = null;
		try {
			out1 = new PrintStream(out1File);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.setOut(out1);*/
		try {
			quer.parser("Select rate from Datafemales_aged_15_24_employment_rate_percent where year>=2000 AND country=Greece ");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		int check6 = 0;
		try {
			check6 = executeQuer.execute(tester, quer);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		/*out1.close();*/
		if(check6==0) {
		
			PrintStream out66 = null;
			try {
				out66 = new PrintStream(out66File);
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.setOut(out66);
			selectAll("Select rate from Datafemales_aged_15_24_employment_rate_percent where year>=2000 AND country='Greece' ");
			out66.close();


			try {
				assertEquals(true,(FileUtils.contentEquals(out6File, out66File)));
			} catch (IOException e) {
				System.err.println("Assertion error: fileUtils.contentEquals crashes. \n\n");
				e.printStackTrace();
			}
		}
	}
	@Test
	public final void testQueryFile7() {

		//Register File
		String inputputPath7=  ".\\TestResources\\DataForTest\\Datafreedix_fh.csv";
		String outputTestPath7 = ".\\TestResources\\DataForTest\\testerForfreedix_fh.txt";
		registerFile(inputputPath7, outputTestPath7);

		//Query

		File out7File=new File(".\\TestResources\\QueryResults\\Datafreedix_fhResults.txt");
		File out77File=new File(".\\TestResources\\QueryResults\\Datafreedix_fhSQL.txt");
		/*PrintStream out1 = null;
		try {
			out1 = new PrintStream(out1File);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.setOut(out1);*/
		try {
			quer.parser("Select year,rate,country from Datafreedix_fh where year=1990 OR country=Guatemala ");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		int check7 = 0;
		try {
			check7 = executeQuer.execute(tester, quer);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		/*out1.close();*/
		if(check7==0) {
		
			PrintStream out77 = null;
			try {
				out77 = new PrintStream(out77File);
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.setOut(out77);
			selectAll("Select year,rate,country from Datafreedix_fh where year=1990 OR country='Guatemala' ");
			out77.close();


			try {
				assertEquals(true,(FileUtils.contentEquals(out7File, out77File)));
			} catch (IOException e) {
				System.err.println("Assertion error: fileUtils.contentEquals crashes. \n\n");
				e.printStackTrace();
			}
		}
	}
	@Test
	public final void testQueryFile8() {

		//Register File
		String inputputPath8= ".\\TestResources\\DataForTest\\Datapersonal_computers_total.csv";
		String outputTestPath8 = ".\\TestResources\\DataForTest\\testerForpersonal_computers_total.txt";
		registerFile(inputputPath8, outputTestPath8);

		//Query

		File out8File=new File(".\\TestResources\\QueryResults\\Datapersonal_computers_totalResults.txt");
		File out88File=new File(".\\TestResources\\QueryResults\\Datapersonal_computers_totalSQL.txt");
		/*PrintStream out1 = null;
		try {
			out1 = new PrintStream(out1File);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.setOut(out1);*/
		try {
			quer.parser("Select country,year from Datapersonal_computers_total where year=1990 OR country=Myanmar ");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		int check8 = 0;
		try {
			check8 = executeQuer.execute(tester, quer);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		/*out1.close();*/
		if(check8==0) {
		
			PrintStream out88 = null;
			try {
				out88 = new PrintStream(out88File);
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.setOut(out88);
			selectAll("Select country,year from Datapersonal_computers_total where year=1990 OR country='Myanmar' ");
			out88.close();


			try {
				assertEquals(true,(FileUtils.contentEquals(out8File, out88File)));
			} catch (IOException e) {
				System.err.println("Assertion error: fileUtils.contentEquals crashes. \n\n");
				e.printStackTrace();
			}
		}
	}
	@Test
	public final void testQueryFile9() {

		//Register File
		String inputputPath9= ".\\TestResources\\DataForTest\\Datasurface_area_sq_km.csv";
		String outputTestPath9 = ".\\TestResources\\DataForTest\\testerForsurface_area_sq_km.txt";
		registerFile(inputputPath9, outputTestPath9);

		//Query

		File out9File=new File(".\\TestResources\\QueryResults\\Datasurface_area_sq_kmResults.txt");
		File out99File=new File(".\\TestResources\\QueryResults\\Datasurface_area_sq_kmSQL.txt");
		/*PrintStream out1 = null;
		try {
			out1 = new PrintStream(out1File);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.setOut(out1);*/
		try {
			quer.parser("Select rate from Datasurface_area_sq_km where city=Albania ");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		int check9 = 0;
		try {
			check9 = executeQuer.execute(tester, quer);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		/*out1.close();*/
		if(check9==0) {
		
			PrintStream out99 = null;
			try {
				out99 = new PrintStream(out99File);
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.setOut(out99);
			selectAll("Select rate from Datasurface_area_sq_km where city=Albania ");
			out99.close();


			try {
				assertEquals(true,(FileUtils.contentEquals(out9File, out99File)));
			} catch (IOException e) {
				System.err.println("Assertion error: fileUtils.contentEquals crashes. \n\n");
				e.printStackTrace();
			}
		}
	}
	@Test
	public final void testQueryFile10() {

		//Register File
		String inputputPath10= ".\\TestResources\\DataForTest\\Datachildren_per_woman_total_fertility.csv";
		String outputTestPath10 = ".\\TestResources\\DataForTest\\testerForchildren_per_woman_total_fertility.txt";
		registerFile(inputputPath10, outputTestPath10);

		//Query

		File out10File=new File(".\\TestResources\\QueryResults\\Datachildren_per_woman_total_fertilityResults.txt");
		File out1010File=new File(".\\TestResources\\QueryResults\\Datachildren_per_woman_total_fertilitySQL.txt");
		/*PrintStream out1 = null;
		try {
			out1 = new PrintStream(out1File);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.setOut(out1);*/
		try {
			quer.parser("Select year from Datachildren_per_woman_total_fertility where country=Zimbabwe ");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		int check10 = 0;
		try {
			check10 = executeQuer.execute(tester, quer);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		/*out1.close();*/
		if(check10==0) {
		
			PrintStream out1010 = null;
			try {
				out1010 = new PrintStream(out1010File);
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.setOut(out1010);
			selectAll("Select year from Datachildren_per_woman_total_fertility where country='Zimbabwe' ");
			out1010.close();


			try {
				assertEquals(true,(FileUtils.contentEquals(out10File, out1010File)));
			} catch (IOException e) {
				System.err.println("Assertion error: fileUtils.contentEquals crashes. \n\n");
				e.printStackTrace();
			}
		}
	}
	
	/**
	 * 
	 * 
	 * @param inputputPath1   	The path of the data file 
	 * @param outputTestPath1	The path of the testfile that we create to test the registration
	 * @return
	 */
	private static String[] registerFile(String inputputPath1, String outputTestPath1  ) {

		
		File datafile1 = new File(inputputPath1);
		
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
		String metadataFilePath1 = tester.register(inputputPath1, ",", columns1, numberOfcolumns1);
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

	

	private void selectAll(String sql){  
		Connection conn = null;
		try {
			Class.forName("org.sqlite.JDBC");
			conn = DriverManager.getConnection("jdbc:sqlite:.\\TestResources\\Sqlite\\QueryData.db");
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

}