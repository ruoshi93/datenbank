package datenbank.imdb;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;

public class Movie_companiesTable {

	private String path = "/Users/lili/Documents/Bachelor Thesis/imdb/movie_companies.csv";
	private HashMap<Integer, Movie_companies> data = new HashMap<Integer, Movie_companies>();

	public Movie_companiesTable() {
		convert();
	}

	private void convert() {
		try {
			CSVReader reader = new CSVReader(new FileReader(this.path));

			String[] nextLine;
			while ((nextLine = reader.readNext()) != null) {
				Movie_companies movie_companies = new Movie_companies(nextLine);
				this.data.put(movie_companies.getPrimaryKey(), movie_companies);
			}
		} catch (CsvValidationException e) {
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	class Movie_companies {
		int id;
		int movie_id;
		int company_id;
		int company_type_id;
		String note;

		public int getPrimaryKey() {
			return this.id;
		}

		public Movie_companies(String[] data) {
			this.id = Integer.parseInt(data[0]);
			this.movie_id = Integer.parseInt(data[1]);
			this.company_id = Integer.parseInt(data[2]);
			this.company_type_id = Integer.parseInt(data[3]);
			this.note = data[4];
		}
	}
}
