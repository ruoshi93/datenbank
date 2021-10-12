package datenbank.job;

import datenbank.imdb.Company_nameTable;
import datenbank.imdb.Info_typeTable;
import datenbank.imdb.Kind_typeTable;
import datenbank.imdb.Link_typeTable;
import datenbank.imdb.Movie_companiesTable;
import datenbank.imdb.Movie_info_idxTable;
import datenbank.imdb.Movie_linkTable;
import datenbank.imdb.TitleTable;
import datenbank.main.DatabaseEngine;
import datenbank.main.Result;

public class Query33 extends Query{

	String query = "SELECT * "
				 + "FROM company_name AS cn1, company_name AS cn2, info_type AS it1, info_type AS it2, kind_type AS kt1, kind_type AS kt2, link_type AS lt, movie_companies AS mc1, movie_companies AS mc2, movie_info_idx AS mi_idx1, movie_info_idx AS mi_idx2, movie_link AS ml, title AS t1, title AS t2 "
				 + "WHERE lt.id = ml.link_type_id "
				 + "AND t1.id = ml.movie_id "
				 + "AND t2.id = ml.linked_movie_id "
				 + "AND it1.id = mi_idx1.info_type_id "
				 + "AND t1.id = mi_idx1.movie_id "
				 + "AND kt1.id = t1.kind_id "
				 + "AND cn1.id = mc1.company_id "
				 + "AND t1.id = mc1.movie_id "
				 + "AND ml.movie_id = mi_idx1.movie_id "
				 + "AND ml.movie_id = mc1.movie_id "
				 + "AND mi_idx1.movie_id = mc1.movie_id "
				 + "AND it2.id = mi_idx2.info_type_id "
				 + "AND t2.id = mi_idx2.movie_id "
				 + "AND kt2.id = t2.kind_id "
				 + "AND cn2.id = mc2.company_id "
				 + "AND t2.id = mc2.movie_id "
				 + "AND ml.linked_movie_id = mi_idx2.movie_id "
				 + "AND ml.linked_movie_id = mc2.movie_id "
				 + "AND mi_idx2.movie_id = mc2.movie_id;";

	public Query33() {
		DatabaseEngine.cn1 = new Company_nameTable();
		DatabaseEngine.cn2 = new Company_nameTable();
		DatabaseEngine.it1 = new Info_typeTable();
		DatabaseEngine.it2 = new Info_typeTable();
		DatabaseEngine.kt1 = new Kind_typeTable();
		DatabaseEngine.kt2 = new Kind_typeTable();
		DatabaseEngine.lt = new Link_typeTable();
		DatabaseEngine.mc1 = new Movie_companiesTable();
		DatabaseEngine.mc2 = new Movie_companiesTable();
		DatabaseEngine.mi_idx1 = new Movie_info_idxTable();
		DatabaseEngine.mi_idx2 = new Movie_info_idxTable();
		DatabaseEngine.ml = new Movie_linkTable();
		DatabaseEngine.t1 = new TitleTable();
		DatabaseEngine.t2 = new TitleTable();
		allPermutation("abcdefghijklmnopqrst".toCharArray(), 0);
	}

	@Override
	protected void numbersToCodeExample(String s) {
		Result result = new Result();
		for (char c : s.toCharArray()) {
			switch (c) {
			case 'a':
				result = DatabaseEngine.joinExample(result, "t.id", "mi.movie_id");
				break;
			case 'b':
				result = DatabaseEngine.joinExample(result, "t.id", "mi_idx.movie_id");
				break;
			case 'c':
				result = DatabaseEngine.joinExample(result, "t.id", "ci.movie_id");
				break;
			case 'd':
				result = DatabaseEngine.joinExample(result, "t.id", "mk.movie_id");
				break;
			case 'e':
				result = DatabaseEngine.joinExample(result, "t.id", "cc.movie_id");
				break;
			case 'f':
				result = DatabaseEngine.joinExample(result, "ci.movie_id", "mi.movie_id");
				break;
			case 'g':
				result = DatabaseEngine.joinExample(result, "ci.movie_id", "mi_idx.movie_id");
				break;
			case 'h':
				result = DatabaseEngine.joinExample(result, "ci.movie_id", "mk.movie_id");
				break;
			case 'i':
				result = DatabaseEngine.joinExample(result, "ci.movie_id", "mc.movie_id");
				break;
			case 'j':
				result = DatabaseEngine.joinExample(result, "mi.movie_id", "mi_idx.movie_id");
				break;
			case 'k':
				result = DatabaseEngine.joinExample(result, "mi.movie_id", "mk.movie_id");
				break;
			case 'l':
				result = DatabaseEngine.joinExample(result, "mi.movie_id", "mc.movie_id");
				break;
			case 'm':
				result = DatabaseEngine.joinExample(result, "mi_idx.movie_id", "mk.movie_id");
				break;
			case 'n':
				result = DatabaseEngine.joinExample(result, "mi_idx.movie_id", "mc.movie_id");
				break;
			case 'o':
				result = DatabaseEngine.joinExample(result, "mk.movie_id", "mc.movie_id");
				break;
			case 'p':
				result = DatabaseEngine.joinExample(result, "n.id", "ci.person_id");
				break;
			case 'q':
				result = DatabaseEngine.joinExample(result, "it1.id", "mi.info_type_id");
				break;
			case 'r':
				result = DatabaseEngine.joinExample(result, "it2.id", "mi_idx.info_type_id");
				break;
			case 's':
				result = DatabaseEngine.joinExample(result, "k.id", "mk.keyword_id");
				break;
			case 't':
				result = DatabaseEngine.joinExample(result, "cn.id", "mc.company_id");
				break;
			default:
				System.out.println("Error: The corresponding execution does not exist. ");
				break;
			}
		}
	}

	@Override
	protected Result numbersToCode(String s) {
		Result result = new Result();
		for (char c : s.toCharArray()) {
			switch (c) {
			case 'a':
				result = DatabaseEngine.join(result, "t.id", "mi.movie_id");
				break;
			case 'b':
				result = DatabaseEngine.join(result, "t.id", "mi_idx.movie_id");
				break;
			case 'c':
				result = DatabaseEngine.join(result, "t.id", "ci.movie_id");
				break;
			case 'd':
				result = DatabaseEngine.join(result, "t.id", "mk.movie_id");
				break;
			case 'e':
				result = DatabaseEngine.join(result, "t.id", "cc.movie_id");
				break;
			case 'f':
				result = DatabaseEngine.join(result, "ci.movie_id", "mi.movie_id");
				break;
			case 'g':
				result = DatabaseEngine.join(result, "ci.movie_id", "mi_idx.movie_id");
				break;
			case 'h':
				result = DatabaseEngine.join(result, "ci.movie_id", "mk.movie_id");
				break;
			case 'i':
				result = DatabaseEngine.join(result, "ci.movie_id", "mc.movie_id");
				break;
			case 'j':
				result = DatabaseEngine.join(result, "mi.movie_id", "mi_idx.movie_id");
				break;
			case 'k':
				result = DatabaseEngine.join(result, "mi.movie_id", "mk.movie_id");
				break;
			case 'l':
				result = DatabaseEngine.join(result, "mi.movie_id", "mc.movie_id");
				break;
			case 'm':
				result = DatabaseEngine.join(result, "mi_idx.movie_id", "mk.movie_id");
				break;
			case 'n':
				result = DatabaseEngine.join(result, "mi_idx.movie_id", "mc.movie_id");
				break;
			case 'o':
				result = DatabaseEngine.join(result, "mk.movie_id", "mc.movie_id");
				break;
			case 'p':
				result = DatabaseEngine.join(result, "n.id", "ci.person_id");
				break;
			case 'q':
				result = DatabaseEngine.join(result, "it1.id", "mi.info_type_id");
				break;
			case 'r':
				result = DatabaseEngine.join(result, "it2.id", "mi_idx.info_type_id");
				break;
			case 's':
				result = DatabaseEngine.join(result, "k.id", "mk.keyword_id");
				break;
			case 't':
				result = DatabaseEngine.join(result, "cn.id", "mc.company_id");
				break;
			default:
				System.out.println("Error: The corresponding execution does not exist. ");
				break;
			}
		}
		return result;
	}
}
