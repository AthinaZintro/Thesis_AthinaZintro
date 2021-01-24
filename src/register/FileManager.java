package register;

import java.io.File;

import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;

import metadata.Info;

public  class FileManager implements IFileManager {
	private HashMap<String, Info> fileMetadataInfoCollection;
	private HashMap<String, String> fileMetadataFileCollection;
	private HashMap<String, String> fileAndPathCollection;

	public FileManager() {
		fileMetadataInfoCollection = new HashMap<String, Info>();
		fileMetadataFileCollection = new HashMap<String, String>();
		fileAndPathCollection = new HashMap<String, String>();
	}

	public HashMap<String, Info> getFileMetadataInfoCollection() {
		return fileMetadataInfoCollection;
	}

	public void setFileMetadataInfoCollection(HashMap<String, Info> fileMetadataInfoCollection) {
		this.fileMetadataInfoCollection = fileMetadataInfoCollection;
	}

	public HashMap<String, String> getFileMetadataFileCollection() {
		return fileMetadataFileCollection;
	}

	public void setFileMetadataFileCollection(HashMap<String, String> fileMetadataFileCollection) {
		this.fileMetadataFileCollection = fileMetadataFileCollection;
	}

	public HashMap<String, String> getFileAndPathCollection() {
		return fileAndPathCollection;
	}

	public void setFileAndPathCollection(HashMap<String, String> fileAndPathCollection) {
		this.fileAndPathCollection = fileAndPathCollection;
	}

	public String register(String inFilePath, String columnsDelimiter, String[] columnsNames, String columnsCount) {

		String inputfilePath = inFilePath;
		File inputfile = new File(inputfilePath);
		String filename = inputfile.getName();
		String filenameWithoutExtension = filename.substring(0, filename.lastIndexOf("."));
		String output = inputfilePath.substring(0, inputfilePath.lastIndexOf("."));
		String outputFilePath = output + "Metadata.txt";

		Info info = new Info(filename, columnsDelimiter, columnsNames, columnsCount);
		// System.out.println(filename);

		FileWriter writer = null;
		try {
			writer = new FileWriter(outputFilePath);
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		try {
			writer.write(info.getFilename() + "\n");
			writer.write(info.getdelimiterOflabels() + "\n");
			writer.write(info.getnumberOflabels() + "\n");
			String localArray[] = info.getLabels();
			for (int i = 0; i < info.getnumberOflabels(); i++) {
				writer.write(localArray[i] + "\n");
				// System.out.println(columnsNames.length);
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
		//System.out.println("File is created at " + outputFilePath);

		fileMetadataInfoCollection.put(inputfilePath, info);
		fileMetadataFileCollection.put(inputfilePath, outputFilePath);
		fileAndPathCollection.put(filenameWithoutExtension, inputfilePath);
		return outputFilePath;
	}

}
