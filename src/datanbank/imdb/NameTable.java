package datanbank.imdb;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;

public class NameTable {

	private String path = "/Users/lili/Documents/Bachelor Thesis/imdb/name.csv";
	private HashMap<Integer, Name> data = new HashMap<Integer, Name>();

	public NameTable() {
		convert();
	}

	private void convert() {
		try {
			CSVReader reader = new CSVReader(new FileReader(this.path));

			String[] nextLine;
			while ((nextLine = reader.readNext()) != null) {
				Name name = new Name(nextLine);
				this.data.put(name.getPrimaryKey(), name);
			}
		} catch (CsvValidationException e) {
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	class Name {
		int id;
		String name;
		String imdb_index;
		int imdb_id;
		String gender;
		String name_pcode_cf;
		String name_pcode_nf;
		String surname_pcode;
		String md5sum;

		public int getPrimaryKey() {
			return this.id;
		}

		public Name(String[] data) {
			this.id = Integer.parseInt(data[0]);
			this.name = data[1];
			this.imdb_index = data[2];
			this.imdb_id = Integer.parseInt(data[3]);
			this.gender = data[4];
			this.name_pcode_cf = data[5];
			this.name_pcode_nf = data[6];
			this.surname_pcode = data[7];
			this.md5sum = data[8];
		}
	}
}
