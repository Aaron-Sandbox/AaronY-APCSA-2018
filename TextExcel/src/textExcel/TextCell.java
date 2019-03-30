package textExcel;
/*
 * @author Aaron Yu
 * @version March 2019
 * Cell that stores text
 */

public class TextCell implements Cell {
	
	private String text;

	@Override
	public String abbreviatedCellText() {
		String abbrv = text + "          ";
		return abbrv.substring(0, 10);
	}

	@Override
	public String fullCellText() {
		// TODO Auto-generated method stub
		return "\"" + text + "\"";
	}
	
	public TextCell(String text) {
		this.text = text;
	}

}
