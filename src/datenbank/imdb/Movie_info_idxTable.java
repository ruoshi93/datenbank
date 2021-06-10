package datenbank.imdb;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;

public class Movie_info_idxTable {

	private String path = "/Users/lili/Documents/Bachelor Thesis/imdb/movie_info_idx.csv";
	private HashMap<Integer, Movie_info_idx> data = new HashMap<Integer, Movie_info_idx>();

	public Movie_info_idxTable() {
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

	class Movie_info_idx {
		int id;
		int movie_id;
		int info_type_id;
		String info;
		String note;

		public int getPrimaryKey() {
			return this.id;
		}

		public Movie_info_idx(String[] data) {
			this.id = Integer.parseInt(data[0]);
			this.movie_id = Integer.parseInt(data[1]);
			this.info_type_id = Integer.parseInt(data[2]);
			this.info = data[3];
			this.note = data[4];
		}
	}
}
