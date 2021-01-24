package clientRegister;

import register.FileManagerFactory;
import register.IFileManager;

public class ClientRegisterFile {
	
	public static void main(String[] args) {
		registerInfo(args);
		
	}
	
	public static void registerInfo(String[] args) {
		String filename = args[0];
		String delimiterOflabels =  args[1];
		String numberOflabels =args[2];

		int j=0;
		String[] labels = new String[Integer.parseInt(numberOflabels)];
		for(int i=3;i<args.length;i++) {
			labels[j]=args[i];
			j++;
			
		}
		String path=".//TestResources//DataForTest//"+filename;

		
		FileManagerFactory factory = new FileManagerFactory();
		IFileManager fileManager = factory.generateFileManager();
		fileManager.register(path, delimiterOflabels, labels, numberOflabels);
		
	}

}



