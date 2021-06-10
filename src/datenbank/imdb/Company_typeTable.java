package datenbank.imdb;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;

public class Company_typeTable {

	private String path = "/Users/lili/Documents/Bachelor Thesis/imdb/company_type.csv";
	private HashMap<Integer, Company_type> data = new HashMap<Integer, Company_type>();

	public Company_typeTable() {
		convert();
	}

	private void convert() {
		try {
			CSVReader reader = new CSVReader(new FileReader(this.path));

			String[] nextLine;
			while ((nextLine = reader.readNext()) != null) {
				Company_type company_type = new Company_type(nextLine);
				this.data.put(company_type.getPrimaryKey(), company_type);
			}
		} catch (CsvValidationException e) {
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	class Company_type {
		int id;
		String kind;

		public int getPrimaryKey() {
			return this.id;
		}

		public Company_type(String[] data) {
			this.id = Integer.parseInt(data[0]);
			this.kind = data[1];
		}
	}
}
