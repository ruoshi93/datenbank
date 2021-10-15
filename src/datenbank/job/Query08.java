package datenbank.job;

import datenbank.imdb.Aka_nameTable;
import datenbank.imdb.Cast_infoTable;
import datenbank.imdb.Company_nameTable;
import datenbank.imdb.Movie_companiesTable;
import datenbank.imdb.NameTable;
import datenbank.imdb.Role_typeTable;
import datenbank.imdb.TitleTable;
import datenbank.main.DatabaseEngine;
import datenbank.main.Result;

public class Query08 extends Query {

	String query = "SELECT * "
				 + "FROM aka_name AS an, cast_info AS ci, company_name AS cn, movie_companies AS mc, name AS n, role_type AS rt, title AS t "
				 + "WHERE an.person_id = n.id "
				 + "AND n.id = ci.person_id "
				 + "AND ci.movie_id = t.id "
				 + "AND t.id = mc.movie_id "
				 + "AND mc.company_id = cn.id "
				 + "AND ci.role_id = rt.id "
				 + "AND an.person_id = ci.person_id "
				 + "AND ci.movie_id = mc.movie_id;";

	public Query08() {
		DatabaseEngine.an = new Aka_nameTable();
		DatabaseEngine.ci = new Cast_infoTable();
		DatabaseEngine.cn = new Company_nameTable();
		DatabaseEngine.mc = new Movie_companiesTable();
		DatabaseEngine.n = new NameTable();
		DatabaseEngine.rt = new Role_typeTable();
		DatabaseEngine.t = new TitleTable();
		allPermutation("12345678".toCharArray(), 0);
		execute();
	}

	@Override
	protected void numbersToCodeExample(String s) {
		Result result = new Result();
		for (char c : s.toCharArray()) {
			switch (c) {
			case '1':
				result = DatabaseEngine.joinExample(result, "an.person_id", "n.id");
				break;
			case '2':
				result = DatabaseEngine.joinExample(result, "n.id", "ci.person_id");
				break;
			case '3':
				result = DatabaseEngine.joinExample(result, "ci.movie_id", "t.id");
				break;
			case '4':
				result = DatabaseEngine.joinExample(result, "t.id", "mc.movie_id");
				break;
			case '5':
				result = DatabaseEngine.joinExample(result, "mc.company_id", "cn.id");
				break;
			case '6':
				result = DatabaseEngine.joinExample(result, "ci.role_id", "rt.id");
				break;
			case '7':
				result = DatabaseEngine.joinExample(result, "an.person_id", "ci.person_id");
				break;
			case '8':
				result = DatabaseEngine.joinExample(result, "ci.movie_id", "mc.movie_id");
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
				result = DatabaseEngine.join(result, "an.person_id", "n.id");
				break;
			case '2':
				result = DatabaseEngine.join(result, "n.id", "ci.person_id");
				break;
			case '3':
				result = DatabaseEngine.join(result, "ci.movie_id", "t.id");
				break;
			case '4':
				result = DatabaseEngine.join(result, "t.id", "mc.movie_id");
				break;
			case '5':
				result = DatabaseEngine.join(result, "mc.company_id", "cn.id");
				break;
			case '6':
				result = DatabaseEngine.join(result, "ci.role_id", "rt.id");
				break;
			case '7':
				result = DatabaseEngine.join(result, "an.person_id", "ci.person_id");
				break;
			case '8':
				result = DatabaseEngine.join(result, "ci.movie_id", "mc.movie_id");
				break;
			default:
				System.out.println("Error: The corresponding execution does not exist. ");
				break;
			}
		}
		return result;
	}

}
