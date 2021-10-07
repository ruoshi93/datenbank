package datenbank.imdb;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;

import datenbank.main.Row;
import datenbank.main.Table;

public class Role_typeTable extends Table {

	public Role_typeTable() {
		name = "role_type";
		path = "/Users/lili/Documents/Bachelor Thesis/imdb/role_type.csv";
		title = new String[] { "id", "role" };
		row = new Role_type();
		convert();
	}

	private void convert() {
		try {
			CSVReader reader = new CSVReader(new FileReader(this.path));

			String[] nextLine;
			while ((nextLine = reader.readNext()) != null) {
				Role_type role_type = new Role_type(nextLine);
				this.data.put(role_type.getPrimaryKey(), role_type);
				this.example = this.data;
			}
		} catch (CsvValidationException e) {
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	class Role_type extends Row {
		private Integer id;
		private String role;

		@Override
		public <T> T get(String s) {
			switch (s) {
			case "id":
				return (T) this.id;
			case "role":
				return (T) this.role;
			default:
				return null;
			}
		}

		public int getPrimaryKey() {
			return this.id;
		}

		public Role_type() {
		}

		public Role_type(String[] data) {
			this.id = this.parseStringToInt(data[0]);
			this.role = data[1];
		}

	}
}
