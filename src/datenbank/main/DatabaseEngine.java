package datenbank.main;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import datenbank.imdb.*;

public class DatabaseEngine {

	static Table an = new Aka_nameTable();
	static Table at = new Aka_titleTable();;
	static Table ci = new Cast_infoTable();;
	static Table chn = new Char_nameTable();
	static Table cct = new Comp_cast_typeTable();
	static Table cn = new Company_nameTable();
	static Table ct = new Company_typeTable();
	static Table cc = new Complete_castTable();
	static Table it = new Info_typeTable();
	static Table k = new KeywordTable();
	static Table kt = new Kind_typeTable();
	static Table lt = new Link_typeTable();
	static Table mc = new Movie_companiesTable();
	static Table mii = new Movie_info_idxTable();
	static Table mi = new Movie_infoTable();
	static Table mk = new Movie_keywordTable();
	static Table ml = new Movie_linkTable();
	static Table n = new NameTable();
	static Table pi = new Person_infoTable();
	static Table rt = new Role_typeTable();
	static Table t = new TitleTable();

	public static Table get(String name) {
		switch (name) {
		case "an":
			return an;
		case "at":
			return at;
		case "ci":
			return ci;
		case "chn":
			return chn;
		case "cct":
			return cct;
		case "cn":
			return cn;
		case "ct":
			return ct;
		case "cc":
			return cc;
		case "it":
			return it;
		case "k":
			return k;
		case "kt":
			return kt;
		case "lt":
			return lt;
		case "mc":
			return mc;
		case "mii":
			return mii;
		case "mi":
			return mi;
		case "mk":
			return mk;
		case "ml":
			return ml;
		case "n":
			return n;
		case "pi":
			return pi;
		case "rt":
			return rt;
		case "t":
			return t;
		default:
			return null;
		}
	}

	public static Result joinExample(Result result, String s1, String s2) {

		String[] query1 = s1.split("\\.");
		String[] query2 = s2.split("\\.");
		Table table1 = get(query1[0]);
		String attr1 = query1[1];
		Table table2 = get(query2[0]);
		String attr2 = query2[1];
		
		// Examine the tables and attributes
		if(table1==null) {
			System.out.println("Error: The table \""+query1[0]+"\" does not exist. ");
		}else if (table2==null) {
			System.out.println("Error: The table \""+query2[0]+"\" does not exist. ");
		}else if(table1.getRow().get(attr1)==null) {
			System.out.println("Error: The attribute \""+attr1+"\" does not exist in the table \""+query1[0]+"\". ");
		}else if(table2.getRow().get(attr2)==null) {
			System.out.println("Error: The attribute \""+attr2+"\" does not exist in the table \""+query2[0]+"\". ");
		}

		HashMap<Integer, ArrayList<Integer>> resultData = result.getData();
		ArrayList<Table> resultSchema = result.getSchema();

		if (resultData.isEmpty()) {
			resultSchema.add(table1);
			resultSchema.add(table2);
			if (attr1.equals("id")) {
				if (attr2.equals("id")) {
					Iterator<Map.Entry<Integer, Row>> it1 = table1.getExample().entrySet().iterator();
					while (it1.hasNext()) {
						HashMap.Entry<Integer, Row> entry1 = it1.next();
						Integer pk1 = entry1.getKey();
						Row value2 = table2.getExample().get(pk1);
						if (value2 != null) {
							ArrayList<Integer> list = new ArrayList<Integer>();
							list.add(pk1);
							list.add(value2.getPrimaryKey());
							resultData.put(pk1 + value2.getPrimaryKey(), list);
						}
					}
				} else {
					Iterator<Map.Entry<Integer, Row>> it2 = table2.getExample().entrySet().iterator();
					while (it2.hasNext()) {
						HashMap.Entry<Integer, Row> entry2 = it2.next();
						Row value2 = entry2.getValue();
						Row value1 = table1.getExample().get(value2.get(attr2));
						if (value1 != null) {
							ArrayList<Integer> list = new ArrayList<Integer>();
							list.add(value1.getPrimaryKey());
							list.add(value2.getPrimaryKey());
							resultData.put(value1.getPrimaryKey() + value2.getPrimaryKey(), list);
						}
					}
				}
			} else {
				if (attr2.equals("id")) {
					Iterator<Map.Entry<Integer, Row>> it1 = table1.getExample().entrySet().iterator();
					while (it1.hasNext()) {
						HashMap.Entry<Integer, Row> entry1 = it1.next();
						Integer pk1 = entry1.getKey();
						Row value1 = entry1.getValue();
						Row value2 = table2.getExample().get(value1.get(attr1));
						if (value2 != null) {
							ArrayList<Integer> list = new ArrayList<Integer>();
							list.add(value1.getPrimaryKey());
							list.add(value2.getPrimaryKey());
							resultData.put(value1.getPrimaryKey() + value2.getPrimaryKey(), list);
						}
					}
				} else {
					Iterator<Map.Entry<Integer, Row>> it1 = table1.getExample().entrySet().iterator();
					HashMap pk2HashMap = table2.getExamplePKMap(attr2);
					while (it1.hasNext()) {
						HashMap.Entry<Integer, Row> entry1 = it1.next();
						Integer pk1 = entry1.getKey();
						Row value1 = entry1.getValue();
						ArrayList<Integer> pk2List = (ArrayList<Integer>) pk2HashMap.get(value1.get(attr1));
						if (pk2List != null) {
							ArrayList<Integer> list = new ArrayList<Integer>();
							for (int i = 0; i < pk2List.size(); i++) {
								list.add(pk1);
								list.add(pk2List.get(i));
								// TODO Examine if the key is duplicate
								resultData.put(pk1 + pk2List.get(i), list);
							}
						}
					}
				}
			}

		} else {
			Result newResult = new Result();

			newResult.setSchema((ArrayList<Table>) resultSchema.clone());

			ArrayList<Table> newResultSchema = newResult.getSchema();

			HashMap<Integer, ArrayList<Integer>> newResultData = newResult.getData();

			if (resultSchema.contains(table1)) {

				int index = resultSchema.indexOf(table1);

				newResultSchema.add(table2);

				Iterator<Map.Entry<Integer, ArrayList<Integer>>> it1 = resultData.entrySet().iterator();

				if (attr1.equals("id")) {
					if (attr2.equals("id")) {
						while (it1.hasNext()) {
							HashMap.Entry<Integer, ArrayList<Integer>> entry1 = it1.next();
							Integer pk1 = entry1.getValue().get(index);
							Row value2 = table2.getExample().get(pk1);
							if (value2 != null) {
								ArrayList<Integer> newList = (ArrayList<Integer>) entry1.getValue().clone();
								newList.add(value2.getPrimaryKey());
								newResultData.put(entry1.getKey() + value2.getPrimaryKey(), newList);
							}
						}
					} else {
						HashMap pk2HashMap = table2.getExamplePKMap(attr2);
						while (it1.hasNext()) {
							HashMap.Entry<Integer, ArrayList<Integer>> entry1 = it1.next();
							Integer pk1 = entry1.getValue().get(index);
							ArrayList<Integer> pk2List = (ArrayList<Integer>) pk2HashMap.get(pk1);
							if (pk2List != null) {
								for (int i = 0; i < pk2List.size(); i++) {
									ArrayList<Integer> newList = (ArrayList<Integer>) entry1.getValue().clone();
									newList.add(pk2List.get(i));
									newResultData.put(entry1.getKey() + pk2List.get(i), newList);
								}
							}
						}
					}
				} else {
					if (attr2.equals("id")) {
						while (it1.hasNext()) {
							HashMap.Entry<Integer, ArrayList<Integer>> entry1 = it1.next();
							Integer pk1 = entry1.getValue().get(index);
							Row value1 = table1.getExample().get(pk1);
							Row value2 = table2.getExample().get(value1.get(attr1));
							if (value2 != null) {
								ArrayList<Integer> newList = (ArrayList<Integer>) entry1.getValue().clone();
								newList.add(value2.getPrimaryKey());
								newResultData.put(entry1.getKey() + value2.getPrimaryKey(), newList);
							}
						}
					} else {
						HashMap pk2HashMap = table2.getExamplePKMap(attr2);
						while (it1.hasNext()) {
							HashMap.Entry<Integer, ArrayList<Integer>> entry1 = it1.next();
							Integer pk1 = entry1.getValue().get(index);
							Row value1 = table1.getExample().get(pk1);
							ArrayList<Integer> pk2List = (ArrayList<Integer>) pk2HashMap.get(value1.get(attr1));
							if (pk2List != null) {
								for (int i = 0; i < pk2List.size(); i++) {
									ArrayList<Integer> newList = (ArrayList<Integer>) entry1.getValue().clone();
									newList.add(pk2List.get(i));
									newResultData.put(entry1.getKey() + pk2List.get(i), newList);
								}
							}
						}
					}
				}

			} else if (resultSchema.contains(table2)) {

				int index = resultSchema.indexOf(table2);

				newResultSchema.add(table1);

				Iterator<Map.Entry<Integer, ArrayList<Integer>>> it2 = resultData.entrySet().iterator();

				if (attr1.equals("id")) {
					if (attr2.equals("id")) {
						while (it2.hasNext()) {
							Map.Entry<Integer, ArrayList<Integer>> entry2 = it2.next();
							Integer pk2 = entry2.getValue().get(index);
							Row value1 = table1.getExample().get(pk2);
							if (value1 != null) {
								ArrayList<Integer> newList = (ArrayList<Integer>) entry2.getValue().clone();
								newList.add(value1.getPrimaryKey());
								newResultData.put(entry2.getKey() + value1.getPrimaryKey(), newList);
							}
						}
					} else {
						while (it2.hasNext()) {
							Map.Entry<Integer, ArrayList<Integer>> entry2 = it2.next();
							Integer pk2 = entry2.getValue().get(index);
							Row value2 = table2.getExample().get(pk2);
							Row value1 = table1.getExample().get(value2.get(attr2));
							if (value1 != null) {
								ArrayList<Integer> newList = (ArrayList<Integer>) entry2.getValue().clone();
								newList.add(value1.getPrimaryKey());
								newResultData.put(entry2.getKey() + value1.getPrimaryKey(), newList);
							}
						}
					}
				} else {
					HashMap pk1HashMap = table1.getExamplePKMap(attr1);
					if (attr2.equals("id")) {
						while (it2.hasNext()) {
							Map.Entry<Integer, ArrayList<Integer>> entry2 = it2.next();
							Integer pk2 = entry2.getValue().get(index);
							ArrayList<Integer> pk1List = (ArrayList<Integer>) pk1HashMap.get(pk2);
							if (pk1List != null) {
								for (int i = 0; i < pk1List.size(); i++) {
									ArrayList<Integer> newList = (ArrayList<Integer>) entry2.getValue().clone();
									newList.add(pk1List.get(i));
									newResultData.put(entry2.getKey() + pk1List.get(i), newList);
								}
							}
						}
					} else {
						while (it2.hasNext()) {
							Map.Entry<Integer, ArrayList<Integer>> entry2 = it2.next();
							Integer pk2 = entry2.getValue().get(index);
							Row value2 = table2.getExample().get(pk2);
							ArrayList<Integer> pk1List = (ArrayList<Integer>) pk1HashMap.get(value2.get(attr2));
							if (pk1List != null) {
								for (int i = 0; i < pk1List.size(); i++) {
									ArrayList<Integer> newList = (ArrayList<Integer>) entry2.getValue().clone();
									newList.add(pk1List.get(i));
									newResultData.put(entry2.getKey() + pk1List.get(i), newList);
								}
							}
						}
					}
				}

			} else {
				// TODO Exception
				System.out.println("Exception");
			}

			result = newResult;
		}

		return result;
	}

	public static Result join(Result result, String s1, String s2) {

		String[] query1 = s1.split("\\.");
		String[] query2 = s2.split("\\.");
		Table table1 = get(query1[0]);
		String attr1 = query1[1];
		Table table2 = get(query2[0]);
		String attr2 = query2[1];
		
		// Examine the tables and attributes
		if(table1==null) {
			System.out.println("Error: The table \""+query1[0]+"\" does not exist. ");
		}else if (table2==null) {
			System.out.println("Error: The table \""+query2[0]+"\" does not exist. ");
		}else if(table1.getRow().get(attr1)==null) {
			System.out.println("Error: The attribute \""+attr1+"\" does not exist in the table \""+query1[0]+"\". ");
		}else if(table2.getRow().get(attr2)==null) {
			System.out.println("Error: The attribute \""+attr2+"\" does not exist in the table \""+query2[0]+"\". ");
		}

		HashMap<Integer, ArrayList<Integer>> resultData = result.getData();
		ArrayList<Table> resultSchema = result.getSchema();

		if (resultData.isEmpty()) {
			resultSchema.add(table1);
			resultSchema.add(table2);
			if (attr1.equals("id")) {
				if (attr2.equals("id")) {
					Iterator<Map.Entry<Integer, Row>> it1 = table1.getData().entrySet().iterator();
					while (it1.hasNext()) {
						HashMap.Entry<Integer, Row> entry1 = it1.next();
						Integer pk1 = entry1.getKey();
						Row value2 = table2.getData().get(pk1);
						if (value2 != null) {
							ArrayList<Integer> list = new ArrayList<Integer>();
							list.add(pk1);
							list.add(value2.getPrimaryKey());
							resultData.put(pk1 + value2.getPrimaryKey(), list);
						}
					}
				} else {
					Iterator<Map.Entry<Integer, Row>> it2 = table2.getData().entrySet().iterator();
					while (it2.hasNext()) {
						HashMap.Entry<Integer, Row> entry2 = it2.next();
						Row value2 = entry2.getValue();
						Row value1 = table1.getData().get(value2.get(attr2));
						if (value1 != null) {
							ArrayList<Integer> list = new ArrayList<Integer>();
							list.add(value1.getPrimaryKey());
							list.add(value2.getPrimaryKey());
							resultData.put(value1.getPrimaryKey() + value2.getPrimaryKey(), list);
						}
					}
				}
			} else {
				if (attr2.equals("id")) {
					Iterator<Map.Entry<Integer, Row>> it1 = table1.getData().entrySet().iterator();
					while (it1.hasNext()) {
						HashMap.Entry<Integer, Row> entry1 = it1.next();
						Integer pk1 = entry1.getKey();
						Row value1 = entry1.getValue();
						Row value2 = table2.getData().get(value1.get(attr1));
						if (value2 != null) {
							ArrayList<Integer> list = new ArrayList<Integer>();
							list.add(value1.getPrimaryKey());
							list.add(value2.getPrimaryKey());
							resultData.put(value1.getPrimaryKey() + value2.getPrimaryKey(), list);
						}
					}
				} else {
					Iterator<Map.Entry<Integer, Row>> it1 = table1.getData().entrySet().iterator();
					HashMap pk2HashMap = table2.getPKMap(attr2);
					while (it1.hasNext()) {
						HashMap.Entry<Integer, Row> entry1 = it1.next();
						Integer pk1 = entry1.getKey();
						Row value1 = entry1.getValue();
						ArrayList<Integer> pk2List = (ArrayList<Integer>) pk2HashMap.get(value1.get(attr1));
						if (pk2List != null) {
							ArrayList<Integer> list = new ArrayList<Integer>();
							for (int i = 0; i < pk2List.size(); i++) {
								list.add(pk1);
								list.add(pk2List.get(i));
								// TODO Examine if the key is duplicate
								resultData.put(pk1 + pk2List.get(i), list);
							}
						}
					}
				}
			}

		} else {
			Result newResult = new Result();

			newResult.setSchema((ArrayList<Table>) resultSchema.clone());

			ArrayList<Table> newResultSchema = newResult.getSchema();

			HashMap<Integer, ArrayList<Integer>> newResultData = newResult.getData();

			if (resultSchema.contains(table1)) {

				int index = resultSchema.indexOf(table1);

				newResultSchema.add(table2);

				Iterator<Map.Entry<Integer, ArrayList<Integer>>> it1 = resultData.entrySet().iterator();

				if (attr1.equals("id")) {
					if (attr2.equals("id")) {
						while (it1.hasNext()) {
							HashMap.Entry<Integer, ArrayList<Integer>> entry1 = it1.next();
							Integer pk1 = entry1.getValue().get(index);
							Row value2 = table2.getData().get(pk1);
							if (value2 != null) {
								ArrayList<Integer> newList = (ArrayList<Integer>) entry1.getValue().clone();
								newList.add(value2.getPrimaryKey());
								newResultData.put(entry1.getKey() + value2.getPrimaryKey(), newList);
							}
						}
					} else {
						HashMap pk2HashMap = table2.getPKMap(attr2);
						while (it1.hasNext()) {
							HashMap.Entry<Integer, ArrayList<Integer>> entry1 = it1.next();
							Integer pk1 = entry1.getValue().get(index);
							ArrayList<Integer> pk2List = (ArrayList<Integer>) pk2HashMap.get(pk1);
							if (pk2List != null) {
								for (int i = 0; i < pk2List.size(); i++) {
									ArrayList<Integer> newList = (ArrayList<Integer>) entry1.getValue().clone();
									newList.add(pk2List.get(i));
									newResultData.put(entry1.getKey() + pk2List.get(i), newList);
								}
							}
						}
					}
				} else {
					if (attr2.equals("id")) {
						while (it1.hasNext()) {
							HashMap.Entry<Integer, ArrayList<Integer>> entry1 = it1.next();
							Integer pk1 = entry1.getValue().get(index);
							Row value1 = table1.getData().get(pk1);
							Row value2 = table2.getData().get(value1.get(attr1));
							if (value2 != null) {
								ArrayList<Integer> newList = (ArrayList<Integer>) entry1.getValue().clone();
								newList.add(value2.getPrimaryKey());
								newResultData.put(entry1.getKey() + value2.getPrimaryKey(), newList);
							}
						}
					} else {
						HashMap pk2HashMap = table2.getPKMap(attr2);
						while (it1.hasNext()) {
							HashMap.Entry<Integer, ArrayList<Integer>> entry1 = it1.next();
							Integer pk1 = entry1.getValue().get(index);
							Row value1 = table1.getData().get(pk1);
							ArrayList<Integer> pk2List = (ArrayList<Integer>) pk2HashMap.get(value1.get(attr1));
							if (pk2List != null) {
								for (int i = 0; i < pk2List.size(); i++) {
									ArrayList<Integer> newList = (ArrayList<Integer>) entry1.getValue().clone();
									newList.add(pk2List.get(i));
									newResultData.put(entry1.getKey() + pk2List.get(i), newList);
								}
							}
						}
					}
				}

			} else if (resultSchema.contains(table2)) {

				int index = resultSchema.indexOf(table2);

				newResultSchema.add(table1);

				Iterator<Map.Entry<Integer, ArrayList<Integer>>> it2 = resultData.entrySet().iterator();

				if (attr1.equals("id")) {
					if (attr2.equals("id")) {
						while (it2.hasNext()) {
							Map.Entry<Integer, ArrayList<Integer>> entry2 = it2.next();
							Integer pk2 = entry2.getValue().get(index);
							Row value1 = table1.getData().get(pk2);
							if (value1 != null) {
								ArrayList<Integer> newList = (ArrayList<Integer>) entry2.getValue().clone();
								newList.add(value1.getPrimaryKey());
								newResultData.put(entry2.getKey() + value1.getPrimaryKey(), newList);
							}
						}
					} else {
						while (it2.hasNext()) {
							Map.Entry<Integer, ArrayList<Integer>> entry2 = it2.next();
							Integer pk2 = entry2.getValue().get(index);
							Row value2 = table2.getData().get(pk2);
							Row value1 = table1.getData().get(value2.get(attr2));
							if (value1 != null) {
								ArrayList<Integer> newList = (ArrayList<Integer>) entry2.getValue().clone();
								newList.add(value1.getPrimaryKey());
								newResultData.put(entry2.getKey() + value1.getPrimaryKey(), newList);
							}
						}
					}
				} else {
					HashMap pk1HashMap = table1.getPKMap(attr1);
					if (attr2.equals("id")) {
						while (it2.hasNext()) {
							Map.Entry<Integer, ArrayList<Integer>> entry2 = it2.next();
							Integer pk2 = entry2.getValue().get(index);
							ArrayList<Integer> pk1List = (ArrayList<Integer>) pk1HashMap.get(pk2);
							if (pk1List != null) {
								for (int i = 0; i < pk1List.size(); i++) {
									ArrayList<Integer> newList = (ArrayList<Integer>) entry2.getValue().clone();
									newList.add(pk1List.get(i));
									newResultData.put(entry2.getKey() + pk1List.get(i), newList);
								}
							}
						}
					} else {
						while (it2.hasNext()) {
							Map.Entry<Integer, ArrayList<Integer>> entry2 = it2.next();
							Integer pk2 = entry2.getValue().get(index);
							Row value2 = table2.getData().get(pk2);
							ArrayList<Integer> pk1List = (ArrayList<Integer>) pk1HashMap.get(value2.get(attr2));
							if (pk1List != null) {
								for (int i = 0; i < pk1List.size(); i++) {
									ArrayList<Integer> newList = (ArrayList<Integer>) entry2.getValue().clone();
									newList.add(pk1List.get(i));
									newResultData.put(entry2.getKey() + pk1List.get(i), newList);
								}
							}
						}
					}
				}

			} else {
				// TODO Exception
				System.out.println("Exception");
			}

			result = newResult;
		}

		return result;
	}

	private static String printRuntime(long runT) {
		String s;

		if (runT < 1000) {
			s = runT + " ms. ";
		} else if (runT < 60000) {
			s = runT / 1000 + " secs " + runT % 1000 + " ms. ";
		} else {
			s = runT / 60000 + " min " + (runT % 60000) / 1000 + " secs " + runT % 60000 % 1000 + " ms. ";
		}

		return s;
	}

	private static void printRuntimeArray(Long[] runtime) {
		for (long r : runtime) {
			System.out.println(r + "ms  =  " + printRuntime(r));
		}
	}

	public static void dynamicJoinOrder(String query) {
		Result result = new Result();

		String[] queries = query.split("AND");

	}

	public static void main(String[] args) throws IOException {


		String[] queries = { "1", "2", "3", "4", "5" };
		Long[] runtime = new Long[queries.length];

		LineChart lc = new LineChart(queries);

		long startTime;
		long endTime;
		
		int i = 0;
		while(i < 10) {
		System.out.println("----------"+i+" iteration----------");
		
		Result result1 = new Result();
		
		startTime=System.currentTimeMillis();
		result1 = join(result1,"cn.id","mc.company_id");
		endTime=System.currentTimeMillis();
		runtime[0]=endTime-startTime;
		
		startTime=System.currentTimeMillis();
		result1 = join(result1,"mc.movie_id","t.id");
		endTime=System.currentTimeMillis();
		runtime[1]=endTime-startTime;
		
		startTime=System.currentTimeMillis();
		result1 = join(result1,"mk.movie_id","t.id");
		endTime=System.currentTimeMillis();
		runtime[2]=endTime-startTime;
		
		startTime=System.currentTimeMillis();
		result1 = join(result1,"mc.movie_id","mk.movie_id");
		endTime=System.currentTimeMillis();
		runtime[3]=endTime-startTime;
		
		startTime=System.currentTimeMillis();
		result1 = join(result1,"mk.keyword_id","k.id");
		endTime=System.currentTimeMillis();
		runtime[4]=endTime-startTime;
		
		printRuntimeArray(runtime);
		lc.addLine("12345",runtime);	
		
		Result result1Example = new Result();
		
		startTime=System.currentTimeMillis();
		result1Example = joinExample(result1Example,"cn.id","mc.company_id");
		endTime=System.currentTimeMillis();
		runtime[0]=endTime-startTime;
		
		
		startTime=System.currentTimeMillis();
		result1Example = joinExample(result1Example,"mc.movie_id","t.id");
		endTime=System.currentTimeMillis();
		runtime[1]=endTime-startTime;
		
		startTime=System.currentTimeMillis();
		result1Example = joinExample(result1Example,"mk.movie_id","t.id");
		endTime=System.currentTimeMillis();
		runtime[2]=endTime-startTime;
		
		startTime=System.currentTimeMillis();
		result1Example = joinExample(result1Example,"mc.movie_id","mk.movie_id");
		endTime=System.currentTimeMillis();
		runtime[3]=endTime-startTime;
		
		startTime=System.currentTimeMillis();
		result1Example = joinExample(result1Example,"mk.keyword_id","k.id");
		endTime=System.currentTimeMillis();
		runtime[4]=endTime-startTime;
		
		printRuntimeArray(runtime);
		lc.addLine("12345Example",runtime);
		
		i++;
		}
//		Result result2 = new Result();
//		
//		startTime=System.currentTimeMillis();
//		result2 = join(result2,"mc.movie_id","t.id");
//		endTime=System.currentTimeMillis();
//		runtime[1]=endTime-startTime;
//		
//		startTime=System.currentTimeMillis();
//		result2 = join(result2,"mk.movie_id","t.id");
//		endTime=System.currentTimeMillis();
//		runtime[2]=endTime-startTime;
//		
//		startTime=System.currentTimeMillis();
//		result2 = join(result2,"mc.movie_id","mk.movie_id");
//		endTime=System.currentTimeMillis();
//		runtime[3]=endTime-startTime;
//		
//		startTime=System.currentTimeMillis();
//		result2 = join(result2,"mk.keyword_id","k.id");
//		endTime=System.currentTimeMillis();
//		runtime[4]=endTime-startTime;
//		
//		startTime=System.currentTimeMillis();
//		result2 = join(result2,"cn.id","mc.company_id");
//		endTime=System.currentTimeMillis();
//		runtime[0]=endTime-startTime;
//		
//		printRuntimeArray(runtime);
//		lc.addLine("23451",runtime);

//		///////////////////////////////////////////////Test for ExampleJoin
//		Result resultExample = new Result();
//		
//		startTime=System.currentTimeMillis();
//		resultExample = joinExample(resultExample,"mc.movie_id","t.id");
//		endTime=System.currentTimeMillis();
//		runtime[1]=endTime-startTime;
//		
//		startTime=System.currentTimeMillis();
//		resultExample = joinExample(resultExample,"mk.movie_id","t.id");
//		endTime=System.currentTimeMillis();
//		runtime[2]=endTime-startTime;
//		
//		startTime=System.currentTimeMillis();
//		resultExample = joinExample(resultExample,"mc.movie_id","mk.movie_id");
//		endTime=System.currentTimeMillis();
//		runtime[3]=endTime-startTime;
//		
//		startTime=System.currentTimeMillis();
//		resultExample = joinExample(resultExample,"mk.keyword_id","k.id");
//		endTime=System.currentTimeMillis();
//		runtime[4]=endTime-startTime;
//		
//		startTime=System.currentTimeMillis();
//		resultExample = joinExample(resultExample,"cn.id","mc.company_id");
//		endTime=System.currentTimeMillis();
//		runtime[0]=endTime-startTime;
//		
//		printRuntimeArray(runtime);
//		lc.addLine("23451Example",runtime);
		
		lc.drawLineChart();

	}

}
