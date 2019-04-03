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
		return "\"" + text + "\"";
	}
	
	public TextCell(String text) {
		this.text = text;
	}

	@Override
	public int compareTo(Object o) {
		if(o instanceof TextCell) {
			
			// Casts cell to TextCell and stores values
			TextCell t = (TextCell) o;
			String s1 = t.fullCellText();
			String s2 = this.fullCellText();
			
			// Returns 0 if cell values are equal
			if(s1.equals(s2)) return 0;
			
			// Loops through both strings to figure out which comes first alphabetically
			String shorter = s1.length() > s2.length() ? s2 : s1;
			for(int i = 0; i < shorter.length(); i++) {
				if(s1.charAt(i) > s2.charAt(i)) {
					return -1;
				} else if (s1.charAt(i) < s2.charAt(i)) {
					return 1;
				}
			}
			
			// If code reaches this point, the Strings are the same up to the length of the shorter string
			// Ex. "str" and "string" have the same first three letters
			// Returns based on which string is shorter 
			if(shorter.equals(s1)) {
				return -1;
			} else return 1;
		}
		
		return 1;
	}

}
