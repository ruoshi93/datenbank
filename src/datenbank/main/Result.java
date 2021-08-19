package datenbank.main;
import datenbank.imdb.Row;
import datenbank.imdb.TableDemo;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.ArrayList;


public class Result implements Cloneable{
	
	private ArrayList<TableDemo> schema = new ArrayList<TableDemo>();
	
	//HashMap data only stores the corresponding primary keys ("id") of each table in the schema
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

	public boolean isEmpty() {
		return schema.isEmpty()&&data.isEmpty();
	}
	
    @Override  
    public Object clone() {  
        Result result = null;  
        try{  
            result = (Result)super.clone();  
        }catch(CloneNotSupportedException e) {  
            e.printStackTrace();  
        }
        result.setSchema((ArrayList<TableDemo>)this.schema.clone());
        result.setData((HashMap<Integer,ArrayList<Integer>>)this.data.clone());
        return result;  
    } 


	//TODO Complete the toString method
    @Override
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
 