package datenbank.job;

import datenbank.imdb.Company_nameTable;
import datenbank.imdb.Company_typeTable;
import datenbank.imdb.KeywordTable;
import datenbank.imdb.Link_typeTable;
import datenbank.imdb.Movie_companiesTable;
import datenbank.imdb.Movie_infoTable;
import datenbank.imdb.Movie_keywordTable;
import datenbank.imdb.Movie_linkTable;
import datenbank.imdb.TitleTable;
import datenbank.main.DatabaseEngine;
import datenbank.main.Result;

public class Query21 extends Query{

	String query = "SELECT * "
				 + "FROM company_name AS cn, company_type AS ct, keyword AS k, link_type AS lt, movie_companies AS mc, movie_info AS mi, movie_keyword AS mk, movie_link AS ml, title AS t "
				 + "WHERE lt.id = ml.link_type_id "
				 + "AND ml.movie_id = t.id "
				 + "AND t.id = mk.movie_id "
				 + "AND mk.keyword_id = k.id "
				 + "AND t.id = mc.movie_id "
				 + "AND mc.company_type_id = ct.id "
				 + "AND mc.company_id = cn.id "
				 + "AND mi.movie_id = t.id "
				 + "AND ml.movie_id = mk.movie_id "
				 + "AND ml.movie_id = mc.movie_id "
				 + "AND mk.movie_id = mc.movie_id "
				 + "AND ml.movie_id = mi.movie_id "
				 + "AND mk.movie_id = mi.movie_id "
				 + "AND mc.movie_id = mi.movie_id;";

	public Query21() {
		DatabaseEngine.cn = new Company_nameTable();
		DatabaseEngine.ct = new Company_typeTable();
		DatabaseEngine.k = new KeywordTable();
		DatabaseEngine.lt = new Link_typeTable();
		DatabaseEngine.mc = new Movie_companiesTable();
		DatabaseEngine.mi = new Movie_infoTable();
		DatabaseEngine.mk = new Movie_keywordTable();
		DatabaseEngine.ml = new Movie_linkTable();
		DatabaseEngine.t = new TitleTable();
		allPermutation("abcdefghijklmn".toCharArray(), 0);
		execute();
	}

	@Override
	protected void numbersToCodeExample(String s) {
		Result result = new Result();
		for (char c : s.toCharArray()) {
			switch (c) {
			case 'a':
				result = DatabaseEngine.joinExample(result, "lt.id", "ml.link_type_id");
				break;
			case 'b':
				result = DatabaseEngine.joinExample(result, "ml.movie_id", "t.id");
				break;
			case 'c':
				result = DatabaseEngine.joinExample(result, "t.id", "mk.movie_id");
				break;
			case 'd':
				result = DatabaseEngine.joinExample(result, "mk.keyword_id", "k.id");
				break;
			case 'e':
				result = DatabaseEngine.joinExample(result, "t.id", "mc.movie_id");
				break;
			case 'f':
				result = DatabaseEngine.joinExample(result, "mc.company_type_id", "ct.id");
				break;
			case 'g':
				result = DatabaseEngine.joinExample(result, "mc.company_id", "cn.id");
				break;
			case 'h':
				result = DatabaseEngine.joinExample(result, "mi.movie_id", "t.id");
				break;
			case 'i':
				result = DatabaseEngine.joinExample(result, "ml.movie_id", "mk.movie_id");
				break;
			case 'j':
				result = DatabaseEngine.joinExample(result, "ml.movie_id", "mc.movie_id");
				break;
			case 'k':
				result = DatabaseEngine.joinExample(result, "mk.movie_id", "mc.movie_id");
				break;
			case 'l':
				result = DatabaseEngine.joinExample(result, "ml.movie_id", "mi.movie_id");
				break;
			case 'm':
				result = DatabaseEngine.joinExample(result, "mk.movie_id", "mi.movie_id");
				break;
			case 'n':
				result = DatabaseEngine.joinExample(result, "mc.movie_id", "mi.movie_id");
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
				result = DatabaseEngine.join(result, "lt.id", "ml.link_type_id");
				break;
			case 'b':
				result = DatabaseEngine.join(result, "ml.movie_id", "t.id");
				break;
			case 'c':
				result = DatabaseEngine.join(result, "t.id", "mk.movie_id");
				break;
			case 'd':
				result = DatabaseEngine.join(result, "mk.keyword_id", "k.id");
				break;
			case 'e':
				result = DatabaseEngine.join(result, "t.id", "mc.movie_id");
				break;
			case 'f':
				result = DatabaseEngine.join(result, "mc.company_type_id", "ct.id");
				break;
			case 'g':
				result = DatabaseEngine.join(result, "mc.company_id", "cn.id");
				break;
			case 'h':
				result = DatabaseEngine.join(result, "mi.movie_id", "t.id");
				break;
			case 'i':
				result = DatabaseEngine.join(result, "ml.movie_id", "mk.movie_id");
				break;
			case 'j':
				result = DatabaseEngine.join(result, "ml.movie_id", "mc.movie_id");
				break;
			case 'k':
				result = DatabaseEngine.join(result, "mk.movie_id", "mc.movie_id");
				break;
			case 'l':
				result = DatabaseEngine.join(result, "ml.movie_id", "mi.movie_id");
				break;
			case 'm':
				result = DatabaseEngine.join(result, "mk.movie_id", "mi.movie_id");
				break;
			case 'n':
				result = DatabaseEngine.join(result, "mc.movie_id", "mi.movie_id");
				break;
			default:
				System.out.println("Error: The corresponding execution does not exist. ");
				break;
			}
		}
		return result;
	}
}
