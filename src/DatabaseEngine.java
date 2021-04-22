

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
	 * @return The converted HashMap. 
	 */
	public static Map<String,List<String>> csvToHashMap(String path){
		
		Map<String,List<String>> values = new HashMap<String,List<String>>();
		
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
				values.put(nextLine[0], list);
			}
			
		} catch (CsvValidationException e) {
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		System.out.println("Complete file conversion. ");
		
		return values;
	}
	
	public static Map<String,List<String>> join(Map<String,List<String>> m1, Map<String,List<String>> m2, int i1, int i2){
		Map<String,List<String>> m = new HashMap<String,List<String>>();
		
		Iterator<Map.Entry<String, List<String>>> it1 = m1.entrySet().iterator();
		
		//key of the new map
		int i=1;
		
		while(it1.hasNext()) {
			HashMap.Entry<String,List<String>> entry1 = it1.next();
			String s1 = entry1.getValue().get(i1);
			
			Iterator<Map.Entry<String, List<String>>> it2 = m2.entrySet().iterator();
			while(it2.hasNext()) {
				HashMap.Entry<String,List<String>> entry2 = it2.next();
				String s2=entry2.getValue().get(i2);
				
				
				if(s1.equals(s2)) {
					entry1.getValue().addAll(entry2.getValue());
					m.put(String.valueOf(i), entry1.getValue());
					i++;
				}
			}
		}
		
		System.out.println("Complete join. ");
		
		return m;
	}
	
	public static void main(String[] args) {
		
		Map<String,List<String>> movie_keyword = csvToHashMap("/Users/lili/Documents/Bachelor Thesis/imdb/movie_keyword.csv");
		Map<String,List<String>> keyword = csvToHashMap("/Users/lili/Documents/Bachelor Thesis/imdb/keyword.csv");
		Map<String,List<String>> title = csvToHashMap("/Users/lili/Documents/Bachelor Thesis/imdb/title.csv");
		Map<String,List<String>> movie_companies = csvToHashMap("/Users/lili/Documents/Bachelor Thesis/imdb/movie_companies.csv");
		Map<String,List<String>> company_name = csvToHashMap("/Users/lili/Documents/Bachelor Thesis/imdb/company_name.csv");
		
		Map<String,List<String>> results = join(join(join(join(movie_keyword, keyword, 2, 0), title, 1, 0), movie_companies, 6, 1),company_name, 20, 0);
		System.out.println(results);
		
//		Map<String,List<String>> info_type = csvToHashMap("/Users/lili/Documents/Bachelor Thesis/imdb/info_type.csv");
//		System.out.println(join(info_type,info_type,0,0));

	}
}