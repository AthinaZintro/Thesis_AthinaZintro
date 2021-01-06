package query;



import java.util.ArrayList;
import java.util.HashMap;
import register.FileManager;




public class StatementsCode {
	
	/**
	* @param comparisonOperators  	     	 The comparison operators that we have to the conditions of where
	* @param parametersOfConditions   		 The parametersOfConditions that we have to the conditions of where
	* @param countOperators   		 		 The countOperators that we have to the conditions of where
	*/

	private ArrayList <String>  comparisonOperators; 
	private  ArrayList <String> parametersOfConditions;
	private ArrayList <String> countOperators;
	

	 public StatementsCode() {
		 
		 	this.comparisonOperators = new ArrayList<String>() ;
			this.parametersOfConditions=new ArrayList<String>();
			this.countOperators= new ArrayList<String>();
			
	 }
	
	public String from(FileManager manager, String[] from) {		/**Chooses the file that has the from comparisonOperatorss and returns the path */
		 
		   
		 	System.out.print(" "+from[0]+" "+"\n");

			String x = from[0];
			String path = manager.getFileAndPathCollection().get(x);         
			  
			System.out.println("path "+path);
			return path;
		 
	 }
	 public int select(String [] columns, String[] select) {			/**Takes the column that has the select comparisonOperatorss and returns the position */
		 
		    
		    String w = select[0];
			int positionS=0;
			for(int i=0;i<columns.length;i++) {
				if (columns[i].equals(w)){
					 positionS = i;
				}
			}
			return positionS;
	 }

	public ArrayList<Integer> where(String [] columns,String conditions) {			/**Takes the condition that has the wherecomparisonOperatorss comparisonOperatorss and returns the position */
		ArrayList <String> cond=new ArrayList<String>();
		cond.add("=");
		cond.add(">");
		cond.add("<");
		cond.add(">=");
		cond.add("<=");
		cond.add("<>");
	
		String[] condSplit = conditions.split(" ");
	
		ArrayList<String> countConditions=new ArrayList<String> ();
		
		for(int i=1;i<condSplit.length;i++) {
			if((condSplit[i].equals("AND"))||(condSplit[i].equals("OR"))) {
					countOperators.add(condSplit[i]);
			}else {
					countConditions.add(condSplit[i]);
			}
		}
		ArrayList<Integer> fistPartCondition = comparisonOperatorDirection(countConditions,cond,columns);
		
		

		return fistPartCondition;
		
	}	
	/**
	 * @return the comparisonOperators
	 */
	public ArrayList<String> getComparisonOperators() {
		return comparisonOperators;
	}
	/**
	 * @param comparisonOperators the comparisonOperators to set
	*/
	public void setComparisonOperators(ArrayList<String> comparisonOperators) {
		this.comparisonOperators = comparisonOperators;
	}

	/**
	 * @return the parametersOfConditions
	 */
	public ArrayList<String> getParametersOfConditions() {
		return parametersOfConditions;
	}
	/**
	 * @param parametersOfConditions the parametersOfConditions to set
	*/
	public void setParametersOfConditions(ArrayList<String> parametersOfConditions) {
		this.parametersOfConditions = parametersOfConditions;
	}
	/**
	 * @return the countOperators
	 */
	public ArrayList<String> getCountOperators() {
		return countOperators;
	}
	/**
	 * @param countOperators the countOperators to set
	*/
	public void setCountOperators(ArrayList<String> countOperators) {
		this.countOperators = countOperators;
	}
	/**
	 * Takes the countConditions and split to the cond(comparison Operators)
	 * takes the firsts parts conditions words and returns every position of them by the column 
	 * and saves the comparison Operators (comparisonOperators) and every second Word of conditions (parametersOfConditions)
	 */
	public ArrayList<Integer> comparisonOperatorDirection(ArrayList<String> countConditions,ArrayList <String> cond,String [] columns){
		HashMap<String,Integer> columnsHashmap =new HashMap<String,Integer>(); 
		/**The columnsHashmap has the <columns,position on the file> 
		*use to know which column is the firsrPartCondition word
		*/
		for(int i=0;i<columns.length;i++) {
			columnsHashmap.put(columns[i], i);
				 
			
		}
		ArrayList<Integer> fistPartCondition=new ArrayList<Integer>() ;
		for(int j=0;j<countConditions.size();j++) {
			for (int i = 0; i < cond.size(); i++) {
			    if(countConditions.get(j).contains(cond.get(i))) {
			    	String[] con = countConditions.get(j).split(cond.get(i));
					fistPartCondition.add(columnsHashmap.get(con[0]));
			    	comparisonOperators.add(cond.get(i));
			    	parametersOfConditions.add(con[1]);
			    	setParametersOfConditions(parametersOfConditions);
			  }
			}
		}
	
		return fistPartCondition;
		
		
	}
	
}

		
	

	
	