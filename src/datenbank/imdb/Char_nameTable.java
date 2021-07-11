package datenbank.imdb;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;

public class Char_nameTable extends TableDemo {

	public Char_nameTable() {
		name = "char_name";
		path = "/Users/lili/Documents/Bachelor Thesis/imdb/char_name.csv";
		convert();
	}

	private void convert() {
		try {
			CSVReader reader = new CSVReader(new FileReader(this.path));

			String[] nextLine;
			while ((nextLine = reader.readNext()) != null) {
				Char_name char_name = new Char_name(nextLine);
				this.data.put(char_name.getPrimaryKey(), char_name);
			}
		} catch (CsvValidationException e) {
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	class Char_name extends Row {
		private int id;
		private String name;
		private String imdb_index;
		private int imdb_id;
		private String name_pcode_nf;
		private String surname_pcode;
		private String md5sum;

		@Override
		public <T> T get(String s) {
			switch (s) {
			case "id":
				return (T) (Integer) this.id;
			case "name":
				return (T) this.name;
			case "imdb_index":
				return (T) this.imdb_index;
			case "imdb_id":
				return (T) (Integer) this.imdb_id;
			case "name_pcode_nf":
				return (T) this.name_pcode_nf;
			case "surname_pcode":
				return (T) this.surname_pcode;
			case "md5sum":
				return (T) this.md5sum;
			default:
				return null;
			}
		}

		public int getPrimaryKey() {
			return this.id;
		}

		public Char_name(String[] data) {
			this.id = this.parseStringToInt(data[0]);
			this.name = data[1];
			this.imdb_index = data[2];
			this.imdb_id = this.parseStringToInt(data[3]);
			this.name_pcode_nf = data[4];
			this.surname_pcode = data[5];
			this.md5sum = data[6];
		}

	}
}
