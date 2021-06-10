package datenbank.imdb;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;

public class Link_typeTable {

	private String path = "/Users/lili/Documents/Bachelor Thesis/imdb/link_type.csv";
	private HashMap<Integer, Link_type> data = new HashMap<Integer, Link_type>();

	public Link_typeTable() {
		convert();
	}

	private void convert() {
		try {
			CSVReader reader = new CSVReader(new FileReader(this.path));

			String[] nextLine;
			while ((nextLine = reader.readNext()) != null) {
				Link_type link_type = new Link_type(nextLine);
				this.data.put(link_type.getPrimaryKey(), link_type);
			}
		} catch (CsvValidationException e) {
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	class Link_type {
		int id;
		String link;

		public int getPrimaryKey() {
			return this.id;
		}

		public Link_type(String[] data) {
			this.id = Integer.parseInt(data[0]);
			this.link = data[1];
		}
	}
}
