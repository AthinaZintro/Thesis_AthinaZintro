package query;


import java.io.IOException;


public class QueryParsing {
	

	/**
	* @param fromWords  	     The words fromWords the fromWords query word
	* @param selectWords   		 The words fromWords the selectWords query word
	* @param whereWords  		 The words fromWords the where query word
	*/
	
	private String [] fromWords;    
	private String[] selectWords;   
	private String whereWords; 
	

	/**
	 * @return the fromWords
	 */
	public String[] getFromWords() {
		return fromWords;
	}
	/**
	 * @param fromWords the fromWords to set
	*/
	public void setFromWords(String[] fromWords) {
		this.fromWords = fromWords;
	}
	/**
	 * @return the selectWords
	 */
	public String[] getSelectWords() {
		return selectWords;
	}
	/**
	 * @param selectWords the selectWords to set
	*/
	public void setSelectWords(String[] selectWords) {
		this.selectWords = selectWords;
	}

	
	/**
	 * @return the whereWords
	 */
	public String getWhereWords() {
		return whereWords;
	}
	/**
	 * @param whereWords the whereWords to set
	*/
	public void setWhereWords(String whereWords) {
		this.whereWords = whereWords;
	}

	
	public void parser(String query) throws IOException {
		
		String[] parameters = query.split("Select");		/**Split the query in the right places to take the selectWords words*/
		String[] par=parameters[1].split("from");
		String selectWordsWords = par[0].replaceAll("\\s","");
		String [] selectWords=selectWordsWords.split(",");
		for(int i=0;i<selectWords.length;i++) {
			//System.out.print(" "+selectWords[i]+" "+"\n");
		}
		setSelectWords(selectWords);

		String[] where=par[1].split("where");				/**Split the query in the right places to take the fromWords words*/
		String fromWordsWords= where[0].replaceAll("\\s","");
		String [] fromWords=fromWordsWords.split(",");
		setFromWords(fromWords);
		
		//System.out.print(" "+fromWords[0]+" "+"\n");

		
		String whereWords= where[1];	/**Split the query in the right places to take the whereWords words*/
		setWhereWords(whereWords);
		//System.out.print(" "+whereWords+"\n");	
		
		
	}
	
	
}
