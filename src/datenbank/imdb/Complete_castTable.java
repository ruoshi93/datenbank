package datenbank.imdb;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;

public class Complete_castTable {

	private String path = "/Users/lili/Documents/Bachelor Thesis/imdb/complete_cast.csv";
	private HashMap<Integer, Complete_cast> data = new HashMap<Integer, Complete_cast>();

	public Complete_castTable() {
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

	class Complete_cast {
		int id;
		int movie_id;
		int subject_id;
		int status_id;

		public int getPrimaryKey() {
			return this.id;
		}

		public Complete_cast(String[] data) {
			this.id = Integer.parseInt(data[0]);
			this.movie_id = Integer.parseInt(data[1]);
			this.status_id = Integer.parseInt(data[2]);
			this.status_id = Integer.parseInt(data[3]);
		}
	}
}
