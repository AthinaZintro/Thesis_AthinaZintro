package clientQuering;

import java.io.IOException;

import query.ExecuteQueryFactory;
import query.IExecuteQuery;
import query.ParseQuery;
import register.FileManager;

public class ClientQuering {
		
	public static void main(String[] args)  {
		int result = executeFromArgs(args);
		if(result==-1) {
			System.out.println("The column information that you gave is wrong!\n");
		}else if(result==-2) {
			System.out.println("The file is not registered!\n");
		}else if(result==-3) {
			System.out.println("The query that you have typed is wrong\n");
		}
		
	}
	
	public static int executeFromArgs(String[] args) {
		int out=0;
		FileManager manager=new FileManager();
		ParseQuery quer=new ParseQuery();
		ExecuteQueryFactory factory = new ExecuteQueryFactory();
		IExecuteQuery executeQuery = factory.generateExecuteQuery();
		String queryString = "";
		for(int i=0;i<args.length;i++) {
			queryString += args[i]+" ";
		}
		System.out.println(queryString);

		try {
			out=quer.createParser(queryString);
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(out!=-3) {
			try {
				out = executeQuery.execute(manager, quer);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return out;
		
	}


}
