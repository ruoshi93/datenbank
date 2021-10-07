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

public class Aka_titleTable extends Table {

	public Aka_titleTable() {
		name = "aka_title";
		path = "/Users/lili/Documents/Bachelor Thesis/imdb/aka_title.csv";
		title = new ArrayList<String>(Arrays.asList(new String[] { "id", "movie_id", "title", "imdb_index", "kind_id", "production_year", "phonetic_code",
				"episode_of_id", "season_nr", "episode_nr", "note", "md5sum" }));
		row = new Aka_title();
		convert();
	}

	private void convert() {
		try {
			CSVReader reader = new CSVReader(new FileReader(this.path));

			String[] nextLine;
			int i = 0;
			while ((nextLine = reader.readNext()) != null) {
				Aka_title aka_title = new Aka_title(nextLine);
				this.data.put(aka_title.getPrimaryKey(), aka_title);
				if (i % this.samplingSpace == 0) {
					this.example.put(aka_title.getPrimaryKey(), aka_title);
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

	class Aka_title extends Row {
		private Integer id;
		private Integer movie_id;
		private String title;
		private String imdb_index;
		private Integer kind_id;
		private Integer production_year;
		private String phonetic_code;
		private Integer episode_of_id;
		private Integer season_nr;
		private Integer episode_nr;
		private String note;
		private String md5sum;

		@Override
		public <T> T get(String s) {
			switch (s) {
			case "id":
				return (T) this.id;
			case "movie_id":
				return (T) this.movie_id;
			case "title":
				return (T) this.title;
			case "imdb_index":
				return (T) this.imdb_index;
			case "kind_id":
				return (T) this.kind_id;
			case "production_year":
				return (T) this.production_year;
			case "phonetic_code":
				return (T) this.phonetic_code;
			case "episode_of_id":
				return (T) this.episode_of_id;
			case "season_nr":
				return (T) this.season_nr;
			case "episode_nr":
				return (T) this.episode_nr;
			case "note":
				return (T) this.note;
			case "md5sum":
				return (T) this.md5sum;
			default:
				return null;
			}
		}

		public int getPrimaryKey() {
			return this.id;
		}

		public Aka_title() {
		}

		public Aka_title(String[] data) {
			this.id = this.parseStringToInt(data[0]);
			this.movie_id = this.parseStringToInt(data[1]);
			this.title = data[2];
			this.imdb_index = data[3];
			this.kind_id = this.parseStringToInt(data[4]);
			this.production_year = this.parseStringToInt(data[5]);
			this.phonetic_code = data[6];
			this.episode_of_id = this.parseStringToInt(data[7]);
			this.season_nr = this.parseStringToInt(data[8]);
			this.episode_nr = this.parseStringToInt(data[9]);
			this.note = data[10];
			this.md5sum = data[11];
		}
	}
}
