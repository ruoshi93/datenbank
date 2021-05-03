import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;

public class Table {
	private String path;
	private List<String> schema;
	private Map<String, List<String>> table;
	private Map<String, Map<String, List<String>>> attributes;

	public Table(String path, List<String> schema) {
		this.path = path;
		this.schema = schema;
		this.table = new MyHashMap<String, List<String>>();
		this.attributes = new HashMap<String, Map<String, List<String>>>();
		this.convert();
	}

	public Table(String path, List<String> schema, String attr) {
		this.path = path;
		this.schema = schema;
		this.table = new MyHashMap<String, List<String>>();
		this.attributes = new HashMap<String, Map<String, List<String>>>();
		this.convert();
		this.setAttribute(attr);
	}

	public Table(Map<String, List<String>> table, List<String> schema) {
		this.table = table;
		this.schema = schema;
	}

	public Map<String, List<String>> getTable() {
		return this.table;
	}

	public List<String> getSchema() {
		return this.schema;
	}

	public Map<String, List<String>> getAttribute(String name) {
		return this.attributes.get(name);
	}

	public void setAttribute(String attr) {
		Map<String, List<String>> indexMap = new MyHashMap<String, List<String>>();
		int i = schema.indexOf(attr);

		Iterator<Map.Entry<String, List<String>>> it = table.entrySet().iterator();
		while (it.hasNext()) {

			HashMap.Entry<String, List<String>> entry = it.next();
			String primaryKey = entry.getKey();
			String attribute = entry.getValue().get(i);

			List<String> indexArray = indexMap.get(attribute);

			if (indexArray != null) {
				indexArray.add(primaryKey);
			} else {
				List<String> pkList = new ArrayList();
				pkList.add(primaryKey);
				indexMap.put(attribute, pkList);
			}

		}

		attributes.put(attr, indexMap);
	}

	/**
	 * This method converts an CSV File to HashMap.
	 * 
	 * @param path The path of the CSV File.
	 * @return The converted HashMap.
	 */
	private void convert() {

		try {

			CSVReader reader = new CSVReader(new FileReader(path));

			String[] nextLine;

			while ((nextLine = reader.readNext()) != null) {
				List<String> list = new ArrayList<String>();
				int i = 0;
				while (i < nextLine.length) {
					list.add(nextLine[i]);
					i++;
				}
				table.put(nextLine[0], list);
			}

		} catch (CsvValidationException e) {
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public Table join(Table table2, String attr1, String attr2) {
		Map<String, List<String>> newTable = new HashMap<String, List<String>>();

		Map<String, List<String>> indexMap1 = this.attributes.get(attr1);
		Map<String, List<String>> indexMap2 = table2.getAttribute(attr2);

		Iterator<Map.Entry<String, List<String>>> it = indexMap1.entrySet().iterator();
		while (it.hasNext()) {
			HashMap.Entry<String, List<String>> entry1 = it.next();
			List<String> pkList2 = indexMap2.get(entry1.getKey());
			if (pkList2 != null) {
				List<String> pkList1 = entry1.getValue();
				for (int i1 = 0; i1 < pkList1.size(); i1++) {
					String pk1 = pkList1.get(i1);
					for (int i2 = 0; i2 < pkList2.size(); i2++) {
						String pk2 = pkList2.get(i2);
						ArrayList<String> newList = (ArrayList<String>) ((ArrayList<String>) table.get(pk1)).clone();
						newList.addAll(table2.getTable().get(pk2));
						newTable.put(pk1 + pk2, newList);
					}
				}
			}
		}
		ArrayList<String> newSchema = (ArrayList<String>) ((ArrayList<String>) this.schema).clone();
		newSchema.addAll(table2.getSchema());
		return new Table(newTable, newSchema);
	}

	public Table join(Table table2, String attr1) {
		Map<String, List<String>> newTable = new HashMap<String, List<String>>();
		
		ArrayList<String> newSchema = (ArrayList<String>) ((ArrayList<String>) this.schema).clone();
		newSchema.addAll(table2.getSchema());
		return new Table(newTable, newSchema);
	}

	public String toString() {
		String s = "";
		for(int i = 0;i<this.schema.size();i++) {
			s+=this.schema.get(i);
		}
		s += "\n";
		Iterator<Map.Entry<String, List<String>>> it = this.table.entrySet().iterator();
		while(it.hasNext()) {
			Map.Entry<String,List<String>> me = it.next();
			s = s + me.getValue() + "\n";
		}
		return s;
	}

}
