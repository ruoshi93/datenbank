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

public class TitleTable extends Table {

	public TitleTable() {
		name = "title";
		path = "imdb/title.csv";
		title = new ArrayList<String>(
				Arrays.asList(new String[] { "id", "title", "imdb_index", "kind_id", "production_year", "imdb_id",
						"phonetic_code", "episode_of_id", "season_nr", "episode_nr", "series_years", "md5sum" }));
		row = new Title();

		convert();

	}

	private void convert() {
		try {
			CSVReader reader = new CSVReader(new FileReader(this.path));

			String[] nextLine;
			while ((nextLine = reader.readNext()) != null) {
				Title title = new Title(nextLine);
				this.data.put(title.getPrimaryKey(), title);

				double random = this.r.nextDouble();
				if (this.lowerBound <= random && random < this.upperBound) {
					this.example.put(title.getPrimaryKey(), title);
				}
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
		private Integer id;
		private String title;
		private String imdb_index;
		private Integer kind_id;
		private Integer production_year;
		private Integer imdb_id;
		private String phonetic_code;
		private Integer episode_of_id;
		private Integer season_nr;
		private Integer episode_nr;
		private String series_years;
		private String md5sum;

		@Override
		public <T> T get(String s) {
			switch (s) {
			case "id":
				return (T) this.id;
			case "title":
				return (T) this.title;
			case "imdb_index":
				return (T) this.imdb_index;
			case "kind_id":
				return (T) this.kind_id;
			case "production_year":
				return (T) this.production_year;
			case "imdb_id":
				return (T) this.imdb_id;
			case "phonetic_code":
				return (T) this.phonetic_code;
			case "episode_of_id":
				return (T) this.episode_of_id;
			case "season_nr":
				return (T) this.season_nr;
			case "episode_nr":
				return (T) this.episode_nr;
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

		// TODO Delete or modify toString()
		public String toString() {
			return " | " + this.id + " | " + this.title + " | " + this.imdb_index + " | " + this.kind_id + " | "
					+ this.production_year + " | " + this.imdb_id + " | " + this.phonetic_code + " | "
					+ this.episode_of_id + " | " + this.season_nr + " | " + this.episode_nr + " | " + this.series_years
					+ " | " + this.md5sum + " | ";
		}

		public Title() {
		}

		public Title(String[] data) {
			this.id = this.parseStringToInt(data[0]);
			this.title = data[1];
			this.imdb_index = data[2];
			this.kind_id = this.parseStringToInt(data[3]);
			this.production_year = this.parseStringToInt(data[4]);
			this.imdb_id = this.parseStringToInt(data[5]);
			this.phonetic_code = data[6];
			this.episode_of_id = this.parseStringToInt(data[7]);
			this.season_nr = this.parseStringToInt(data[8]);
			this.episode_nr = this.parseStringToInt(data[9]);
			this.series_years = data[10];
			this.md5sum = data[11];
		}
	}
}
