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

public class Company_nameTable extends Table {

	public Company_nameTable() {
		name = "company_name";
		path = "imdb/company_name.csv";
		title = new ArrayList<String>(Arrays.asList(new String[] { "id", "name", "country_code", "imdb_id", "name_pcode_nf", "name_pcode_sf", "md5sum" }));
		row = new Company_name();
		convert();
	}

	protected void convert() {
		try {
			CSVReader reader = new CSVReader(new FileReader(this.path));

			String[] nextLine;
			int i = 0;
			while ((nextLine = reader.readNext()) != null) {
				Company_name company_name = new Company_name(nextLine);
				this.data.put(company_name.getPrimaryKey(), company_name);
				if (i % this.samplingSpace == 0) {
					this.example.put(company_name.getPrimaryKey(), company_name);
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

	class Company_name extends Row {
		private Integer id;
		private String name;
		private String country_code;
		private Integer imdb_id;
		private String name_pcode_nf;
		private String name_pcode_sf;
		private String md5sum;

		public <T> T get(String s) {
			switch (s) {
			case "id":
				return (T) (Integer) this.id;
			case "name":
				return (T) this.name;
			case "country_code":
				return (T) this.country_code;
			case "imdb_id":
				return (T) (Integer) this.imdb_id;
			case "name_pcode_nf":
				return (T) this.name_pcode_nf;
			case "name_pcode_sf":
				return (T) this.name_pcode_sf;
			case "md5sum":
				return (T) this.md5sum;
			default:
				return null;
			}
		}

		public int getPrimaryKey() {
			return this.id;
		}

		public Company_name() {
		}

		public Company_name(String[] data) {
			this.id = this.parseStringToInt(data[0]);
			this.name = data[1];
			this.country_code = data[2];
			this.imdb_id = this.parseStringToInt(data[3]);
			this.name_pcode_nf = data[4];
			this.name_pcode_sf = data[5];
			this.md5sum = data[6];
		}
	}
}
