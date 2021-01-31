package query;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

import register.IFileManager;

public  class QueryServer implements IQueryServer {
	private boolean resultl;

	/**
	 * @param manager FileManager class
	 * @param columns The columns of the file
	 * @param quer    QueryParsing class
	 * @return 
	 *
	 */
	public Integer execute(IFileManager manager,String query )  {
		QueryParser quer=new QueryParser();
		HashMap<String, String[]> queryHashMap = new HashMap<String, String[]>();
		try {
			queryHashMap = quer.createParser(query);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(queryHashMap.containsKey("error")) {
			//System.out.println("The query that you wrote isn't in the right form ");
			return -3;

		}
		QueryProcessor code = new QueryProcessor();

		/**
		 * Take the query that we split open the file from fromParameters words, choose
		 * column with the selectParameters and check the conditions that has the
		 * whereConditions
		 */
		
		String[] fromParameters =  queryHashMap.get("from");

		String[] selectParameters = queryHashMap.get("Select");

		String[] whereC = queryHashMap.get("where");
		String whereConditions=whereC[0];
		
		String path = code.computeFrom(manager, fromParameters);
		if(path==null) {
			//System.out.println("The file is not registered!!!");
			return -2;
		}
		String[] columns = null;
		try {
			columns = takeColumns(path);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		ArrayList<Integer> selectValues = code.computeSelect(columns, selectParameters);
		HashMap<String, ArrayList<String>> queryProcessor =new HashMap<String, ArrayList<String>>();
		if(whereConditions!="") {
			queryProcessor = code.computeWhere(columns, whereConditions);
		}
		ArrayList<String> relationalsOperator = queryProcessor.get("comparisonOperators");

		ArrayList<String> conditionsParameters =queryProcessor.get("parametersOfConditions");

		ArrayList<String> operators = queryProcessor.get("countOperators");
		ArrayList<String> firstpart=queryProcessor.get("fistPartCondition");
		ArrayList<Integer> w= new ArrayList<Integer>();
		if(firstpart!=null) {
			for(int i=0;i<firstpart.size();i++){
				w.add(Integer.parseInt(firstpart.get(i)));
			}
		}

		/**
		 * Reading the file, calling operatorsChoosing to check if every condition is
		 * true or false and creating the where condition bringing all the results
		 * together with the
		 */

		String line;
		int k = 1;
		String printFilePath=".//TestResources//QueryResults//"+fromParameters[0]+"Results.txt";
		PrintStream out1 = null;
		try {
			out1 = new PrintStream(printFilePath);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.setOut(out1);
		try (BufferedReader br = new BufferedReader(new FileReader(path))) {
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
						br.close();

						out1.flush();
						out1.close();
					
						
						//System.out.println("The column information that you gave is wrong\n");
						return -1;
					}
				}
				if ((k > 4)&&(w.isEmpty()==false)) {
					cols = line.split(",", columns.length);
					for (int m = 0; m < w.size(); m++) {
						if (cols[w.get(m)].isEmpty() != true) {
							result.add(chooseOperator(relationalsOperator.get(m), cols[w.get(m)],
									conditionsParameters.get(m)));
						} else {
							result.add(false);
						}
					}
					//System.out.println(w.size());
					int i=0;
					if(w.size()>1) {
						HashMap<Integer, Boolean> resultB = new HashMap<Integer, Boolean>();
						for (int j = 0; j < operators.size(); j++) {
							if (operators.get(j).equals("AND")) {
								if( (resultB.containsKey(i-1) == true)&&(operators.get(j-1).equals("AND"))) {
									resultl = resultB.get(i-1) && result.get(j + 1);
									resultB.put(i-1, resultl);

								} else {
									resultl = result.get(j) && result.get(j + 1);
									resultB.put(i, resultl);
									i++;
								}

							}else if ((operators.get(j).equals("OR")==true)) {
									if(j==0) {
										resultB.put(i, result.get(j));
										i++;
									}
									if(j+1<result.size()-1) {
										if(operators.get(j+1).equals("OR")==true) {
											resultB.put(i, result.get(j+1));
											i++;

										}
									}else {
										resultB.put(i, result.get(j+1));

									}
							}
						}
						for (int j = 0; j < resultB.size()-1; j++) {						
								if(j==0) {
									resultl=resultB.get(j);
								
								}			
								resultl=resultl|| resultB.get(j+1);
						}

						
					}else {
						resultl=result.get(0);
					}
					if (resultl == true) {
						String printString = "";
						for (int y = 0; y < selectValues.size(); y++) {
							printString = printString + cols[selectValues.get(y)] + " ";

						}
					
						System.out.println(printString);
						
					}
				}else if((k > 4)&&w.isEmpty()) {
					cols = line.split(",", columns.length);
					String printString = "";
					for (int y = 0; y < selectValues.size(); y++) {
						printString = printString + cols[selectValues.get(y)] + " ";

					}
				
					System.out.println(printString);
					

				}
				k++;
			
			}
			br.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		out1.flush();
		out1.close();
		

		return 0;
	}


	
	/**
	 * 
	 * @param a The comparison operator of condition
	 * @param x The first parameter before the comparison operator of condition
	 * @param y The second parameter before the comparison operator of condition
	 * @return The result of a condition
	 */
	private boolean chooseOperator(String a, String x,String y) { 
		/** Checks which comparison operator the condition has */
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
	/* Reading the Datatestfile and return name of columns */
	private static String[] takeColumns(String path) throws FileNotFoundException {
		int j = 0;
		File file=new File(path);
		Scanner newReader = new Scanner(file);
		String[] columns = null;
		while (newReader.hasNextLine()) {
			String line = newReader.nextLine();
			if (j == 3) {
				String str = line.replaceAll("\\s", "");
				columns = str.split(",");
				// System.out.println(line);

				break;
			}
			j++;
		}
		newReader.close();
		return columns;

	}

}
