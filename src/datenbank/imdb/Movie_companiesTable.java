package datenbank.imdb;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;

import datenbank.main.Row;
import datenbank.main.Table;

public class Movie_companiesTable extends Table {

	public Movie_companiesTable() {
		name = "movie_companies";
		path = "/Users/lili/Documents/Bachelor Thesis/imdb/movie_companies.csv";
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
		private int id;
		private int movie_id;
		private int company_id;
		private int company_type_id;
		private String note;

		@Override
		public <T> T get(String s) {
			switch (s) {
			case "id":
				return (T) (Integer) this.id;
			case "movie_id":
				return (T) (Integer) this.movie_id;
			case "company_id":
				return (T) (Integer) this.company_id;
			case "company_type_id":
				return (T) (Integer) this.company_type_id;
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
