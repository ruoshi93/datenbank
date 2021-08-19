package datenbank.main;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class MyHashMap<K,V> extends HashMap<K,V> {
	
	private static final long serialVersionUID = 3523705904473627838L;

	@Override
	public String toString() {
		String s = "";
		Iterator<Map.Entry<K, V>> it = this.entrySet().iterator();
		while(it.hasNext()) {
			Map.Entry<K, V> me = it.next();
			s = s + me.getValue() + "\n";
		}
		return s;
	}

}
