package datanbank.imdb;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;

public class Cast_infoTable {

	private String path = "/Users/lili/Documents/Bachelor Thesis/imdb/cast_info.csv";
	private HashMap<Integer, Cast_info> data = new HashMap<Integer, Cast_info>();

	public Cast_infoTable() {
		convert();
	}

	private void convert() {
		try {
			CSVReader reader = new CSVReader(new FileReader(this.path));

			String[] nextLine;
			while ((nextLine = reader.readNext()) != null) {
				Cast_info cast_info = new Cast_info(nextLine);
				this.data.put(cast_info.getPrimaryKey(), cast_info);
			}
		} catch (CsvValidationException e) {
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	class Cast_info {
		int id;
		int person_id;
		int movie_id;
		int person_role_id;
		String note;
		int nr_order;
		int role_id;

		public int getPrimaryKey() {
			return this.id;
		}

		public Cast_info(String[] data) {
			this.id = Integer.parseInt(data[0]);
			this.person_id = Integer.parseInt(data[1]);
			this.movie_id = Integer.parseInt(data[2]);
			;
			this.person_role_id = Integer.parseInt(data[3]);
			this.note = data[4];
			this.nr_order = Integer.parseInt(data[5]);
			this.role_id = Integer.parseInt(data[6]);
		}
	}
}
