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

public class Link_typeTable extends Table {

	public Link_typeTable() {
		name = "link_type";
		path = "/Users/lili/Documents/Bachelor Thesis/imdb/link_type.csv";
		title = new ArrayList<String>(Arrays.asList(new String[] { "id", "link" }));
		row = new Link_type();
		convert();
	}

	private void convert() {
		try {
			CSVReader reader = new CSVReader(new FileReader(this.path));

			String[] nextLine;
			while ((nextLine = reader.readNext()) != null) {
				Link_type link_type = new Link_type(nextLine);
				this.data.put(link_type.getPrimaryKey(), link_type);
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

	class Link_type extends Row {
		private Integer id;
		private String link;

		@Override
		public <T> T get(String s) {
			switch (s) {
			case "id":
				return (T) this.id;
			case "link":
				return (T) this.link;
			default:
				return null;
			}
		}

		public int getPrimaryKey() {
			return this.id;
		}

		public Link_type() {
		}

		public Link_type(String[] data) {
			this.id = this.parseStringToInt(data[0]);
			this.link = data[1];
		}

	}
}
