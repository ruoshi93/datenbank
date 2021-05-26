
public class Cell {

	
	private String value;

	
	public Cell(String value) {
		this.value=value;
	}
	
	public String getValue() {
		return this.value;
	}
	
	@Override
	public boolean equals(Object o) {
		if(o==this) {
			return true;
		}
		if(o==null) {
			return false;
		}
		if(this.getClass()!=o.getClass()) {
			return false;
		}
		if(((Cell) o).getValue()==this.value) {
			return true;
		}else {
			return false;
		}
	}
	
	@Override
	public int hashCode() {
		int result = 23;
		
		result = 31 * result + (value == null ? 0 : value.hashCode());
		
		return result;
	}
	
	
	
}
