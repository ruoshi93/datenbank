package datenbank.job;

import datenbank.imdb.Cast_infoTable;
import datenbank.imdb.Comp_cast_typeTable;
import datenbank.imdb.Complete_castTable;
import datenbank.imdb.Info_typeTable;
import datenbank.imdb.KeywordTable;
import datenbank.imdb.Movie_infoTable;
import datenbank.imdb.Movie_info_idxTable;
import datenbank.imdb.Movie_keywordTable;
import datenbank.imdb.NameTable;
import datenbank.imdb.TitleTable;
import datenbank.main.DatabaseEngine;
import datenbank.main.Result;

public class Query30 extends Query {

	String query = "SELECT * "
				 + "FROM complete_cast AS cc, comp_cast_type AS cct1, comp_cast_type AS cct2, cast_info AS ci, info_type AS it1, info_type AS it2, keyword AS k, movie_info AS mi, movie_info_idx AS mi_idx, movie_keyword AS mk, name AS n, title AS t "
				 + "WHERE t.id = mi.movie_id "
				 + "AND t.id = mi_idx.movie_id "
				 + "AND t.id = ci.movie_id "
				 + "AND t.id = mk.movie_id "
				 + "AND t.id = cc.movie_id "
				 + "AND ci.movie_id = mi.movie_id "
				 + "AND ci.movie_id = mi_idx.movie_id "
				 + "AND ci.movie_id = mk.movie_id "
				 + "AND ci.movie_id = cc.movie_id "
				 + "AND mi.movie_id = mi_idx.movie_id "
				 + "AND mi.movie_id = mk.movie_id "
				 + "AND mi.movie_id = cc.movie_id "
				 + "AND mi_idx.movie_id = mk.movie_id "
				 + "AND mi_idx.movie_id = cc.movie_id "
				 + "AND mk.movie_id = cc.movie_id "
				 + "AND n.id = ci.person_id "
				 + "AND it1.id = mi.info_type_id "
				 + "AND it2.id = mi_idx.info_type_id "
				 + "AND k.id = mk.keyword_id "
				 + "AND cct1.id = cc.subject_id "
				 + "AND cct2.id = cc.status_id;";

	public Query30() {
		DatabaseEngine.cc = new Complete_castTable();
		DatabaseEngine.cct1 = new Comp_cast_typeTable();
		DatabaseEngine.cct2 = new Comp_cast_typeTable();
		DatabaseEngine.ci = new Cast_infoTable();
		DatabaseEngine.it1 = new Info_typeTable();
		DatabaseEngine.it2 = new Info_typeTable();
		DatabaseEngine.k = new KeywordTable();
		DatabaseEngine.mi_idx = new Movie_info_idxTable();
		DatabaseEngine.mi = new Movie_infoTable();
		DatabaseEngine.mk = new Movie_keywordTable();
		DatabaseEngine.n = new NameTable();
		DatabaseEngine.t = new TitleTable();
		allPermutation("abcdefghijklmnopqrstu".toCharArray(), 0);
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
				result = DatabaseEngine.joinExample(result, "t.id", "mi_idx.movie_id");
				break;
			case 'c':
				result = DatabaseEngine.joinExample(result, "t.id", "ci.movie_id");
				break;
			case 'd':
				result = DatabaseEngine.joinExample(result, "t.id", "mk.movie_id");
				break;
			case 'e':
				result = DatabaseEngine.joinExample(result, "t.id", "cc.movie_id");
				break;
			case 'f':
				result = DatabaseEngine.joinExample(result, "ci.movie_id", "mi.movie_id");
				break;
			case 'g':
				result = DatabaseEngine.joinExample(result, "ci.movie_id", "mi_idx.movie_id");
				break;
			case 'h':
				result = DatabaseEngine.joinExample(result, "ci.movie_id", "mk.movie_id");
				break;
			case 'i':
				result = DatabaseEngine.joinExample(result, "ci.movie_id", "cc.movie_id");
				break;
			case 'j':
				result = DatabaseEngine.joinExample(result, "mi.movie_id", "mi_idx.movie_id");
				break;
			case 'k':
				result = DatabaseEngine.joinExample(result, "mi.movie_id", "mk.movie_id");
				break;
			case 'l':
				result = DatabaseEngine.joinExample(result, "mi.movie_id", "cc.movie_id");
				break;
			case 'm':
				result = DatabaseEngine.joinExample(result, "mi_idx.movie_id", "mk.movie_id");
				break;
			case 'n':
				result = DatabaseEngine.joinExample(result, "mi_idx.movie_id", "cc.movie_id");
				break;
			case 'o':
				result = DatabaseEngine.joinExample(result, "mk.movie_id", "cc.movie_id");
				break;
			case 'p':
				result = DatabaseEngine.joinExample(result, "n.id", "ci.person_id");
				break;
			case 'q':
				result = DatabaseEngine.joinExample(result, "it1.id", "mi.info_type_id");
				break;
			case 'r':
				result = DatabaseEngine.joinExample(result, "it2.id", "mi_idx.info_type_id");
				break;
			case 's':
				result = DatabaseEngine.joinExample(result, "k.id", "mk.keyword_id");
				break;
			case 't':
				result = DatabaseEngine.joinExample(result, "cct1.id", "cc.subject_id");
				break;
			case 'u':
				result = DatabaseEngine.joinExample(result, "cct2.id", "cc.status_id");
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
				result = DatabaseEngine.join(result, "t.id", "mi_idx.movie_id");
				break;
			case 'c':
				result = DatabaseEngine.join(result, "t.id", "ci.movie_id");
				break;
			case 'd':
				result = DatabaseEngine.join(result, "t.id", "mk.movie_id");
				break;
			case 'e':
				result = DatabaseEngine.join(result, "t.id", "cc.movie_id");
				break;
			case 'f':
				result = DatabaseEngine.join(result, "ci.movie_id", "mi.movie_id");
				break;
			case 'g':
				result = DatabaseEngine.join(result, "ci.movie_id", "mi_idx.movie_id");
				break;
			case 'h':
				result = DatabaseEngine.join(result, "ci.movie_id", "mk.movie_id");
				break;
			case 'i':
				result = DatabaseEngine.join(result, "ci.movie_id", "cc.movie_id");
				break;
			case 'j':
				result = DatabaseEngine.join(result, "mi.movie_id", "mi_idx.movie_id");
				break;
			case 'k':
				result = DatabaseEngine.join(result, "mi.movie_id", "mk.movie_id");
				break;
			case 'l':
				result = DatabaseEngine.join(result, "mi.movie_id", "cc.movie_id");
				break;
			case 'm':
				result = DatabaseEngine.join(result, "mi_idx.movie_id", "mk.movie_id");
				break;
			case 'n':
				result = DatabaseEngine.join(result, "mi_idx.movie_id", "cc.movie_id");
				break;
			case 'o':
				result = DatabaseEngine.join(result, "mk.movie_id", "cc.movie_id");
				break;
			case 'p':
				result = DatabaseEngine.join(result, "n.id", "ci.person_id");
				break;
			case 'q':
				result = DatabaseEngine.join(result, "it1.id", "mi.info_type_id");
				break;
			case 'r':
				result = DatabaseEngine.join(result, "it2.id", "mi_idx.info_type_id");
				break;
			case 's':
				result = DatabaseEngine.join(result, "k.id", "mk.keyword_id");
				break;
			case 't':
				result = DatabaseEngine.join(result, "cct1.id", "cc.subject_id");
				break;
			case 'u':
				result = DatabaseEngine.join(result, "cct2.id", "cc.status_id");
				break;
			default:
				System.out.println("Error: The corresponding execution does not exist. ");
				break;
			}
		}
		return result;
	}
}
