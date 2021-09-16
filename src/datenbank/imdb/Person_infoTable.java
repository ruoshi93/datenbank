package datenbank.imdb;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;

import datenbank.main.Row;
import datenbank.main.Table;

public class Person_infoTable extends Table {

	public Person_infoTable() {
		name = "person_info";
		path = "/Users/lili/Documents/Bachelor Thesis/imdb/person_info.csv";
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
				if (i % 7 == 0) {
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
		private int id;
		private int person_id;
		private int info_type_id;
		private String info;
		private String note;

		@Override
		public <T> T get(String s) {
			switch (s) {
			case "id":
				return (T) (Integer) this.id;
			case "person_id":
				return (T) (Integer) this.person_id;
			case "info_type_id":
				return (T) (Integer) this.info_type_id;
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
