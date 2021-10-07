package datenbank.imdb;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;

import datenbank.main.Row;
import datenbank.main.Table;

public class Movie_info_idxTable extends Table {

	public Movie_info_idxTable() {
		name = "movie_info_idx";
		path = "/Users/lili/Documents/Bachelor Thesis/imdb/movie_info_idx.csv";
		title = new String[] { "id", "movie_id", "info_type_id", "info", "note" };
		row = new Movie_info_idx();
		convert();
	}

	private void convert() {
		try {
			CSVReader reader = new CSVReader(new FileReader(this.path));

			String[] nextLine;
			int i = 0;
			while ((nextLine = reader.readNext()) != null) {
				Movie_info_idx movie_info_idx = new Movie_info_idx(nextLine);
				this.data.put(movie_info_idx.getPrimaryKey(), movie_info_idx);
				if (i % this.samplingSpace == 0) {
					this.example.put(movie_info_idx.getPrimaryKey(), movie_info_idx);
				}
				i++;
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
		private Integer id;
		private Integer movie_id;
		private Integer info_type_id;
		private String info;
		private String note;

		@Override
		public <T> T get(String s) {
			switch (s) {
			case "id":
				return (T) this.id;
			case "movie_id":
				return (T) this.movie_id;
			case "info_type_id":
				return (T) this.info_type_id;
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

		public Movie_info_idx() {
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
