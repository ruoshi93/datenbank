package datenbank.main;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map.Entry;

public abstract class Table {
	protected String name;
	protected String path;
	protected HashMap<Integer, Row> data = new HashMap<Integer, Row>();
	// TODO Delete all the path and data of the subclasses

	public <T> HashMap<T, ArrayList<Integer>> getPKMap(String s) {
		HashMap<T, ArrayList<Integer>> pkMap = new HashMap<T, ArrayList<Integer>>();
		Iterator<Entry<Integer, Row>> it1 = data.entrySet().iterator();
		while (it1.hasNext()) {
			Entry<Integer, Row> entry1 = it1.next();
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

	public void setData(HashMap<Integer, Row> data) {
		this.data = data;
	}

	public HashMap<Integer, Row> getData() {
		return this.data;
	}
}
