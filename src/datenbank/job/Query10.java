package datenbank.job;

import datenbank.imdb.Cast_infoTable;
import datenbank.imdb.Char_nameTable;
import datenbank.imdb.Company_nameTable;
import datenbank.imdb.Company_typeTable;
import datenbank.imdb.Movie_companiesTable;
import datenbank.imdb.Role_typeTable;
import datenbank.imdb.TitleTable;
import datenbank.main.DatabaseEngine;
import datenbank.main.Result;

public class Query10 extends Query {

	String query = "SELECT *"
				 + "FROM char_name AS chn, cast_info AS ci, company_name AS cn, company_type AS ct, movie_companies AS mc, role_type AS rt, title AS t "
				 + "WHERE t.id = mc.movie_id "
				 + "AND t.id = ci.movie_id "
				 + "AND ci.movie_id = mc.movie_id "
				 + "AND chn.id = ci.person_role_id "
				 + "AND rt.id = ci.role_id "
				 + "AND cn.id = mc.company_id "
				 + "AND ct.id = mc.company_type_id;";

	public Query10() {
		DatabaseEngine.chn = new Char_nameTable();
		DatabaseEngine.ci = new Cast_infoTable();
		DatabaseEngine.cn = new Company_nameTable();
		DatabaseEngine.ct = new Company_typeTable();
		DatabaseEngine.mc = new Movie_companiesTable();
		DatabaseEngine.rt = new Role_typeTable();
		DatabaseEngine.t = new TitleTable();
		allPermutation("1234567".toCharArray(), 0);
	}

	@Override
	protected void numbersToCodeExample(String s) {
		Result result = new Result();
		for (char c : s.toCharArray()) {
			switch (c) {
			case '1':
				result = DatabaseEngine.joinExample(result, "t.id", "mc.movie_id");
				break;
			case '2':
				result = DatabaseEngine.joinExample(result, "t.id", "ci.movie_id");
				break;
			case '3':
				result = DatabaseEngine.joinExample(result, "ci.movie_id", "mc.movie_id");
				break;
			case '4':
				result = DatabaseEngine.joinExample(result, "chn.id", "ci.person_role_id");
				break;
			case '5':
				result = DatabaseEngine.joinExample(result, "rt.id", "ci.role_id");
				break;
			case '6':
				result = DatabaseEngine.joinExample(result, "cn.id", "mc.company_id");
				break;
			case '7':
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
			case '1':
				result = DatabaseEngine.join(result, "t.id", "mc.movie_id");
				break;
			case '2':
				result = DatabaseEngine.join(result, "t.id", "ci.movie_id");
				break;
			case '3':
				result = DatabaseEngine.join(result, "ci.movie_id", "mc.movie_id");
				break;
			case '4':
				result = DatabaseEngine.join(result, "chn.id", "ci.person_role_id");
				break;
			case '5':
				result = DatabaseEngine.join(result, "rt.id", "ci.role_id");
				break;
			case '6':
				result = DatabaseEngine.join(result, "cn.id", "mc.company_id");
				break;
			case '7':
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
