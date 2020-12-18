package test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import org.apache.commons.io.FileUtils;
import org.junit.Test;

import register.FileManager;


public class FileManagerTest {
	@Test
	public void testRegistration() throws IOException {
		
		String inputFilepath=".\\TestResources\\population_aged_20_39_years_male_percent.csv";
		File testfile = new File(inputFilepath);
		String testfilename=testfile.getName();
		 
		String outputFilePath = ".\\TestResources\\testFor"+testfilename;
		Scanner myReader = new Scanner(testfile);
		ArrayList<String> years = new ArrayList<String>();
		
		
		/** Write one test file from csv files that i had downloaded
		 * 
		 */
		
		FileWriter writer=null;
		try {
			writer = new FileWriter (outputFilePath,true);
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		int i=0;
		writer.write("year");
		writer.write(",");
		writer.write("country");
		writer.write(",");
		writer.write("rate");
		writer.write("\n");
	    while (myReader.hasNextLine()) {
	        String data = myReader.nextLine();
	        String[] labels = data.split(",");
	        if(i!=0) {
	        	for(int k=1;k<labels.length;k++) {
	 	        	//System.out.println(years.get(k-1)+","+labels[0]+","+labels[k]);
	 	        	writer.write(years.get(k-1));
	 	        	writer.write(",");
	 	        	writer.write(labels[0]);
	 	        	writer.write(",");
	 	        	writer.write(labels[k]);
	 	        	writer.write("\n");
	 	        }
	        	i++;      
	        }else {
	        	for(int j=1;j<labels.length;j++) {
		        	//System.out.println(labels[j]);
		        	years.add(labels[j]);
		        	
	        	}
	        	i++;
	        }

	    }

	    writer.close();
        myReader.close();
        
        /* Readind the testfile and calling register to select the info*/
	   
		File file = new File(outputFilePath);
		Scanner newReader = new Scanner(file);
		String[] XXXLabels;
		
		String line=newReader.nextLine();
		String str = line.replaceAll("\\s","");
		XXXLabels=str.split(",");
    	System.out.println(line);
		String XXXnumberOflabels = ""+XXXLabels.length+"";
		
		
  		FileManager tester = new FileManager(); 
		
		String infoFiletesterPath = tester.register(outputFilePath, ",", XXXLabels, XXXnumberOflabels);
		
		String outputtestpath=".\\TestResources\\test.txt";
		File testerfile = new File(outputtestpath);
		File infoFile=new File(infoFiletesterPath);
		
		/*Creating a file for testing register */
		FileWriter w=null;
		try {
			w = new FileWriter (outputtestpath);
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		w.write(file.getName());
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
		
		assertEquals(true, (FileUtils.contentEquals(infoFile, testerfile)));
	

        
		
	    newReader.close();
	}
}

