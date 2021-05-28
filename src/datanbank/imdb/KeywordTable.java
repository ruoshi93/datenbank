package datanbank.imdb;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;

public class KeywordTable {

	private String path = "/Users/lili/Documents/Bachelor Thesis/imdb/keyword.csv";
	private HashMap<Integer, Keyword> data = new HashMap<Integer, Keyword>();

	public KeywordTable() {
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

	class Keyword {
		int id;
		String keyword;
		String phonetic_code;

		public int getPrimaryKey() {
			return this.id;
		}

		public Keyword(String[] data) {
			this.id = Integer.parseInt(data[0]);
			this.keyword = data[1];
			this.phonetic_code = data[2];
		}
	}
}
