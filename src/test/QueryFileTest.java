package test;

import static org.junit.Assert.assertEquals;

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
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import query.ExecuteQuery;
import query.ParseQuery;
import register.FileManager;

public class QueryFileTest {
	private static FileManager tester;
	private static ParseQuery quer;
	private static ExecuteQuery executeQuer;

	/*@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		tester = new FileManager();
		quer = new QueryParsing();
		executeQuer = new ExecuteQuery();
	}*/


	
	 @BeforeEach 
	 public  void setUp() throws Exception {
		tester = new FileManager();
		quer = new ParseQuery();
		executeQuer = new ExecuteQuery();
	 }
	 


	/*
	 * Calling the QueryParsing to split the query with parser method and the
	 * ExecuteQuery to execute it with execute method 
	 * Writing the results to a file 
	 * Calling the selectAll to execute the query with SQLite to check if the results are right
	 * Writing the results to a file
	 * comparing the two files 
	 *       
	 */
	@Test
	public final void testRegisterFiles() {
		//Register File 
		String inputputPath1 = ".\\TestResources\\DataForTest\\Datapopulation_aged_20_39_years_male_percent.csv";
		String outputTestPath1 = ".\\TestResources\\TesterForMetadata\\testerForpopulation_aged_20_39_years_male_percent.txt";
		registerFile(inputputPath1, outputTestPath1);
		
		String inputputPath2 = ".\\TestResources\\DataForTest\\Datacell_phones_total.csv";
		String outputTestPath2 =".\\TestResources\\TesterForMetadata\\testerForcell_phones_total.txt";
		registerFile(inputputPath2, outputTestPath2);

		String inputputPath3 = ".\\TestResources\\DataForTest\\Dataco2_emissions_tonnes_per_person.csv";
		String outputTestPath3 = ".\\TestResources\\TesterForMetadata\\testerForco2_emissions_tonnes_per_person.txt";
		registerFile(inputputPath3, outputTestPath3);

		String inputputPath4 =".\\TestResources\\DataForTest\\Datadata_quality_life_expectancy.csv";
		String outputTestPath4 = ".\\TestResources\\TesterForMetadata\\testerFordata_quality_life_expectancy.txt";
		registerFile(inputputPath4, outputTestPath4);
		
		String inputputPath5= ".\\TestResources\\DataForTest\\Dataenergy_production_total.csv";
		String outputTestPath5 = ".\\TestResources\\TesterForMetadata\\testerForenergy_production_total.txt";
		registerFile(inputputPath5, outputTestPath5);
		
		String inputputPath6= ".\\TestResources\\DataForTest\\Datafemales_aged_15_24_employment_rate_percent.csv";
		String outputTestPath6 = ".\\TestResources\\TesterForMetadata\\testerForfemales_aged_15_24_employment_rate_percent.txt";
		registerFile(inputputPath6, outputTestPath6);
		
		String inputputPath7=  ".\\TestResources\\DataForTest\\Datafreedix_fh.csv";
		String outputTestPath7 = ".\\TestResources\\TesterForMetadata\\testerForfreedix_fh.txt";
		registerFile(inputputPath7, outputTestPath7);
		
		String inputputPath8= ".\\TestResources\\DataForTest\\Datapersonal_computers_total.csv";
		String outputTestPath8 = ".\\TestResources\\TesterForMetadata\\testerForpersonal_computers_total.txt";
		registerFile(inputputPath8, outputTestPath8);
		
		String inputputPath9= ".\\TestResources\\DataForTest\\Datasurface_area_sq_km.csv";
		String outputTestPath9 = ".\\TestResources\\TesterForMetadata\\testerForsurface_area_sq_km.txt";
		registerFile(inputputPath9, outputTestPath9);
		
		String inputputPath10= ".\\TestResources\\DataForTest\\Datachildren_per_woman_total_fertility.csv";
		String outputTestPath10 = ".\\TestResources\\TesterForMetadata\\testerForchildren_per_woman_total_fertility.txt";
		registerFile(inputputPath10, outputTestPath10);

		
	
	}
	@Test
	public final void testQueryFiles() {
		//Query 
		String outpath1=".\\TestResources\\QueryResults\\Datapopulation_aged_20_39_years_male_percentResults.txt";
		String outSqlpath1=".\\TestResources\\SqliteResults\\Datapopulation_aged_20_39_years_male_percentSQL.txt";
		String query1="Select country,rate,year from Datapopulation_aged_20_39_years_male_percent where 	rate>0.295 AND 	country=Afghanistan OR year<=1971 AND year>1960 OR			 rate<0.400 AND rate>0.100 OR country='Afghanistan' AND year<=2000 OR year>1990 AND rate<0.200 ";
		String querySQL1="Select country,rate,year from Datapopulation_aged_20_39_years_male_percent where rate>0.295 AND		 country='Afghanistan'   OR year<=1971 AND year>1960 OR 			rate<0.400 AND rate>0.100 OR country='Greece' AND year<=2000 OR year>1990 AND rate<0.200 ";
		executeTheQuery(outpath1,outSqlpath1,query1,querySQL1);
		
		String outpath2=".\\TestResources\\QueryResults\\Datacell_phones_totalResults.txt";
		String outSqlpath2=".\\TestResources\\SqliteResults\\Datacell_phones_totalSQL.txt";
		String query2="Select country,rate from Datacell_phones_total where country=Grenada";
		String querySQL2="Select country,rate from Datacell_phones_total where country='Grenada'";
		executeTheQuery(outpath2,outSqlpath2,query2,querySQL2);
		
		String outpath3=".\\TestResources\\QueryResults\\Dataco2_emissions_tonnes_per_personResults.txt";
		String outSqlpath3=".\\TestResources\\SqliteResults\\Dataco2_emissions_tonnes_per_personSQL.txt";
		String query3="Select country from Dataco2_emissions_tonnes_per_person where year=1970 ";
		String querySQL3="Select country from Dataco2_emissions_tonnes_per_person where year=1970 ";
		executeTheQuery(outpath3,outSqlpath3,query3,querySQL3);
		
		String outpath4=".\\TestResources\\QueryResults\\Datadata_quality_life_expectancyResults.txt";
		String outSqlpath4=".\\TestResources\\SqliteResults\\Datadata_quality_life_expectancySQL.txt";
		String query4="Select rate,country from Datadata_quality_life_expectancy where country=Niger AND rate<80";
		String querySQL4="Select rate,country from Datadata_quality_life_expectancy where country='Niger' AND rate<80";
		executeTheQuery(outpath4,outSqlpath4,query4,querySQL4);
		
		String outpath5=".\\TestResources\\QueryResults\\Dataenergy_production_totalResults.txt";
		String outSqlpath5=".\\TestResources\\SqliteResults\\Dataenergy_production_totalSQL.txt";
		String query5="Select rate from Dataenergy_production_total where year=2000 AND country=Greece ";
		String querySQL5="Select rate from Dataenergy_production_total where year='2000' AND country='Greece'";
		executeTheQuery(outpath5,outSqlpath5,query5,querySQL5);
		
		String outpath6=".\\TestResources\\QueryResults\\Datafemales_aged_15_24_employment_rate_percentResults.txt";
		String outSqlpath6=".\\TestResources\\SqliteResults\\Datafemales_aged_15_24_employment_rate_percentSQL.txt";
		String query6="Select rate from Datafemales_aged_15_24_employment_rate_percent where year>=2000 AND country=Greece ";
		String querySQL6="Select rate from Datafemales_aged_15_24_employment_rate_percent where year>=2000 AND country='Greece' ";
		executeTheQuery(outpath6,outSqlpath6,query6,querySQL6);
		
		String outpath7=".\\TestResources\\QueryResults\\Datafreedix_fhResults.txt";
		String outSqlpath7=".\\TestResources\\SqliteResults\\Datafreedix_fhSQL.txt";
		String query7="Select year,rate,country from Datafreedix_fh where year=1990 OR country=Guatemala ";
		String querySQL7="Select year,rate,country from Datafreedix_fh where year=1990 OR country='Guatemala' ";
		executeTheQuery(outpath7,outSqlpath7,query7,querySQL7);
		
		String outpath8=".\\TestResources\\QueryResults\\Datapersonal_computers_totalResults.txt";
		String outSqlpath8=".\\TestResources\\SqliteResults\\Datapersonal_computers_totalSQL.txt";
		String query8="Select country,year from Datapersonal_computers_total where year=1990 OR country=Myanmar ";
		String querySQL8="Select country,year from Datapersonal_computers_total where year=1990 OR country='Myanmar' ";
		executeTheQuery(outpath8,outSqlpath8,query8,querySQL8);
		
		String outpath9=".\\TestResources\\QueryResults\\Datasurface_area_sq_kmResults.txt";
		String outSqlpath9=".\\TestResources\\SqliteResults\\Datasurface_area_sq_kmSQL.txt";
		String query9="Select rate from Datasurface_area_sq_km where rate<0";
		String querySQL9="Select rate from Datasurface_area_sq_km where rate<0 ";
		executeTheQuery(outpath9,outSqlpath9,query9,querySQL9);
		
		String outpath10=".\\TestResources\\QueryResults\\Datachildren_per_woman_total_fertilityResults.txt";
		String outSqlpath10=".\\TestResources\\SqliteResults\\Datachildren_per_woman_total_fertilitySQL.txt";
		String query10="Select year from Datachildren_per_woman_total_fertility where country=Zimbabwe ";
		String querySQL10="Select year from Datachildren_per_woman_total_fertility where country='Zimbabwe' ";
		executeTheQuery(outpath10,outSqlpath10,query10,querySQL10);
		
	}
	
	/**
	 * 
	 * 
	 * @param outpath   	The path of the query results from the project  
	 * @param outSqlpath	The path of the query results from the Sqlite 
	 * @param query			The query string to execute the query to the project
	 * @param querySql		TThe query string to execute the query to Sqlite 
	 * @return
	 */
	
	private static void executeTheQuery(String outpath,String outSqlpath,String query,String querySql) {
		int check = 0;
		File outFile=new File(outpath);
		File outSqlFile=new File(outSqlpath);
		try {
			check=quer.createParser(query);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(check!=-3) {
			try {
				check = executeQuer.execute(tester, quer);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if(check==0) {
			
				PrintStream out = null;
				try {
					out = new PrintStream(outSqlFile);
				} catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				System.setOut(out);
				selectAll(querySql);
				out.close();
	
	
				try {
					assertEquals(true,(FileUtils.contentEquals(outFile, outSqlFile)));
				} catch (IOException e) {
					System.err.println("Assertion error: fileUtils.contentEquals crashes. \n\n");
					e.printStackTrace();
				}
			}else if(check==-1) {
				System.out.println("The column information that you gave is wrong!\n");
			}else {
				System.out.println("The file is not registered!\n");
	
			}
		}else {
			System.out.println("The query that you have typed is wrong\n");
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

	

	private static void selectAll(String sql){  
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
