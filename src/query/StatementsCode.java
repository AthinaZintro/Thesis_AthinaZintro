package query;


import java.util.ArrayList;


import register.FileManager;




public class StatementsCode {
	
	/**
	* @param comparisonOperators  	     	 The comparison operators that we have to the conditions of where
	* @param parametersOfConditions   		 The parametersOfConditions that we have to the conditions of where
	*
	*/

	private String comparisonOperators; 
	private String [] parametersOfConditions;
	//private ArrayList <String> countOperators;
	
	/**
	 * @return the parametersOfConditions
	 */
	public String[] getparametersOfConditions() {
		return parametersOfConditions;
	}
	/**
	 * @param parametersOfConditions the parametersOfConditions to set
	*/
	public void setparametersOfConditions(String[] parametersOfConditions) {
		this.parametersOfConditions = parametersOfConditions;
	}
	/**
	 * @return the comparisonOperators
	 */
	public String getcomparisonOperators() {
		return comparisonOperators;
	}
	/**
	 * @param comparisonOperators the comparisonOperators to set
	*/
	public void setcomparisonOperators(String comparisonOperators) {
		this.comparisonOperators = comparisonOperators;
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

	public int where(String [] columns,String conditions) {			/**Takes the condition that has the wherecomparisonOperatorss comparisonOperatorss and returns the position */
		ArrayList <String> cond=new ArrayList<String>();
		 ArrayList<String> operators=new ArrayList<String>();
		
		cond.add("=");
		cond.add(">");
		cond.add("<");
		cond.add(">=");
		cond.add("<=");
		cond.add("<>");
		operators.add("AND");
		operators.add("OR");
		
		for (int i = 0; i < operators.size(); i++) {
		    if(conditions.contains(operators.get(i))) {
		    	System.out.println( operators.get(i));
		    	//String oper=operators.get(i);
		    	//countOperators.add(oper);
		    	
		    	
		  }
		}
		//String[] abc = conditions[0].split("AND|OR");
		//for (int i = 0; i < abc.length; i++) {
		//	System.out.println(abc[i]);
		//}
		
		
		for (int i = 0; i < cond.size(); i++) {
		    if(conditions.contains(cond.get(i))) {
		    	System.out.println( cond.get(i));
		    	comparisonOperators= cond.get(i);
		    	setcomparisonOperators(comparisonOperators);
		    	
		    	
		  }
		}
		int positionW=0;
		  
		String[] parametersOfConditions = conditions.split(comparisonOperators);
		setparametersOfConditions(parametersOfConditions);
    	for(int i=0;i<columns.length;i++) {
    		if (columns[i].equals(parametersOfConditions[0])){
    				 positionW= i;
    				 
    		}
    	}
		return positionW;
		
		
	}		
		
}
	

	
	