package datenbank.imdb;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map.Entry;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;

import datenbank.imdb.Company_nameTable.Company_name;

public abstract class TableDemo {
	protected String name;
	protected String path;
	protected HashMap<Integer, Row> data = new HashMap<Integer, Row>();
	// TODO Delete all the path and data of the subclasses

	public <T> HashMap<T, ArrayList<Integer>> get(String s) {
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
