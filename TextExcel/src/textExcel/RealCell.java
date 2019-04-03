package textExcel;
/*
 * @author Aaron Yu
 * @version March 2019
 * Superclass that stores a double value
 */

public class RealCell implements Cell {
	
	private String value;
	
	public RealCell(String value) {
		this.value = value;
	}

	@Override
	public String abbreviatedCellText() {
		String s = value + "          ";
		return s.substring(0, 10);
	}

	@Override
	public String fullCellText() {
		return value;
	}
	
	public double getDoubleValue() {
		return Double.parseDouble(value);
	}

	@Override
	public int compareTo(Object o) {
		if(o instanceof RealCell) {
			RealCell r = (RealCell) o;
			if(this.getDoubleValue() == r.getDoubleValue()) {
				return 0;
			} else {
				return r.getDoubleValue() > this.getDoubleValue() ? -1 : 1;
			}
		}
		return 1;
	}
	
}
