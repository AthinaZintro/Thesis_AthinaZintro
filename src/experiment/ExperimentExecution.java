package experiment;
import static org.junit.Assert.assertEquals;

import java.io.BufferedWriter;
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
import java.util.HashMap;
import java.util.Map.Entry;

import org.apache.commons.io.FileUtils;

import query.QueryServer;
import register.FileManager;

public class ExperimentExecution {
	public static void main(String[] args) {
		FileManager tester=new FileManager();

		experimentRegisterFiles(tester);
		experimentQueryFiles(tester) ;
		
	}
	

	private static void experimentRegisterFiles(FileManager tester) {
		//Register File 
		HashMap<String,Long> timeRegister= new HashMap<String,Long>();
		String inputputPath1 =".\\TestResources\\DataForTest\\at_least_basic_water_source_urban_access_percentEXP.csv";
		String [] columns1= {"year","country","rate"};
		String numberOfcolumns1=""+columns1.length;
		File file1 =new File(inputputPath1);
		String filename1=file1.getName();
		long start1 = System.nanoTime();
		tester.register(inputputPath1, ",", columns1, numberOfcolumns1);
		long finish1 = System.nanoTime();
		long timeElapsed1 = finish1 - start1;
		timeRegister.put(filename1, timeElapsed1);
		
		String inputputPath2 = ".\\TestResources\\DataForTest\\Loan1MEXP.csv";
		String [] columns2= {"loan_id","account_id","amount","duration"};
		String numberOfcolumns2=""+columns2.length;
		File file2 =new File(inputputPath2);
		String filename2=file2.getName();
		long start2 = System.nanoTime();
		tester.register(inputputPath2, ";", columns2, numberOfcolumns2);
		long finish2 = System.nanoTime();
		long timeElapsed2 = finish2 - start2;
		timeRegister.put(filename2, timeElapsed2);
		
		
		String inputputPath3 = ".\\TestResources\\DataForTest\\accountEXP.csv";
		String [] columns3= {"account_id","district_id","frequency","date","district_name","region","Inhabitants","Municipalities499","Municipalities500_2999","Municipalities2000_9999","Municipalities10000","Cities","Ratio_of_urban _Inh","Avg_Salary","Unemploymant_Rate95","Unemploymant_Rate96","Enterpreuners","Commited_Crimes95","Commited_Crimes96","All"};
		String numberOfcolumns3=""+columns3.length;
		File file3 =new File(inputputPath3);
		String filename3=file3.getName();
		long start3 = System.nanoTime();
		tester.register(inputputPath3, ";", columns3, numberOfcolumns3);
		long finish3 = System.nanoTime();
		long timeElapsed3 = finish3 - start3;
		timeRegister.put(filename3, timeElapsed3);
		
		
		String inputputPath4 = ".\\TestResources\\DataForTest\\children_per_woman_total_fertilityEXP.csv";
		String [] columns4= {"year","country","rate"};
		String numberOfcolumns4=""+columns4.length;
		File file4 =new File(inputputPath4);
		String filename4=file4.getName();
		long start4 = System.nanoTime();
		tester.register(inputputPath4, ",", columns4, numberOfcolumns4);
		long finish4 = System.nanoTime();
		long timeElapsed4 = finish4 - start4;
		timeRegister.put(filename4, timeElapsed4);
		
		
		String inputputPath5 = ".\\TestResources\\DataForTest\\dateEXP.csv";
		String [] columns5= {"SK_Day","Day","Month","Year","All"};
		String numberOfcolumns5=""+columns5.length;
		File file5 =new File(inputputPath5);
		String filename5=file5.getName();
		long start5 = System.nanoTime();
		tester.register(inputputPath5, ",", columns5, numberOfcolumns5);
		long finish5 = System.nanoTime();
		long timeElapsed5 = finish5 - start5;
		timeRegister.put(filename5, timeElapsed5);
		
		
		
		String inputputPath6 = ".\\TestResources\\DataForTest\\loanEXP.csv";
		String [] columns6= {"loan_id","account_id","date","status","amount","duration","payments"};
		String numberOfcolumns6=""+columns6.length;
		File file6 =new File(inputputPath6);
		String filename6=file6.getName();
		long start6 = System.nanoTime();
		tester.register(inputputPath6, ";", columns6, numberOfcolumns6);
		long finish6 = System.nanoTime();
		long timeElapsed6 = finish6 - start6;
		timeRegister.put(filename6, timeElapsed6);
		
		
		String inputputPath7 = ".\\TestResources\\DataForTest\\payment_reasonEXP.csv";
		String [] columns7= {"reason","All"};
		String numberOfcolumns7=""+columns7.length;
		File file7 =new File(inputputPath7);
		String filename7=file7.getName();
		long start7 = System.nanoTime();
		tester.register(inputputPath7, ";", columns7, numberOfcolumns7);
		long finish7 = System.nanoTime();
		long timeElapsed7 = finish7 - start7;
		timeRegister.put(filename7, timeElapsed7);
		
		String outputFilePath=".\\TestResources\\ExperimentsTime\\timeRegisterExperiments.txt";
		File file = new File(outputFilePath);
	        
        BufferedWriter bf = null;;
        
        try{
            
            bf = new BufferedWriter( new FileWriter(file) );
            bf.write( "Filename" + " | "+"TimeRegisterOfCode"+"\n" );
            for(Entry<String,Long> entry : timeRegister.entrySet()){
                bf.write( entry.getKey() + " | " + entry.getValue() );
                bf.newLine();
            }
            
            bf.flush();
 
        }catch(IOException e){
            e.printStackTrace();
        }finally{
            
            try{
                //always close the writer
                bf.close();
            }catch(Exception e){}
        }
   
	}	
	private static void experimentQueryFiles(FileManager tester) {
		
		HashMap<String,ArrayList<Long>> timeResults= new HashMap<String,ArrayList<Long>>();
		String outpath1=".\\TestResources\\QueryResults\\at_least_basic_water_source_urban_access_percentEXPresults.txt";
		String outSqlpath1=".\\TestResources\\SqliteResults\\at_least_basic_water_source_urban_access_percentEXPresultsSQL.txt";
		String query1="Select country,rate,year from at_least_basic_water_source_urban_access_percentEXP where 	rate>0.295 AND 	country=Afghanistan OR year<=1971 AND year>1960";
		String querySQL1="Select country,rate,year from at_least_basic_water_source_urban_access_percentEXP where rate>0.295 AND		 country='Afghanistan'   OR year<=1971 AND year>1960";
		timeResults=executeTheQuery(tester,timeResults,outpath1,outSqlpath1,query1,querySQL1);
		
		String outpath2=".\\TestResources\\QueryResults\\Loan1MEXPresults.txt";
		String outSqlpath2=".\\TestResources\\SqliteResults\\Loan1MEXPresultsSQL.txt";
		String query2="Select amount from Loan1MEXP ";
		String querySQL2="Select amount from Loan1MEXP ";
		timeResults=executeTheQuery(tester,timeResults,outpath2,outSqlpath2,query2,querySQL2);
		
	    String outpath3=".\\TestResources\\QueryResults\\accountEXPresults.txt";
		String outSqlpath3=".\\TestResources\\SqliteResults\\accountEXPresultsSQL.txt";
		String query3="Select account_id,date,district_name  from accountEXP where district_name=Frydek - Mistek ";
		String querySQL3="Select account_id,date,district_name from accountEXP where district_name='Frydek - Mistek' ";
		timeResults=executeTheQuery(tester,timeResults,outpath3,outSqlpath3,query3,querySQL3);
		
		String outpath4=".\\TestResources\\QueryResults\\children_per_woman_total_fertilityEXPresults.txt";
		String outSqlpath4=".\\TestResources\\SqliteResults\\children_per_woman_total_fertilityEXPresultsSQL.txt";
		String query4="Select year from children_per_woman_total_fertilityEXP where country=Afghanistan ";
		String querySQL4="Select year from children_per_woman_total_fertilityEXP where country='Afghanistan' ";
		timeResults=executeTheQuery(tester,timeResults,outpath4,outSqlpath4,query4,querySQL4);
		
	
		String outpath5=".\\TestResources\\QueryResults\\dateEXPresults.txt";
		String outSqlpath5=".\\TestResources\\SqliteResults\\dateEXPresultsSQL.txt";
		String query5="Select SK_Day,Day from dateEXP where Year=2000";
		String querySQL5="Select SK_Day,Day from dateEXP where Year=2000";
		timeResults=executeTheQuery(tester,timeResults, outpath5,outSqlpath5,query5,querySQL5);
		
		String outpath6=".\\TestResources\\QueryResults\\loanEXPresults.txt";
		String outSqlpath6=".\\TestResources\\SqliteResults\\loanEXPresultsSQL.txt";
		String query6="Select account_id, status from loanEXP where duration>=50 OR loan_id<7000 AND status=Running Contract/OK";
		String querySQL6="Select account_id, status from loanEXP where duration>=50 OR loan_id<7000 AND status='Running Contract/OK' ";
		timeResults=executeTheQuery(tester,timeResults,outpath6,outSqlpath6,query6,querySQL6);
		
		String outpath7=".\\TestResources\\QueryResults\\payment_reasonEXPresults.txt";
		String outSqlpath7=".\\TestResources\\SqliteResults\\payment_reasonEXPresultsSQL.txt";
		String query7="Select reason from payment_reasonEXP ";
		String querySQL7="Select reason from payment_reasonEXP ";
		timeResults=executeTheQuery(tester,timeResults,outpath7,outSqlpath7,query7,querySQL7);
		
		String outputFilePath=".\\TestResources\\ExperimentsTime\\timeQueryExperiments.txt";
		File file = new File(outputFilePath);
	        
        BufferedWriter bf = null;;
        
        try{
            
            bf = new BufferedWriter( new FileWriter(file) );
            bf.write( "Filename" + " | "+"TimeOfCode"+" | "+ "TimeOfSqlite"+"\n" );
            for(Entry<String, ArrayList<Long>> entry : timeResults.entrySet()){
                bf.write( entry.getKey() + " | " + entry.getValue() );
                bf.newLine();
            }
            
            bf.flush();
 
        }catch(IOException e){
            e.printStackTrace();
        }finally{
            
            try{
                //always close the writer
                bf.close();
            }catch(Exception e){}
        }
   
	}
	
	
	private static HashMap<String, ArrayList<Long>> executeTheQuery(FileManager tester, HashMap<String,ArrayList<Long>> timeResults,String outpath,String outSqlpath,String query,String querySql) {
		int check = 0;
		File outFile=new File(outpath);
		String filename=outFile.getName();
		File outSqlFile=new File(outSqlpath);
		QueryServer executeQuer = new QueryServer();
		ArrayList<Long> array = new ArrayList<>();
		long start = System.nanoTime();
		check = executeQuer.execute(tester, query);
		long finish = System.nanoTime();
		long timeElapsed = finish - start;
		array.add(timeElapsed);
	
		if(check==0) {
	
			PrintStream out = null;
			try {
				out = new PrintStream(outSqlFile);
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.setOut(out);
			long start1 = System.nanoTime();
			selectAll(querySql);
			long finish1 = System.nanoTime();
			long timeElapsed1 = finish1 - start1;
			array.add(timeElapsed1);
			out.close();
		
	
			try {
				assertEquals(true,(FileUtils.contentEquals(outFile, outSqlFile)));
			} catch (IOException e) {
				System.err.println("Assertion error: fileUtils.contentEquals crashes. \n\n");
				e.printStackTrace();
			}
		
		}else {
			System.out.println("The query that you have typed is wrong\n");
		}
		timeResults.put(filename, array);
		return timeResults;
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
