package datanbank.imdb;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;

public class TitleTable {

	private String path = "/Users/lili/Documents/Bachelor Thesis/imdb/title.csv";
	private HashMap<Integer, Title> data = new HashMap<Integer, Title>();

	public TitleTable() {
		convert();
	}

	private void convert() {
		try {
			CSVReader reader = new CSVReader(new FileReader(this.path));

			String[] nextLine;
			while ((nextLine = reader.readNext()) != null) {
				Title title = new Title(nextLine);
				this.data.put(title.getPrimaryKey(), title);
			}
		} catch (CsvValidationException e) {
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	class Title {
		int id;
		String title;
		String imdb_index;
		int kind_id;
		int production_year;
		int imdb_id;
		String phonetic_code;
		int episode_of_id;
		int season_nr;
		int episode_nr;
		String series_years;
		String md5sum;

		public int getPrimaryKey() {
			return this.id;
		}

		public Title(String[] data) {
			this.id = Integer.parseInt(data[0]);
			this.title = data[1];
			this.imdb_index = data[2];
			this.kind_id = Integer.parseInt(data[3]);
			this.production_year = Integer.parseInt(data[4]);
			this.imdb_id = Integer.parseInt(data[5]);
			this.phonetic_code = data[6];
			this.episode_of_id = Integer.parseInt(data[7]);
			this.season_nr = Integer.parseInt(data[8]);
			this.episode_nr = Integer.parseInt(data[9]);
			this.series_years = data[10];
			this.md5sum = data[11];
		}
	}
}
