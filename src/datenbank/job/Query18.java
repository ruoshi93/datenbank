package datenbank.job;

import datenbank.imdb.Cast_infoTable;
import datenbank.imdb.Info_typeTable;
import datenbank.imdb.Movie_infoTable;
import datenbank.imdb.Movie_info_idxTable;
import datenbank.imdb.NameTable;
import datenbank.imdb.TitleTable;
import datenbank.main.DatabaseEngine;
import datenbank.main.Result;

public class Query18 extends Query {

	String query = "SELECT * "
			+ "FROM cast_info AS ci, info_type AS it1, info_type AS it2, movie_info AS mi, movie_info_idx AS mi_idx, name AS n, title AS t "
			+ "WHERE t.id = mi.movie_id "
			+ "AND t.id = mi_idx.movie_id "
			+ "AND t.id = ci.movie_id "
			+ "AND ci.movie_id = mi.movie_id "
			+ "AND ci.movie_id = mi_idx.movie_id "
			+ "AND mi.movie_id = mi_idx.movie_id "
			+ "AND n.id = ci.person_id "
			+ "AND it1.id = mi.info_type_id "
			+ "AND it2.id = mi_idx.info_type_id;";

	public Query18() {
		DatabaseEngine.ci = new Cast_infoTable();
		DatabaseEngine.it1 = new Info_typeTable();
		DatabaseEngine.it2 = new Info_typeTable();
		DatabaseEngine.mi_idx = new Movie_info_idxTable();
		DatabaseEngine.mi = new Movie_infoTable();
		DatabaseEngine.n = new NameTable();
		DatabaseEngine.t = new TitleTable();
		allPermutation("123456789".toCharArray(), 0);
	}

	@Override
	protected void numbersToCodeExample(String s) {
		Result result = new Result();
		for (char c : s.toCharArray()) {
			switch (c) {
			case '1':
				result = DatabaseEngine.joinExample(result, "t.id", "mi.movie_id");
				break;
			case '2':
				result = DatabaseEngine.joinExample(result, "t.id", "mi_idx.movie_id");
				break;
			case '3':
				result = DatabaseEngine.joinExample(result, "t.id", "ci.movie_id");
				break;
			case '4':
				result = DatabaseEngine.joinExample(result, "ci.movie_id", "mi.movie_id");
				break;
			case '5':
				result = DatabaseEngine.joinExample(result, "ci.movie_id", "mi_idx.movie_id");
				break;
			case '6':
				result = DatabaseEngine.joinExample(result, "mi.movie_id", "mi_idx.movie_id");
				break;
			case '7':
				result = DatabaseEngine.joinExample(result, "n.id", "ci.person_id");
				break;
			case '8':
				result = DatabaseEngine.joinExample(result, "it1.id", "mi.info_type_id");
				break;
			case '9':
				result = DatabaseEngine.joinExample(result, "it2.id", "mi_idx.info_type_id");
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
			case '1':
				result = DatabaseEngine.join(result, "t.id", "mi.movie_id");
				break;
			case '2':
				result = DatabaseEngine.join(result, "t.id", "mi_idx.movie_id");
				break;
			case '3':
				result = DatabaseEngine.join(result, "t.id", "ci.movie_id");
				break;
			case '4':
				result = DatabaseEngine.join(result, "ci.movie_id", "mi.movie_id");
				break;
			case '5':
				result = DatabaseEngine.join(result, "ci.movie_id", "mi_idx.movie_id");
				break;
			case '6':
				result = DatabaseEngine.join(result, "mi.movie_id", "mi_idx.movie_id");
				break;
			case '7':
				result = DatabaseEngine.join(result, "n.id", "ci.person_id");
				break;
			case '8':
				result = DatabaseEngine.join(result, "it1.id", "mi.info_type_id");
				break;
			case '9':
				result = DatabaseEngine.join(result, "it2.id", "mi_idx.info_type_id");
				break;
			default:
				System.out.println("Error: The corresponding execution does not exist. ");
				break;
			}
		}
		return result;
	}
}
