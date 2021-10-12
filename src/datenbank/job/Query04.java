package datenbank.job;

import datenbank.imdb.Info_typeTable;
import datenbank.imdb.KeywordTable;
import datenbank.imdb.Movie_info_idxTable;
import datenbank.imdb.Movie_keywordTable;
import datenbank.imdb.TitleTable;
import datenbank.main.DatabaseEngine;
import datenbank.main.Result;

public class Query04 extends Query {

	String query = "SELECT * "
				 + "FROM info_type AS it, keyword AS k, movie_info_idx AS mi_idx, movie_keyword AS mk, title AS t "
				 + "WHERE t.id = mi_idx.movie_id "
				 + "AND t.id = mk.movie_id "
				 + "AND mk.movie_id = mi_idx.movie_id "
				 + "AND k.id = mk.keyword_id "
				 + "AND it.id = mi_idx.info_type_id;";

	public Query04() {
		DatabaseEngine.it = new Info_typeTable();
		DatabaseEngine.k = new KeywordTable();
		DatabaseEngine.mi_idx = new Movie_info_idxTable();
		DatabaseEngine.mk = new Movie_keywordTable();
		DatabaseEngine.t = new TitleTable();
		allPermutation("12345".toCharArray(), 0);
	}

	@Override
	protected void numbersToCodeExample(String s) {
		Result result = new Result();
		for (char c : s.toCharArray()) {
			switch (c) {
			case '1':
				result = DatabaseEngine.joinExample(result, "t.id", "mi_idx.movie_id");
				break;
			case '2':
				result = DatabaseEngine.joinExample(result, "t.id", "mk.movie_id");
				break;
			case '3':
				result = DatabaseEngine.joinExample(result, "mk.movie_id", "mi_idx.movie_id");
				break;
			case '4':
				result = DatabaseEngine.joinExample(result, "k.id", "mk.keyword_id");
				break;
			case '5':
				result = DatabaseEngine.joinExample(result, "it.id", "mi_idx.info_type_id");
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
				result = DatabaseEngine.join(result, "t.id", "mi_idx.movie_id");
				break;
			case '2':
				result = DatabaseEngine.join(result, "t.id", "mk.movie_id");
				break;
			case '3':
				result = DatabaseEngine.join(result, "mk.movie_id", "mi_idx.movie_id");
				break;
			case '4':
				result = DatabaseEngine.join(result, "k.id", "mk.keyword_id");
				break;
			case '5':
				result = DatabaseEngine.join(result, "it.id", "mi_idx.info_type_id");
				break;
			default:
				System.out.println("Error: The corresponding execution does not exist. ");
				break;
			}
		}
		return result;
	}

}
