package register;



import java.awt.FileDialog;
import java.awt.Frame;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;



public class FileManager {

	public File register(Info info) throws IOException{
		File file = new File("info"+info.filename+".txt"); //Create a file
		FileWriter writer = new FileWriter (file);			//Write the values
		writer.write(info.filename+"\n");
		writer.write(info.limit+"\n");
		writer.write(info.num+"\n");
		for(int i=0;i<info.num;i++) {
			writer.write(info.labels[i]+"\n");
		}
		System.out.println("File is created");
		writer.close();
		
		
		return file;										//Return File
		
		
	}
	public Info check() throws IOException{
		
		  Info info =new Info();
    	  Frame Frame = null;
    	  FileDialog dialog = new FileDialog(Frame, "Select File to Open"); 	//Open a window to choose file
    	  dialog.setMode(FileDialog.LOAD);
    	  dialog.setVisible(true);
    	  String file = dialog.getFile();
          File ma = new File(dialog.getDirectory()+file);
	      Scanner myReader = null;
		try {
			myReader = new Scanner(ma);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	      int i=0;
	      while (myReader.hasNextLine()) {										//	Read every line and initialize every variable
	    	String line=myReader.nextLine();
	    	if(i==0) {
	    		info.filename=line;	
	    	}else if(i==1) {
	    		info.limit=line;	
	    	}else if(i==2) {
	    		info.num=Integer.parseInt(line);
	    	}else if(i==3) {
	    		String str = line.replaceAll("\\s","");
	    		info.labels=str.split(info.limit);
	    	}
	    		
	    	
	    	i+=1;
	    	
	      }
	    
	     myReader.close();  
	
		return info;															//Return object info that has the variables
	  

	}	

	
}



