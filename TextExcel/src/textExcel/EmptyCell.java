package textExcel;

public class EmptyCell implements Cell {

	@Override
	public String abbreviatedCellText() { // text for spreadsheet cell display, must be exactly length 10
		// TODO Auto-generated method stub
		return "          ";
	}

	@Override
	public String fullCellText() { // text for individual cell inspection, not truncated or padded
		// TODO Auto-generated method stub
		return "";
	}

}
