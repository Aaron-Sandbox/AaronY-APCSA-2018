package textExcel;
/*
 * @author Aaron Yu
 * @version March 2019
 * Class to handle empty cells
 */
public class EmptyCell implements Cell {

	@Override
	public String abbreviatedCellText() { // text for spreadsheet cell display, must be exactly length 10
		return "          ";
	}

	@Override
	public String fullCellText() { // text for individual cell inspection, not truncated or padded
		return "";
	}

	@Override
	public int compareTo(Object o) {
		// TODO Auto-generated method stub
		return 0;
	}
	

}
