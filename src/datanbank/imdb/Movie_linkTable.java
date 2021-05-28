package datanbank.imdb;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;

public class Movie_linkTable {

	private String path = "/Users/lili/Documents/Bachelor Thesis/imdb/movie_link.csv";
	private HashMap<Integer, Movie_link> data = new HashMap<Integer, Movie_link>();

	public Movie_linkTable() {
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

	class Movie_link {
		int id;
		int movie_id;
		int linked_movie_id;
		int link_type_id;

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
