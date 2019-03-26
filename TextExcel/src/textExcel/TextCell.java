package textExcel;

public class TextCell implements Cell {
	
	private String text;

	@Override
	public String abbreviatedCellText() {
		// TODO Auto-generated method stub
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
