package datanbank.imdb;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;

public class Company_nameTable {
	
	private String path = "/Users/lili/Documents/Bachelor Thesis/imdb/company_name.csv";
	private HashMap<Integer,Company_name> data = new HashMap<Integer,Company_name>();
	
	public Company_nameTable() {
		convert();
	}
	
	private void convert() {
		try {
			CSVReader reader = new CSVReader(new FileReader(this.path));

			String[] nextLine;
			while ((nextLine = reader.readNext()) != null) {
				Company_name company_name = new Company_name(nextLine);
				this.data.put(company_name.getPrimaryKey(), company_name);
			}
		} catch (CsvValidationException e) {
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	class Company_name {
	    int id;
	    String name;
	    String country_code;
	    int imdb_id;
	    String name_pcode_nf;
	    String name_pcode_sf;
	    String md5sum;
	    
	    
	    public int getPrimaryKey() {
	    	return this.id;
	    }
	    
	    public Company_name(String[] data) {
	    	this.id=Integer.parseInt(data[0]);
	    	this.name=data[1];
	    	this.country_code=data[2];
	    	this.imdb_id=Integer.parseInt(data[3]);
	    	this.name_pcode_nf=data[4];
	    	this.name_pcode_sf=data[5];
	    	this.md5sum=data[6];
	    }
	}
}
