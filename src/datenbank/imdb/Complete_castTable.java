package datenbank.imdb;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;

public class Complete_castTable extends TableDemo {

	public Complete_castTable() {
		name = "complete_cast";
		path = "/Users/lili/Documents/Bachelor Thesis/imdb/complete_cast.csv";
		convert();
	}

	private void convert() {
		try {
			CSVReader reader = new CSVReader(new FileReader(this.path));

			String[] nextLine;
			while ((nextLine = reader.readNext()) != null) {
				Complete_cast complete_cast = new Complete_cast(nextLine);
				this.data.put(complete_cast.getPrimaryKey(), complete_cast);
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
		private int id;
		private int movie_id;
		private int subject_id;
		private int status_id;

		@Override
		public <T> T get(String s) {
			switch (s) {
			case "id":
				return (T) (Integer) this.id;
			case "movie_id":
				return (T) (Integer) this.movie_id;
			case "subject_id":
				return (T) (Integer) this.subject_id;
			case "status_id":
				return (T) (Integer) this.status_id;
			default:
				return null;
			}
		}

		public int getPrimaryKey() {
			return this.id;
		}

		public Complete_cast(String[] data) {
			this.id = this.parseStringToInt(data[0]);
			this.movie_id = this.parseStringToInt(data[1]);
			this.status_id = this.parseStringToInt(data[2]);
			this.status_id = this.parseStringToInt(data[3]);
		}

	}
}
