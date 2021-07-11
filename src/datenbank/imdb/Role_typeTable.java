package datenbank.imdb;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;

public class Role_typeTable extends TableDemo {

	public Role_typeTable() {
		name = "role_type";
		path = "/Users/lili/Documents/Bachelor Thesis/imdb/role_type.csv";
		convert();
	}

	private void convert() {
		try {
			CSVReader reader = new CSVReader(new FileReader(this.path));

			String[] nextLine;
			while ((nextLine = reader.readNext()) != null) {
				Role_type role_type = new Role_type(nextLine);
				this.data.put(role_type.getPrimaryKey(), role_type);
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
		private int id;
		private String role;

		@Override
		public <T> T get(String s) {
			switch (s) {
			case "id":
				return (T) (Integer) this.id;
			case "role":
				return (T) this.role;
			default:
				return null;
			}
		}

		public int getPrimaryKey() {
			return this.id;
		}

		public Role_type(String[] data) {
			this.id = this.parseStringToInt(data[0]);
			this.role = data[1];
		}

	}
}
