package datenbank.job;

import datenbank.main.DatabaseEngine;
import datenbank.main.Result;

public class Query05 extends Query {

	String query = "SELECT * "
			+ "FROM company_type AS ct, info_type AS it, movie_companies AS mc, movie_info AS mi, title AS t "
			+ "WHERE t.id = mi.movie_id "
			+ "AND t.id = mc.movie_id "
			+ "AND mc.movie_id = mi.movie_id "
			+ "AND ct.id = mc.company_type_id "
			+ "AND it.id = mi.info_type_id;";

	public Query05() {
		allPermutation("12345".toCharArray(), 0);
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
				result = DatabaseEngine.joinExample(result, "t.id", "mc.movie_id");
				break;
			case '3':
				result = DatabaseEngine.joinExample(result, "mc.movie_id", "mi.movie_id");
				break;
			case '4':
				result = DatabaseEngine.joinExample(result, "ct.id", "mc.company_type_id");
				break;
			case '5':
				result = DatabaseEngine.joinExample(result, "it.id", "mi.info_type_id");
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
				result = DatabaseEngine.join(result, "t.id", "mc.movie_id");
				break;
			case '3':
				result = DatabaseEngine.join(result, "mc.movie_id", "mi.movie_id");
				break;
			case '4':
				result = DatabaseEngine.join(result, "ct.id", "mc.company_type_id");
				break;
			case '5':
				result = DatabaseEngine.join(result, "it.id", "mi.info_type_id");
				break;
			default:
				System.out.println("Error: The corresponding execution does not exist. ");
				break;
			}
		}
		return result;
	}

}
