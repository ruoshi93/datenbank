package datenbank.imdb;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;

public class Kind_typeTable {

	private String path = "/Users/lili/Documents/Bachelor Thesis/imdb/kind_type.csv";
	private HashMap<Integer, Kind_type> data = new HashMap<Integer, Kind_type>();

	public Kind_typeTable() {
		convert();
	}

	private void convert() {
		try {
			CSVReader reader = new CSVReader(new FileReader(this.path));

			String[] nextLine;
			while ((nextLine = reader.readNext()) != null) {
				Kind_type kind_type = new Kind_type(nextLine);
				this.data.put(kind_type.getPrimaryKey(), kind_type);
			}
		} catch (CsvValidationException e) {
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	class Kind_type {
		int id;
		String kind;

		public int getPrimaryKey() {
			return this.id;
		}

		public Kind_type(String[] data) {
			this.id = Integer.parseInt(data[0]);
			this.kind = data[1];
		}
	}
}
