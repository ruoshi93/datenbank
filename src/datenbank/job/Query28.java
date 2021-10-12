package datenbank.job;

import datenbank.imdb.Comp_cast_typeTable;
import datenbank.imdb.Company_nameTable;
import datenbank.imdb.Company_typeTable;
import datenbank.imdb.Complete_castTable;
import datenbank.imdb.Info_typeTable;
import datenbank.imdb.KeywordTable;
import datenbank.imdb.Kind_typeTable;
import datenbank.imdb.Movie_companiesTable;
import datenbank.imdb.Movie_infoTable;
import datenbank.imdb.Movie_info_idxTable;
import datenbank.imdb.Movie_keywordTable;
import datenbank.imdb.TitleTable;
import datenbank.main.DatabaseEngine;
import datenbank.main.Result;

public class Query28 extends Query {
	
	String query = "SELECT * "
				 + "FROM complete_cast AS cc, comp_cast_type AS cct1, comp_cast_type AS cct2, company_name AS cn, company_type AS ct, info_type AS it1, info_type AS it2, keyword AS k, kind_type AS kt, movie_companies AS mc, movie_info AS mi, movie_info_idx AS mi_idx, movie_keyword AS mk, title AS t "
				 + "WHERE kt.id = t.kind_id "
				 + "AND t.id = mi.movie_id "
				 + "AND t.id = mk.movie_id "
				 + "AND t.id = mi_idx.movie_id "
				 + "AND t.id = mc.movie_id "
				 + "AND t.id = cc.movie_id "
				 + "AND mk.movie_id = mi.movie_id "
				 + "AND mk.movie_id = mi_idx.movie_id "
				 + "AND mk.movie_id = mc.movie_id "
				 + "AND mk.movie_id = cc.movie_id "
				 + "AND mi.movie_id = mi_idx.movie_id "
				 + "AND mi.movie_id = mc.movie_id "
				 + "AND mi.movie_id = cc.movie_id "
				 + "AND mc.movie_id = mi_idx.movie_id "
				 + "AND mc.movie_id = cc.movie_id "
				 + "AND mi_idx.movie_id = cc.movie_id "
				 + "AND k.id = mk.keyword_id "
				 + "AND it1.id = mi.info_type_id "
				 + "AND it2.id = mi_idx.info_type_id "
				 + "AND ct.id = mc.company_type_id "
				 + "AND cn.id = mc.company_id"
				 + "AND cct1.id = cc.subject_id "
				 + "AND cct2.id = cc.status_id;";

	public Query28() {
		DatabaseEngine.cc = new Complete_castTable();
		DatabaseEngine.cct1 = new Comp_cast_typeTable();
		DatabaseEngine.cct2 = new Comp_cast_typeTable();
		DatabaseEngine.cn = new Company_nameTable();
		DatabaseEngine.ct = new Company_typeTable();
		DatabaseEngine.it1 = new Info_typeTable();
		DatabaseEngine.it2 = new Info_typeTable();
		DatabaseEngine.k = new KeywordTable();
		DatabaseEngine.kt = new Kind_typeTable();
		DatabaseEngine.mc = new Movie_companiesTable();
		DatabaseEngine.mi_idx = new Movie_info_idxTable();
		DatabaseEngine.mi = new Movie_infoTable();
		DatabaseEngine.mk = new Movie_keywordTable();
		DatabaseEngine.t = new TitleTable();
		allPermutation("abcdefghijklmnopqrstuvw".toCharArray(), 0);
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
				result = DatabaseEngine.joinExample(result, "t.id", "mi.movie_id");
				break;
			case 'c':
				result = DatabaseEngine.joinExample(result, "t.id", "mk.movie_id");
				break;
			case 'd':
				result = DatabaseEngine.joinExample(result, "t.id", "mi_idx.movie_id");
				break;
			case 'e':
				result = DatabaseEngine.joinExample(result, "t.id", "mc.movie_id");
				break;
			case 'f':
				result = DatabaseEngine.joinExample(result, "t.id", "cc.movie_id");
				break;
			case 'g':
				result = DatabaseEngine.joinExample(result, "mk.movie_id", "mi.movie_id");
				break;
			case 'h':
				result = DatabaseEngine.joinExample(result, "mk.movie_id", "mi_idx.movie_id");
				break;
			case 'i':
				result = DatabaseEngine.joinExample(result, "mk.movie_id", "mc.movie_id");
				break;
			case 'j':
				result = DatabaseEngine.joinExample(result, "mk.movie_id", "cc.movie_id");
				break;
			case 'k':
				result = DatabaseEngine.joinExample(result, "mi.movie_id", "mi_idx.movie_id");
				break;
			case 'l':
				result = DatabaseEngine.joinExample(result, "mi.movie_id", "mc.movie_id");
				break;
			case 'm':
				result = DatabaseEngine.joinExample(result, "mi.movie_id", "cc.movie_id");
				break;
			case 'n':
				result = DatabaseEngine.joinExample(result, "mc.movie_id", "mi_idx.movie_id");
				break;
			case 'o':
				result = DatabaseEngine.joinExample(result, "mc.movie_id", "cc.movie_id");
				break;
			case 'p':
				result = DatabaseEngine.joinExample(result, "mi_idx.movie_id", "cc.movie_id");
				break;
			case 'q':
				result = DatabaseEngine.joinExample(result, "k.id", "mk.keyword_id");
				break;
			case 'r':
				result = DatabaseEngine.joinExample(result, "it1.id", "mi.info_type_id");
				break;
			case 's':
				result = DatabaseEngine.joinExample(result, "it2.id", "mi_idx.info_type_id");
				break;
			case 't':
				result = DatabaseEngine.joinExample(result, "ct.id", "mc.company_type_id");
				break;
			case 'u':
				result = DatabaseEngine.joinExample(result, "cn.id", "mc.company_id");
				break;
			case 'v':
				result = DatabaseEngine.joinExample(result, "cct1.id", "cc.subject_id");
				break;
			case 'w':
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
				result = DatabaseEngine.join(result, "kt.id", "t.kind_id");
				break;
			case 'b':
				result = DatabaseEngine.join(result, "t.id", "mi.movie_id");
				break;
			case 'c':
				result = DatabaseEngine.join(result, "t.id", "mk.movie_id");
				break;
			case 'd':
				result = DatabaseEngine.join(result, "t.id", "mi_idx.movie_id");
				break;
			case 'e':
				result = DatabaseEngine.join(result, "t.id", "mc.movie_id");
				break;
			case 'f':
				result = DatabaseEngine.join(result, "t.id", "cc.movie_id");
				break;
			case 'g':
				result = DatabaseEngine.join(result, "mk.movie_id", "mi.movie_id");
				break;
			case 'h':
				result = DatabaseEngine.join(result, "mk.movie_id", "mi_idx.movie_id");
				break;
			case 'i':
				result = DatabaseEngine.join(result, "mk.movie_id", "mc.movie_id");
				break;
			case 'j':
				result = DatabaseEngine.join(result, "mk.movie_id", "cc.movie_id");
				break;
			case 'k':
				result = DatabaseEngine.join(result, "mi.movie_id", "mi_idx.movie_id");
				break;
			case 'l':
				result = DatabaseEngine.join(result, "mi.movie_id", "mc.movie_id");
				break;
			case 'm':
				result = DatabaseEngine.join(result, "mi.movie_id", "cc.movie_id");
				break;
			case 'n':
				result = DatabaseEngine.join(result, "mc.movie_id", "mi_idx.movie_id");
				break;
			case 'o':
				result = DatabaseEngine.join(result, "mc.movie_id", "cc.movie_id");
				break;
			case 'p':
				result = DatabaseEngine.join(result, "mi_idx.movie_id", "cc.movie_id");
				break;
			case 'q':
				result = DatabaseEngine.join(result, "k.id", "mk.keyword_id");
				break;
			case 'r':
				result = DatabaseEngine.join(result, "it1.id", "mi.info_type_id");
				break;
			case 's':
				result = DatabaseEngine.join(result, "it2.id", "mi_idx.info_type_id");
				break;
			case 't':
				result = DatabaseEngine.join(result, "ct.id", "mc.company_type_id");
				break;
			case 'u':
				result = DatabaseEngine.join(result, "cn.id", "mc.company_id");
				break;
			case 'v':
				result = DatabaseEngine.join(result, "cct1.id", "cc.subject_id");
				break;
			case 'w':
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
