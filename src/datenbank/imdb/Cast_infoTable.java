package datenbank.imdb;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;

public class Cast_infoTable extends TableDemo {

	public Cast_infoTable() {
		name = "cast_info";
		path = "/Users/lili/Documents/Bachelor Thesis/imdb/cast_info.csv";
		convert();
	}

	private void convert() {
		try {
			CSVReader reader = new CSVReader(new FileReader(this.path));

			String[] nextLine;
			while ((nextLine = reader.readNext()) != null) {
				Cast_info cast_info = new Cast_info(nextLine);
				this.data.put(cast_info.getPrimaryKey(), cast_info);
			}
		} catch (CsvValidationException e) {
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	class Cast_info extends Row {
		private int id;
		private int person_id;
		private int movie_id;
		private int person_role_id;
		private String note;
		private int nr_order;
		private int role_id;

		@Override
		public <T> T get(String s) {
			switch (s) {
			case "id":
				return (T) (Integer) this.id;
			case "person_id":
				return (T) (Integer) this.person_id;
			case "movie_id":
				return (T) (Integer) this.movie_id;
			case "person_role_id":
				return (T) (Integer) this.person_role_id;
			case "note":
				return (T) this.note;
			case "nr_order":
				return (T) (Integer) this.nr_order;
			case "role_id":
				return (T) (Integer) this.role_id;
			default:
				return null;

			}
		}

		public int getPrimaryKey() {
			return this.id;
		}

		public Cast_info(String[] data) {
			this.id = Integer.parseInt(data[0]);
			this.person_id = Integer.parseInt(data[1]);
			this.movie_id = Integer.parseInt(data[2]);
			;
			this.person_role_id = Integer.parseInt(data[3]);
			this.note = data[4];
			this.nr_order = Integer.parseInt(data[5]);
			this.role_id = Integer.parseInt(data[6]);
		}

	}
}
