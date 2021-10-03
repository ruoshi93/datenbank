package datenbank.imdb;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;

import datenbank.main.Row;
import datenbank.main.Table;

public class Aka_nameTable extends Table {

	public Aka_nameTable() {
		name = "aka_name";
		path = "/Users/lili/Documents/Bachelor Thesis/imdb/aka_name.csv";
		row = new Aka_name();
		convert();
	}

	private void convert() {
		try {
			CSVReader reader = new CSVReader(new FileReader(this.path));

			String[] nextLine;
			int i = 0;
			while ((nextLine = reader.readNext()) != null) {
				Aka_name aka_name = new Aka_name(nextLine);
				this.data.put(aka_name.getPrimaryKey(), aka_name);
				if (i % this.samplingSpace == 0) {
					this.example.put(aka_name.getPrimaryKey(), aka_name);
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

	class Aka_name extends Row {
		private int id;
		private int movie_id;
		private String name;
		private String imdb_index;
		private String name_pcode_cf;
		private String name_pcode_nf;
		private String surname_pcode;
		private String md5sum;

		@Override
		public <T> T get(String s) {
			switch (s) {
			case "id":
				return (T) (Integer) this.id;
			case "movie_id":
				return (T) (Integer) this.movie_id;
			case "name":
				return (T) this.name;
			case "imdb_index":
				return (T) this.imdb_index;
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

		public Aka_name() {
		}

		public Aka_name(String[] data) {
			this.id = this.parseStringToInt(data[0]);
			this.movie_id = this.parseStringToInt(data[1]);
			this.name = data[2];
			this.imdb_index = data[3];
			this.name_pcode_cf = data[4];
			this.name_pcode_nf = data[5];
			this.surname_pcode = data[6];
			this.md5sum = data[7];
		}

	}
}
