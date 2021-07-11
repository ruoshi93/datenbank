package datenbank.imdb;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;

public class KeywordTable extends TableDemo {

	public KeywordTable() {
		name = "keyword";
		path = "/Users/lili/Documents/Bachelor Thesis/imdb/keyword.csv";
		convert();
	}

	private void convert() {
		try {
			CSVReader reader = new CSVReader(new FileReader(this.path));

			String[] nextLine;
			while ((nextLine = reader.readNext()) != null) {
				Keyword keyword = new Keyword(nextLine);
				this.data.put(keyword.getPrimaryKey(), keyword);
			}
		} catch (CsvValidationException e) {
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	class Keyword extends Row {
		private int id;
		private String keyword;
		private String phonetic_code;

		public <T> T get(String s) {
			switch (s) {
			case "id":
				return (T) (Integer) this.id;
			case "keyword":
				return (T) this.keyword;
			case "phonetic_code":
				return (T) this.phonetic_code;
			default:
				return null;
			}
		}

		public int getPrimaryKey() {
			return this.id;
		}

		public Keyword(String[] data) {
			this.id = this.parseStringToInt(data[0]);
			this.keyword = data[1];
			this.phonetic_code = data[2];
		}
	}
}
