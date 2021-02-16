package clientRegisterApplication;
import java.io.File;
import register.FileManagerFactory;
import register.IFileManager;
public class ClientRegisterFile {
	
	public static void main(String[] args) {
		int result=registerInfo(args);
		if (result==0) {
			System.out.println("Registration succeeded");
		}else {
			System.out.println("The information that you gave are wrong, there is not such file to register");
		}
	}
	public static int registerInfo(String[] args) {
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
		File file =new File(path);
		if(file.exists()) {
			FileManagerFactory factory = new FileManagerFactory();
			IFileManager fileManager = factory.generateFileManager();
			fileManager.register(path, delimiterOflabels, labels, numberOflabels);
			return 0;
		}else {
			return -1;
		}
	}
}




