package query;

import java.io.IOException;
import java.util.HashMap;

public  class QueryParser {


	public HashMap<String, String[]> createParser(String query) throws IOException {
		HashMap <String,String[]> queryHashMap=new HashMap<String,String[]>();
		/** Split the query in the right places to take the selectWords words */
		String[] parameters = query.split("Select");
		String [] whereWords = new String[1];

		if(parameters.length==2) {
		
			String[] par = parameters[1].split("from");
			if(par.length>=2) {
				String selectWordsWords = par[0].replaceAll("\\s", "");
				String[] selectWords = selectWordsWords.split(",");
				for (int i = 0; i < selectWords.length; i++) {
					// System.out.print(" "+selectWords[i]+" "+"\n");
				}
				
				queryHashMap.put("Select",selectWords);
	
				String[] where = par[1].split("where"); /** Split the query in the right places to take the fromWords words */
				String fromWordsWords = where[0].replaceAll("\\s", "");
				String[] fromWords = fromWordsWords.split(",");

				queryHashMap.put("from",fromWords);
		
				// System.out.print(" "+fromWords[0]+" "+"\n");
				if(where.length>=2) {
					whereWords[0]= where[1]; /** Split the query in the right places to take the whereWords words */
	
					queryHashMap.put("where",whereWords);
					// System.out.print(" "+whereWords+"\n");
				}else {
					whereWords[0]="";
					queryHashMap.put("where",whereWords);
				}
			}else {
				
				queryHashMap.put("error", null);
			}
		}else {
			queryHashMap.put("error", null);
			
		}
		return queryHashMap;

	}

}
