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

public class Complete_castTable extends Table {

	public Complete_castTable() {
		name = "complete_cast";
		path = "/Users/lili/Documents/Bachelor Thesis/imdb/complete_cast.csv";
		title = new ArrayList<String>( Arrays.asList(new String[] { "id", "movie_id", "subject_id", "status_id" }));
		row = new Complete_cast();
		convert();
	}

	private void convert() {
		try {
			CSVReader reader = new CSVReader(new FileReader(this.path));

			String[] nextLine;
			int i = 0;
			while ((nextLine = reader.readNext()) != null) {
				Complete_cast complete_cast = new Complete_cast(nextLine);
				this.data.put(complete_cast.getPrimaryKey(), complete_cast);
				if (i % this.samplingSpace == 0) {
					this.example.put(complete_cast.getPrimaryKey(), complete_cast);
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

	class Complete_cast extends Row {
		private Integer id;
		private Integer movie_id;
		private Integer subject_id;
		private Integer status_id;

		@Override
		public <T> T get(String s) {
			switch (s) {
			case "id":
				return (T) this.id;
			case "movie_id":
				return (T) this.movie_id;
			case "subject_id":
				return (T) this.subject_id;
			case "status_id":
				return (T) this.status_id;
			default:
				return null;
			}
		}

		public int getPrimaryKey() {
			return this.id;
		}

		public Complete_cast() {
		}

		public Complete_cast(String[] data) {
			this.id = this.parseStringToInt(data[0]);
			this.movie_id = this.parseStringToInt(data[1]);
			this.subject_id = this.parseStringToInt(data[2]);
			this.status_id = this.parseStringToInt(data[3]);
		}

	}
}
