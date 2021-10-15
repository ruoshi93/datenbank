package datenbank.job;

import datenbank.imdb.Cast_infoTable;
import datenbank.imdb.Char_nameTable;
import datenbank.imdb.Comp_cast_typeTable;
import datenbank.imdb.Complete_castTable;
import datenbank.imdb.Info_typeTable;
import datenbank.imdb.KeywordTable;
import datenbank.imdb.Kind_typeTable;
import datenbank.imdb.Movie_info_idxTable;
import datenbank.imdb.Movie_keywordTable;
import datenbank.imdb.NameTable;
import datenbank.imdb.TitleTable;
import datenbank.main.DatabaseEngine;
import datenbank.main.Result;

public class Query26 extends Query{

	String query = "SELECT * "
			+ "FROM complete_cast AS cc, comp_cast_type AS cct1, comp_cast_type AS cct2, char_name AS chn, cast_info AS ci, info_type AS it2, keyword AS k, kind_type AS kt, movie_info_idx AS mi_idx, movie_keyword AS mk, name AS n, title AS t "
			+ "WHERE kt.id = t.kind_id "
			+ "AND t.id = mk.movie_id "
			+ "AND t.id = ci.movie_id "
			+ "AND t.id = cc.movie_id "
			+ "AND t.id = mi_idx.movie_id "
			+ "AND mk.movie_id = ci.movie_id "
			+ "AND mk.movie_id = cc.movie_id "
			+ "AND mk.movie_id = mi_idx.movie_id "
			+ "AND ci.movie_id = cc.movie_id "
			+ "AND ci.movie_id = mi_idx.movie_id "
			+ "AND cc.movie_id = mi_idx.movie_id "
			+ "AND chn.id = ci.person_role_id "
			+ "AND n.id = ci.person_id "
			+ "AND k.id = mk.keyword_id "
			+ "AND cct1.id = cc.subject_id "
			+ "AND cct2.id = cc.status_id "
			+ "AND it2.id = mi_idx.info_type_id;";

	public Query26() {
		DatabaseEngine.cc = new Complete_castTable();
		DatabaseEngine.cct1 = new Comp_cast_typeTable();
		DatabaseEngine.cct2 = new Comp_cast_typeTable();
		DatabaseEngine.chn = new Char_nameTable();
		DatabaseEngine.ci = new Cast_infoTable();
		DatabaseEngine.it2 = new Info_typeTable();
		DatabaseEngine.k = new KeywordTable();
		DatabaseEngine.kt = new Kind_typeTable();
		DatabaseEngine.mi_idx = new Movie_info_idxTable();
		DatabaseEngine.mk = new Movie_keywordTable();
		DatabaseEngine.n = new NameTable();
		DatabaseEngine.t = new TitleTable();
		allPermutation("abcdefghijklmnopq".toCharArray(), 0);
		execute();
	}

	@Override
	protected void numbersToCodeExample(String s) {
		Result result = new Result();
		for (char c : s.toCharArray()) {
			switch (c) {
			case 'a':
				result = DatabaseEngine.joinExample(result, "kt.id", "t.kind_id");
				break;
			case 'b':
				result = DatabaseEngine.joinExample(result, "t.id", "mk.movie_id");
				break;
			case 'c':
				result = DatabaseEngine.joinExample(result, "t.id", "ci.movie_id");
				break;
			case 'd':
				result = DatabaseEngine.joinExample(result, "t.id", "cc.movie_id");
				break;
			case 'e':
				result = DatabaseEngine.joinExample(result, "t.id", "mi_idx.movie_id");
				break;
			case 'f':
				result = DatabaseEngine.joinExample(result, "mk.movie_id", "ci.movie_id");
				break;
			case 'g':
				result = DatabaseEngine.joinExample(result, "mk.movie_id", "cc.movie_id");
				break;
			case 'h':
				result = DatabaseEngine.joinExample(result, "mk.movie_id", "mi_idx.movie_id");
				break;
			case 'i':
				result = DatabaseEngine.joinExample(result, "ci.movie_id", "cc.movie_id");
				break;
			case 'j':
				result = DatabaseEngine.joinExample(result, "ci.movie_id", "mi_idx.movie_id");
				break;
			case 'k':
				result = DatabaseEngine.joinExample(result, "cc.movie_id", "mi_idx.movie_id");
				break;
			case 'l':
				result = DatabaseEngine.joinExample(result, "chn.id", "ci.person_role_id");
				break;
			case 'm':
				result = DatabaseEngine.joinExample(result, "n.id", "ci.person_id");
				break;
			case 'n':
				result = DatabaseEngine.joinExample(result, "k.id", "mk.keyword_id");
				break;
			case 'o':
				result = DatabaseEngine.joinExample(result, "cct1.id", "cc.subject_id");
				break;
			case 'p':
				result = DatabaseEngine.joinExample(result, "cct2.id", "cc.status_id");
				break;
			case 'q':
				result = DatabaseEngine.joinExample(result, "it2.id", "mi_idx.info_type_id");
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
				result = DatabaseEngine.join(result, "kt.id", "t.kind_id");
				break;
			case 'b':
				result = DatabaseEngine.join(result, "t.id", "mk.movie_id");
				break;
			case 'c':
				result = DatabaseEngine.join(result, "t.id", "ci.movie_id");
				break;
			case 'd':
				result = DatabaseEngine.join(result, "t.id", "cc.movie_id");
				break;
			case 'e':
				result = DatabaseEngine.join(result, "t.id", "mi_idx.movie_id");
				break;
			case 'f':
				result = DatabaseEngine.join(result, "mk.movie_id", "ci.movie_id");
				break;
			case 'g':
				result = DatabaseEngine.join(result, "mk.movie_id", "cc.movie_id");
				break;
			case 'h':
				result = DatabaseEngine.join(result, "mk.movie_id", "mi_idx.movie_id");
				break;
			case 'i':
				result = DatabaseEngine.join(result, "ci.movie_id", "cc.movie_id");
				break;
			case 'j':
				result = DatabaseEngine.join(result, "ci.movie_id", "mi_idx.movie_id");
				break;
			case 'k':
				result = DatabaseEngine.join(result, "cc.movie_id", "mi_idx.movie_id");
				break;
			case 'l':
				result = DatabaseEngine.join(result, "chn.id", "ci.person_role_id");
				break;
			case 'm':
				result = DatabaseEngine.join(result, "n.id", "ci.person_id");
				break;
			case 'n':
				result = DatabaseEngine.join(result, "k.id", "mk.keyword_id");
				break;
			case 'o':
				result = DatabaseEngine.join(result, "cct1.id", "cc.subject_id");
				break;
			case 'p':
				result = DatabaseEngine.join(result, "cct2.id", "cc.status_id");
				break;
			case 'q':
				result = DatabaseEngine.join(result, "it2.id", "mi_idx.info_type_id");
				break;
			default:
				System.out.println("Error: The corresponding execution does not exist. ");
				break;
			}
		}
		return result;
	}
}
