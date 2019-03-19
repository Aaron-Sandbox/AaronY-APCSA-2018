package textExcel;

public class TextCell implements Cell {
	
	private String text;

	@Override
	public String abbreviatedCellText() {
		// TODO Auto-generated method stub
		if(text.length() >= 10) {
			return text.substring(0, 10);
		}
		String addSpaces = text;
		for(int i = addSpaces.length(); i < 10; i++) {
			addSpaces += " ";
		}
		return addSpaces;
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
