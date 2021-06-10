package datenbank.imdb;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;

public class Movie_linkTable extends TableDemo {

	public Movie_linkTable() {
		name = "movie_link";
		path = "/Users/lili/Documents/Bachelor Thesis/imdb/movie_link.csv";
		convert();
	}

	private void convert() {
		try {
			CSVReader reader = new CSVReader(new FileReader(this.path));

			String[] nextLine;
			while ((nextLine = reader.readNext()) != null) {
				Movie_link movie_link = new Movie_link(nextLine);
				this.data.put(movie_link.getPrimaryKey(), movie_link);
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
		private int id;
		private int movie_id;
		private int linked_movie_id;
		private int link_type_id;

		@Override
		public <T> T get(String s) {
			switch (s) {
			case "id":
				return (T) (Integer) this.id;
			case "movie_id":
				return (T) (Integer) this.movie_id;
			case "linked_movie_id":
				return (T) (Integer) this.linked_movie_id;
			case "link_type_id":
				return (T) (Integer) this.link_type_id;
			default:
				return null;
			}
		}

		public int getPrimaryKey() {
			return this.id;
		}

		public Movie_link(String[] data) {
			this.id = Integer.parseInt(data[0]);
			this.movie_id = Integer.parseInt(data[1]);
			this.linked_movie_id = Integer.parseInt(data[2]);
			this.link_type_id = Integer.parseInt(data[3]);
		}

	}
}
