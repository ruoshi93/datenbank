package datenbank.main;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import datenbank.imdb.*;
import datenbank.job.*;

public class DatabaseEngine {

//	static Table an = new Aka_nameTable();
//	static Table at = new Aka_titleTable();;
//	static Table ci = new Cast_infoTable();;
//	static Table chn = new Char_nameTable();
//	static Table cct = new Comp_cast_typeTable();
	static Table cn = new Company_nameTable();
//	static Table ct = new Company_typeTable();
//	static Table cc = new Complete_castTable();
//	static Table it = new Info_typeTable();
	static Table k = new KeywordTable();
//	static Table kt = new Kind_typeTable();
//	static Table lt = new Link_typeTable();
	static Table mc = new Movie_companiesTable();
//	static Table mi_idx = new Movie_info_idxTable();
//	static Table mi = new Movie_infoTable();
	static Table mk = new Movie_keywordTable();
//	static Table ml = new Movie_linkTable();
//	static Table n = new NameTable();
//	static Table pi = new Person_infoTable();
//	static Table rt = new Role_typeTable();
	static Table t = new TitleTable();

	public static Table get(String name) {
		switch (name) {
//		case "an":
//			return an;
//		case "at":
//			return at;
//		case "ci":
//			return ci;
//		case "chn":
//			return chn;
//		case "cct":
//			return cct;
		case "cn":
			return cn;
//		case "ct":
//			return ct;
//		case "cc":
//			return cc;
//		case "it":
//			return it;
		case "k":
			return k;
//		case "kt":
//			return kt;
//		case "lt":
//			return lt;
		case "mc":
			return mc;
//		case "mi_idx":
//			return mi_idx;
//		case "mi":
//			return mi;
		case "mk":
			return mk;
//		case "ml":
//			return ml;
//		case "n":
//			return n;
//		case "pi":
//			return pi;
//		case "rt":
//			return rt;
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
		if (table1 == null) {
			System.out.println("Error: The table \"" + query1[0] + "\" does not exist. ");
		} else if (table2 == null) {
			System.out.println("Error: The table \"" + query2[0] + "\" does not exist. ");
		} else if (!table1.getTitle().contains(attr1)) {
			System.out.println(
					"Error: The attribute \"" + attr1 + "\" does not exist in the table \"" + query1[0] + "\". ");
		} else if (!table2.getTitle().contains(attr2)) {
			System.out.println(
					"Error: The attribute \"" + attr2 + "\" does not exist in the table \"" + query2[0] + "\". ");
		}

		HashMap<Integer, ArrayList<Integer>> resultData = result.getData();
		ArrayList<Table> resultSchema = result.getSchema();

		if (resultData.isEmpty()) {
			resultSchema.add(table1);
			resultSchema.add(table2);
			Integer pk = 1;
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
							resultData.put(pk, list);
							pk++;
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
							resultData.put(pk, list);
							pk++;
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
							resultData.put(pk, list);
							pk++;
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
							for (int i = 0; i < pk2List.size(); i++) {
								ArrayList<Integer> list = new ArrayList<Integer>();
								list.add(pk1);
								list.add(pk2List.get(i));
								// TODO Examine if the key is duplicate
								resultData.put(pk, list);
								pk++;
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

			if (result.storageContains(table1) && !result.storageContains(table2) && resultSchema.contains(table2)) {

				Result storageResult = result.getStorageResult(table1);
				int index2 = resultSchema.indexOf(table2);
				newResultSchema.addAll((Collection<? extends Table>) storageResult.getSchema().clone());

				Integer pk = 1;
				if (attr1.equals("id")) {

					HashMap<Integer, Integer> pkMap1 = storageResult.getIDPKMap(table1);
					Iterator<Map.Entry<Integer, ArrayList<Integer>>> it2 = resultData.entrySet().iterator();

					if (attr2.equals("id")) {
						while (it2.hasNext()) {
							HashMap.Entry<Integer, ArrayList<Integer>> entry2 = it2.next();
							Integer pk1 = pkMap1.get(entry2.getValue().get(index2));
							if (pk1 != null) {
								ArrayList<Integer> newList = (ArrayList<Integer>) entry2.getValue().clone();
								newList.addAll(
										(Collection<? extends Integer>) storageResult.getData().get(pk1).clone());
								newResultData.put(pk, newList);
								pk++;
							}
						}
					} else {
						while (it2.hasNext()) {
							HashMap.Entry<Integer, ArrayList<Integer>> entry2 = it2.next();
							Integer pk1 = pkMap1.get(table2.getData().get(entry2.getValue().get(index2)).get(attr2));
							if (pk1 != null) {
								ArrayList<Integer> newList = (ArrayList<Integer>) entry2.getValue().clone();
								newList.addAll(
										(Collection<? extends Integer>) storageResult.getData().get(pk1).clone());
								newResultData.put(pk, newList);
								pk++;
							}
						}
					}

				} else {
					Iterator<Map.Entry<Integer, ArrayList<Integer>>> it2 = resultData.entrySet().iterator();
					HashMap attrPKMap1 = storageResult.getAttrPKMap(table1, attr1);
					if (attr2.equals("id")) {
						while (it2.hasNext()) {
							HashMap.Entry<Integer, ArrayList<Integer>> entry2 = it2.next();
							Integer id2 = entry2.getValue().get(index2);
							ArrayList<Integer> pkList1 = (ArrayList<Integer>) attrPKMap1.get(id2);
							if (pkList1 != null) {
								for (Integer pk1 : pkList1) {
									ArrayList<Integer> newList = (ArrayList<Integer>) entry2.getValue().clone();
									newList.addAll(storageResult.getData().get(pk1));
									newResultData.put(pk, newList);
									pk++;
								}
							}
						}
					} else {
						while (it2.hasNext()) {
							HashMap.Entry<Integer, ArrayList<Integer>> entry2 = it2.next();
							Integer id2 = entry2.getValue().get(index2);
							ArrayList<Integer> pkList1 = (ArrayList<Integer>) attrPKMap1
									.get(table2.getData().get(id2).get(attr2));
							if (pkList1 != null) {
								for (Integer pk1 : pkList1) {
									ArrayList<Integer> newList = (ArrayList<Integer>) entry2.getValue().clone();
									newList.addAll(storageResult.getData().get(pk1));
									newResultData.put(pk, newList);
									pk++;
								}
							}
						}
					}
				}

				newResult.setStorage((HashMap<ArrayList<Table>, Result>) result.getStorage().clone());
				newResult.removeStorageTable(table1);
				result = newResult;

			} else if (result.storageContains(table2) && !result.storageContains(table1)
					&& resultSchema.contains(table1)) {

				Result storageResult = result.getStorageResult(table2);
				newResultSchema.addAll((Collection<? extends Table>) storageResult.getSchema().clone());
				Integer pk = 1;
				if (attr1.equals("id")) {

					Iterator<Map.Entry<Integer, ArrayList<Integer>>> it1 = resultData.entrySet().iterator();
					int index1 = resultSchema.indexOf(table1);

					if (attr2.equals("id")) {
						HashMap<Integer, Integer> pkMap2 = storageResult.getIDPKMap(table2);
						while (it1.hasNext()) {
							HashMap.Entry<Integer, ArrayList<Integer>> entry1 = it1.next();
							Integer pk2 = pkMap2.get(entry1.getValue().get(index1));
							if (pk2 != null) {
								ArrayList<Integer> newList = (ArrayList<Integer>) entry1.getValue().clone();
								newList.addAll(
										(Collection<? extends Integer>) storageResult.getData().get(pk2).clone());
								newResultData.put(pk, newList);
								pk++;
							}
						}
					} else {
						HashMap attrPKMap2 = storageResult.getAttrPKMap(table2, attr2);
						while (it1.hasNext()) {
							HashMap.Entry<Integer, ArrayList<Integer>> entry1 = it1.next();
							Integer id1 = entry1.getValue().get(index1);
							ArrayList<Integer> pkList2 = (ArrayList<Integer>) attrPKMap2.get(id1);
							if (pkList2 != null) {
								for (Integer pk2 : pkList2) {
									ArrayList<Integer> newList = (ArrayList<Integer>) entry1.getValue().clone();
									newList.addAll(
											(Collection<? extends Integer>) storageResult.getData().get(pk2).clone());
									newResultData.put(pk, newList);
									pk++;
								}
							}
						}
					}

				} else {
					Iterator<Map.Entry<Integer, ArrayList<Integer>>> it2 = storageResult.getData().entrySet()
							.iterator();
					int index2 = storageResult.getSchema().indexOf(table2);
					HashMap attrPKMap1 = result.getAttrPKMap(table1, attr1);

					if (attr2.equals("id")) {
						while (it2.hasNext()) {
							Map.Entry<Integer, ArrayList<Integer>> entry2 = it2.next();
							Integer id2 = entry2.getValue().get(index2);
							ArrayList<Integer> pkList1 = (ArrayList<Integer>) attrPKMap1.get(id2);
							if (pkList1 != null) {
								for (Integer pk1 : pkList1) {
									ArrayList<Integer> newList = (ArrayList<Integer>) resultData.get(pk1).clone();
									newList.addAll((Collection<? extends Integer>) entry2.getValue().clone());
									newResultData.put(pk, newList);
									pk++;
								}
							}
						}
					} else {
						while (it2.hasNext()) {
							Map.Entry<Integer, ArrayList<Integer>> entry2 = it2.next();
							Integer id2 = entry2.getValue().get(index2);
							ArrayList<Integer> pkList1 = (ArrayList<Integer>) attrPKMap1
									.get(table2.getData().get(id2).get(attr2));
							if (pkList1 != null) {
								for (Integer pk1 : pkList1) {
									ArrayList<Integer> newList = (ArrayList<Integer>) resultData.get(pk1).clone();
									newList.addAll((Collection<? extends Integer>) entry2.getValue().clone());
									newResultData.put(pk, newList);
									pk++;
								}
							}
						}
					}
				}

				newResult.setStorage((HashMap<ArrayList<Table>, Result>) result.getStorage().clone());
				newResult.removeStorageTable(table2);
				result = newResult;

			} else if (result.storageContains(table1) && result.storageContains(table2)) {

				if (result.storageIndex(table1) == result.storageIndex(table2)) {
					for (Map.Entry<ArrayList<Table>, Result> entry : result.getStorage().entrySet()) {
						ArrayList<Table> key = entry.getKey();
						if (key.contains(table1)) {
							Result newStorageResult = join(entry.getValue(), s1, s2);
							result.getStorage().remove(key);
							result.getStorage().put(key, newStorageResult);
							break;
						}
					}
				} else {
					ArrayList<Table> key1 = null;
					ArrayList<Table> key2 = null;
					Result storageResult1 = null;
					Result storageResult2 = null;
					for (Map.Entry<ArrayList<Table>, Result> entry : result.getStorage().entrySet()) {
						if (entry.getKey().contains(table1)) {
							key1 = entry.getKey();
							storageResult1 = entry.getValue();
						}
						if (entry.getKey().contains(table2)) {
							key2 = entry.getKey();
							storageResult2 = entry.getValue();
						}
					}
					storageResult1.getStorage().put(key2, storageResult2);
					result.getStorage().remove(key1);
					result.getStorage().remove(key2);
					result.getStorage().put((ArrayList<Table>) storageResult1.getSchema().clone(),
							join(storageResult1, s1, s2));

				}
			} else if (resultSchema.contains(table1) && resultSchema.contains(table2)) {
				Iterator<Map.Entry<Integer, ArrayList<Integer>>> it = resultData.entrySet().iterator();
				int index1 = resultSchema.indexOf(table1);
				int index2 = resultSchema.indexOf(table2);
				Integer pk = 1;
				if (attr1.equals("id")) {
					if (attr2.equals("id")) {
						while (it.hasNext()) {
							Map.Entry<Integer, ArrayList<Integer>> entry = it.next();
							ArrayList<Integer> value = entry.getValue();
							if (value.get(index1).equals(value.get(index2))) {
								newResultData.put(pk, entry.getValue());
								pk++;
							}
						}
					} else {
						while (it.hasNext()) {
							Map.Entry<Integer, ArrayList<Integer>> entry = it.next();
							ArrayList<Integer> value = entry.getValue();
							Integer id2 = value.get(index2);
							if (value.get(index1).equals(table2.getData().get(id2).get(attr2))) {
								newResultData.put(pk, entry.getValue());
								pk++;
							}
						}
					}
				} else {
					if (attr2.equals("id")) {
						while (it.hasNext()) {
							Map.Entry<Integer, ArrayList<Integer>> entry = it.next();
							ArrayList<Integer> value = entry.getValue();
							Integer id1 = value.get(index1);
							if (table1.getData().get(id1).get(attr1).equals(value.get(index2))) {
								newResultData.put(pk, entry.getValue());
								pk++;
							}
						}
					} else {
						while (it.hasNext()) {
							Map.Entry<Integer, ArrayList<Integer>> entry = it.next();
							ArrayList<Integer> value = entry.getValue();
							Integer id1 = value.get(index1);
							Integer id2 = value.get(index2);
							if (table1.getData().get(id1).get(attr1).equals(table2.getData().get(id2).get(attr2))) {
								newResultData.put(pk, entry.getValue());
								pk++;
							}
						}
					}
				}

				newResult.setStorage((HashMap<ArrayList<Table>, Result>) result.getStorage().clone());
				result = newResult;

			} else if (resultSchema.contains(table1) && !resultSchema.contains(table2)) {

				int index = resultSchema.indexOf(table1);

				newResultSchema.add(table2);

				Iterator<Map.Entry<Integer, ArrayList<Integer>>> it1 = resultData.entrySet().iterator();
				Integer pk = 1;

				if (attr1.equals("id")) {
					if (attr2.equals("id")) {
						while (it1.hasNext()) {
							HashMap.Entry<Integer, ArrayList<Integer>> entry1 = it1.next();
							Integer pk1 = entry1.getValue().get(index);
							Row value2 = table2.getExample().get(pk1);
							if (value2 != null) {
								ArrayList<Integer> newList = (ArrayList<Integer>) entry1.getValue().clone();
								newList.add(value2.getPrimaryKey());
								newResultData.put(pk, newList);
								pk++;
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
									newResultData.put(pk, newList);
									pk++;
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
								newResultData.put(pk, newList);
								pk++;
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
									newResultData.put(pk, newList);
									pk++;
								}
							}
						}
					}
				}
				newResult.setStorage((HashMap<ArrayList<Table>, Result>) result.getStorage().clone());
				result = newResult;
			} else if (resultSchema.contains(table2) && !resultSchema.contains(table1)) {

				int index = resultSchema.indexOf(table2);

				newResultSchema.add(table1);

				Iterator<Map.Entry<Integer, ArrayList<Integer>>> it2 = resultData.entrySet().iterator();
				Integer pk = 1;

				if (attr1.equals("id")) {
					if (attr2.equals("id")) {
						while (it2.hasNext()) {
							Map.Entry<Integer, ArrayList<Integer>> entry2 = it2.next();
							Integer pk2 = entry2.getValue().get(index);
							Row value1 = table1.getExample().get(pk2);
							if (value1 != null) {
								ArrayList<Integer> newList = (ArrayList<Integer>) entry2.getValue().clone();
								newList.add(value1.getPrimaryKey());
								newResultData.put(pk, newList);
								pk++;
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
								newResultData.put(pk, newList);
								pk++;
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
									newResultData.put(pk, newList);
									pk++;
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
									newResultData.put(pk, newList);
									pk++;
								}
							}
						}
					}
				}
				newResult.setStorage((HashMap<ArrayList<Table>, Result>) result.getStorage().clone());
				result = newResult;
			} else {
				if (result.getStorage().size() == 0) {
					ArrayList<Table> l = new ArrayList<Table>();
					l.add(table1);
					l.add(table2);
					result.getStorage().put(l, joinExample(new Result(), s1, s2));
				} else {
					for (Map.Entry<ArrayList<Table>, Result> entry : result.getStorage().entrySet()) {
						if (entry.getKey().contains(table1)) {
							Result resultStorage = joinExample(entry.getValue(), s1, s2);
							result.getStorage().remove(entry.getKey());
							entry.getKey().add(table2);
							result.getStorage().put(entry.getKey(), resultStorage);
						}
						if (entry.getKey().contains(table2)) {
							Result resultStorage = joinExample(entry.getValue(), s1, s2);
							result.getStorage().remove(entry.getKey());
							entry.getKey().add(table1);
							result.getStorage().put(entry.getKey(), resultStorage);
						} else {
							ArrayList<Table> l = new ArrayList<Table>();
							l.add(table1);
							l.add(table2);
							result.getStorage().put(l, joinExample(new Result(), s1, s2));
						}
					}
				}
			}
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
		if (table1 == null) {
			System.out.println("Error: The table \"" + query1[0] + "\" does not exist. ");
		} else if (table2 == null) {
			System.out.println("Error: The table \"" + query2[0] + "\" does not exist. ");
		} else if (!table1.getTitle().contains(attr1)) {
			System.out.println(
					"Error: The attribute \"" + attr1 + "\" does not exist in the table \"" + query1[0] + "\". ");
		} else if (!table2.getTitle().contains(attr2)) {
			System.out.println(
					"Error: The attribute \"" + attr2 + "\" does not exist in the table \"" + query2[0] + "\". ");
		}

		HashMap<Integer, ArrayList<Integer>> resultData = result.getData();
		ArrayList<Table> resultSchema = result.getSchema();

		if (resultData.isEmpty()) {
			resultSchema.add(table1);
			resultSchema.add(table2);
			Integer pk = 1;
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
							resultData.put(pk, list);
							pk++;
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
							resultData.put(pk, list);
							pk++;
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
							resultData.put(pk, list);
							pk++;
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
							for (int i = 0; i < pk2List.size(); i++) {
								ArrayList<Integer> list = new ArrayList<Integer>();
								list.add(pk1);
								list.add(pk2List.get(i));
								resultData.put(pk, list);
								pk++;
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

			if (result.storageContains(table1) && !result.storageContains(table2) && resultSchema.contains(table2)) {

				Result storageResult = result.getStorageResult(table1);
				int index2 = resultSchema.indexOf(table2);
				newResultSchema.addAll((Collection<? extends Table>) storageResult.getSchema().clone());
				Integer pk = 1;

				if (attr1.equals("id")) {

					HashMap<Integer, Integer> pkMap1 = storageResult.getIDPKMap(table1);
					Iterator<Map.Entry<Integer, ArrayList<Integer>>> it2 = resultData.entrySet().iterator();

					if (attr2.equals("id")) {
						while (it2.hasNext()) {
							HashMap.Entry<Integer, ArrayList<Integer>> entry2 = it2.next();
							Integer pk1 = pkMap1.get(entry2.getValue().get(index2));
							if (pk1 != null) {
								ArrayList<Integer> newList = (ArrayList<Integer>) entry2.getValue().clone();
								newList.addAll(
										(Collection<? extends Integer>) storageResult.getData().get(pk1).clone());
								newResultData.put(pk, newList);
								pk++;
							}
						}
					} else {
						while (it2.hasNext()) {
							HashMap.Entry<Integer, ArrayList<Integer>> entry2 = it2.next();
							Integer pk1 = pkMap1.get(table2.getData().get(entry2.getValue().get(index2)).get(attr2));
							if (pk1 != null) {
								ArrayList<Integer> newList = (ArrayList<Integer>) entry2.getValue().clone();
								newList.addAll(
										(Collection<? extends Integer>) storageResult.getData().get(pk1).clone());
								newResultData.put(pk, newList);
								pk++;
							}
						}
					}

				} else {
					Iterator<Map.Entry<Integer, ArrayList<Integer>>> it2 = resultData.entrySet().iterator();
					HashMap attrPKMap1 = storageResult.getAttrPKMap(table1, attr1);
					if (attr2.equals("id")) {
						while (it2.hasNext()) {
							HashMap.Entry<Integer, ArrayList<Integer>> entry2 = it2.next();
							Integer id2 = entry2.getValue().get(index2);
							ArrayList<Integer> pkList1 = (ArrayList<Integer>) attrPKMap1.get(id2);
							if (pkList1 != null) {
								for (Integer pk1 : pkList1) {
									ArrayList<Integer> newList = (ArrayList<Integer>) entry2.getValue().clone();
									newList.addAll(storageResult.getData().get(pk1));
									newResultData.put(pk, newList);
									pk++;
								}
							}
						}
					} else {
						while (it2.hasNext()) {
							HashMap.Entry<Integer, ArrayList<Integer>> entry2 = it2.next();
							Integer id2 = entry2.getValue().get(index2);
							ArrayList<Integer> pkList1 = (ArrayList<Integer>) attrPKMap1
									.get(table2.getData().get(id2).get(attr2));
							if (pkList1 != null) {
								for (Integer pk1 : pkList1) {
									ArrayList<Integer> newList = (ArrayList<Integer>) entry2.getValue().clone();
									newList.addAll(storageResult.getData().get(pk1));
									newResultData.put(pk, newList);
									pk++;
								}
							}
						}
					}
				}

				newResult.setStorage((HashMap<ArrayList<Table>, Result>) result.getStorage().clone());
				newResult.removeStorageTable(table1);
				result = newResult;

			} else if (result.storageContains(table2) && !result.storageContains(table1)
					&& resultSchema.contains(table1)) {

				Result storageResult = result.getStorageResult(table2);
				newResultSchema.addAll((Collection<? extends Table>) storageResult.getSchema().clone());
				Integer pk = 1;

				if (attr1.equals("id")) {

					Iterator<Map.Entry<Integer, ArrayList<Integer>>> it1 = resultData.entrySet().iterator();
					int index1 = resultSchema.indexOf(table1);

					if (attr2.equals("id")) {
						HashMap<Integer, Integer> pkMap2 = storageResult.getIDPKMap(table2);
						while (it1.hasNext()) {
							HashMap.Entry<Integer, ArrayList<Integer>> entry1 = it1.next();
							Integer pk2 = pkMap2.get(entry1.getValue().get(index1));
							if (pk2 != null) {
								ArrayList<Integer> newList = (ArrayList<Integer>) entry1.getValue().clone();
								newList.addAll(
										(Collection<? extends Integer>) storageResult.getData().get(pk2).clone());
								newResultData.put(pk, newList);
								pk++;
							}
						}
					} else {
						HashMap attrPKMap2 = storageResult.getAttrPKMap(table2, attr2);
						while (it1.hasNext()) {
							HashMap.Entry<Integer, ArrayList<Integer>> entry1 = it1.next();
							Integer id1 = entry1.getValue().get(index1);
							ArrayList<Integer> pkList2 = (ArrayList<Integer>) attrPKMap2.get(id1);
							if (pkList2 != null) {
								for (Integer pk2 : pkList2) {
									ArrayList<Integer> newList = (ArrayList<Integer>) entry1.getValue().clone();
									newList.addAll(
											(Collection<? extends Integer>) storageResult.getData().get(pk2).clone());
									newResultData.put(pk, newList);
									pk++;
								}
							}
						}
					}

				} else {
					Iterator<Map.Entry<Integer, ArrayList<Integer>>> it2 = storageResult.getData().entrySet()
							.iterator();
					int index2 = storageResult.getSchema().indexOf(table2);
					HashMap attrPKMap1 = result.getAttrPKMap(table1, attr1);

					if (attr2.equals("id")) {
						while (it2.hasNext()) {
							Map.Entry<Integer, ArrayList<Integer>> entry2 = it2.next();
							Integer id2 = entry2.getValue().get(index2);
							ArrayList<Integer> pkList1 = (ArrayList<Integer>) attrPKMap1.get(id2);
							if (pkList1 != null) {
								for (Integer pk1 : pkList1) {
									ArrayList<Integer> newList = (ArrayList<Integer>) resultData.get(pk1).clone();
									newList.addAll((Collection<? extends Integer>) entry2.getValue().clone());
									newResultData.put(pk, newList);
									pk++;
								}
							}
						}
					} else {
						while (it2.hasNext()) {
							Map.Entry<Integer, ArrayList<Integer>> entry2 = it2.next();
							Integer id2 = entry2.getValue().get(index2);
							ArrayList<Integer> pkList1 = (ArrayList<Integer>) attrPKMap1
									.get(table2.getData().get(id2).get(attr2));
							if (pkList1 != null) {
								for (Integer pk1 : pkList1) {
									ArrayList<Integer> newList = (ArrayList<Integer>) resultData.get(pk1).clone();
									newList.addAll((Collection<? extends Integer>) entry2.getValue().clone());
									newResultData.put(pk, newList);
									pk++;
								}
							}
						}
					}
				}

				newResult.setStorage((HashMap<ArrayList<Table>, Result>) result.getStorage().clone());
				newResult.removeStorageTable(table2);
				result = newResult;

			} else if (result.storageContains(table1) && result.storageContains(table2)) {

				if (result.storageIndex(table1) == result.storageIndex(table2)) {
					for (Map.Entry<ArrayList<Table>, Result> entry : result.getStorage().entrySet()) {
						ArrayList<Table> key = entry.getKey();
						if (key.contains(table1)) {
							Result newStorageResult = join(entry.getValue(), s1, s2);
							result.getStorage().remove(key);
							result.getStorage().put(key, newStorageResult);
							break;
						}
					}
				} else {
					ArrayList<Table> key1 = null;
					ArrayList<Table> key2 = null;
					Result storageResult1 = null;
					Result storageResult2 = null;
					for (Map.Entry<ArrayList<Table>, Result> entry : result.getStorage().entrySet()) {
						if (entry.getKey().contains(table1)) {
							key1 = entry.getKey();
							storageResult1 = entry.getValue();
						}
						if (entry.getKey().contains(table2)) {
							key2 = entry.getKey();
							storageResult2 = entry.getValue();
						}
					}
					storageResult1.getStorage().put(key2, storageResult2);
					result.getStorage().remove(key1);
					result.getStorage().remove(key2);
					result.getStorage().put((ArrayList<Table>) storageResult1.getSchema().clone(),
							join(storageResult1, s1, s2));

				}
			} else if (resultSchema.contains(table1) && resultSchema.contains(table2)) {

				Iterator<Map.Entry<Integer, ArrayList<Integer>>> it = resultData.entrySet().iterator();
				int index1 = resultSchema.indexOf(table1);
				int index2 = resultSchema.indexOf(table2);
				Integer pk = 1;

				if (attr1.equals("id")) {
					if (attr2.equals("id")) {
						while (it.hasNext()) {
							Map.Entry<Integer, ArrayList<Integer>> entry = it.next();
							ArrayList<Integer> value = entry.getValue();
							if (value.get(index1).equals(value.get(index2))) {
								newResultData.put(pk, entry.getValue());
								pk++;
							}
						}
					} else {
						while (it.hasNext()) {
							Map.Entry<Integer, ArrayList<Integer>> entry = it.next();
							ArrayList<Integer> value = entry.getValue();
							Integer id2 = value.get(index2);
							if (value.get(index1).equals(table2.getData().get(id2).get(attr2))) {
								newResultData.put(pk, entry.getValue());
								pk++;
							}
						}
					}
				} else {
					if (attr2.equals("id")) {
						while (it.hasNext()) {
							Map.Entry<Integer, ArrayList<Integer>> entry = it.next();
							ArrayList<Integer> value = entry.getValue();
							Integer id1 = value.get(index1);
							if (table1.getData().get(id1).get(attr1).equals(value.get(index2))) {
								newResultData.put(pk, entry.getValue());
								pk++;
							}
						}
					} else {
						while (it.hasNext()) {
							Map.Entry<Integer, ArrayList<Integer>> entry = it.next();
							ArrayList<Integer> value = entry.getValue();
							Integer id1 = value.get(index1);
							Integer id2 = value.get(index2);
							if (table1.getData().get(id1).get(attr1).equals(table2.getData().get(id2).get(attr2))) {
								newResultData.put(pk, entry.getValue());
								pk++;
							}
						}
					}
				}

				newResult.setStorage((HashMap<ArrayList<Table>, Result>) result.getStorage().clone());
				result = newResult;

			} else if (resultSchema.contains(table1) && !resultSchema.contains(table2)) {

				int index = resultSchema.indexOf(table1);

				newResultSchema.add(table2);

				Iterator<Map.Entry<Integer, ArrayList<Integer>>> it1 = resultData.entrySet().iterator();
				Integer pk = 1;

				if (attr1.equals("id")) {
					if (attr2.equals("id")) {
						while (it1.hasNext()) {
							HashMap.Entry<Integer, ArrayList<Integer>> entry1 = it1.next();
							Integer pk1 = entry1.getValue().get(index);
							Row value2 = table2.getData().get(pk1);
							if (value2 != null) {
								ArrayList<Integer> newList = (ArrayList<Integer>) entry1.getValue().clone();
								newList.add(value2.getPrimaryKey());
								newResultData.put(pk, newList);
								pk++;
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
									newResultData.put(pk, newList);
									pk++;
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
								newResultData.put(pk, newList);
								pk++;
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
									newResultData.put(pk, newList);
									pk++;
								}
							}
						}
					}
				}
				newResult.setStorage((HashMap<ArrayList<Table>, Result>) result.getStorage().clone());
				result = newResult;
			} else if (resultSchema.contains(table2) && !resultSchema.contains(table1)) {

				int index = resultSchema.indexOf(table2);

				newResultSchema.add(table1);

				Iterator<Map.Entry<Integer, ArrayList<Integer>>> it2 = resultData.entrySet().iterator();
				Integer pk = 1;

				if (attr1.equals("id")) {
					if (attr2.equals("id")) {
						while (it2.hasNext()) {
							Map.Entry<Integer, ArrayList<Integer>> entry2 = it2.next();
							Integer pk2 = entry2.getValue().get(index);
							Row value1 = table1.getData().get(pk2);
							if (value1 != null) {
								ArrayList<Integer> newList = (ArrayList<Integer>) entry2.getValue().clone();
								newList.add(value1.getPrimaryKey());
								newResultData.put(pk, newList);
								pk++;
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
								newResultData.put(pk, newList);
								pk++;
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
									newResultData.put(pk, newList);
									pk++;
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
									newResultData.put(pk, newList);
									pk++;
								}
							}
						}
					}
				}
				newResult.setStorage((HashMap<ArrayList<Table>, Result>) result.getStorage().clone());
				result = newResult;
			} else {
				if (result.getStorage().size() == 0) {
					ArrayList<Table> l = new ArrayList<Table>();
					l.add(table1);
					l.add(table2);
					result.getStorage().put(l, join(new Result(), s1, s2));
				} else {
					for (Map.Entry<ArrayList<Table>, Result> entry : result.getStorage().entrySet()) {
						if (entry.getKey().contains(table1)) {
							Result resultStorage = join(entry.getValue(), s1, s2);
							result.getStorage().remove(entry.getKey());
							entry.getKey().add(table2);
							result.getStorage().put(entry.getKey(), resultStorage);
						}
						if (entry.getKey().contains(table2)) {
							Result resultStorage = join(entry.getValue(), s1, s2);
							result.getStorage().remove(entry.getKey());
							entry.getKey().add(table1);
							result.getStorage().put(entry.getKey(), resultStorage);
						} else {
							ArrayList<Table> l = new ArrayList<Table>();
							l.add(table1);
							l.add(table2);
							result.getStorage().put(l, join(new Result(), s1, s2));
						}
					}
				}
			}
		}
		return result;
	}

	public static String printRuntime(long runT) {
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

	// TODO
	public static void dynamicJoinOrder(String query) {
		Result result = new Result();

		String[] queries = query.split("AND");

	}

	public static void main(String[] args) throws IOException {
		
		String[] queries = { "1", "2", "3", "4", "5", "total" };
		Long[] runtime = new Long[queries.length];

		LineChart lc = new LineChart(queries);

		long startTime;
		long endTime;

		String[] orders = { "12345", "12354", "12435", "12453", "12543", "12534", "13245", "13254", "13425", "13452",
				"13542", "13524", "14325", "14352", "14235", "14253", "14523", "14532", "15342", "15324", "15432",
				"15423", "15243", "15234", "21345", "21354", "21435", "21453", "21543", "21534", "23145", "23154",
				"23415", "23451", "23541", "23514", "24315", "24351", "24135", "24153", "24513", "24531", "25341",
				"25314", "25431", "25413", "25143", "25134", "32145", "32154", "32415", "32451", "32541", "32514",
				"31245", "31254", "31425", "31452", "31542", "31524", "34125", "34152", "34215", "34251", "34521",
				"34512", "35142", "35124", "35412", "35421", "35241", "35214", "42315", "42351", "42135", "42153",
				"42513", "42531", "43215", "43251", "43125", "43152", "43512", "43521", "41325", "41352", "41235",
				"41253", "41523", "41532", "45312", "45321", "45132", "45123", "45213", "45231", "52341", "52314",
				"52431", "52413", "52143", "52134", "53241", "53214", "53421", "53412", "53142", "53124", "54321",
				"54312", "54231", "54213", "54123", "54132", "51342", "51324", "51432", "51423", "51243", "51234" };

//		HashMap<Integer, String> sizeArr = new HashMap<Integer, String>();
		Map<String,Long> runtimeArr = new HashMap<String,Long>();

		
		for (String order : orders) {

//			System.out.println("-------------order: " + order + " --------------");

			Result result = new Result();
			for (char c : order.toCharArray()) {

				switch (c) {
				case '1':
					startTime = System.currentTimeMillis();
//					System.out.println("cn.id=mc.company_id");
					result = joinExample(result, "cn.id", "mc.company_id");
					endTime = System.currentTimeMillis();
					runtime[0] = endTime - startTime;
					break;
				case '2':
					startTime = System.currentTimeMillis();
//				System.out.println("mc.movie_id=t.id");
					result = joinExample(result, "mc.movie_id", "t.id");
					endTime = System.currentTimeMillis();
					runtime[1] = endTime - startTime;
					break;
				case '3':
					startTime = System.currentTimeMillis();
//				System.out.println("mk.movie_id=t.id");
					result = joinExample(result, "mk.movie_id", "t.id");
					endTime = System.currentTimeMillis();
					runtime[2] = endTime - startTime;
					break;
				case '4':
					startTime = System.currentTimeMillis();
//					System.out.println("mc.movie_id=mk.movie_id");
					result = joinExample(result, "mc.movie_id", "mk.movie_id");
					endTime = System.currentTimeMillis();
					runtime[3] = endTime - startTime;
					break;
				case '5':
					startTime = System.currentTimeMillis();
//					System.out.println("mk.keyword_id=k.id");
					result = joinExample(result, "mk.keyword_id", "k.id");
					endTime = System.currentTimeMillis();
					runtime[4] = endTime - startTime;
					break;
				default:
					System.out.println("Error: The corresponding execution does not exist. ");
					break;
				}
			}
			runtime[5] = runtime[0] + runtime[1] + runtime[2] + runtime[3] + runtime[4];
			runtimeArr.put(order,runtime[5]);
//			printRuntimeArray(runtime);
			System.out.println("The size of the result in the order " + order + " is: " + result.getData().size());
//			sizeArr.put(result.getData().size(), order);
			lc.addLine(order, runtime);

		}

		
//		System.out.println("There are "+sizeArr.size()+" kind of sizes in this HashMap. ");

		Long totalRuntime = 0L;
		
		List<Entry<String, Long>> testList = new ArrayList<Entry<String, Long>>(runtimeArr.entrySet());
		Collections.sort(testList, new Comparator<Map.Entry<String, Long>>() {
			public int compare(Map.Entry<String, Long> o1, Map.Entry<String, Long> o2) {
				return (int) (o2.getValue() - o1.getValue());
			}
		});

		for (Entry<String, Long> t : testList) {
			System.out.println("["+t.getKey() + "] " + t.getValue());
			totalRuntime+=t.getValue();
		}

		System.out.println("The total runtime is: "+totalRuntime+" ms. ");
		
		lc.drawLineChart();

	}

}
