package datanbank.imdb;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;

public class Comp_cast_typeTable {

	private String path = "/Users/lili/Documents/Bachelor Thesis/imdb/comp_cast_type.csv";
	private HashMap<Integer, Comp_cast_type> data = new HashMap<Integer, Comp_cast_type>();

	public Comp_cast_typeTable() {
		convert();
	}

	private void convert() {
		try {
			CSVReader reader = new CSVReader(new FileReader(this.path));

			String[] nextLine;
			while ((nextLine = reader.readNext()) != null) {
				Comp_cast_type comp_cast_type = new Comp_cast_type(nextLine);
				this.data.put(comp_cast_type.getPrimaryKey(), comp_cast_type);
			}
		} catch (CsvValidationException e) {
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	class Comp_cast_type {
		int id;
		String kind;

		public int getPrimaryKey() {
			return this.id;
		}

		public Comp_cast_type(String[] data) {
			this.id = Integer.parseInt(data[0]);
			this.kind = data[1];
		}
	}
}
