package datenbank.imdb;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;

public class TitleTable extends TableDemo {

	public TitleTable() {
		name = "title";
		path = "/Users/lili/Documents/Bachelor Thesis/imdb/title.csv";
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

	class Title extends Row {
		private int id;
		private String title;
		private String imdb_index;
		private int kind_id;
		private int production_year;
		private int imdb_id;
		private String phonetic_code;
		private int episode_of_id;
		private int season_nr;
		private int episode_nr;
		private String series_years;
		private String md5sum;

		@Override
		public <T> T get(String s) {
			switch (s) {
			case "id":
				return (T) (Integer) this.id;
			case "title":
				return (T) this.title;
			case "imdb_index":
				return (T) this.imdb_index;
			case "kind_id":
				return (T) (Integer) this.kind_id;
			case "production_year":
				return (T) (Integer) this.production_year;
			case "imdb_id":
				return (T) (Integer) this.imdb_id;
			case "phonetic_code":
				return (T) this.phonetic_code;
			case "episode_of_id":
				return (T) (Integer) this.episode_of_id;
			case "season_nr":
				return (T) (Integer) this.season_nr;
			case "episode_nr":
				return (T) (Integer) this.episode_nr;
			case "series_years":
				return (T) this.series_years;
			case "md5sum":
				return (T) this.md5sum;
			default:
				return null;
			}
		}

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
