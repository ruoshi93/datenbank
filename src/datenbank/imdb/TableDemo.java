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
	protected String path;
	protected HashMap<Integer,RowDemo> data = new HashMap<Integer,RowDemo>();
	//TODO Delete all the path and data of the subclasses
	
	public <T> HashMap<T,ArrayList<Integer>> get(String s){
		HashMap<T,ArrayList<Integer>> pkMap = new HashMap<T,ArrayList<Integer>>();
		Iterator<Entry<Integer, RowDemo>> it1 = data.entrySet().iterator();
		while(it1.hasNext()) {
			Entry<Integer, RowDemo> entry1 = it1.next();
			Integer pk = entry1.getKey();
			T value = entry1.getValue().get(s);
			ArrayList<Integer> pkMapValue = pkMap.get(value);
			if(pkMapValue!=null) {
				pkMapValue.add(pk);
			}else {
				ArrayList<Integer> pkList = new ArrayList<Integer>();
				pkList.add(pk);
				pkMap.put(value, pkList);
			}
		}
		return pkMap;
	}
	
	public String getPath() {
		return path;
	}


	public void setPath(String path) {
		this.path = path;
	}


	public void setData(HashMap<Integer, RowDemo> data) {
		this.data = data;
	}


	public HashMap<Integer, RowDemo> getData() {
		return this.data;
	}
}
