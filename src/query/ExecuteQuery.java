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
		ArrayList<Integer> selectValues=code.select(columns, selectParameters);
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
		int n=1;
		while ((line = br.readLine()) != null) {
			ArrayList<Boolean> result = new ArrayList<Boolean>();
			String[] cols =null;
			if(k==4) {
				String printString=" ";
				cols = line.split(",",columns.length);
				for(int y=0;y<selectValues.size();y++) {						
						printString = printString+" "+cols[selectValues.get(y)];
				}
				
				System.out.println(printString);
				if(w.contains(-1)) {
					System.out.println("The column information that you gave is wrong\n");
					return;
				}
			}
			if(k>4) {
				cols = line.split(",",columns.length);
				for(int m=0;m<w.size();m++) {
					if(cols[w.get(m)].isEmpty()!=true) {
						result.add(operatorsChoosing(relationalsOperator.get(m),cols[w.get(m)],conditionsParameters.get(m)));
					}else {
						result.add(false);
					}
					if(m==0) {
						resultBoolean=result.get(0);
						setResultBoolean(resultBoolean);
					}else {
						for(int j=0;j<operators.size();j++) {
							if(operators.get(j).equals("AND")) {
					    		resultBoolean=resultBoolean &&  result.get(m);
					    	}else {
					    		resultBoolean=resultBoolean ||  result.get(m);;
					    	}
						}
					}
				}

				if (resultBoolean ==true){
		    	    String printString=" ";
					for(int y=0;y<selectValues.size();y++) {						
							printString = printString+" "+cols[selectValues.get(y)];
							
					}	
				
					System.out.println(n+" "+printString);
					
		    	
				}else {
					n++;

				}
			}
				    	
		    
		    k++;
		    
		}

		if(n+4==k) {
			System.out.println("No result matches your given information \n");
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

			}else if(a.equals("<=")){
				if (x1<=y1) {
					return true;
				}

			}else if(a.equals(">=")){
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
	
	
	