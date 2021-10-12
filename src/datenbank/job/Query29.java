package datenbank.job;

import datenbank.imdb.Aka_nameTable;
import datenbank.imdb.Cast_infoTable;
import datenbank.imdb.Char_nameTable;
import datenbank.imdb.Comp_cast_typeTable;
import datenbank.imdb.Company_nameTable;
import datenbank.imdb.Complete_castTable;
import datenbank.imdb.Info_typeTable;
import datenbank.imdb.KeywordTable;
import datenbank.imdb.Movie_companiesTable;
import datenbank.imdb.Movie_infoTable;
import datenbank.imdb.Movie_keywordTable;
import datenbank.imdb.NameTable;
import datenbank.imdb.Person_infoTable;
import datenbank.imdb.Role_typeTable;
import datenbank.imdb.TitleTable;
import datenbank.main.DatabaseEngine;
import datenbank.main.Result;

public class Query29 extends Query{
	
	String query = "SELECT * "
				 + "FROM aka_name AS an, complete_cast AS cc, comp_cast_type AS cct1, comp_cast_type AS cct2, char_name AS chn, cast_info AS ci, company_name AS cn, info_type AS it, info_type AS it2, keyword AS k, movie_companies AS mc, movie_info AS mi, movie_keyword AS mk, name AS n, person_info AS pi, role_type AS rt, title AS t "
				 + "WHERE t.id = mi.movie_id "
				 + "AND t.id = mc.movie_id "
				 + "AND t.id = ci.movie_id "
				 + "AND t.id = mk.movie_id "
				 + "AND t.id = cc.movie_id "
				 + "AND mc.movie_id = ci.movie_id "
				 + "AND mc.movie_id = mi.movie_id "
				 + "AND mc.movie_id = mk.movie_id "
				 + "AND mc.movie_id = cc.movie_id "
				 + "AND mi.movie_id = ci.movie_id "
				 + "AND mi.movie_id = mk.movie_id "
				 + "AND mi.movie_id = cc.movie_id "
				 + "AND ci.movie_id = mk.movie_id "
				 + "AND ci.movie_id = cc.movie_id "
				 + "AND mk.movie_id = cc.movie_id "
				 + "AND cn.id = mc.company_id "
				 + "AND it.id = mi.info_type_id "
				 + "AND n.id = ci.person_id "
				 + "AND rt.id = ci.role_id "
				 + "AND n.id = an.person_id "
				 + "AND ci.person_id = an.person_id "
				 + "AND chn.id = ci.person_role_id "
				 + "AND n.id = pi.person_id "
				 + "AND ci.person_id = pi.person_id "
				 + "AND it2.id = pi.info_type_id "
				 + "AND k.id = mk.keyword_id "
				 + "AND cct1.id = cc.subject_id "
				 + "AND cct2.id = cc.status_id;";

	public Query29() {
		DatabaseEngine.an = new Aka_nameTable();
		DatabaseEngine.cc = new Complete_castTable();
		DatabaseEngine.cct1 = new Comp_cast_typeTable();
		DatabaseEngine.cct2 = new Comp_cast_typeTable();
		DatabaseEngine.chn = new Char_nameTable();
		DatabaseEngine.ci = new Cast_infoTable();
		DatabaseEngine.cn = new Company_nameTable();
		DatabaseEngine.it = new Info_typeTable();
		DatabaseEngine.it2 = new Info_typeTable();
		DatabaseEngine.k = new KeywordTable();
		DatabaseEngine.mc = new Movie_companiesTable();
		DatabaseEngine.mi = new Movie_infoTable();
		DatabaseEngine.mk = new Movie_keywordTable();
		DatabaseEngine.n = new NameTable();
		DatabaseEngine.pi = new Person_infoTable();
		DatabaseEngine.rt = new Role_typeTable();
		DatabaseEngine.t = new TitleTable();
		allPermutation("abcdefghijklmnopqrstuvwxyz12".toCharArray(), 0);
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
				result = DatabaseEngine.joinExample(result, "t.id", "mk.movie_id");
				break;
			case 'e':
				result = DatabaseEngine.joinExample(result, "t.id", "cc.movie_id");
				break;
			case 'f':
				result = DatabaseEngine.joinExample(result, "mc.movie_id", "ci.movie_id");
				break;
			case 'g':
				result = DatabaseEngine.joinExample(result, "mc.movie_id", "mi.movie_id");
				break;
			case 'h':
				result = DatabaseEngine.joinExample(result, "mc.movie_id", "mk.movie_id");
				break;
			case 'i':
				result = DatabaseEngine.joinExample(result, "mc.movie_id", "cc.movie_id");
				break;
			case 'j':
				result = DatabaseEngine.joinExample(result, "mi.movie_id", "ci.movie_id");
				break;
			case 'k':
				result = DatabaseEngine.joinExample(result, "mi.movie_id", "mk.movie_id");
				break;
			case 'l':
				result = DatabaseEngine.joinExample(result, "mi.movie_id", "cc.movie_id");
				break;
			case 'm':
				result = DatabaseEngine.joinExample(result, "ci.movie_id", "mk.movie_id");
				break;
			case 'n':
				result = DatabaseEngine.joinExample(result, "ci.movie_id", "cc.movie_id");
				break;
			case 'o':
				result = DatabaseEngine.joinExample(result, "mk.movie_id", "cc.movie_id");
				break;
			case 'p':
				result = DatabaseEngine.joinExample(result, "cn.id", "mc.company_id");
				break;
			case 'q':
				result = DatabaseEngine.joinExample(result, "it.id", "mi.info_type_id");
				break;
			case 'r':
				result = DatabaseEngine.joinExample(result, "n.id", "ci.person_id");
				break;
			case 's':
				result = DatabaseEngine.joinExample(result, "rt.id", "ci.role_id");
				break;
			case 't':
				result = DatabaseEngine.joinExample(result, "n.id", "an.person_id");
				break;
			case 'u':
				result = DatabaseEngine.joinExample(result, "ci.person_id", "an.person_id");
				break;
			case 'v':
				result = DatabaseEngine.joinExample(result, "chn.id", "ci.person_role_id");
				break;
			case 'w':
				result = DatabaseEngine.joinExample(result, "n.id", "pi.person_id");
				break;
			case 'x':
				result = DatabaseEngine.joinExample(result, "ci.person_id", "pi.person_id");
				break;
			case 'y':
				result = DatabaseEngine.joinExample(result, "it2.id", "pi.info_type_id");
				break;
			case 'z':
				result = DatabaseEngine.joinExample(result, "k.id", "mk.keyword_id");
				break;
			case '1':
				result = DatabaseEngine.joinExample(result, "cct1.id", "cc.subject_id");
				break;
			case '2':
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
				result = DatabaseEngine.join(result, "t.id", "mc.movie_id");
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
				result = DatabaseEngine.join(result, "mc.movie_id", "ci.movie_id");
				break;
			case 'g':
				result = DatabaseEngine.join(result, "mc.movie_id", "mi.movie_id");
				break;
			case 'h':
				result = DatabaseEngine.join(result, "mc.movie_id", "mk.movie_id");
				break;
			case 'i':
				result = DatabaseEngine.join(result, "mc.movie_id", "cc.movie_id");
				break;
			case 'j':
				result = DatabaseEngine.join(result, "mi.movie_id", "ci.movie_id");
				break;
			case 'k':
				result = DatabaseEngine.join(result, "mi.movie_id", "mk.movie_id");
				break;
			case 'l':
				result = DatabaseEngine.join(result, "mi.movie_id", "cc.movie_id");
				break;
			case 'm':
				result = DatabaseEngine.join(result, "ci.movie_id", "mk.movie_id");
				break;
			case 'n':
				result = DatabaseEngine.join(result, "ci.movie_id", "cc.movie_id");
				break;
			case 'o':
				result = DatabaseEngine.join(result, "mk.movie_id", "cc.movie_id");
				break;
			case 'p':
				result = DatabaseEngine.join(result, "cn.id", "mc.company_id");
				break;
			case 'q':
				result = DatabaseEngine.join(result, "it.id", "mi.info_type_id");
				break;
			case 'r':
				result = DatabaseEngine.join(result, "n.id", "ci.person_id");
				break;
			case 's':
				result = DatabaseEngine.join(result, "rt.id", "ci.role_id");
				break;
			case 't':
				result = DatabaseEngine.join(result, "n.id", "an.person_id");
				break;
			case 'u':
				result = DatabaseEngine.join(result, "ci.person_id", "an.person_id");
				break;
			case 'v':
				result = DatabaseEngine.join(result, "chn.id", "ci.person_role_id");
				break;
			case 'w':
				result = DatabaseEngine.join(result, "n.id", "pi.person_id");
				break;
			case 'x':
				result = DatabaseEngine.join(result, "ci.person_id", "pi.person_id");
				break;
			case 'y':
				result = DatabaseEngine.join(result, "it2.id", "pi.info_type_id");
				break;
			case 'z':
				result = DatabaseEngine.join(result, "k.id", "mk.keyword_id");
				break;
			case '1':
				result = DatabaseEngine.join(result, "cct1.id", "cc.subject_id");
				break;
			case '2':
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
