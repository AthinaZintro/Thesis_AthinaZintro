package test;

import static org.junit.Assert.assertEquals;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStream;
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
import org.junit.Test;

import register.FileManager;
import query.ExecuteQuery;
import query.QueryParsing;

public class FileManagerTest {
	@Test
	public void testRegistration() throws IOException {

		FileManager tester = new FileManager();
		QueryParsing quer = new QueryParsing();
		ExecuteQuery executeQuer = new ExecuteQuery();
		

		/*
		 * In every test give paths, create DataFiles in the right format, make an
		 * metadataFile and a tester for that and checks if the tester and the
		 * metadataFile are equals*/
	
		File file1 = new File(".\\TestResources\\DownloadedData\\population_aged_20_39_years_male_percent.csv");
		String outputPath1 = ".\\TestResources\\DataForTest\\Datapopulation_aged_20_39_years_male_percent.csv";
		File datafile1 = createDataFile(file1, outputPath1);
		String outputTestPath1 = ".\\TestResources\\DataForTest\\testerForpopulation_aged_20_39_years_male_percent.txt";
		File test1 = makeTesters(datafile1, outputTestPath1);
		String[] columns1 = readingTest(datafile1);
		String numberOfcolumns1 = "" + columns1.length + "";
		String metadataFilePath1 = tester.register(outputPath1, ",", columns1, numberOfcolumns1);
		File metadataFile1 = new File(metadataFilePath1);
		assertEquals(true, (FileUtils.contentEquals(metadataFile1, test1)));

	

		/*
		 * Calling the QueryParsing to split the query with parser method and the
		 * ExecuteQuery to execute it with execute method 
		 * Writing the results to a file 
		 * Calling the selectAll to execute the query with SQLite to check if the results are right
		 * Writing the results to a file
		 * comparing the two files 
		 *       
		 *       */
		File out1File=new File(".\\TestResources\\QueryResults\\Datapopulation_aged_20_39_years_male_percentResults.txt");
		File out11File=new File(".\\TestResources\\QueryResults\\Datapopulation_aged_20_39_years_male_percentSQL.txt");
		PrintStream out1 = new PrintStream(out1File);
		System.setOut(out1);
		quer.parser("Select country,rate,year from Datapopulation_aged_20_39_years_male_percent where rate>0.295  OR country=Afghanistan OR year<=1971 AND year>1960 OR rate<0.400 OR year=2000");
		executeQuer.execute(tester, columns1, quer);
		out1.close();
		PrintStream out11 = new PrintStream(out11File);
		System.setOut(out11);
		selectAll("Select country,rate,year from Datapopulation_aged_20_39_years_male_percent where rate>0.295 OR country='Afghanistan' OR year<=1971 AND year>1960 OR rate<0.400 OR year=2000");
		out11.close();
	

		assertEquals(true,(FileUtils.contentEquals(out1File, out11File)));
		
		
		File file2 = new File(".\\TestResources\\DownloadedData\\cell_phones_total.csv");
		String outputPath2 = (".\\TestResources\\DataForTest\\Datacell_phones_total.csv");
		File datafile2 = createDataFile(file2, outputPath2);
		String outputTestPath2 = ".\\TestResources\\DataForTest\\testerForcell_phones_total.txt";
		File test2 = makeTesters(datafile2, outputTestPath2);
		String[] columns2 = readingTest(datafile2);
		String numberOfcolumns2 = "" + columns2.length + "";
		String metadataFilePath2 = tester.register(outputPath2, ",", columns2, numberOfcolumns2);
		File metadataFile2 = new File(metadataFilePath2);
		assertEquals(true, (FileUtils.contentEquals(metadataFile2, test2)));
		

		File out2File=new File(".\\TestResources\\QueryResults\\Datacell_phones_totalResults.txt");
		File out22File=new File(".\\TestResources\\QueryResults\\Datacell_phones_totalSQL.txt");
		PrintStream out2 = new PrintStream(out2File);
		System.setOut(out2);
		quer.parser("Select country,rate from Datacell_phones_total where country=Grenada");
		executeQuer.execute(tester, columns2, quer);
		out2.close();
		PrintStream out22 = new PrintStream(out22File);
		System.setOut(out22);
		selectAll("Select country,rate from Datacell_phones_total where country='Grenada'");
		out22.close();
		
		assertEquals(true,(FileUtils.contentEquals(out2File, out22File)));
		
		
		
		
		File file3 = new File(".\\TestResources\\DownloadedData\\co2_emissions_tonnes_per_person.csv");
		String outputPath3 = ".\\TestResources\\DataForTest\\Dataco2_emissions_tonnes_per_person.csv";
		File datafile3 = createDataFile(file3, outputPath3);
		String outputTestPath3 = ".\\TestResources\\DataForTest\\testerForco2_emissions_tonnes_per_person.txt";
		File test3 = makeTesters(datafile3, outputTestPath3);
		String[] columns3 = readingTest(datafile3);
		String numberOfcolumns3 = "" + columns3.length + "";
		String metadataFilePath3 = tester.register(outputPath3, ",", columns3, numberOfcolumns3);
		File metadataFile3 = new File(metadataFilePath3);
		assertEquals(true, (FileUtils.contentEquals(metadataFile3, test3)));
		
		
		
		File out3File=new File(".\\TestResources\\QueryResults\\Dataco2_emissions_tonnes_per_personResults.txt");
		File out33File=new File(".\\TestResources\\QueryResults\\Dataco2_emissions_tonnes_per_personSQL.txt");
		PrintStream out3 = new PrintStream(out3File);
		System.setOut(out3);
		quer.parser("Select country from Dataco2_emissions_tonnes_per_person where year=1970 ");
		executeQuer.execute(tester, columns3, quer);
		out3.close();
		
		PrintStream out33 = new PrintStream(out33File);
		System.setOut(out33);
		selectAll("Select country from Dataco2_emissions_tonnes_per_person where year=1970 ");
		out33.close();
		
		
		assertEquals(true,(FileUtils.contentEquals(out3File, out33File)));
		


		File file4 = new File(".\\TestResources\\DownloadedData\\data_quality_life_expectancy.csv");
		String outputPath4 = ".\\TestResources\\DataForTest\\Datadata_quality_life_expectancy.csv";
		File datafile4 = createDataFile(file4, outputPath4);
		String outputTestPath4 = ".\\TestResources\\DataForTest\\testerFordata_quality_life_expectancy.txt";
		File test4 = makeTesters(datafile4, outputTestPath4);
		String[] columns4 = readingTest(datafile4);
		String numberOfcolumns4 = "" + columns4.length + "";
		String metadataFilePath4 = tester.register(outputPath4, ",", columns4, numberOfcolumns4);
		File metadataFile4 = new File(metadataFilePath4);
		assertEquals(true, (FileUtils.contentEquals(metadataFile4, test4)));

		
		File out4File=new File(".\\TestResources\\QueryResults\\Datadata_quality_life_expectancyResults.txt");
		File out44File=new File(".\\TestResources\\QueryResults\\Datadata_quality_life_expectancySQL.txt");
		PrintStream out4 = new PrintStream(out4File);
		System.setOut(out4);
		quer.parser("Select rate,country from Datadata_quality_life_expectancy where country=Niger AND rate<80");
		executeQuer.execute(tester, columns4, quer);
		out4.close();
		
		PrintStream out44 = new PrintStream(out44File);
		System.setOut(out44);
		selectAll("Select rate,country from Datadata_quality_life_expectancy where country='Niger' AND rate<80");
		out44.close(); 
		
		assertEquals(true,(FileUtils.contentEquals(out4File, out44File)));


		File file5 = new File(".\\TestResources\\DownloadedData\\energy_production_total.csv");
		String outputPath5 = ".\\TestResources\\DataForTest\\Dataenergy_production_total.csv";
		File datafile5 = createDataFile(file5, outputPath5);
		String outputTestPath5 = ".\\TestResources\\DataForTest\\testerForenergy_production_total.txt";
		File test5 = makeTesters(datafile5, outputTestPath5);
		String[] columns5 = readingTest(datafile5);
		String numberOfcolumns5 = "" + columns5.length + "";
		String metadataFilePath5 = tester.register(outputPath5, ",", columns5, numberOfcolumns5);
		File metadataFile5 = new File(metadataFilePath5);
		assertEquals(true, (FileUtils.contentEquals(metadataFile5, test5)));
		
		
		File out5File=new File(".\\TestResources\\QueryResults\\Dataenergy_production_totalResults.txt");
		File out55File=new File(".\\TestResources\\QueryResults\\Dataenergy_production_totalSQL.txt");
		PrintStream out5 = new PrintStream(out5File);
		System.setOut(out5);
		quer.parser("Select rate from Dataenergy_production_total where year=2000 AND country=Greece ");
		executeQuer.execute(tester, columns5, quer);
		out5.close();
		PrintStream out55 = new PrintStream(out55File);
		System.setOut(out55);
		selectAll("Select rate from Dataenergy_production_total where year='2000' AND country='Greece'");
		out55.close();

		assertEquals(true,(FileUtils.contentEquals(out5File, out55File)));

		
		
		File file6 = new File(".\\TestResources\\DownloadedData\\females_aged_15_24_employment_rate_percent.csv");
		String outputPath6 = ".\\TestResources\\DataForTest\\Datafemales_aged_15_24_employment_rate_percent.csv";
		File datafile6 = createDataFile(file6, outputPath6);
		String outputTestPath6 = ".\\TestResources\\DataForTest\\testerForfemales_aged_15_24_employment_rate_percent.txt";
		File test6 = makeTesters(datafile6, outputTestPath6);
		String[] columns6 = readingTest(datafile6);
		String numberOfcolumns6 = "" + columns6.length + "";
		String metadataFilePath6 = tester.register(outputPath6, ",", columns6, numberOfcolumns6);
		File metadataFile6 = new File(metadataFilePath6);
		assertEquals(true, (FileUtils.contentEquals(metadataFile6, test6)));
		
		

		File out6File=new File(".\\TestResources\\QueryResults\\Datafemales_aged_15_24_employment_rate_percentResults.txt");
		File out66File=new File(".\\TestResources\\QueryResults\\Datafemales_aged_15_24_employment_rate_percentSQL.txt");
		PrintStream out6 = new PrintStream(out6File);
		System.setOut(out6);
		quer.parser(
				"Select rate from Datafemales_aged_15_24_employment_rate_percent where year>=2000 AND country=Greece ");
		executeQuer.execute(tester, columns6, quer);
		out6.close();
		
		PrintStream out66 = new PrintStream(out66File);
		System.setOut(out66);
		selectAll("Select rate from Datafemales_aged_15_24_employment_rate_percent where year>=2000 AND country='Greece' ");
		out66.close();
		assertEquals(true,(FileUtils.contentEquals(out6File, out66File)));

		
		File file7 = new File(".\\TestResources\\DownloadedData\\freedix_fh.csv");
		String outputPath7 = ".\\TestResources\\DataForTest\\Datafreedix_fh.csv";
		File datafile7 = createDataFile(file7, outputPath7);
		String outputTestPath7 = ".\\TestResources\\DataForTest\\testerForfreedix_fh.txt";
		File test7 = makeTesters(datafile7, outputTestPath7);
		String[] columns7 = readingTest(datafile7);
		String numberOfcolumns7 = "" + columns7.length + "";
		String metadataFilePath7 = tester.register(outputPath7, ",", columns7, numberOfcolumns7);
		File metadataFile7 = new File(metadataFilePath7);
		assertEquals(true, (FileUtils.contentEquals(metadataFile7, test7)));

		
		File out7File=new File(".\\TestResources\\QueryResults\\Datafreedix_fhResults.txt");
		File out77File=new File(".\\TestResources\\QueryResults\\Datafreedix_fhSQL.txt");
		PrintStream out7 = new PrintStream(out7File);
		System.setOut(out7);
		quer.parser("Select year,rate,country from Datafreedix_fh where year=1990 OR country=Guatemala ");
		executeQuer.execute(tester, columns7, quer);
		out7.close();
		
		PrintStream out77 = new PrintStream(out77File);
		System.setOut(out77);
		selectAll("Select year,rate,country from Datafreedix_fh where year=1990 OR country='Guatemala' ");
		out77.close();
		
		assertEquals(true,(FileUtils.contentEquals(out7File, out77File)));

		
		File file8 = new File(".\\TestResources\\DownloadedData\\personal_computers_total.csv");
		String outputPath8 = ".\\TestResources\\DataForTest\\Datapersonal_computers_total.csv";
		File datafile8 = createDataFile(file8, outputPath8);
		String outputTestPath8 = ".\\TestResources\\DataForTest\\testerForpersonal_computers_total.txt";
		File test8 = makeTesters(datafile8, outputTestPath8);
		String[] columns8 = readingTest(datafile8);
		String numberOfcolumns8 = "" + columns8.length + "";
		String metadataFilePath8 = tester.register(outputPath8, ",", columns8, numberOfcolumns8);
		File metadataFile8 = new File(metadataFilePath8);
		assertEquals(true, (FileUtils.contentEquals(metadataFile8, test8)));

		
		File out8File=new File(".\\TestResources\\QueryResults\\Datapersonal_computers_totalResults.txt");
		File out88File=new File(".\\TestResources\\QueryResults\\Datapersonal_computers_totalSQL.txt");
		PrintStream out8 = new PrintStream(out8File);
		System.setOut(out8);
		quer.parser("Select country,year from Datapersonal_computers_total where year=1990 OR country=Myanmar ");
		executeQuer.execute(tester, columns8, quer);
		out8.close();
		
		PrintStream out88 = new PrintStream(out88File);
		System.setOut(out88);
		selectAll("Select country,year from Datapersonal_computers_total where year=1990 OR country='Myanmar' ");
		out88.close();
		
		
		assertEquals(true,(FileUtils.contentEquals(out8File, out88File)));

	
		File file9 = new File(".\\TestResources\\DownloadedData\\surface_area_sq_km.csv");
		String outputPath9 = ".\\TestResources\\DataForTest\\Datasurface_area_sq_km.csv";
		File datafile9 = createDataFile(file9, outputPath9);
		String outputTestPath9 = ".\\TestResources\\DataForTest\\testerForsurface_area_sq_km.txt";
		File test9 = makeTesters(datafile9, outputTestPath9);
		String[] columns9 = readingTest(datafile9);
		String numberOfcolumns9 = "" + columns9.length + "";
		String metadataFilePath9 = tester.register(outputPath9, ",", columns9, numberOfcolumns9);
		File metadataFile9 = new File(metadataFilePath9);
		assertEquals(true, (FileUtils.contentEquals(metadataFile9, test9)));
		
		
		File out9File=new File(".\\TestResources\\QueryResults\\Datasurface_area_sq_kmResults.txt");
		File out99File=new File(".\\TestResources\\QueryResults\\Datasurface_area_sq_kmSQL.txt");
		PrintStream out9 = new PrintStream(out9File);
		System.setOut(out9);
		quer.parser("Select rate from Datasurface_area_sq_km where rate<0 ");
		executeQuer.execute(tester, columns9, quer);
		out9.close();
		PrintStream out99 = new PrintStream(out99File);
		System.setOut(out99);
		selectAll("Select rate from Datasurface_area_sq_km where rate<0 ");
		out99.close();
		
		assertEquals(true,(FileUtils.contentEquals(out9File, out99File)));

		
		
		File file10 = new File(".\\TestResources\\DownloadedData\\children_per_woman_total_fertility.csv");
		String outputPath10 = ".\\TestResources\\DataForTest\\Datachildren_per_woman_total_fertility.csv";
		File datafile10 = createDataFile(file10, outputPath10);
		String outputTestPath10 = ".\\TestResources\\DataForTest\\testerForchildren_per_woman_total_fertility.txt";
		File test10 = makeTesters(datafile10, outputTestPath10);
		String[] columns10 = readingTest(datafile10);
		String numberOfcolumns10 = "" + columns10.length + "";
		String metadataFilePath10 = tester.register(outputPath10, ",", columns10, numberOfcolumns10);
		File metadataFile10 = new File(metadataFilePath10);
		assertEquals(true, (FileUtils.contentEquals(metadataFile10, test10)));
		
		
		File out10File=new File(".\\TestResources\\QueryResults\\Datachildren_per_woman_total_fertilityResults.txt");
		File out1010File=new File(".\\TestResources\\QueryResults\\Datachildren_per_woman_total_fertilitySQL.txt");

		PrintStream out10 = new PrintStream(out10File);
		System.setOut(out10);
		quer.parser("Select year from Datachildren_per_woman_total_fertility where country=Zimbabwe ");
		executeQuer.execute(tester, columns10, quer);
		out10.close();
		PrintStream out1010 = new PrintStream(out1010File);
		System.setOut(out1010);
		selectAll("Select year from Datachildren_per_woman_total_fertility where country='Zimbabwe' ");
		out1010.close();
		
		assertEquals(true,(FileUtils.contentEquals(out10File, out1010File)));

	
	}

	private String[] readingTest(File file) throws FileNotFoundException {
		/* Readind the Datatestfile and return name of columns */

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

	private File makeTesters(File test, String outputtestpath) throws IOException {
		/* Creating a testerfile for the DataFileMetadata */

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

	private File createDataFile(File testfile, String outputFilePath) throws IOException {

		/* Write Data test file in the right format by reading the csv files */

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
	public void selectAll(String sql){  
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
}
