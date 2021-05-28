package datanbank.imdb;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;

public class Aka_nameTable {

	private String path = "/Users/lili/Documents/Bachelor Thesis/imdb/aka_name.csv";
	private HashMap<Integer, Aka_name> data = new HashMap<Integer, Aka_name>();

	public Aka_nameTable() {
		convert();
	}

	private void convert() {
		try {
			CSVReader reader = new CSVReader(new FileReader(this.path));

			String[] nextLine;
			while ((nextLine = reader.readNext()) != null) {
				Aka_name aka_name = new Aka_name(nextLine);
				this.data.put(aka_name.getPrimaryKey(), aka_name);
			}
		} catch (CsvValidationException e) {
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	class Aka_name {
		int id;
		int movie_id;
		String name;
		String imdb_index;
		String name_pcode_cf;
		String name_pcode_nf;
		String surname_pcode;
		String md5sum;

		public int getPrimaryKey() {
			return this.id;
		}

		public Aka_name(String[] data) {
			this.id = Integer.parseInt(data[0]);
			this.movie_id = Integer.parseInt(data[1]);
			this.name = data[2];
			this.imdb_index = data[3];
			this.name_pcode_cf = data[4];
			this.name_pcode_nf = data[5];
			this.surname_pcode = data[6];
			this.md5sum = data[7];
		}

	}
}
