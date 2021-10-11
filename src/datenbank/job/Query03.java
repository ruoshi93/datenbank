package datenbank.job;

import datenbank.main.DatabaseEngine;
import datenbank.main.Result;

public class Query03 extends Query {

	String query = "SELECT *"
				 + "FROM keyword AS k, movie_info AS mi, movie_keyword AS mk, title AS t "
				 + "WHERE t.id = mi.movie_id "
				 + "AND t.id = mk.movie_id "
				 + "AND mk.movie_id = mi.movie_id "
				 + "AND k.id = mk.keyword_id;";

	public Query03() {
		allPermutation("1234".toCharArray(), 0);
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
				result = DatabaseEngine.joinExample(result, "t.id", "mk.movie_id");
				break;
			case '3':
				result = DatabaseEngine.joinExample(result, "mk.movie_id", "mi.movie_id");
				break;
			case '4':
				result = DatabaseEngine.joinExample(result, "k.id", "mk.keyword_id");
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
				result = DatabaseEngine.join(result, "t.id", "mk.movie_id");
				break;
			case '3':
				result = DatabaseEngine.join(result, "mk.movie_id", "mi.movie_id");
				break;
			case '4':
				result = DatabaseEngine.join(result, "k.id", "mk.keyword_id");
				break;
			default:
				System.out.println("Error: The corresponding execution does not exist. ");
				break;
			}
		}
		return result;
	}

}
