package datenbank.imdb;

public abstract class Row {

	public abstract int getPrimaryKey();

	public abstract <T> T get(String s);
	
	
	protected int parseStringToInt(String s) {
		if(s.equals("")) {
			return 0;
		}else {
			return Integer.parseInt(s);
		}
	}
}
