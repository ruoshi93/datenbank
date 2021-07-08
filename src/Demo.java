
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import datenbank.imdb.*;

public class Demo {

	static TableDemo an = new Aka_nameTable();
	static TableDemo at = new Aka_titleTable();
	static TableDemo ci = new Cast_infoTable();
	static TableDemo chn = new Char_nameTable();
	static TableDemo cct = new Comp_cast_typeTable();
	static TableDemo cn = new Company_nameTable();
	static TableDemo ct = new Company_typeTable();
	static TableDemo cc = new Complete_castTable();
	static TableDemo it = new Info_typeTable();
	static TableDemo k = new KeywordTable();
	static TableDemo kt = new Kind_typeTable();
	static TableDemo lt = new Link_typeTable();
	static TableDemo mc = new Movie_companiesTable();
	static TableDemo mii = new Movie_info_idxTable();
	static TableDemo mi = new Movie_infoTable();
	static TableDemo mk = new Movie_keywordTable();
	static TableDemo ml = new Movie_linkTable();
	static TableDemo n = new NameTable();
	static TableDemo pi = new Person_infoTable();
	static TableDemo rt = new Role_typeTable();
	static TableDemo t = new TitleTable();

	public static TableDemo get(String name) {
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

	public static void join(Result result, String s1, String s2) {
		String[] query1 = s1.split("\\.");
		String[] query2 = s2.split("\\.");
		TableDemo table1 = get(query1[0]);
		String attr1 = query1[1];
		TableDemo table2 = get(query2[0]);
		String attr2 = query2[1];

		HashMap<Integer, ArrayList<Integer>> resultData = result.getData();
		ArrayList<TableDemo> resultSchema = result.getSchema();

		if (resultData == null) {
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
							result.getData().put(pk1 + value2.getPrimaryKey(), list);
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
							result.getData().put(value1.getPrimaryKey() + value2.getPrimaryKey(), list);
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
							result.getData().put(value1.getPrimaryKey() + value2.getPrimaryKey(), list);
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
								result.getData().put(pk1 + pk2List.get(i), list);
							}
						}
					}
				}
			}
		} else {
			Result newResult = new Result();
			ArrayList<TableDemo> newResultSchema = newResult.getSchema();
			newResultSchema = (ArrayList<TableDemo>) resultSchema.clone();
			HashMap<Integer, ArrayList<Integer>> newResultData = newResult.getData();

			if (resultSchema.contains(table1)) {

				int index = resultSchema.indexOf(table1);
				newResultSchema.add(table2);
				Iterator<Map.Entry<Integer, ArrayList<Integer>>> it1 = result.getData().entrySet().iterator();

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
				resultSchema.add(table1);
				Iterator<Map.Entry<Integer, ArrayList<Integer>>> it2 = result.getData().entrySet().iterator();

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
			}

			result = newResult;
		}
	}

	public static void main(String[] args) {
		Result result = new Result();
		join(result,"mk.movie_keywords","k.id");
	}

}
