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
	
}
