package clientQueringApplication;

import java.io.IOException;

import query.QueryServerFactory;
import query.IQueryServer;
import register.FileManagerFactory;
import register.IFileManager;

public class ClientQuering {
		
	public static void main(String[] args)  {
		int result = executeFromArgs(args);
		if(result==-1) {
			System.out.println("The column information that you gave is wrong!\n");
		}else if(result==-2) {
			System.out.println("The file is not registered!\n");
		}else if(result==-3) {
			System.out.println("The query that you have typed is wrong\n");
		}else {
			System.out.println("The query execution succeeded\n");
		}
		
	}
	
	public static int executeFromArgs(String[] args) {
		int out=0;
		FileManagerFactory managerfactory = new FileManagerFactory();
		IFileManager manager = managerfactory.generateFileManager();
		QueryServerFactory factory = new QueryServerFactory();
		IQueryServer executeQuery = factory.generateExecuteQuery();
		String queryString = "";
		for(int i=0;i<args.length;i++) {
			queryString += args[i]+" ";
		}
		System.out.println(queryString);

		try {
			out = executeQuery.execute(manager, queryString);
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return out;
		
	}


}
