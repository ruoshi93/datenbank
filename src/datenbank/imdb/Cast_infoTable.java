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

public class Cast_infoTable extends Table {

	public Cast_infoTable() {
		name = "cast_info";
		path = "/Users/lili/Documents/Bachelor Thesis/imdb/cast_info.csv";
		title = new ArrayList<String> (Arrays.asList(new String[] { "id", "person_id", "movie_id", "person_role_id", "note", "nr_order", "role_id" }));
		row = new Cast_info();
		convert();
	}

	private void convert() {
		try {
			CSVReader reader = new CSVReader(new FileReader(this.path));

			String[] nextLine;
			int i = 0;
			while ((nextLine = reader.readNext()) != null) {
				Cast_info cast_info = new Cast_info(nextLine);
				this.data.put(cast_info.getPrimaryKey(), cast_info);
				if (i % this.samplingSpace == 0) {
					this.example.put(cast_info.getPrimaryKey(), cast_info);
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

	class Cast_info extends Row {
		private Integer id;
		private Integer person_id;
		private Integer movie_id;
		private Integer person_role_id;
		private String note;
		private Integer nr_order;
		private Integer role_id;

		@Override
		public <T> T get(String s) {
			switch (s) {
			case "id":
				return (T) this.id;
			case "person_id":
				return (T) this.person_id;
			case "movie_id":
				return (T) this.movie_id;
			case "person_role_id":
				return (T) this.person_role_id;
			case "note":
				return (T) this.note;
			case "nr_order":
				return (T) this.nr_order;
			case "role_id":
				return (T) this.role_id;
			default:
				return null;

			}
		}

		public int getPrimaryKey() {
			return this.id;
		}

		public Cast_info() {
		}

		public Cast_info(String[] data) {
			this.id = this.parseStringToInt(data[0]);
			this.person_id = this.parseStringToInt(data[1]);
			this.movie_id = this.parseStringToInt(data[2]);
			this.person_role_id = this.parseStringToInt(data[3]);
			this.note = data[4];
			this.nr_order = this.parseStringToInt(data[5]);
			this.role_id = this.parseStringToInt(data[6]);
		}

	}
}
