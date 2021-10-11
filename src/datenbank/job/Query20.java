package datenbank.job;

import datenbank.main.DatabaseEngine;
import datenbank.main.Result;

public class Query20 extends Query {

	String query = "SELECT * "
				 + "FROM complete_cast AS cc, comp_cast_type AS cct1, comp_cast_type AS cct2, char_name AS chn, cast_info AS ci, keyword AS k, kind_type AS kt, movie_keyword AS mk, name AS n, title AS t "
				 + "WHERE kt.id = t.kind_id "
				 + "AND t.id = mk.movie_id "
				 + "AND t.id = ci.movie_id "
				 + "AND t.id = cc.movie_id "
				 + "AND mk.movie_id = ci.movie_id "
				 + "AND mk.movie_id = cc.movie_id "
				 + "AND ci.movie_id = cc.movie_id "
				 + "AND chn.id = ci.person_role_id "
				 + "AND n.id = ci.person_id "
				 + "AND k.id = mk.keyword_id "
				 + "AND cct1.id = cc.subject_id "
				 + "AND cct2.id = cc.status_id;";

	public Query20() {
		allPermutation("abcdefghijk".toCharArray(), 0);
	}

	@Override
	protected void numbersToCodeExample(String s) {
		Result result = new Result();
		for (char c : s.toCharArray()) {
			switch (c) {
			case '1':
				result = DatabaseEngine.joinExample(result, "cn.id", "mc.company_id");
				break;
			case '2':
				result = DatabaseEngine.joinExample(result, "mc.movie_id", "t.id");
				break;
			case '3':
				result = DatabaseEngine.joinExample(result, "mk.movie_id", "t.id");
				break;
			case '4':
				result = DatabaseEngine.joinExample(result, "mc.movie_id", "mk.movie_id");
				break;
			case '5':
				result = DatabaseEngine.joinExample(result, "mk.keyword_id", "k.id");
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
				result = DatabaseEngine.join(result, "cn.id", "mc.company_id");
				break;
			case '2':
				result = DatabaseEngine.join(result, "mc.movie_id", "t.id");
				break;
			case '3':
				result = DatabaseEngine.join(result, "mk.movie_id", "t.id");
				break;
			case '4':
				result = DatabaseEngine.join(result, "mc.movie_id", "mk.movie_id");
				break;
			case '5':
				result = DatabaseEngine.join(result, "mk.keyword_id", "k.id");
				break;
			default:
				System.out.println("Error: The corresponding execution does not exist. ");
				break;
			}
		}
		return result;
	}

}
