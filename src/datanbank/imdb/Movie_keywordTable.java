package datanbank.imdb;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;

public class Movie_keywordTable {

	private String path = "/Users/lili/Documents/Bachelor Thesis/imdb/movie_keyword.csv";
	private HashMap<Integer, Movie_keyword> data = new HashMap<Integer, Movie_keyword>();

	public Movie_keywordTable() {
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

	class Movie_keyword {
		int id;
		int movie_id;
		int keyword_id;

		public int getPrimaryKey() {
			return this.id;
		}

		public Movie_keyword(String[] data) {
			this.id = Integer.parseInt(data[0]);
			this.movie_id = Integer.parseInt(data[1]);
			this.keyword_id = Integer.parseInt(data[2]);
		}
	}
}
