
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
				Iterator<Map.Entry<Integer, Row>> it1 = t1.getData().entrySet().iterator();
				while (it1.hasNext()) {
					HashMap.Entry<Integer, Row> entry1 = it1.next();
					Integer i = entry1.getKey();
					if (t2.getData().get(i) != null) {
						ArrayList<Integer> list = new ArrayList<Integer>();
						list.add(i);
						result.getData().put(i, list);
					}
				}
			} else {
				Iterator<Map.Entry<Integer, Row>> it2 = t2.getData().entrySet().iterator();
				while (it2.hasNext()) {
					HashMap.Entry<Integer, Row> entry2 = it2.next();
					Integer pk2 = entry2.getKey();
					Row value2 = entry2.getValue();
					Row value1 = t1.getData().get(value2.get(s2));
					if (value1 != null) {
						ArrayList<Integer> list = new ArrayList<Integer>();
						list.add(pk2);
						result.getData().put(value1.getPrimaryKey(), list);
					}
				}
			}
		} else {
			if(s2.equals("id")) {
				Iterator<Map.Entry<Integer, Row>> it1 = t1.getData().entrySet().iterator();
				while (it1.hasNext()) {
					HashMap.Entry<Integer, Row> entry1 = it1.next();
					Integer pk1 = entry1.getKey();
					Row value1=entry1.getValue();
					Row value2=t2.getData().get(value1.get(s1));
					if (value2 != null) {
						ArrayList<Integer> list = new ArrayList<Integer>();
						list.add(value2.getPrimaryKey());
						result.getData().put(pk1, list);
					}
				}
			}else {
				Iterator<Map.Entry<Integer, Row>> it1 = t1.getData().entrySet().iterator();
				HashMap pk2HashMap = t2.get(s2);
				while (it1.hasNext()) {
					HashMap.Entry<Integer, Row> entry1 = it1.next();
					Integer pk1 = entry1.getKey();
					Row value1 = entry1.getValue();
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
		TableDemo an = new Aka_nameTable();
		TableDemo at = new Aka_titleTable();
//		TableDemo ci = new Cast_infoTable();
		TableDemo chn= new Char_nameTable();
		TableDemo cct= new Comp_cast_typeTable();
		TableDemo cn = new Company_nameTable();
		TableDemo ct = new Company_typeTable();
		TableDemo cc = new Complete_castTable();
		TableDemo it = new Info_typeTable();
		TableDemo k = new KeywordTable();
		TableDemo kt = new Kind_typeTable();
		TableDemo lt = new Link_typeTable();
		TableDemo mc = new Movie_companiesTable();
		TableDemo mii= new Movie_info_idxTable();
//		TableDemo mi = new Movie_infoTable();
		TableDemo mk = new Movie_keywordTable();
		TableDemo ml = new Movie_linkTable();
		TableDemo n = new NameTable();
//		TableDemo pi = new Person_infoTable();
		TableDemo rt = new Role_typeTable();
//		TableDemo t = new TitleTable();

//		Result result = join(mk,"keyword_id",k,"id");
//		System.out.println("Join completed");
//		System.out.println(result);
	}

}
