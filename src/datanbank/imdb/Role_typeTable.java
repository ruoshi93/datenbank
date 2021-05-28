package datanbank.imdb;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;

public class Role_typeTable {

	private String path = "/Users/lili/Documents/Bachelor Thesis/imdb/role_type.csv";
	private HashMap<Integer, Role_type> data = new HashMap<Integer, Role_type>();

	public Role_typeTable() {
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

	class Role_type {
		int id;
		String role;

		public int getPrimaryKey() {
			return this.id;
		}

		public Role_type(String[] data) {
			this.id = Integer.parseInt(data[0]);
			this.role = data[1];
		}
	}
}
