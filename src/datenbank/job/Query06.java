package datenbank.job;

import datenbank.main.DatabaseEngine;
import datenbank.main.Result;

public class Query06 extends Query {

	String query = "SELECT * "
				 + "FROM cast_info AS ci, keyword AS k, movie_keyword AS mk, name AS n, title AS t "
				 + "WHERE k.id = mk.keyword_id "
				 + "AND t.id = mk.movie_id "
				 + "AND t.id = ci.movie_id "
				 + "AND ci.movie_id = mk.movie_id "
				 + "AND n.id = ci.person_id;";

	public Query06() {
		allPermutation("12345".toCharArray(), 0);
	}

	@Override
	protected void numbersToCodeExample(String s) {
		Result result = new Result();
		for (char c : s.toCharArray()) {
			switch (c) {
			case '1':
				result = DatabaseEngine.joinExample(result, "k.id", "mk.keyword_id");
				break;
			case '2':
				result = DatabaseEngine.joinExample(result, "t.id", "mk.movie_id");
				break;
			case '3':
				result = DatabaseEngine.joinExample(result, "t.id", "ci.movie_id");
				break;
			case '4':
				result = DatabaseEngine.joinExample(result, "ci.movie_id" , "mk.movie_id");
				break;
			case '5':
				result = DatabaseEngine.joinExample(result, "n.id", "ci.person_id");
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
				result = DatabaseEngine.join(result, "k.id", "mk.keyword_id");
				break;
			case '2':
				result = DatabaseEngine.join(result, "t.id", "mk.movie_id");
				break;
			case '3':
				result = DatabaseEngine.join(result, "t.id", "ci.movie_id");
				break;
			case '4':
				result = DatabaseEngine.join(result, "ci.movie_id" , "mk.movie_id");
				break;
			case '5':
				result = DatabaseEngine.join(result, "n.id", "ci.person_id");
				break;
			default:
				System.out.println("Error: The corresponding execution does not exist. ");
				break;
			}
		}
		return result;
	}

}
