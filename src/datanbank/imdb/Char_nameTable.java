package datanbank.imdb;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;

public class Char_nameTable {

	private String path = "/Users/lili/Documents/Bachelor Thesis/imdb/char_name.csv";
	private HashMap<Integer, Char_name> data = new HashMap<Integer, Char_name>();

	public Char_nameTable() {
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

	class Char_name {
		int id;
		String name;
		String imdb_index;
		int imdb_id;
		String name_pcode_nf;
		String surname_pcode;
		String md5sum;

		public int getPrimaryKey() {
			return this.id;
		}

		public Char_name(String[] data) {
			this.id = Integer.parseInt(data[0]);
			this.name = data[1];
			this.imdb_index = data[2];
			this.imdb_id = Integer.parseInt(data[3]);
			this.name_pcode_nf = data[4];
			this.surname_pcode = data[5];
			this.md5sum = data[6];
		}
	}
}
