package datanbank.imdb;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;

public class Info_typeTable {

	private String path = "/Users/lili/Documents/Bachelor Thesis/imdb/info_type.csv";
	private HashMap<Integer, Info_type> data = new HashMap<Integer, Info_type>();

	public Info_typeTable() {
		convert();
	}

	private void convert() {
		try {
			CSVReader reader = new CSVReader(new FileReader(this.path));

			String[] nextLine;
			while ((nextLine = reader.readNext()) != null) {
				Info_type info_type = new Info_type(nextLine);
				this.data.put(info_type.getPrimaryKey(), info_type);
			}
		} catch (CsvValidationException e) {
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	class Info_type {
		int id;
		String info;

		public int getPrimaryKey() {
			return this.id;
		}

		public Info_type(String[] data) {
			this.id = Integer.parseInt(data[0]);
			this.info = data[1];
		}
	}
}
