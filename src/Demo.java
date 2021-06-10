
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import datenbank.imdb.*;

public class Demo {

	public static Result join(TableDemo t1, String s1, TableDemo t2, String s2) {

		Result result = new Result();
		result.getSchema().add(t1);
		result.getSchema().add(t2);

		if (s1.equals("id")) {
			if (s2.equals("id")) {
				Iterator<Map.Entry<Integer, RowDemo>> it1 = t1.getData().entrySet().iterator();
				while (it1.hasNext()) {
					HashMap.Entry<Integer, RowDemo> entry1 = it1.next();
					Integer i = entry1.getKey();
					if (t2.getData().get(i) != null) {
						ArrayList<Integer> list = new ArrayList<Integer>();
						list.add(i);
						result.getData().put(i, list);
					}
				}
			} else {
				Iterator<Map.Entry<Integer, RowDemo>> it2 = t2.getData().entrySet().iterator();
				while (it2.hasNext()) {
					HashMap.Entry<Integer, RowDemo> entry2 = it2.next();
					Integer pk2 = entry2.getKey();
					RowDemo value2 = entry2.getValue();
					RowDemo value1 = t1.getData().get(value2.get(s2));
					if (value1 != null) {
						ArrayList<Integer> list = new ArrayList<Integer>();
						list.add(pk2);
						result.getData().put(value1.getPrimaryKey(), list);
					}
				}
			}
		} else {
			if(s2.equals("id")) {
				Iterator<Map.Entry<Integer, RowDemo>> it1 = t1.getData().entrySet().iterator();
				while (it1.hasNext()) {
					HashMap.Entry<Integer, RowDemo> entry1 = it1.next();
					Integer pk1 = entry1.getKey();
					RowDemo value1=entry1.getValue();
					RowDemo value2=t2.getData().get(value1.get(s1));
					if (value2 != null) {
						ArrayList<Integer> list = new ArrayList<Integer>();
						list.add(value2.getPrimaryKey());
						result.getData().put(pk1, list);
					}
				}
			}else {
				Iterator<Map.Entry<Integer, RowDemo>> it1 = t1.getData().entrySet().iterator();
				HashMap pk2HashMap = t2.get(s2);
				while (it1.hasNext()) {
					HashMap.Entry<Integer, RowDemo> entry1 = it1.next();
					Integer pk1 = entry1.getKey();
					RowDemo value1 = entry1.getValue();
					ArrayList<Integer> pk2List = (ArrayList<Integer>) pk2HashMap.get(value1.get(s1));
					if (pk2List != null) {
						ArrayList<Integer> list = new ArrayList<Integer>();
						for(int i = 0;i<pk2List.size();i++) {
							list.add(pk2List.get(i));
							result.getData().put(pk1, list);
						}
					}
				}
			}
		}
		return result;
	}

	public static Result join(TableDemo t1, String s1, Result t2, String s2) {
		Result result = new Result();

		return result;
	}

	public static Result join(Result t1, String s1, TableDemo t2, String s2) {
		Result result = t1;
		t1.getSchema().add(t2);
		//TODO clone result

		return result;
	}

	public static Result join(Result t1, String s1, Result t2, String s2) {
		Result result = new Result();

		return result;
	}

	public static void main(String[] args) {

		Company_nameTable cn = new Company_nameTable();
		KeywordTable k = new KeywordTable();
		Movie_companiesTable mc = new Movie_companiesTable();
		Movie_keywordTable mk = new Movie_keywordTable();
		TitleTable t = new TitleTable();

		Result result = join(mk,"keyword_id",k,"id");
		System.out.println("Join completed");
		System.out.println(result);
	}

}
