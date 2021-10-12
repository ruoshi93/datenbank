package datenbank.job;

import datenbank.imdb.Cast_infoTable;
import datenbank.imdb.Company_nameTable;
import datenbank.imdb.KeywordTable;
import datenbank.imdb.Movie_companiesTable;
import datenbank.imdb.Movie_keywordTable;
import datenbank.imdb.NameTable;
import datenbank.imdb.TitleTable;
import datenbank.main.DatabaseEngine;
import datenbank.main.Result;

public class Query17 extends Query {

	String query = "SELECT * "
			+ "FROM cast_info AS ci, company_name AS cn, keyword AS k, movie_companies AS mc, movie_keyword AS mk, name AS n, title AS t "
			+ "WHERE n.id = ci.person_id "
			+ "AND ci.movie_id = t.id "
			+ "AND t.id = mk.movie_id "
			+ "AND mk.keyword_id = k.id "
			+ "AND t.id = mc.movie_id "
			+ "AND mc.company_id = cn.id "
			+ "AND ci.movie_id = mc.movie_id "
			+ "AND ci.movie_id = mk.movie_id "
			+ "AND mc.movie_id = mk.movie_id;";

	public Query17() {
		DatabaseEngine.ci = new Cast_infoTable();
		DatabaseEngine.cn = new Company_nameTable();
		DatabaseEngine.k = new KeywordTable();
		DatabaseEngine.mc = new Movie_companiesTable();
		DatabaseEngine.mk = new Movie_keywordTable();
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
				result = DatabaseEngine.joinExample(result, "n.id", "ci.person_id");
				break;
			case '2':
				result = DatabaseEngine.joinExample(result, "ci.movie_id", "t.id");
				break;
			case '3':
				result = DatabaseEngine.joinExample(result, "t.id", "mk.movie_id");
				break;
			case '4':
				result = DatabaseEngine.joinExample(result, "mk.keyword_id", "k.id");
				break;
			case '5':
				result = DatabaseEngine.joinExample(result, "t.id", "mc.movie_id");
				break;
			case '6':
				result = DatabaseEngine.joinExample(result, "mc.company_id", "cn.id");
				break;
			case '7':
				result = DatabaseEngine.joinExample(result, "ci.movie_id", "mc.movie_id");
				break;
			case '8':
				result = DatabaseEngine.joinExample(result, "ci.movie_id", "mk.movie_id");
				break;
			case '9':
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
			case '1':
				result = DatabaseEngine.join(result, "n.id", "ci.person_id");
				break;
			case '2':
				result = DatabaseEngine.join(result, "ci.movie_id", "t.id");
				break;
			case '3':
				result = DatabaseEngine.join(result, "t.id", "mk.movie_id");
				break;
			case '4':
				result = DatabaseEngine.join(result, "mk.keyword_id", "k.id");
				break;
			case '5':
				result = DatabaseEngine.join(result, "t.id", "mc.movie_id");
				break;
			case '6':
				result = DatabaseEngine.join(result, "mc.company_id", "cn.id");
				break;
			case '7':
				result = DatabaseEngine.join(result, "ci.movie_id", "mc.movie_id");
				break;
			case '8':
				result = DatabaseEngine.join(result, "ci.movie_id", "mk.movie_id");
				break;
			case '9':
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
