package datenbank.imdb;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;

import datenbank.main.Row;
import datenbank.main.Table;

public class Movie_keywordTable extends Table {

	public Movie_keywordTable() {
		name = "movie_keyword";
		path = "/Users/lili/Documents/Bachelor Thesis/imdb/movie_keyword.csv";
		convert();
	}

	private void convert() {
		try {
			CSVReader reader = new CSVReader(new FileReader(this.path));

			String[] nextLine;
			while ((nextLine = reader.readNext()) != null) {
				Movie_keyword movie_keyword = new Movie_keyword(nextLine);
				this.data.put(movie_keyword.getPrimaryKey(), movie_keyword);
			}
		} catch (CsvValidationException e) {
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	class Movie_keyword extends Row {
		private int id;
		private int movie_id;
		private int keyword_id;

		public <T> T get(String s) {
			switch (s) {
			case "id":
				return (T) (Integer) this.id;
			case "movie_id":
				return (T) (Integer) this.movie_id;
			case "keyword_id":
				return (T) (Integer) this.keyword_id;
			default:
				return null;
			}
		}

		public int getPrimaryKey() {
			return this.id;
		}

		public Movie_keyword(String[] data) {
			this.id = this.parseStringToInt(data[0]);
			this.movie_id = this.parseStringToInt(data[1]);
			this.keyword_id = this.parseStringToInt(data[2]);
		}
	}
}
