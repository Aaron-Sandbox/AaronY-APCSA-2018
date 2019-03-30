package textExcel;
/*
 * @author Aaron Yu
 * @version March 2019
 * Class that stores a double value
 */

public class ValueCell extends RealCell {
	
	public ValueCell(String value) {
		super(value);
	}

	@Override 
	public String abbreviatedCellText() {
		String s = super.fullCellText();
		
		if(s.contains(".")) s = s.replaceAll("0*$", ""); // Only if the string contains a decimal, remove all the following zeroes
		if(s.charAt(s.length()-1) == '.') s+="0"; // If the string now has a decimal with no following zero at the end, add the zero
		if(s.indexOf('.') == -1) s+=".0"; // If the string has no decimals, suffix ".0"
		
		// Corrects length after truncation of following zeroes
		s = s + "          ";
		return s.substring(0, 10);
	}

}
