package query;

import java.io.BufferedReader;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import register.FileManager;

public class ExecuteQuery {
	private boolean resultl;

	/**
	 * @param manager FileManager class
	 * @param columns The columns of the file
	 * @param quer    QueryParsing class
	 */
	public void execute(FileManager manager, String[] columns, QueryParsing quer) throws IOException {

		StatementsCode code = new StatementsCode();

		/**
		 * Take the query that we split open the file from fromParameters words, choose
		 * column with the selectParameters and check the conditions that has the
		 * whereConditions
		 */
		String[] fromParameters = quer.getFromWords();
		String[] selectParameters = quer.getSelectWords();
		String whereConditions = quer.getWhereWords();
		String path = code.from(manager, fromParameters);
		ArrayList<Integer> selectValues = code.select(columns, selectParameters);
		ArrayList<Integer> w = code.where(columns, whereConditions);
		ArrayList<String> relationalsOperator = code.getComparisonOperators();
		ArrayList<String> conditionsParameters = code.getParametersOfConditions();
		ArrayList<String> operators = code.getCountOperators();

		/**
		 * Reading the file, calling operatorsChoosing to check if every condition is
		 * true or false and creating the where condition bringing all the results
		 * together with the
		 */

		String line;
		int k = 1;

		BufferedReader br = new BufferedReader(new FileReader(path));
		int n = 1;
		while ((line = br.readLine()) != null) {
			ArrayList<Boolean> result = new ArrayList<Boolean>();
			String[] cols = null;
			if (k == 4) {
				String printString = "";
				cols = line.split(",", columns.length);
				for (int y = 0; y < selectValues.size(); y++) {
					printString = printString + cols[selectValues.get(y)] + " ";
				}

				System.out.println(printString);
				if (w.contains(-1)) {
					System.out.println("The column information that you gave is wrong\n");
					return;
				}
			}
			if (k > 4) {
				cols = line.split(",", columns.length);
				for (int m = 0; m < w.size(); m++) {
					if (cols[w.get(m)].isEmpty() != true) {
						result.add(operatorsChoosing(relationalsOperator.get(m), cols[w.get(m)],
								conditionsParameters.get(m)));
						 //System.out.println(cols[w.get(m)]+" "+relationalsOperator.get(m)+""+conditionsParameters.get(m));

						 //System.out.println(result.get(m));
					} else {
						 //System.out.println("oooooooooo"+cols[w.get(m)]);
						// "+relationalsOperator.get(m)+" "+conditionsParameters.get(m));

						/// System.out.println(result.get(m));
						result.add(true);
					}
				}
				//System.out.println(w.size());

				if(w.size()>1) {
					HashMap<Integer, Boolean> resultB = new HashMap<Integer, Boolean>();
					for (int j = 0; j < operators.size(); j++) {
						if (operators.get(j).equals("AND")) {
							/*if((resultB.containsKey(j+1) == true)&&(j-1<0)) {
								resultl = resultB.get(j) && result.get(j + 1);
								resultB.put(j+1, resultl);
								setResultl(resultl);
							}else */if (resultB.containsKey(j-1) == true) {
								resultl = resultB.get(j-1) && result.get(j + 1);
								resultB.put(j, resultl);
								setResultl(resultl);
	
							} else {
								resultl = result.get(j) && result.get(j + 1);
								resultB.put(j, resultl);
								setResultl(resultl);
							}
	
						}
					}
					int f=0;
					for (int j = 0; j < operators.size(); j++) {						
						if (operators.get(j).equals("OR")) {
							if(f==0) {
								if(resultB.containsKey(j-1)==true) {
									resultl=resultB.get(j-1);	
								}else {
									resultl=result.get(j);
								}
								f++;
							}
							if(((resultB.containsKey(j-1) == true))&&(resultB.containsKey(j+1) == true)) {
								resultl=resultB.get(j-1)|| resultB.get(j+1);
								resultB.put(j, resultl);
								setResultl(resultl);
							}else if(((resultB.containsKey(resultB.size()-j) == true))&&(resultB.size()>j)) {
								//System.out.print(resultB.get(j+1));
								resultl = resultl || resultB.get(resultB.size()-j);
								//System.out.println(resultl);
								resultB.put(j, resultl);
								setResultl(resultl);
							}else if(((resultB.containsKey(j+1) == true))&&(resultB.size()<=j)) {
								//System.out.print(resultB.get(j+1));
								resultl = resultl || resultB.get(j+1);
								//System.out.println(resultl);
								resultB.put(j, resultl);
								setResultl(resultl);
							
							} else {
								resultl =resultl || result.get(j + 1);
								//System.out.println("1 "+resultl);
								//resultB.put(j, resultl);
								setResultl(resultl);
	
							}
						}
	
					}
				}else {
					resultl=result.get(0);
				}
				//System.out.println("fin "+resultl);

				if (resultl == true) {
					String printString = "";
					for (int y = 0; y < selectValues.size(); y++) {
						printString = printString + cols[selectValues.get(y)] + " ";

					}

					System.out.println(printString);

				} else {
					n++;

				}
			}

			

			if(k==180) {
				//break;
			}

			k++;
			
			
		}
		
		
		br.close();

	}

	public boolean isResultl() {
		return resultl;
	}

	public void setResultl(boolean resultl) {
		this.resultl = resultl;
	}

	/**
	 * 
	 * @param a The comparison operator of condition
	 * @param x The first parameter before the comparison operator of condition
	 * @param y The second parameter before the comparison operator of condition
	 * @return The result of a condition
	 */
	public boolean operatorsChoosing(String a, String x,
			String y) { /** Checks which comparison operator the condition has */
		// System.out.println("a: "+a+" x: "+x+" y: "+y+"\n"); /* makes the comparison
		// and returns true or false for that */

		if (a.equals("=")) {
			if (x.equals(y)) {
				return true;
			}

		} else {
			double x1 = Double.parseDouble(x);
			double y1 = Double.parseDouble(y);
			if (a.equals(">")) {
				if (x1 > y1) {
					// System.out.println("a: "+a+" x1: "+x1+" y1: "+y1+"\n");
					return true;

				}
			} else if (a.equals("<")) {
				if (x1 < y1) {
					return true;
				}

			} else if (a.equals("<=")) {
				if (x1 <= y1) {
					return true;
				}

			} else if (a.equals(">=")) {
				if (x1 >= y1) {
					return true;
				}

			} else if (a.equals("!=")) {
				if (x1 != y1) {
					return true;
				}

			} else {
				System.out.println("No relational operator");
				return false;
			}
		}
		return false;

	}

}
