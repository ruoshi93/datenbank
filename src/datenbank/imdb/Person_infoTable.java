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

public class Person_infoTable extends Table {

	public Person_infoTable() {
		name = "person_info";
		path = "imdb/person_info.csv";
		title = new ArrayList<String>(Arrays.asList(new String[] { "id", "person_id", "info_type_id", "info", "note" }));
		row = new Person_info();
		convert();
	}

	private void convert() {
		try {
			CSVReader reader = new CSVReader(new FileReader(this.path));

			String[] nextLine;
			int i = 0;
			while ((nextLine = reader.readNext()) != null) {
				Person_info person_info = new Person_info(nextLine);
				this.data.put(person_info.getPrimaryKey(), person_info);
				if (i % this.samplingSpace == 0) {
					this.example.put(person_info.getPrimaryKey(), person_info);
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

	class Person_info extends Row {
		private Integer id;
		private Integer person_id;
		private Integer info_type_id;
		private String info;
		private String note;

		@Override
		public <T> T get(String s) {
			switch (s) {
			case "id":
				return (T) this.id;
			case "person_id":
				return (T) this.person_id;
			case "info_type_id":
				return (T) this.info_type_id;
			case "info":
				return (T) this.info;
			case "note":
				return (T) this.note;
			default:
				return null;
			}
		}

		public int getPrimaryKey() {
			return this.id;
		}

		public Person_info() {
		}

		public Person_info(String[] data) {
			this.id = this.parseStringToInt(data[0]);
			this.person_id = this.parseStringToInt(data[1]);
			this.info_type_id = this.parseStringToInt(data[2]);
			this.info = data[3];
			this.note = data[4];
		}
	}
}
