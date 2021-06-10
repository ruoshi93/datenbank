package datenbank.imdb;

public abstract class RowDemo {

	public abstract int getPrimaryKey();

	public abstract <T> T get(String s);
}
