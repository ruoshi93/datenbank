package datenbank.imdb;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;

import datenbank.main.Row;
import datenbank.main.Table;

public class Movie_linkTable extends Table {

	public Movie_linkTable() {
		name = "movie_link";
		path = "/Users/lili/Documents/Bachelor Thesis/imdb/movie_link.csv";
		title = new String[] { "id", "movie_id", "linked_movie_id", "link_type_id" };
		row = new Movie_link();
		convert();
	}

	private void convert() {
		try {
			CSVReader reader = new CSVReader(new FileReader(this.path));

			String[] nextLine;
			int i = 0;
			while ((nextLine = reader.readNext()) != null) {
				Movie_link movie_link = new Movie_link(nextLine);
				this.data.put(movie_link.getPrimaryKey(), movie_link);
				if (i % this.samplingSpace == 0) {
					this.example.put(movie_link.getPrimaryKey(), movie_link);
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

	class Movie_link extends Row {
		private Integer id;
		private Integer movie_id;
		private Integer linked_movie_id;
		private Integer link_type_id;

		@Override
		public <T> T get(String s) {
			switch (s) {
			case "id":
				return (T) this.id;
			case "movie_id":
				return (T) this.movie_id;
			case "linked_movie_id":
				return (T) this.linked_movie_id;
			case "link_type_id":
				return (T) this.link_type_id;
			default:
				return null;
			}
		}

		public int getPrimaryKey() {
			return this.id;
		}

		public Movie_link() {
		}

		public Movie_link(String[] data) {
			this.id = this.parseStringToInt(data[0]);
			this.movie_id = this.parseStringToInt(data[1]);
			this.linked_movie_id = this.parseStringToInt(data[2]);
			this.link_type_id = this.parseStringToInt(data[3]);
		}

	}
}
