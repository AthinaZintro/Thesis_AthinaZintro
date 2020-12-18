package register;

import java.io.File;

import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;

import metadata.Info;



public class FileManager implements IFileManager {
	private HashMap<String, Info> fileMetadataInfoCollection;
	private HashMap<String, String> fileMetadataFileCollection;
	
	public FileManager() {
		fileMetadataInfoCollection = new HashMap<String, Info>();
		fileMetadataFileCollection = new HashMap<String, String>();
	}
	
	
	public String register(String inFilePath, String XXXdelimiterOflabels, String[] XXXLabels, String XXXnumberOflabels) {
		
		String inputfilePath = inFilePath;
		File inputfile = new File(inputfilePath);
		String filename=inputfile.getName();
		String filenameWithoutExtension=filename.substring(0, filename.lastIndexOf(".")); //The filename with out extension because i want the info file to has the file name in it
		String outputFilePath = ".\\TestResources\\info-"+filenameWithoutExtension+".txt";   	
		
		Info info = new Info(filename, XXXdelimiterOflabels, XXXLabels, XXXnumberOflabels);
		
		FileWriter writer=null;
		try {
			writer = new FileWriter (outputFilePath);
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
			
			try {
				writer.write(info.getFilename()+"\n");
				writer.write(info.getdelimiterOflabels()+"\n");
				writer.write(info.getnumberOflabels()+"\n");
				String localArray[] = info.getLabels();
				for(int i=0;i<info.getnumberOflabels();i++) {				
					writer.write(localArray[i]+"\n");
				}
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}


		try {
			writer.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
		System.out.println("File is created at " + outputFilePath);


		fileMetadataInfoCollection.put(inputfilePath, info);		
		fileMetadataFileCollection.put(inputfilePath, outputFilePath);
		return outputFilePath;
	}

	
}



