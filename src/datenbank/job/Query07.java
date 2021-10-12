package datenbank.job;

import datenbank.imdb.Aka_nameTable;
import datenbank.imdb.Cast_infoTable;
import datenbank.imdb.Info_typeTable;
import datenbank.imdb.Link_typeTable;
import datenbank.imdb.Movie_linkTable;
import datenbank.imdb.NameTable;
import datenbank.imdb.Person_infoTable;
import datenbank.imdb.TitleTable;
import datenbank.main.DatabaseEngine;
import datenbank.main.Result;

public class Query07 extends Query{

	String query = "SELECT * "
			+ "FROM aka_name AS an, cast_info AS ci, info_type AS it, link_type AS lt, movie_link AS ml, name AS n, person_info AS pi, title AS t "
			+ "WHERE n.id = an.person_id "
			+ "AND n.id = pi.person_id "
			+ "AND ci.person_id = n.id "
			+ "AND t.id = ci.movie_id "
			+ "AND ml.linked_movie_id = t.id "
			+ "AND lt.id = ml.link_type_id "
			+ "AND it.id = pi.info_type_id "
			+ "AND pi.person_id = an.person_id "
			+ "AND pi.person_id = ci.person_id "
			+ "AND an.person_id = ci.person_id "
			+ "AND ci.movie_id = ml.linked_movie_id;";

	public Query07() {
		DatabaseEngine.an = new Aka_nameTable();
		DatabaseEngine.ci = new Cast_infoTable();
		DatabaseEngine.it = new Info_typeTable();
		DatabaseEngine.lt = new Link_typeTable();
		DatabaseEngine.ml = new Movie_linkTable();
		DatabaseEngine.n = new NameTable();
		DatabaseEngine.pi = new Person_infoTable();
		DatabaseEngine.t = new TitleTable();
		allPermutation("abcdefghijk".toCharArray(), 0);
	}
	
	@Override
	protected void numbersToCodeExample(String s) {
		Result result = new Result();
		for (char c : s.toCharArray()) {
			switch (c) {
			case 'a':
				result = DatabaseEngine.joinExample(result, "n.id", "an.person_id");
				break;
			case 'b':
				result = DatabaseEngine.joinExample(result, "n.id", "pi.person_id");
				break;
			case 'c':
				result = DatabaseEngine.joinExample(result, "ci.person_id", "n.id");
				break;
			case 'd':
				result = DatabaseEngine.joinExample(result, "t.id", "ci.movie_id");
				break;
			case 'e':
				result = DatabaseEngine.joinExample(result, "ml.linked_movie_id", "t.id");
				break;
			case 'f':
				result = DatabaseEngine.joinExample(result, "lt.id", "ml.link_type_id");
				break;
			case 'g':
				result = DatabaseEngine.joinExample(result, "it.id", "pi.info_type_id");
				break;
			case 'h':
				result = DatabaseEngine.joinExample(result, "pi.person_id", "an.person_id");
				break;
			case 'i':
				result = DatabaseEngine.joinExample(result, "pi.person_id", "ci.person_id");
				break;
			case 'j':
				result = DatabaseEngine.joinExample(result, "an.person_id", "ci.person_id");
				break;
			case 'k':
				result = DatabaseEngine.joinExample(result, "ci.movie_id", "ml.linked_movie_id");
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
				result = DatabaseEngine.join(result, "n.id", "an.person_id");
				break;
			case 'b':
				result = DatabaseEngine.join(result, "n.id", "pi.person_id");
				break;
			case 'c':
				result = DatabaseEngine.join(result, "ci.person_id", "n.id");
				break;
			case 'd':
				result = DatabaseEngine.join(result, "t.id", "ci.movie_id");
				break;
			case 'e':
				result = DatabaseEngine.join(result, "ml.linked_movie_id", "t.id");
				break;
			case 'f':
				result = DatabaseEngine.join(result, "lt.id", "ml.link_type_id");
				break;
			case 'g':
				result = DatabaseEngine.join(result, "it.id", "pi.info_type_id");
				break;
			case 'h':
				result = DatabaseEngine.join(result, "pi.person_id", "an.person_id");
				break;
			case 'i':
				result = DatabaseEngine.join(result, "pi.person_id", "ci.person_id");
				break;
			case 'j':
				result = DatabaseEngine.join(result, "an.person_id", "ci.person_id");
				break;
			case 'k':
				result = DatabaseEngine.join(result, "ci.movie_id", "ml.linked_movie_id");
				break;
			default:
				System.out.println("Error: The corresponding execution does not exist. ");
				break;
			}
		}
		return result;
	}

}
