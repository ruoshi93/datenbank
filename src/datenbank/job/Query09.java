package datenbank.job;

import datenbank.main.DatabaseEngine;
import datenbank.main.Result;

public class Query09 extends Query {

	String query = "SELECT * "
				 + "FROM aka_name AS an, char_name AS chn, cast_info AS ci, company_name AS cn, movie_companies AS mc, name AS n, role_type AS rt, title AS t "
				 + "WHERE ci.movie_id = t.id "
				 + "AND t.id = mc.movie_id "
				 + "AND ci.movie_id = mc.movie_id "
				 + "AND mc.company_id = cn.id "
				 + "AND ci.role_id = rt.id "
				 + "AND n.id = ci.person_id "
				 + "AND chn.id = ci.person_role_id "
				 + "AND an.person_id = n.id "
				 + "AND an.person_id = ci.person_id;";

	public Query09() {
		allPermutation("123456789".toCharArray(), 0);
	}

	@Override
	protected void numbersToCodeExample(String s) {
		Result result = new Result();
		for (char c : s.toCharArray()) {
			switch (c) {
			case '1':
				result = DatabaseEngine.joinExample(result, "ci.movie_id", "t.id");
				break;
			case '2':
				result = DatabaseEngine.joinExample(result, "t.id", "mc.movie_id");
				break;
			case '3':
				result = DatabaseEngine.joinExample(result, "ci.movie_id", "mc.movie_id");
				break;
			case '4':
				result = DatabaseEngine.joinExample(result, "mc.company_id", "cn.id");
				break;
			case '5':
				result = DatabaseEngine.joinExample(result, "ci.role_id", "rt.id");
				break;
			case '6':
				result = DatabaseEngine.joinExample(result, "n.id", "ci.person_id");
				break;
			case '7':
				result = DatabaseEngine.joinExample(result, "chn.id", "ci.person_role_id");
				break;
			case '8':
				result = DatabaseEngine.joinExample(result, "an.person_id", "n.id");
				break;
			case '9':
				result = DatabaseEngine.joinExample(result, "an.person_id", "ci.person_id");
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
				result = DatabaseEngine.join(result, "ci.movie_id", "t.id");
				break;
			case '2':
				result = DatabaseEngine.join(result, "t.id", "mc.movie_id");
				break;
			case '3':
				result = DatabaseEngine.join(result, "ci.movie_id", "mc.movie_id");
				break;
			case '4':
				result = DatabaseEngine.join(result, "mc.company_id", "cn.id");
				break;
			case '5':
				result = DatabaseEngine.join(result, "ci.role_id", "rt.id");
				break;
			case '6':
				result = DatabaseEngine.join(result, "n.id", "ci.person_id");
				break;
			case '7':
				result = DatabaseEngine.join(result, "chn.id", "ci.person_role_id");
				break;
			case '8':
				result = DatabaseEngine.join(result, "an.person_id", "n.id");
				break;
			case '9':
				result = DatabaseEngine.join(result, "an.person_id", "ci.person_id");
				break;
			default:
				System.out.println("Error: The corresponding execution does not exist. ");
				break;
			}
		}
		return result;
	}

}
