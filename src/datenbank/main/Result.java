package datenbank.main;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import java.util.ArrayList;
import java.util.Arrays;

public class Result implements Cloneable {

	// stores the tables of the HashMap data in order
	private ArrayList<Table> schema = new ArrayList<Table>();

	// HashMap data only stores the corresponding primary keys ("id") of each table
	// in the schema
	private HashMap<Integer, ArrayList<Integer>> data = new HashMap<Integer, ArrayList<Integer>>();

	// store the join result, that the tables do not exist in the schema
	private HashMap<ArrayList<Table>, Result> storage = new HashMap<ArrayList<Table>, Result>();

	// return a HashMap, that the key is the id of the table in "data" and the value
	// is the corresponding key value in "data"
	public HashMap<Integer, Integer> getIDPKMap(Table t) {
		int index = schema.indexOf(t);
		HashMap<Integer, Integer> pkMap = new HashMap<Integer, Integer>();
		Iterator<Map.Entry<Integer, ArrayList<Integer>>> it = this.getData().entrySet().iterator();
		while (it.hasNext()) {
			HashMap.Entry<Integer, ArrayList<Integer>> entry = it.next();
			pkMap.put(entry.getValue().get(index), entry.getKey());
		}
		return pkMap;
	}

	// return a HashMap, that the key is the value of the attribute in the table of
	// "data" and the value is the corresponding key value in "data"
	public <T> HashMap<T, ArrayList<Integer>> getAttrPKMap(Table t, String attr) {
		int index = schema.indexOf(t);

		HashMap<T, ArrayList<Integer>> pkMap = new HashMap<T, ArrayList<Integer>>();

		Iterator<Map.Entry<Integer, ArrayList<Integer>>> it = this.getData().entrySet().iterator();
		while (it.hasNext()) {
			HashMap.Entry<Integer, ArrayList<Integer>> entry = it.next();
			Integer id = entry.getValue().get(index);
			T attrValue = t.getData().get(id).get(attr);
			ArrayList<Integer> pkMapValue = pkMap.get(attrValue);
			if (pkMapValue != null) {
				pkMapValue.add(entry.getKey());

			} else {
				ArrayList<Integer> newList = new ArrayList<Integer>();
				newList.add(entry.getKey());
				pkMap.put(attrValue, newList);
			}
		}
		return pkMap;
	}

	public ArrayList<Table> getSchema() {
		return schema;
	}

	public void setSchema(ArrayList<Table> schema) {
		this.schema = schema;
	}

	public HashMap<Integer, ArrayList<Integer>> getData() {
		return data;
	}

	public void setData(HashMap<Integer, ArrayList<Integer>> data) {
		this.data = data;
	}

	public void setStorage(HashMap<ArrayList<Table>, Result> storage) {
		this.storage = storage;
	}

	public HashMap<ArrayList<Table>, Result> getStorage() {
		return this.storage;
	}

	public boolean isEmpty() {
		return schema.isEmpty() && data.isEmpty();
	}

	public boolean storageContains(Table t) {
		for (Map.Entry<ArrayList<Table>, Result> entry : this.storage.entrySet()) {
			if (entry.getKey().contains(t)) {
				return true;
			}
		}
		return false;
	}

	public int storageIndex(Table t) {
		int i = 0;
		for (Map.Entry<ArrayList<Table>, Result> entry : this.storage.entrySet()) {
			if (entry.getKey().contains(t)) {
				return i;
			}
			i++;
		}
		return i;
	}

	public Result getStorageResult(Table t) {
		for (Map.Entry<ArrayList<Table>, Result> entry : this.storage.entrySet()) {
			if (entry.getKey().contains(t)) {
				return entry.getValue();
			}
		}
		return null;
	}

	public boolean removeStorageTable(Table t) {
		for (Map.Entry<ArrayList<Table>, Result> entry : this.storage.entrySet()) {
			if (entry.getKey().contains(t)) {
				this.storage.remove(entry.getKey());
				return true;
			}
		}
		return false;
	}

	@Override
	public Object clone() {
		Result result = null;
		try {
			result = (Result) super.clone();
		} catch (CloneNotSupportedException e) {
			e.printStackTrace();
		}
		result.setSchema((ArrayList<Table>) this.schema.clone());
		result.setData((HashMap<Integer, ArrayList<Integer>>) this.data.clone());
		return result;
	}

	public void init() {
		JFrame jf = new JFrame("Result");
		jf.add(new JScrollPane(new JTable(toTableModel(data))));
		jf.pack();
		jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jf.setVisible(true);
	}
 
	public TableModel toTableModel(Map<Integer, ArrayList<Integer>> map) {
		ArrayList<String> allSchema = new ArrayList<String>();
		for(Table t:this.schema) {
			for(String s:t.getTitle()) {
				allSchema.add(t.getName()+"."+s);
			}
		}
		Object[] title = (Object[]) allSchema.toArray();
		DefaultTableModel model = new DefaultTableModel(title, 0);
		
		int size = title.length;
		for (Map.Entry<Integer, ArrayList<Integer>> entry : map.entrySet()) {
			Object[] row = new Object[size];
			
			int i = 0;
			int j = 0;
			for(Integer pk : entry.getValue()) {
				Table t = this.schema.get(i);
				Row r = t.getData().get(pk);
				for(String attr: t.getTitle()) {
					row[j] = r.get(attr);
					j++;
				}
				i++;
			}
			
			model.addRow(row);
		}
		return model;
	}

	@Override
	public String toString() {
		init();
		return "Result: "+this.data.size()+ " rows in total. ";
	}
}
