package datenbank.job;

import datenbank.imdb.Aka_titleTable;
import datenbank.imdb.Company_nameTable;
import datenbank.imdb.Company_typeTable;
import datenbank.imdb.Info_typeTable;
import datenbank.imdb.KeywordTable;
import datenbank.imdb.Movie_companiesTable;
import datenbank.imdb.Movie_infoTable;
import datenbank.imdb.Movie_keywordTable;
import datenbank.imdb.TitleTable;
import datenbank.main.DatabaseEngine;
import datenbank.main.Result;

public class Query15 extends Query {

	String query = "SELECT * "
				 + "FROM aka_title AS at, company_name AS cn, company_type AS ct, info_type AS it, keyword AS k, movie_companies AS mc, movie_info AS mi, movie_keyword AS mk, title AS t "
				 + "WHERE t.id = at.movie_id "
				 + "AND t.id = mi.movie_id "
				 + "AND t.id = mk.movie_id "
				 + "AND t.id = mc.movie_id "
				 + "AND mk.movie_id = mi.movie_id "
				 + "AND mk.movie_id = mc.movie_id "
				 + "AND mk.movie_id = at.movie_id "
				 + "AND mi.movie_id = mc.movie_id "
				 + "AND mi.movie_id = at.movie_id "
				 + "AND mc.movie_id = at.movie_id "
				 + "AND k.id = mk.keyword_id "
				 + "AND it.id = mi.info_type_id "
				 + "AND cn.id = mc.company_id "
				 + "AND ct.id = mc.company_type_id;";

	public Query15() {
		DatabaseEngine.at = new Aka_titleTable();
		DatabaseEngine.cn = new Company_nameTable();
		DatabaseEngine.ct = new Company_typeTable();
		DatabaseEngine.it = new Info_typeTable();
		DatabaseEngine.k = new KeywordTable();
		DatabaseEngine.mc = new Movie_companiesTable();
		DatabaseEngine.mi = new Movie_infoTable();
		DatabaseEngine.mk = new Movie_keywordTable();
		DatabaseEngine.t = new TitleTable();
		allPermutation("abcdefghijklmn".toCharArray(), 0);
	}

	@Override
	protected void numbersToCodeExample(String s) {
		Result result = new Result();
		for (char c : s.toCharArray()) {
			switch (c) {
			case 'a':
				result = DatabaseEngine.joinExample(result, "t.id", "at.movie_id");
				break;
			case 'b':
				result = DatabaseEngine.joinExample(result, "t.id", "mi.movie_id");
				break;
			case 'c':
				result = DatabaseEngine.joinExample(result, "t.id", "mk.movie_id");
				break;
			case 'd':
				result = DatabaseEngine.joinExample(result, "t.id", "mc.movie_id");
				break;
			case 'e':
				result = DatabaseEngine.joinExample(result, "mk.movie_id", "mi.movie_id");
				break;
			case 'f':
				result = DatabaseEngine.joinExample(result, "mk.movie_id", "mc.movie_id");
				break;
			case 'g':
				result = DatabaseEngine.joinExample(result, "mk.movie_id", "at.movie_id");
				break;
			case 'h':
				result = DatabaseEngine.joinExample(result, "mi.movie_id", "mc.movie_id");
				break;
			case 'i':
				result = DatabaseEngine.joinExample(result, "mi.movie_id", "at.movie_id");
				break;
			case 'j':
				result = DatabaseEngine.joinExample(result, "mc.movie_id", "at.movie_id");
				break;
			case 'k':
				result = DatabaseEngine.joinExample(result, "k.id", "mk.keyword_id");
				break;
			case 'l':
				result = DatabaseEngine.joinExample(result, "it.id", "mi.info_type_id");
				break;
			case 'm':
				result = DatabaseEngine.joinExample(result, "cn.id", "mc.company_id");
				break;
			case 'n':
				result = DatabaseEngine.joinExample(result, "ct.id", "mc.company_type_id");
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
				result = DatabaseEngine.join(result, "t.id", "at.movie_id");
				break;
			case 'b':
				result = DatabaseEngine.join(result, "t.id", "mi.movie_id");
				break;
			case 'c':
				result = DatabaseEngine.join(result, "t.id", "mk.movie_id");
				break;
			case 'd':
				result = DatabaseEngine.join(result, "t.id", "mc.movie_id");
				break;
			case 'e':
				result = DatabaseEngine.join(result, "mk.movie_id", "mi.movie_id");
				break;
			case 'f':
				result = DatabaseEngine.join(result, "mk.movie_id", "mc.movie_id");
				break;
			case 'g':
				result = DatabaseEngine.join(result, "mk.movie_id", "at.movie_id");
				break;
			case 'h':
				result = DatabaseEngine.join(result, "mi.movie_id", "mc.movie_id");
				break;
			case 'i':
				result = DatabaseEngine.join(result, "mi.movie_id", "at.movie_id");
				break;
			case 'j':
				result = DatabaseEngine.join(result, "mc.movie_id", "at.movie_id");
				break;
			case 'k':
				result = DatabaseEngine.join(result, "k.id", "mk.keyword_id");
				break;
			case 'l':
				result = DatabaseEngine.join(result, "it.id", "mi.info_type_id");
				break;
			case 'm':
				result = DatabaseEngine.join(result, "cn.id", "mc.company_id");
				break;
			case 'n':
				result = DatabaseEngine.join(result, "ct.id", "mc.company_type_id");
				break;
			default:
				System.out.println("Error: The corresponding execution does not exist. ");
				break;
			}
		}
		return result;
	}

}
