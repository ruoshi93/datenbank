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

public class Info_typeTable extends Table {

	public Info_typeTable() {
		name = "info_type";
		path = "imdb/info_type.csv";
		title = new ArrayList<String>(Arrays.asList(new String[] { "id", "info" }));
		row = new Info_type();
		convert();
	}

	private void convert() {
		try {
			CSVReader reader = new CSVReader(new FileReader(this.path));

			String[] nextLine;
			while ((nextLine = reader.readNext()) != null) {
				Info_type info_type = new Info_type(nextLine);
				this.data.put(info_type.getPrimaryKey(), info_type);
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

	class Info_type extends Row {
		private Integer id;
		private String info;

		@Override
		public <T> T get(String s) {
			switch (s) {
			case "id":
				return (T) this.id;
			case "info":
				return (T) this.info;
			default:
				return null;
			}
		}

		public int getPrimaryKey() {
			return this.id;
		}

		public Info_type() {
		}

		public Info_type(String[] data) {
			this.id = this.parseStringToInt(data[0]);
			this.info = data[1];
		}

	}
}
