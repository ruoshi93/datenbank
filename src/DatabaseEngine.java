

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

public class DatabaseEngine {
	
	/**
	 * This method converts an CSV File to HashMap. 
	 * @param path The path of the CSV File. 
	 * @param key  The index of the column, which is the key of the HashMap. 
	 * @return The converted HashMap. 
	 */
	public static Map<String,List<String>> csvToHashMap(String path,int key){
		
		Map<String,List<String>> values = new myHashMap<String,List<String>>();
		
		try {
			
			CSVReader reader = new CSVReader (new FileReader (path));
			
			String[] nextLine;
			
			while((nextLine=reader.readNext())!=null) {
				List<String> list = new ArrayList<String>();
				int i = 0; 
				while(i<nextLine.length) {
					list.add(nextLine[i]);
					i++;
				}
				values.put(nextLine[key], list);
			}
			
		} catch (CsvValidationException e) {
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
		return values;
	}
	
	/**
	 * This method joins two HashMaps. 
	 * @param m1 The first HashMap. 
	 * @param m2 The second HashMap. 
	 * @param key The index of the key in the joined HashMap. 
	 * @return The joined HashMap. 
	 */
	public static Map<String,List<String>> join(Map<String,List<String>> m1, Map<String,List<String>> m2, int key){
		Map<String,List<String>> m = new myHashMap<String,List<String>>();
		
		Iterator<Map.Entry<String, List<String>>> it1 = m1.entrySet().iterator();
			
		while(it1.hasNext()) {
			HashMap.Entry<String,List<String>> entry1 = it1.next();
			String s1 = entry1.getKey();
			List<String> list1 = entry1.getValue();
			List<String> list2 = m2.get(s1);
			if(list2!=null) {
				list1.addAll(list2);
				m.put(list1.get(key), list1);
			}
		}
		
		return m;
	}
	
	public static void main(String[] args) {
		
		Map<String,List<String>> movie_keyword = csvToHashMap("/Users/lili/Documents/Bachelor Thesis/imdb/movie_keyword.csv",2);
		Map<String,List<String>> keyword = csvToHashMap("/Users/lili/Documents/Bachelor Thesis/imdb/keyword.csv",0);
		Map<String,List<String>> title = csvToHashMap("/Users/lili/Documents/Bachelor Thesis/imdb/title.csv",0);
		Map<String,List<String>> movie_companies = csvToHashMap("/Users/lili/Documents/Bachelor Thesis/imdb/movie_companies.csv",1);
		Map<String,List<String>> company_name = csvToHashMap("/Users/lili/Documents/Bachelor Thesis/imdb/company_name.csv",0);
		
		Map<String,List<String>> joinResults = join(join(join(join(movie_keyword, keyword, 1), title, 6), movie_companies, 20),company_name, 4);
		
		System.out.println(joinResults);

	}
}

class myHashMap<K,V> extends HashMap<K,V>{
	private static final long serialVersionUID = 3523705904473627838L;

	@Override
	public String toString() {
		String s = "";
		Iterator<Map.Entry<K, V>> it = this.entrySet().iterator();
		while(it.hasNext()) {
			Map.Entry<K, V> me = it.next();
			s = s + me.getValue() + "\n";
		}
		return s;
	}
}