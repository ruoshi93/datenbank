package datenbank.job;

import datenbank.main.DatabaseEngine;
import datenbank.main.Result;

public class Query16 extends Query{

	String query = "SELECT * "
			+ "FROM aka_name AS an, cast_info AS ci, company_name AS cn, keyword AS k, movie_companies AS mc, movie_keyword AS mk, name AS n, title AS t "
			+ "WHERE an.person_id = n.id "
			+ "AND n.id = ci.person_id "
			+ "AND ci.movie_id = t.id "
			+ "AND t.id = mk.movie_id "
			+ "AND mk.keyword_id = k.id "
			+ "AND t.id = mc.movie_id "
			+ "AND mc.company_id = cn.id "
			+ "AND an.person_id = ci.person_id "
			+ "AND ci.movie_id = mc.movie_id "
			+ "AND ci.movie_id = mk.movie_id "
			+ "AND mc.movie_id = mk.movie_id;";

	public Query16() {
		allPermutation("abcdefghijk".toCharArray(), 0);
	}

	@Override
	protected void numbersToCodeExample(String s) {
		Result result = new Result();
		for (char c : s.toCharArray()) {
			switch (c) {
			case 'a':
				result = DatabaseEngine.joinExample(result, "an.person_id", "n.id");
				break;
			case 'b':
				result = DatabaseEngine.joinExample(result, "n.id", "ci.person_id");
				break;
			case 'c':
				result = DatabaseEngine.joinExample(result, "ci.movie_id", "t.id");
				break;
			case 'd':
				result = DatabaseEngine.joinExample(result, "t.id", "mk.movie_id");
				break;
			case 'e':
				result = DatabaseEngine.joinExample(result, "mk.keyword_id", "k.id");
				break;
			case 'f':
				result = DatabaseEngine.joinExample(result, "t.id", "mc.movie_id");
				break;
			case 'g':
				result = DatabaseEngine.joinExample(result, "mc.company_id", "cn.id");
				break;
			case 'h':
				result = DatabaseEngine.joinExample(result, "an.person_id", "ci.person_id");
				break;
			case 'i':
				result = DatabaseEngine.joinExample(result, "ci.movie_id", "mc.movie_id");
				break;
			case 'j':
				result = DatabaseEngine.joinExample(result, "ci.movie_id", "mk.movie_id");
				break;
			case 'k':
				result = DatabaseEngine.joinExample(result, "mc.movie_id", "mk.movie_id");
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
				result = DatabaseEngine.join(result, "an.person_id", "n.id");
				break;
			case 'b':
				result = DatabaseEngine.join(result, "n.id", "ci.person_id");
				break;
			case 'c':
				result = DatabaseEngine.join(result, "ci.movie_id", "t.id");
				break;
			case 'd':
				result = DatabaseEngine.join(result, "t.id", "mk.movie_id");
				break;
			case 'e':
				result = DatabaseEngine.join(result, "mk.keyword_id", "k.id");
				break;
			case 'f':
				result = DatabaseEngine.join(result, "t.id", "mc.movie_id");
				break;
			case 'g':
				result = DatabaseEngine.join(result, "mc.company_id", "cn.id");
				break;
			case 'h':
				result = DatabaseEngine.join(result, "an.person_id", "ci.person_id");
				break;
			case 'i':
				result = DatabaseEngine.join(result, "ci.movie_id", "mc.movie_id");
				break;
			case 'j':
				result = DatabaseEngine.join(result, "ci.movie_id", "mk.movie_id");
				break;
			case 'k':
				result = DatabaseEngine.join(result, "mc.movie_id", "mk.movie_id");
				break;
			default:
				System.out.println("Error: The corresponding execution does not exist. ");
				break;
			}
		}
		return result;
	}
}
