package clientGui;

import java.awt.FileDialog;
import java.awt.Frame;
import java.io.FileNotFoundException;
import java.io.File;
import java.util.Scanner;

import register.FileManagerFactory;
import register.IFileManager;

/**
 * This class choose the file and creates a new one with the info that we need
 * 
 * @param filename          The name of the file
 * @param delimiterOflabels The delimiter that separates the columns of the file
 * @param numberOflabels    The number of columns of the file
 * @param labels            The columns of the file
 */
public class DraftApplication {
	
	public  void draft() {
		@SuppressWarnings("unused")
		String filename ="";
		String delimiterOflabels = "";
		String numberOflabels = "";
		String labels[] = null;

		Frame Frame = null;
		FileDialog dialog = new FileDialog(Frame, "Select File to Open"); // Open a window to choose file
		dialog.setMode(FileDialog.LOAD);
		dialog.setVisible(true);
		String file = dialog.getFile();
		File ma = new File(dialog.getDirectory() + file);

		Scanner myReader = null;
		try {
			myReader = new Scanner(ma);
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		int i = 0;
		while (myReader.hasNextLine()) { // Read every line and initialize every variable
			String line = myReader.nextLine();
			if (i == 0) {
				filename = line;
				System.out.println(line);
			} else if (i == 1) {
				delimiterOflabels = line;
				System.out.println(line);
			} else if (i == 2) {
				numberOflabels = line;
				System.out.println(line);
			} else if (i == 3) {
				String str = line.replaceAll("\\s", "");
				labels = str.split(delimiterOflabels);
			}
			i += 1;
		}

		myReader.close();
		FileManagerFactory factory = new FileManagerFactory();
		IFileManager fileManager = factory.generateFileManager();
		String out = fileManager.register(ma.getAbsolutePath(), delimiterOflabels, labels, numberOflabels);
		System.out.println(out);
		System.exit(-100);
	}

	
}
