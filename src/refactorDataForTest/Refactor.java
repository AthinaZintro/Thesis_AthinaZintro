package refactorDataForTest;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Refactor {
	
	public static void main(String args[]) {
		File file1 = new File(".\\TestResources\\DownloadedData\\population_aged_20_39_years_male_percent.csv");
		String outputPath1 = ".\\TestResources\\DataForTest\\Datapopulation_aged_20_39_years_male_percent.csv";
		try {
			createDataFile(file1, outputPath1);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		File file2 = new File(".\\TestResources\\DownloadedData\\cell_phones_total.csv");
		String outputPath2 = (".\\TestResources\\DataForTest\\Datacell_phones_total.csv");
		try {
			createDataFile(file2, outputPath2);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		File file3 = new File(".\\TestResources\\DownloadedData\\co2_emissions_tonnes_per_person.csv");
		String outputPath3 = ".\\TestResources\\DataForTest\\Dataco2_emissions_tonnes_per_person.csv";
		try {
			createDataFile(file3, outputPath3);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		File file4 = new File(".\\TestResources\\DownloadedData\\data_quality_life_expectancy.csv");
		String outputPath4 = ".\\TestResources\\DataForTest\\Datadata_quality_life_expectancy.csv";
		try {
			createDataFile(file4, outputPath4);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		File file5 = new File(".\\TestResources\\DownloadedData\\energy_production_total.csv");
		String outputPath5 = ".\\TestResources\\DataForTest\\Dataenergy_production_total.csv";
		try {
			createDataFile(file5, outputPath5);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		File file6 = new File(".\\TestResources\\DownloadedData\\females_aged_15_24_employment_rate_percent.csv");
		String outputPath6 = ".\\TestResources\\DataForTest\\Datafemales_aged_15_24_employment_rate_percent.csv";
		try {
			createDataFile(file6, outputPath6);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		File file7 = new File(".\\TestResources\\DownloadedData\\freedix_fh.csv");
		String outputPath7 = ".\\TestResources\\DataForTest\\Datafreedix_fh.csv";
		try {
			createDataFile(file7, outputPath7);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		File file8 = new File(".\\TestResources\\DownloadedData\\personal_computers_total.csv");
		String outputPath8 = ".\\TestResources\\DataForTest\\Datapersonal_computers_total.csv";
		try {
			createDataFile(file8, outputPath8);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		File file9 = new File(".\\TestResources\\DownloadedData\\surface_area_sq_km.csv");
		String outputPath9 = ".\\TestResources\\DataForTest\\Datasurface_area_sq_km.csv";
		try {
			createDataFile(file9, outputPath9);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		File file10 = new File(".\\TestResources\\DownloadedData\\children_per_woman_total_fertility.csv");
		String outputPath10 = ".\\TestResources\\DataForTest\\Datachildren_per_woman_total_fertility.csv";
		try {
			createDataFile(file10, outputPath10);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	private static File createDataFile(File testfile, String outputFilePath) throws IOException  {

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

}
