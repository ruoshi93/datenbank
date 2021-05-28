package datanbank.imdb;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;

public class Movie_infoTable {

	private String path = "/Users/lili/Documents/Bachelor Thesis/imdb/movie_info.csv";
	private HashMap<Integer, Movie_info> data = new HashMap<Integer, Movie_info>();

	public Movie_infoTable() {
		convert();
	}

	private void convert() {
		try {
			CSVReader reader = new CSVReader(new FileReader(this.path));

			String[] nextLine;
			while ((nextLine = reader.readNext()) != null) {
				Movie_info movie_info = new Movie_info(nextLine);
				this.data.put(movie_info.getPrimaryKey(), movie_info);
			}
		} catch (CsvValidationException e) {
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	class Movie_info {
		int id;
		int movie_id;
		int info_type_id;
		String info;
		String note;

		public int getPrimaryKey() {
			return this.id;
		}

		public Movie_info(String[] data) {
			this.id = Integer.parseInt(data[0]);
			this.movie_id = Integer.parseInt(data[1]);
			this.info_type_id = Integer.parseInt(data[2]);
			this.info = data[3];
			this.note = data[4];
		}
	}
}
