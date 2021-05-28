package datanbank.imdb;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;

public class Aka_titleTable {
	
	private String path = "/Users/lili/Documents/Bachelor Thesis/imdb/aka_title.csv";
	private HashMap<Integer,Aka_title> data = new HashMap<Integer,Aka_title>();
	
	public Aka_titleTable() {
		convert();
	}
	
	private void convert() {
		try {
			CSVReader reader = new CSVReader(new FileReader(this.path));

			String[] nextLine;
			while ((nextLine = reader.readNext()) != null) {
				Aka_title aka_title = new Aka_title(nextLine);
				this.data.put(aka_title.getPrimaryKey(), aka_title);
			}
		} catch (CsvValidationException e) {
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	class Aka_title {
	    int id;
	    int movie_id;
	    String title;
	    String imdb_index;
	    int kind_id;
	    int production_year;
	    String phonetic_code;
	    int episode_of_id;
	    int season_nr;
	    int episode_nr;
	    String note;
	    String md5sum;
	    
	    public int getPrimaryKey() {
	    	return this.id;
	    }
	    
	    public Aka_title(String[] data) {
	    	this.id=Integer.parseInt(data[0]);
	    	this.movie_id=Integer.parseInt(data[1]);
	    	this.title=data[2];
	    	this.imdb_index=data[3];
	    	this.kind_id=Integer.parseInt(data[4]);
	    	this.production_year=Integer.parseInt(data[5]);
	    	this.phonetic_code=data[6];
	    	this.episode_of_id=Integer.parseInt(data[7]);
	    	this.season_nr=Integer.parseInt(data[8]);
	    	this.episode_nr=Integer.parseInt(data[9]);
	    	this.note=data[10];
	    	this.md5sum=data[11];
	    }
	}
}
