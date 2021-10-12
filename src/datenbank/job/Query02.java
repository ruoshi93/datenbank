package datenbank.job;

import datenbank.imdb.Company_nameTable;
import datenbank.imdb.KeywordTable;
import datenbank.imdb.Movie_companiesTable;
import datenbank.imdb.Movie_keywordTable;
import datenbank.imdb.TitleTable;
import datenbank.main.*;

public class Query02 extends Query {

	String query = "SELECT * "
				 + "FROM company_name AS cn, keyword AS k, movie_companies AS mc, movie_keyword AS mk, title AS t "
				 + "WHERE cn.id = mc.company_id "
				 + "AND mc.movie_id = t.id "
				 + "AND t.id = mk.movie_id "
				 + "AND mk.keyword_id = k.id "
				 + "AND mc.movie_id = mk.movie_id;";

	public Query02() {
		DatabaseEngine.cn = new Company_nameTable();
		DatabaseEngine.k = new KeywordTable();
		DatabaseEngine.mc = new Movie_companiesTable();
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
