package clientQuering;

import java.io.IOException;

import query.ExecuteQueryFactory;
import query.IExecuteQuery;
import query.QueryParsing;
import register.FileManager;

public class ClientQuering {
		
	public static void main(String[] args)  {
		executeFromArgs(args);
		
	}
	
	public static void executeFromArgs(String[] args) {
		FileManager manager=new FileManager();
		QueryParsing quer=new QueryParsing();
		ExecuteQueryFactory factory = new ExecuteQueryFactory();
		IExecuteQuery executeQuery = factory.generateExecuteQuery();
		String queryString = "";
		for(int i=0;i<args.length;i++) {
			queryString += args[i]+" ";
		}
		System.out.println(queryString);

		try {
			quer.parser(queryString);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			 executeQuery.execute(manager, quer);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}


}
