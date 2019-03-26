package textExcel;

public class FormulaCell extends RealCell {
	
	public FormulaCell(String value) {
		super(value);
	}
	
	@Override
	public String abbreviatedCellText() {
		return super.abbreviatedCellText();
	}
	
	@Override
	public String fullCellText() {
		return super.fullCellText();
	}
	
	@Override
	public double getDoubleValue() {
		return 0;
	}
}
