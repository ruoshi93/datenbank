import datenbank.imdb.RowDemo;
import datenbank.imdb.TableDemo;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.ArrayList;


public class Result{
	
	private ArrayList<TableDemo> schema = new ArrayList<TableDemo>();
	private HashMap<Integer,ArrayList<Integer>> data = new HashMap<Integer,ArrayList<Integer>>();
	
	
	public ArrayList<TableDemo> getSchema() {
		return schema;
	}

	public void setSchema(ArrayList<TableDemo> schema) {
		this.schema = schema;
	}

	public HashMap<Integer, ArrayList<Integer>> getData() {
		return data;
	}

	public void setData(HashMap<Integer, ArrayList<Integer>> data) {
		this.data = data;
	}




	//TODO Complete the toString method
	public String toString() {
		String s=new String();
//		Iterator<Map.Entry<Integer, ArrayList<Integer>>> it = data.entrySet().iterator();
//		while (it.hasNext()) {
//			HashMap.Entry<Integer, ArrayList<Integer>> entry = it.next();
//			s += schema.get(0).getData().get(entry.getKey());
//			
//		}
		s += data;
		return s;
	}
}
 