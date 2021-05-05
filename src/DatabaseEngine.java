
//import java.io.FileNotFoundException;
//import java.io.FileReader;
//import java.io.IOException;
//import java.util.ArrayList;
import java.util.Arrays;
//import java.util.HashMap;
//import java.util.Iterator;
import java.util.List;
//import java.util.Map;
//
//import com.opencsv.CSVReader;
//import com.opencsv.exceptions.CsvValidationException;

public class DatabaseEngine {

//	/**
//	 * This method converts an CSV File to HashMap.
//	 * 
//	 * @param path The path of the CSV File.
//	 * @param key  The index of the column, which is the key of the HashMap.
//	 * @return The converted HashMap.
//	 */
//	public static Map<String, List<String>> csvToHashMap(String path, int key) {
//
//		Map<String, List<String>> values = new MyHashMap<String, List<String>>();
//
//		try {
//
//			CSVReader reader = new CSVReader(new FileReader(path));
//
//			String[] nextLine;
//
//			while ((nextLine = reader.readNext()) != null) {
//				List<String> list = new ArrayList<String>();
//				int i = 0;
//				while (i < nextLine.length) {
//					list.add(nextLine[i]);
//					i++;
//				}
//				values.put(nextLine[key], list);
//			}
//
//		} catch (CsvValidationException e) {
//			e.printStackTrace();
//		} catch (FileNotFoundException e) {
//			e.printStackTrace();
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//
//		return values;
//	}
//
//	/**
//	 * This method joins two HashMaps.
//	 * 
//	 * @param m1  The first HashMap.
//	 * @param m2  The second HashMap.
//	 * @param key The index of the key in the joined HashMap.
//	 * @return The joined HashMap.
//	 */
//	public static Map<String, List<String>> join(Map<String, List<String>> m1, Map<String, List<String>> m2, int key) {
//		Map<String, List<String>> m = new MyHashMap<String, List<String>>();
//
//		Iterator<Map.Entry<String, List<String>>> it1 = m1.entrySet().iterator();
//
//		while (it1.hasNext()) {
//			HashMap.Entry<String, List<String>> entry1 = it1.next();
//			String s1 = entry1.getKey();
//			List<String> list1 = entry1.getValue();
//			List<String> list2 = m2.get(s1);
//			if (list2 != null) {
//				list1.addAll(list2);
//				m.put(list1.get(key), list1);
//			}
//		}
//
//		return m;
//	}
	
	private static String runTime(long runT) {
		if(runT<1000) {
			return runT+" ms. "; 
		}else if(runT<60000) {
			return runT/1000+" secs "+runT%1000+" ms. ";
		}else {
			return runT/60000+" min " + (runT%60000)/1000 + " secs " + runT%60000%1000+" ms. ";
		}
	}

	public static void main(String[] args) {

//		Map<String,List<String>> movie_keyword = csvToHashMap("/Users/lili/Documents/Bachelor Thesis/imdb/movie_keyword.csv",2);
//		Map<String,List<String>> keyword = csvToHashMap("/Users/lili/Documents/Bachelor Thesis/imdb/keyword.csv",0);
//		Map<String,List<String>> title = csvToHashMap("/Users/lili/Documents/Bachelor Thesis/imdb/title.csv",0);
//		Map<String,List<String>> movie_companies = csvToHashMap("/Users/lili/Documents/Bachelor Thesis/imdb/movie_companies.csv",1);
//		Map<String,List<String>> company_name = csvToHashMap("/Users/lili/Documents/Bachelor Thesis/imdb/company_name.csv",0);
//		
//		Map<String,List<String>> joinResults = join(join(join(join(movie_keyword, keyword, 1), title, 6), movie_companies, 20),company_name, 4);
//		
//		System.out.println(joinResults);
		long startTime=System.currentTimeMillis();
		
		List<String> movie_keyword_schema = Arrays.asList("id", "movie_id", "keyword_id");
		Table movie_keyword = new Table("mk", "/Users/lili/Documents/Bachelor Thesis/imdb/movie_keyword.csv",
				movie_keyword_schema,"mk.keyword_id");

		List<String> keyword_schema = Arrays.asList("id", "keyword", "phonetic_code");
		Table keyword = new Table("k", "/Users/lili/Documents/Bachelor Thesis/imdb/keyword.csv", keyword_schema);

		List<String> title_schema = Arrays.asList("id", "title", "imdb_index", "kind_id", "production_year", "imdb_id",
				"phonetic_code", "episode_of_id", "season_nr", "episode_nr", "series_years", "md5sum");
		Table title = new Table("t", "/Users/lili/Documents/Bachelor Thesis/imdb/title.csv", title_schema);

		List<String> movie_companies_schema = Arrays.asList("id", "movie_id", "company_id","company_type_id","note");
		Table movie_companies = new Table("mc", "/Users/lili/Documents/Bachelor Thesis/imdb/movie_companies.csv",
				movie_companies_schema,"mc.movie_id");

		List<String> company_name_schema = Arrays.asList("id", "name", "country_code","imdb_id","name_pcode_nf","name_pcode_sf","md5sum");
		Table company_name = new Table("cn", "/Users/lili/Documents/Bachelor Thesis/imdb/company_name.csv",
				company_name_schema);
		
		long loadDataTime=System.currentTimeMillis();
		
		//Query
		Table result = movie_keyword.join(keyword, "mk.keyword_id");
		result.setAttribute("mk.movie_id");
		result = result.join(title, "mk.movie_id");
		result.setAttribute("t.id");
		result = result.join(movie_companies, "t.id", "mc.movie_id");
		result.setAttribute("mc.company_id");
		result = result.join(company_name, "mc.company_id");
		
		long endTime=System.currentTimeMillis();
		System.out.println("Total runtime: "+runTime(endTime-startTime));
		System.out.println("Total query runtime: "+runTime(endTime-loadDataTime));  
		
		System.out.println(result);
	}
}
