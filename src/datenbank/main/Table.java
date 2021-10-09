package datenbank.main;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Random;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;


public abstract class Table {
	protected String name;
	protected String path;
	protected Row row;
	protected ArrayList<String> title;
	protected double upperBound = 0.55;
	protected double lowerBound = 0.45;
	protected HashMap<Integer, Row> data = new HashMap<Integer, Row>();
	protected HashMap<Integer, Row> example = new HashMap<Integer, Row>();
	protected Random r = new Random();

	public <T> HashMap<T, ArrayList<Integer>> getPKMap(String attr) {
		HashMap<T, ArrayList<Integer>> pkMap = new HashMap<T, ArrayList<Integer>>();
		Iterator<Entry<Integer, Row>> it = data.entrySet().iterator();
		while (it.hasNext()) {
			Entry<Integer, Row> entry = it.next();
			Integer pk = entry.getKey();
			T value = entry.getValue().get(attr);
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
	
	public void init() {
		JFrame jf = new JFrame(name);
		jf.add(new JScrollPane(new JTable(toTableModel(data))));
		jf.pack();
		jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jf.setVisible(true);
	}
	
	public TableModel toTableModel(Map<Integer, Row> map) {
		DefaultTableModel model = new DefaultTableModel(title.toArray(), 0);
		int size = title.size();
		for (Map.Entry<Integer, Row> entry : map.entrySet()) {
			Object[] row = new Object[size];
			int i = 0;
			for(String attr:title) {
				row[i] = entry.getValue().get(attr);
				i++;
			}
			model.addRow(row);
		}
		return model;
	}
	
	public <T> void printPKMap(String attr) {
		HashMap<T, ArrayList<Integer>> pkMap = this.getPKMap(attr);
		
		DefaultTableModel model = new DefaultTableModel(new String[] {"Key", "Value"}, 0);
		for (Entry<T, ArrayList<Integer>> entry : pkMap.entrySet()) {
			Object[] row = new Object[2];
			row[0] = entry.getKey();
			row[1] = entry.getValue();
			model.addRow(row);
		}
		
		JFrame jf = new JFrame(name);
		jf.add(new JScrollPane(new JTable(model)));
		jf.pack();
		jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jf.setVisible(true);
	}
	
	public String toString() {
		init();
		return "Table "+this.name+": "+data.size()+" rows in total. ";
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
	
	public ArrayList<String> getTitle() {
		return this.title;
	}

	public HashMap<Integer, Row> getData() {
		return this.data;
	}
	
	public HashMap<Integer, Row> getExample() {
		return this.example;
	}
	
}
