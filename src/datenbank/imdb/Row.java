package datenbank.imdb;

public abstract class Row {

	public abstract int getPrimaryKey();

	public abstract <T> T get(String s);
}
