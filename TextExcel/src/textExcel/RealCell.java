package textExcel;

public class RealCell implements Cell {
	
	private String value;
	
	public RealCell(String value) {
		this.value = value.replaceAll("\\s+", "");
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
