package query;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import register.IFileManager;

public class QueryProcessor {

	/**
	 * @param comparisonOperators    The comparison operators that we have to the
	 *                               conditions of where
	 * @param parametersOfConditions The parametersOfConditions that we have to the
	 *                               conditions of where
	 * @param countOperators         The countOperators that we have to the
	 *                               conditions of where
	 */

	private ArrayList<String> comparisonOperators;
	private ArrayList<String> parametersOfConditions;
	private ArrayList<String> countOperators;
	private HashMap<String, ArrayList<String>> queryProcessor;

	public QueryProcessor() {

		this.comparisonOperators = new ArrayList<String>();
		this.parametersOfConditions = new ArrayList<String>();
		this.countOperators = new ArrayList<String>();
		this.queryProcessor =new HashMap<String, ArrayList<String>>();

	}

	public String computeFrom(IFileManager manager,String[] from) { 
		/**
		 * Chooses the file that has the from comparisonOperatorss and returns the path
		 */						

		String x = from[0];
		String path = manager.getFileAndPathCollection().get(x);
		if(path==null) {
			File file = new File(".//TestResources//DataForTest//"+x+"Metadata.txt");
			if(file.exists()) {
				path=".//TestResources//DataForTest//"+x+".csv";
			}
		}

		return path;

	}

	public ArrayList<Integer> computeSelect(String[] columns,String[] select) { 
		/**
		 * Takes the column that has the select comparisonOperatorss and returns the
		 * position
		 */

		ArrayList<Integer> selectPositions = new ArrayList<Integer>();
		for (int j = 0; j < select.length; j++)
			for (int i = 0; i < columns.length; i++) {
				if (columns[i].equals(select[j])) {
					selectPositions.add(i);
				}
			}
		return selectPositions;
	}

	public HashMap<String, ArrayList<String>> computeWhere(String[] columns,String conditions) { 
		/**
		 * Takes the condition that has the wherecomparisonOperatorss
		 * comparisonOperatorss and returns the position
		 */
		ArrayList<String> cond = new ArrayList<String>();
		ArrayList<String> cond1 = new ArrayList<String>();

		cond.add("=");
		cond.add(">");
		cond.add("<");
		cond.add(">=");
		cond.add("<=");
		cond.add("!=");
		cond1.add(">=");
		cond1.add("<=");
		cond1.add("!=");

		String[] condSplit = conditions.split("\\s+");		
		ArrayList<String> countConditions = new ArrayList<String>();

		for (int i = 1; i < condSplit.length; i++) {
			if ((condSplit[i].equals("AND")) || (condSplit[i].equals("OR"))) {
				countOperators.add(condSplit[i]);
			}
		}
		
		String[] condSplit1=conditions.split("\\s+AND\\s+|\\s+OR\\s+");
		
		
		
		for (int i = 0; i < condSplit1.length; i++) {
				countConditions.add(condSplit1[i].trim());
		}	

		ArrayList<String> fistPartCondition = findComparisonOperators(countConditions, cond, cond1, columns);
		
		queryProcessor.put("countOperators", countOperators);
		queryProcessor.put("comparisonOperators", comparisonOperators);
		queryProcessor.put("parametersOfConditions", parametersOfConditions);
		queryProcessor.put("fistPartCondition", fistPartCondition);


		return queryProcessor;

	}

	
	/**
	 * Takes the countConditions and split to the cond(comparison Operators) takes
	 * the firsts parts conditions words and returns every position of them by the
	 * column and saves the comparison Operators (comparisonOperators) and every
	 * second Word of conditions (parametersOfConditions)
	 * 
	 * @param cond1
	 */
	private ArrayList<String> findComparisonOperators(ArrayList<String> countConditions, ArrayList<String> cond,
			ArrayList<String> cond1, String[] columns) {
		HashMap<String, Integer> columnsHashmap = new HashMap<String, Integer>();
		/**
		 * The columnsHashmap has the <columns,position on the file> use to know which
		 * column is the firsrPartCondition word
		 */
		for (int i = 0; i < columns.length; i++) {
			columnsHashmap.put(columns[i], i);

		}
		ArrayList<String> fistPartCondition = new ArrayList<String>();
		for (int j = 0; j < countConditions.size(); j++) {
			int countTimes = 0;

			for (int i = 0; i < cond.size(); i++) {
				if (countConditions.get(j).contains(cond.get(i))) {

					countTimes++;
				}

			}

			if (countTimes > 1) {

				for (int i = 0; i < cond1.size(); i++) {
					if (countConditions.get(j).contains(cond1.get(i))) {
						String[] con1 = countConditions.get(j).split(cond1.get(i));
						if (columnsHashmap.get(con1[0]) == null) {
							fistPartCondition.add(""+-1+"");

						} else {
							fistPartCondition.add(""+columnsHashmap.get(con1[0])+"");
						}
						comparisonOperators.add(cond1.get(i));
						
						parametersOfConditions.add(con1[1]);
					}
				}

			} else {
				for (int i = 0; i < cond.size(); i++) {
					if (countConditions.get(j).contains(cond.get(i))) {
						String[] con = countConditions.get(j).split(cond.get(i));
						if (columnsHashmap.get(con[0]) == null) {
							fistPartCondition.add(""+-1+"");
						} else {
							fistPartCondition.add(""+columnsHashmap.get(con[0])+"");
						}
						comparisonOperators.add(cond.get(i));
						parametersOfConditions.add(con[1]);
					}
				}

			}
		}
	


		return fistPartCondition;

	}

	
}
