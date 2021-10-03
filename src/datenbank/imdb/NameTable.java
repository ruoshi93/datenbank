package datenbank.imdb;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;

import datenbank.main.Row;
import datenbank.main.Table;

public class NameTable extends Table {

	public NameTable() {
		name = "name";
		path = "/Users/lili/Documents/Bachelor Thesis/imdb/name.csv";
		row = new Name();
		convert();
	}

	private void convert() {
		try {
			CSVReader reader = new CSVReader(new FileReader(this.path));

			String[] nextLine;
			int i = 0;
			while ((nextLine = reader.readNext()) != null) {
				Name name = new Name(nextLine);
				this.data.put(name.getPrimaryKey(), name);
				if (i % this.samplingSpace == 0) {
					this.example.put(name.getPrimaryKey(), name);
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

	class Name extends Row {
		private int id;
		private String name;
		private String imdb_index;
		private int imdb_id;
		private String gender;
		private String name_pcode_cf;
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
			case "gender":
				return (T) this.gender;
			case "name_pcode_cf":
				return (T) this.name_pcode_cf;
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

		public Name() {
		}

		public Name(String[] data) {
			this.id = this.parseStringToInt(data[0]);
			this.name = data[1];
			this.imdb_index = data[2];
			this.imdb_id = this.parseStringToInt(data[3]);
			this.gender = data[4];
			this.name_pcode_cf = data[5];
			this.name_pcode_nf = data[6];
			this.surname_pcode = data[7];
			this.md5sum = data[8];
		}

	}
}
