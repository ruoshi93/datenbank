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

public class Comp_cast_typeTable extends Table {

	public Comp_cast_typeTable() {
		name = "comp_cast_type";
		path = "imdb/comp_cast_type.csv";
		title = new ArrayList<String>(Arrays.asList(new String[] { "id", "kind" }));
		row = new Comp_cast_type();
		convert();
	}

	private void convert() {
		try {
			CSVReader reader = new CSVReader(new FileReader(this.path));

			String[] nextLine;
			while ((nextLine = reader.readNext()) != null) {
				Comp_cast_type comp_cast_type = new Comp_cast_type(nextLine);
				this.data.put(comp_cast_type.getPrimaryKey(), comp_cast_type);
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

	class Comp_cast_type extends Row {
		private Integer id;
		private String kind;

		@Override
		public <T> T get(String s) {
			switch (s) {
			case "id":
				return (T) this.id;
			case "kind":
				return (T) this.kind;
			default:
				return null;
			}
		}

		public int getPrimaryKey() {
			return this.id;
		}

		public Comp_cast_type() {
		}

		public Comp_cast_type(String[] data) {
			this.id = this.parseStringToInt(data[0]);
			this.kind = data[1];
		}
	}
}
