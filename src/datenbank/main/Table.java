package datenbank.main;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map.Entry;

public abstract class Table {
	protected String name;
	protected String path;
	protected Row row;
	protected int samplingSpace=11;
	protected HashMap<Integer, Row> data = new HashMap<Integer, Row>();
	protected HashMap<Integer, Row> example = new HashMap<Integer, Row>();
	// TODO Delete all the path and data of the subclasses

	public <T> HashMap<T, ArrayList<Integer>> getPKMap(String s) {
		HashMap<T, ArrayList<Integer>> pkMap = new HashMap<T, ArrayList<Integer>>();
		Iterator<Entry<Integer, Row>> it = data.entrySet().iterator();
		while (it.hasNext()) {
			Entry<Integer, Row> entry1 = it.next();
			Integer pk = entry1.getKey();
			T value = entry1.getValue().get(s);
			ArrayList<Integer> pkMapValue = pkMap.get(value);
			if (pkMapValue != null) {
				pkMapValue.add(pk);
			} else {
				ArrayList<Integer> pkList = new ArrayList<Integer>();
				pkList.add(pk);
				pkMap.put(value, pkList);
			}
		}
		return pkMap;
	}
	
	public <T> HashMap<T, ArrayList<Integer>> getExamplePKMap(String s) {
		HashMap<T, ArrayList<Integer>> pkMap = new HashMap<T, ArrayList<Integer>>();
		Iterator<Entry<Integer, Row>> it = example.entrySet().iterator();
		while (it.hasNext()) {
			Entry<Integer, Row> entry1 = it.next();
			Integer pk = entry1.getKey();
			T value = entry1.getValue().get(s);
			ArrayList<Integer> pkMapValue = pkMap.get(value);
			if (pkMapValue != null) {
				pkMapValue.add(pk);
			} else {
				ArrayList<Integer> pkList = new ArrayList<Integer>();
				pkList.add(pk);
				pkMap.put(value, pkList);
			}
		}
		return pkMap;
	}
	
	//TODO Output the Table
	public String toString() {
		return this.name;
	}

	public String getName() {
		return this.name;
	}
	
	public String getPath() {
		return this.path;
	}
	
	public Row getRow() {
		return this.row;
	}

	public HashMap<Integer, Row> getData() {
		return this.data;
	}
	
	public HashMap<Integer, Row> getExample() {
		return this.example;
	}
	
}
