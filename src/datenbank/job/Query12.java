package datenbank.job;

import datenbank.imdb.Company_nameTable;
import datenbank.imdb.Company_typeTable;
import datenbank.imdb.Info_typeTable;
import datenbank.imdb.Movie_companiesTable;
import datenbank.imdb.Movie_infoTable;
import datenbank.imdb.Movie_info_idxTable;
import datenbank.imdb.TitleTable;
import datenbank.main.DatabaseEngine;
import datenbank.main.Result;

public class Query12 extends Query {
	String query = "SELECT *"
			 	 + "FROM company_name AS cn, company_type AS ct, info_type AS it1, info_type AS it2, movie_companies AS mc, movie_info AS mi, movie_info_idx AS mi_idx, title AS t "
			 	 + "WHERE t.id = mi.movie_id "
			 	 + "AND t.id = mi_idx.movie_id "
			 	 + "AND mi.info_type_id = it1.id "
			 	 + "AND mi_idx.info_type_id = it2.id "
			 	 + "AND t.id = mc.movie_id "
			 	 + "AND ct.id = mc.company_type_id "
			 	 + "AND cn.id = mc.company_id "
			 	 + "AND mc.movie_id = mi.movie_id "
			 	 + "AND mc.movie_id = mi_idx.movie_id "
			 	 + "AND mi.movie_id = mi_idx.movie_id;";

	public Query12() {
		DatabaseEngine.cn = new Company_nameTable();
		DatabaseEngine.ct = new Company_typeTable();
		DatabaseEngine.it1 = new Info_typeTable();
		DatabaseEngine.it2 = new Info_typeTable();
		DatabaseEngine.mc = new Movie_companiesTable();
		DatabaseEngine.mi = new Movie_infoTable();
		DatabaseEngine.mi_idx = new Movie_info_idxTable();
		DatabaseEngine.t = new TitleTable();
		allPermutation("abcdefghij".toCharArray(), 0);
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
				result = DatabaseEngine.joinExample(result, "mi.info_type_id", "it1.id");
				break;
			case 'd':
				result = DatabaseEngine.joinExample(result, "mi_idx.info_type_id", "it2.id");
				break;
			case 'e':
				result = DatabaseEngine.joinExample(result, "t.id", "mc.movie_id");
				break;
			case 'f':
				result = DatabaseEngine.joinExample(result, "ct.id", "mc.company_type_id");
				break;
			case 'g':
				result = DatabaseEngine.joinExample(result, "cn.id", "mc.company_id");
				break;
			case 'h':
				result = DatabaseEngine.joinExample(result, "mc.movie_id", "mi.movie_id");
				break;
			case 'i':
				result = DatabaseEngine.joinExample(result, "mc.movie_id", "mi_idx.movie_id");
				break;
			case 'j':
				result = DatabaseEngine.joinExample(result, "mi.movie_id", "mi_idx.movie_id");
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
				result = DatabaseEngine.join(result, "mi.info_type_id", "it1.id");
				break;
			case 'd':
				result = DatabaseEngine.join(result, "mi_idx.info_type_id", "it2.id");
				break;
			case 'e':
				result = DatabaseEngine.join(result, "t.id", "mc.movie_id");
				break;
			case 'f':
				result = DatabaseEngine.join(result, "ct.id", "mc.company_type_id");
				break;
			case 'g':
				result = DatabaseEngine.join(result, "cn.id", "mc.company_id");
				break;
			case 'h':
				result = DatabaseEngine.join(result, "mc.movie_id", "mi.movie_id");
				break;
			case 'i':
				result = DatabaseEngine.join(result, "mc.movie_id", "mi_idx.movie_id");
				break;
			case 'j':
				result = DatabaseEngine.join(result, "mi.movie_id", "mi_idx.movie_id");
				break;
			default:
				System.out.println("Error: The corresponding execution does not exist. ");
				break;
			}
		}
		return result;
	}
}
