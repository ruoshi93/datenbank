package datenbank.main;

public abstract class Row {

	public abstract int getPrimaryKey();

	public abstract <T> T get(String s);
	
	
	protected Integer parseStringToInt(String s) {
		if(s.equals("")) {
			return null;
		}else {
			return Integer.parseInt(s);
		}
	}
}
