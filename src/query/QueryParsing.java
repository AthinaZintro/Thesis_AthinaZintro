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
	public String[] getfromWords() {
		return fromWords;
	}
	/**
	 * @param fromWords the fromWords to set
	*/
	public void setfromWords(String[] fromWords) {
		this.fromWords = fromWords;
	}
	/**
	 * @return the selectWords
	 */
	public String[] getselectWords() {
		return selectWords;
	}
	/**
	 * @param selectWords the selectWords to set
	*/
	public void setselectWords(String[] selectWords) {
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
		System.out.print(" "+selectWords[0]+" "+"\n");
		setselectWords(selectWords);

		String[] where=par[1].split("where");				/**Split the query in the right places to take the fromWords words*/
		String fromWordsWords= where[0].replaceAll("\\s","");
		String [] fromWords=fromWordsWords.split(",");
		setfromWords(fromWords);
		
		System.out.print(" "+fromWords[0]+" "+"\n");

		
		String whereWords= where[1].replaceAll("\\s","");	/**Split the query in the right places to take the whereWords words*/
		setWhereWords(whereWords);
		System.out.print(" "+whereWords+"\n");	
		
		
	}
	
	
}
