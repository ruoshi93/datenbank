package datenbank.job;

import datenbank.imdb.KeywordTable;
import datenbank.imdb.Link_typeTable;
import datenbank.imdb.Movie_keywordTable;
import datenbank.imdb.Movie_linkTable;
import datenbank.imdb.TitleTable;
import datenbank.main.DatabaseEngine;
import datenbank.main.Result;

public class Query32 extends Query{

	String query = "SELECT *"
			 	 + "FROM keyword AS k, link_type AS lt, movie_keyword AS mk, movie_link AS ml, title AS t, title AS t1 "
			 	 + "WHERE mk.keyword_id = k.id "
			 	 + "AND t.id = mk.movie_id "
			 	 + "AND ml.movie_id = t.id "
			 	 + "AND ml.linked_movie_id = t1.id "
			 	 + "AND lt.id = ml.link_type_id "
			 	 + "AND mk.movie_id = t.id;";

	public Query32() {
		DatabaseEngine.k = new KeywordTable();
		DatabaseEngine.lt = new Link_typeTable();
		DatabaseEngine.mk = new Movie_keywordTable();
		DatabaseEngine.ml = new Movie_linkTable();
		DatabaseEngine.t = new TitleTable();
		DatabaseEngine.t1 = new TitleTable();
		allPermutation("abcdef".toCharArray(), 0);
		execute();
	}

	@Override
	protected void numbersToCodeExample(String s) {
		Result result = new Result();
		for (char c : s.toCharArray()) {
			switch (c) {
			case 'a':
				result = DatabaseEngine.joinExample(result, "mk.keyword_id", "k.id");
				break;
			case 'b':
				result = DatabaseEngine.joinExample(result, "t.id", "mk.movie_id");
				break;
			case 'c':
				result = DatabaseEngine.joinExample(result, "ml.movie_id", "t.id");
				break;
			case 'd':
				result = DatabaseEngine.joinExample(result, "ml.linked_movie_id", "t1.id");
				break;
			case 'e':
				result = DatabaseEngine.joinExample(result, "lt.id", "ml.link_type_id");
				break;
			case 'f':
				result = DatabaseEngine.joinExample(result, "mk.movie_id", "t.id");
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
				result = DatabaseEngine.join(result, "mk.keyword_id", "k.id");
				break;
			case 'b':
				result = DatabaseEngine.join(result, "t.id", "mk.movie_id");
				break;
			case 'c':
				result = DatabaseEngine.join(result, "ml.movie_id", "t.id");
				break;
			case 'd':
				result = DatabaseEngine.join(result, "ml.linked_movie_id", "t1.id");
				break;
			case 'e':
				result = DatabaseEngine.join(result, "lt.id", "ml.link_type_id");
				break;
			case 'f':
				result = DatabaseEngine.join(result, "mk.movie_id", "t.id");
				break;
			default:
				System.out.println("Error: The corresponding execution does not exist. ");
				break;
			}
		}
		return result;
	}
}
