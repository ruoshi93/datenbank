package datenbank.job;

import datenbank.imdb.Info_typeTable;
import datenbank.imdb.KeywordTable;
import datenbank.imdb.Kind_typeTable;
import datenbank.imdb.Movie_infoTable;
import datenbank.imdb.Movie_info_idxTable;
import datenbank.imdb.Movie_keywordTable;
import datenbank.imdb.TitleTable;
import datenbank.main.DatabaseEngine;
import datenbank.main.Result;

public class Query14 extends Query {

	String query = "SELECT * "
				 + "FROM info_type AS it1, info_type AS it2, keyword AS k, kind_type AS kt, movie_info AS mi, movie_info_idx AS mi_idx, movie_keyword AS mk, title AS t "
				 + "WHERE kt.id = t.kind_id " 
				 + "AND t.id = mi.movie_id " 
				 + "AND t.id = mk.movie_id "
				 + "AND t.id = mi_idx.movie_id " 
				 + "AND mk.movie_id = mi.movie_id " 
				 + "AND mk.movie_id = mi_idx.movie_id "
				 + "AND mi.movie_id = mi_idx.movie_id " 
				 + "AND k.id = mk.keyword_id " 
				 + "AND it1.id = mi.info_type_id "
				 + "AND it2.id = mi_idx.info_type_id;";

	public Query14() {
		DatabaseEngine.it1 = new Info_typeTable();
		DatabaseEngine.it2 = new Info_typeTable();
		DatabaseEngine.k = new KeywordTable();
		DatabaseEngine.kt = new Kind_typeTable();
		DatabaseEngine.mi_idx = new Movie_info_idxTable();
		DatabaseEngine.mi = new Movie_infoTable();
		DatabaseEngine.mk = new Movie_keywordTable();
		DatabaseEngine.t = new TitleTable();
		allPermutation("abcdefghij".toCharArray(), 0);
	}

	@Override
	protected void numbersToCodeExample(String s) {
		Result result = new Result();
		for (char c : s.toCharArray()) {
			switch (c) {
			case 'a':
				result = DatabaseEngine.joinExample(result, "kt.id", "t.kind_id");
				break;
			case 'b':
				result = DatabaseEngine.joinExample(result, "t.id", "mi.movie_id");
				break;
			case 'c':
				result = DatabaseEngine.joinExample(result, "t.id", "mk.movie_id");
				break;
			case 'd':
				result = DatabaseEngine.joinExample(result, "t.id", "mi_idx.movie_id");
				break;
			case 'e':
				result = DatabaseEngine.joinExample(result, "mk.movie_id", "mi.movie_id");
				break;
			case 'f':
				result = DatabaseEngine.joinExample(result, "mk.movie_id", "mi_idx.movie_id");
				break;
			case 'g':
				result = DatabaseEngine.joinExample(result, "mi.movie_id", "mi_idx.movie_id");
				break;
			case 'h':
				result = DatabaseEngine.joinExample(result, "k.id", "mk.keyword_id");
				break;
			case 'i':
				result = DatabaseEngine.joinExample(result, "it1.id", "mi.info_type_id");
				break;
			case 'j':
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
			case 'a':
				result = DatabaseEngine.join(result, "kt.id", "t.kind_id");
				break;
			case 'b':
				result = DatabaseEngine.join(result, "t.id", "mi.movie_id");
				break;
			case 'c':
				result = DatabaseEngine.join(result, "t.id", "mk.movie_id");
				break;
			case 'd':
				result = DatabaseEngine.join(result, "t.id", "mi_idx.movie_id");
				break;
			case 'e':
				result = DatabaseEngine.join(result, "mk.movie_id", "mi.movie_id");
				break;
			case 'f':
				result = DatabaseEngine.join(result, "mk.movie_id", "mi_idx.movie_id");
				break;
			case 'g':
				result = DatabaseEngine.join(result, "mi.movie_id", "mi_idx.movie_id");
				break;
			case 'h':
				result = DatabaseEngine.join(result, "k.id", "mk.keyword_id");
				break;
			case 'i':
				result = DatabaseEngine.join(result, "it1.id", "mi.info_type_id");
				break;
			case 'j':
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
