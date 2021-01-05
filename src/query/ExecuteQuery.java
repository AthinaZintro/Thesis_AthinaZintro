package query;

import java.io.BufferedReader;

import java.io.FileReader;
import java.io.IOException;

import register.FileManager;

public class ExecuteQuery {

	
	public void execute(FileManager manager,String [] columns,QueryParsing quer) throws IOException {
		
		
		/**Take the query that we split open the file from fromParameters words,
		 *  choose column with the selectParameters and check the conditions that
		 * 	has the whereConditions
		 */
		String[] fromParameters = quer.getfromWords();
		String[] selectParameters = quer.getselectWords();
		String whereConditions = quer.getWhereWords();
		StatementsCode code=new StatementsCode();
		String path = code.from(manager, fromParameters);
		int selectValue=code.select(columns, selectParameters);
		int whereValue=code.where(columns, whereConditions);
	
		String relationalsOperator = code.getcomparisonOperators();
		String[] conditionsParameters = code.getparametersOfConditions();
		
		/**Reading the file and calling operatorsChoosing to check if the condition is true or false
		 * 
		 */
		String line;
		int k=1;
		BufferedReader br = new BufferedReader(new FileReader(path));
		while ((line = br.readLine()) != null) {
			
			if(k>4) {
				
				String[] cols = line.split(",");
		    	//System.out.println("Column1 = " + cols[whereValue]);		// column of file
		    	////System.out.println("Column = " +param1[1]); 	// The value from select
				
		    	if (operatorsChoosing(relationalsOperator,cols[whereValue],conditionsParameters[1])==true){
		    	     //System.out.println(" "+k+" "+columns[0]+" "+cols[0]+" "+columns[1]+" "+ cols[1] + " "+columns[2]+" "+cols[2] );
		    		 System.out.println(k+"  "+cols[selectValue]);
		    		
		    		
		    	
		    	}
		    	
		    	
		    }
		    k++;
		}
		
		br.close();
		
	}
	public boolean operatorsChoosing(String a,String x,String y) {	/** Checks which comparison operator the condition has  */
		//System.out.println("a: "+a+" x: "+x+" y: "+y+"\n");		/*	makes the comparison and returns true or false for that */
		if(a.equals("=")) {
			if (x.equals(y)) {
				return true;
			}
			
		}else {
			double x1 =Double.parseDouble(x);
	        double y1 =Double.parseDouble(y);
			if(a.equals(">")){
				if (x1>y1) {
					//System.out.println("a: "+a+" x1: "+x1+" y1: "+y1+"\n");
					return true;
					
				}
			}else if(a.equals("<")) {
				if (x1<y1) {
					return true;
				}

			}else if(a.equals("=<")){
				if (x1<=y1) {
					return true;
				}

			}else if(a.equals("=>")){
				if (x1>=y1) {
					return true;
				}

			}else if(a.equals("!=")){
				if (x1!=y1) {
					return true;
				}

			}else {
				System.out.println("No relational operator");
				return false;
			}
		}
		return false;
		
	}
	
}
