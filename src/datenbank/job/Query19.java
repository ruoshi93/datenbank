package datenbank.job;

import datenbank.imdb.Aka_nameTable;
import datenbank.imdb.Cast_infoTable;
import datenbank.imdb.Char_nameTable;
import datenbank.imdb.Company_nameTable;
import datenbank.imdb.Info_typeTable;
import datenbank.imdb.Movie_companiesTable;
import datenbank.imdb.Movie_infoTable;
import datenbank.imdb.NameTable;
import datenbank.imdb.Role_typeTable;
import datenbank.imdb.TitleTable;
import datenbank.main.DatabaseEngine;
import datenbank.main.Result;

public class Query19 extends Query {

	String query = "SELECT * "
				 + "FROM aka_name AS an, char_name AS chn, cast_info AS ci, company_name AS cn, info_type AS it, movie_companies AS mc, movie_info AS mi, name AS n, role_type AS rt, title AS t "
				 + "WHERE t.id = mi.movie_id "
				 + "AND t.id = mc.movie_id "
				 + "AND t.id = ci.movie_id "
				 + "AND mc.movie_id = ci.movie_id "
				 + "AND mc.movie_id = mi.movie_id "
				 + "AND mi.movie_id = ci.movie_id "
				 + "AND cn.id = mc.company_id "
				 + "AND it.id = mi.info_type_id "
				 + "AND n.id = ci.person_id "
				 + "AND rt.id = ci.role_id "
				 + "AND n.id = an.person_id "
				 + "AND ci.person_id = an.person_id "
				 + "AND chn.id = ci.person_role_id;";

	public Query19() {
		DatabaseEngine.an = new Aka_nameTable();
		DatabaseEngine.ci = new Cast_infoTable();
		DatabaseEngine.chn = new Char_nameTable();
		DatabaseEngine.cn = new Company_nameTable();
		DatabaseEngine.it = new Info_typeTable();
		DatabaseEngine.mc = new Movie_companiesTable();
		DatabaseEngine.mi = new Movie_infoTable();
		DatabaseEngine.n = new NameTable();
		DatabaseEngine.rt = new Role_typeTable();
		DatabaseEngine.t = new TitleTable();
		allPermutation("abcdefghijklm".toCharArray(), 0);
		execute();
	}

	@Override
	protected void numbersToCodeExample(String s) {
		Result result = new Result();
		for (char c : s.toCharArray()) {
			switch (c) {
			case 'a':
				result = DatabaseEngine.joinExample(result, "t.id", "mi.movie_id");
				break;
			case 'b':
				result = DatabaseEngine.joinExample(result, "t.id", "mc.movie_id");
				break;
			case 'c':
				result = DatabaseEngine.joinExample(result, "t.id", "ci.movie_id");
				break;
			case 'd':
				result = DatabaseEngine.joinExample(result, "mc.movie_id", "ci.movie_id");
				break;
			case 'e':
				result = DatabaseEngine.joinExample(result, "mc.movie_id", "mi.movie_id");
				break;
			case 'f':
				result = DatabaseEngine.joinExample(result, "mi.movie_id", "ci.movie_id");
				break;
			case 'g':
				result = DatabaseEngine.joinExample(result, "cn.id", "mc.company_id");
				break;
			case 'h':
				result = DatabaseEngine.joinExample(result, "it.id", "mi.info_type_id");
				break;
			case 'i':
				result = DatabaseEngine.joinExample(result, "n.id", "ci.person_id");
				break;
			case 'j':
				result = DatabaseEngine.joinExample(result, "rt.id", "ci.role_id");
				break;
			case 'k':
				result = DatabaseEngine.joinExample(result, "n.id", "an.person_id");
				break;
			case 'l':
				result = DatabaseEngine.joinExample(result, "ci.person_id", "an.person_id");
				break;
			case 'm':
				result = DatabaseEngine.joinExample(result, "chn.id", "ci.person_role_id");
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
				result = DatabaseEngine.join(result, "t.id", "mi.movie_id");
				break;
			case 'b':
				result = DatabaseEngine.join(result, "t.id", "mc.movie_id");
				break;
			case 'c':
				result = DatabaseEngine.join(result, "t.id", "ci.movie_id");
				break;
			case 'd':
				result = DatabaseEngine.join(result, "mc.movie_id", "ci.movie_id");
				break;
			case 'e':
				result = DatabaseEngine.join(result, "mc.movie_id", "mi.movie_id");
				break;
			case 'f':
				result = DatabaseEngine.join(result, "mi.movie_id", "ci.movie_id");
				break;
			case 'g':
				result = DatabaseEngine.join(result, "cn.id", "mc.company_id");
				break;
			case 'h':
				result = DatabaseEngine.join(result, "it.id", "mi.info_type_id");
				break;
			case 'i':
				result = DatabaseEngine.join(result, "n.id", "ci.person_id");
				break;
			case 'j':
				result = DatabaseEngine.join(result, "rt.id", "ci.role_id");
				break;
			case 'k':
				result = DatabaseEngine.join(result, "n.id", "an.person_id");
				break;
			case 'l':
				result = DatabaseEngine.join(result, "ci.person_id", "an.person_id");
				break;
			case 'm':
				result = DatabaseEngine.join(result, "chn.id", "ci.person_role_id");
				break;
			default:
				System.out.println("Error: The corresponding execution does not exist. ");
				break;
			}
		}
		return result;
	}
}
