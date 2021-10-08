package datenbank.imdb;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;

import datenbank.main.Row;
import datenbank.main.Table;

public class Movie_companiesTable extends Table {

	public Movie_companiesTable() {
		name = "movie_companies";
		path = "imdb/movie_companies.csv";
		title = new ArrayList<String>(
				Arrays.asList(new String[] { "id", "movie_id", "company_id", "company_type_id", "note" }));
		row = new Movie_companies();
		convert();
	}

	private void convert() {
		try {
			CSVReader reader = new CSVReader(new FileReader(this.path));

			String[] nextLine;
			int i = 0;
			while ((nextLine = reader.readNext()) != null) {
				Movie_companies movie_companies = new Movie_companies(nextLine);
				this.data.put(movie_companies.getPrimaryKey(), movie_companies);
				if (i % this.samplingSpace == 0) {
					this.example.put(movie_companies.getPrimaryKey(), movie_companies);
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

	class Movie_companies extends Row {
		private Integer id;
		private Integer movie_id;
		private Integer company_id;
		private Integer company_type_id;
		private String note;

		@Override
		public <T> T get(String s) {
			switch (s) {
			case "id":
				return (T) this.id;
			case "movie_id":
				return (T) this.movie_id;
			case "company_id":
				return (T) this.company_id;
			case "company_type_id":
				return (T) this.company_type_id;
			case "note":
				return (T) this.note;
			default:
				return null;
			}
		}

		public int getPrimaryKey() {
			return this.id;
		}

		public Movie_companies() {
		}

		public Movie_companies(String[] data) {
			this.id = this.parseStringToInt(data[0]);
			this.movie_id = this.parseStringToInt(data[1]);
			this.company_id = this.parseStringToInt(data[2]);
			this.company_type_id = this.parseStringToInt(data[3]);
			this.note = data[4];
		}
	}
}
