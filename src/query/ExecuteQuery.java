package query;

import java.io.BufferedReader;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import register.FileManager;

public class ExecuteQuery {
	private boolean resultBoolean;
	
	
	/**
	 * @param manager 			FileManager class
	 * @param columns			The columns of the file
	 * @param quer				QueryParsing class
	 */
	public void execute(FileManager manager,String [] columns,QueryParsing quer) throws IOException {
		
		StatementsCode code=new StatementsCode();

		/**Take the query that we split open the file from fromParameters words,
		 *  choose column with the selectParameters and check the conditions that
		 * 	has the whereConditions
		 */
		String[] fromParameters = quer.getFromWords();
		String[] selectParameters = quer.getSelectWords();
		String whereConditions = quer.getWhereWords();
		String path = code.from(manager, fromParameters);
		int selectValue=code.select(columns, selectParameters);
		ArrayList <Integer> w=code.where(columns,whereConditions);
		ArrayList<String> relationalsOperator = code.getComparisonOperators();
		ArrayList<String> conditionsParameters = code.getParametersOfConditions();
		ArrayList<String> operators =code.getCountOperators();
		
		/**Reading the file, calling operatorsChoosing to check if every condition is true or false
		 * and creating the where condition bringing all the results together with the 
		 */
		
		
		String line;
		int k=1;

		BufferedReader br = new BufferedReader(new FileReader(path));
		while ((line = br.readLine()) != null) {
			Boolean[] result = new Boolean[3];

			if(k>4) {
				
				String[] cols = line.split(",");
	    		

				for(int m=0;m<w.size();m++) {
					result[m]=operatorsChoosing(relationalsOperator.get(m),cols[w.get(m)],conditionsParameters.get(m));
					if(m==0) {
						resultBoolean=result[m];
						setResultBoolean(resultBoolean);
					}
				}
				//System.out.println("result[0] = " + result[0]+" result[1] = "+result[1]+" result[2] = "+result[2]);		
				if ( result(result.length,operators,0)==true){
		    	    //System.out.println(" "+k+" "+columns[0]+" "+cols[0]+" "+columns[1]+" "+ cols[1] + " "+columns[2]+" "+cols[2] );
		    		System.out.println(cols[selectValue]);
		    	
		    	}
				
			}
				    	
		    
		    k++;
		    
		}
		
		br.close();
		
	}
	public boolean isResultBoolean() {
		return resultBoolean;
	}
	public void setResultBoolean(boolean resultBoolean) {
		this.resultBoolean = resultBoolean;
	}
	/**
	 * 
	 * @param a		The comparison operator of condition
	 * @param x		The first parameter before the comparison operator of condition
	 * @param y		The second parameter before the comparison operator of condition
	 * @return		The result of a condition 
	 */
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
	/**
	 * 
	 * @param k 		The length of result array
	 * @param oper		The AND, OR operators that has the query
	 * @param j			A count to run the method/recursive function
	 * @return			The result of query 
	 */
	/**
	 * Takes every result of a condition and combined it with the AND, OR operators that has the query 
	 * and returns the result of query
	 */
	 public boolean  result(int k,ArrayList<String> oper,int j) {
		    if (k > j) {
		    	if(oper.get(j).equals("AND")) {
		    		resultBoolean=resultBoolean &&  result(j+1,oper,j+1);
		    	}else {
		    		resultBoolean=resultBoolean ||  result(j+1,oper,j+1);
		    	}
		    	return resultBoolean;
		    } else {
		    	return resultBoolean;
		    }
		 }
}
	
	
	