package datanbank.imdb;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;

public class Person_infoTable {

	private String path = "/Users/lili/Documents/Bachelor Thesis/imdb/person_info.csv";
	private HashMap<Integer, Person_info> data = new HashMap<Integer, Person_info>();

	public Person_infoTable() {
		convert();
	}

	private void convert() {
		try {
			CSVReader reader = new CSVReader(new FileReader(this.path));

			String[] nextLine;
			while ((nextLine = reader.readNext()) != null) {
				Person_info person_info = new Person_info(nextLine);
				this.data.put(person_info.getPrimaryKey(), person_info);
			}
		} catch (CsvValidationException e) {
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	class Person_info {
		int id;
		int person_id;
		int info_type_id;
		String info;
		String note;

		public int getPrimaryKey() {
			return this.id;
		}

		public Person_info(String[] data) {
			this.id = Integer.parseInt(data[0]);
			this.person_id = Integer.parseInt(data[1]);
			this.info_type_id = Integer.parseInt(data[2]);
			this.info = data[3];
			this.note = data[4];
		}
	}
}
