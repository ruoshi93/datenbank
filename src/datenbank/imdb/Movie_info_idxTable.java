package datenbank.imdb;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;

public class Movie_info_idxTable extends TableDemo {

	public Movie_info_idxTable() {
		name = "movie_info_idx";
		path = "/Users/lili/Documents/Bachelor Thesis/imdb/movie_info_idx.csv";
		convert();
	}

	private void convert() {
		try {
			CSVReader reader = new CSVReader(new FileReader(this.path));

			String[] nextLine;
			while ((nextLine = reader.readNext()) != null) {
				Movie_info_idx movie_info_idx = new Movie_info_idx(nextLine);
				this.data.put(movie_info_idx.getPrimaryKey(), movie_info_idx);
			}
		} catch (CsvValidationException e) {
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	class Movie_info_idx extends Row {
		private int id;
		private int movie_id;
		private int info_type_id;
		private String info;
		private String note;

		@Override
		public <T> T get(String s) {
			switch (s) {
			case "id":
				return (T) (Integer) this.id;
			case "movie_id":
				return (T) (Integer) this.movie_id;
			case "info_type_id":
				return (T) (Integer) this.info_type_id;
			case "info":
				return (T) this.info;
			case "note":
				return (T) this.note;
			default:
				return null;
			}
		}

		public int getPrimaryKey() {
			return this.id;
		}

		public Movie_info_idx(String[] data) {
			this.id = this.parseStringToInt(data[0]);
			this.movie_id = this.parseStringToInt(data[1]);
			this.info_type_id = this.parseStringToInt(data[2]);
			this.info = data[3];
			this.note = data[4];
		}
	}
}
