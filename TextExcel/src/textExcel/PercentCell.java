package textExcel;
/*
 * @author Aaron Yu
 * @version March 2019
 * Cell that stores a percentage
 */

public class PercentCell extends RealCell{
	
	//Stores values as decimal (not percent form)
	public PercentCell(String value) {
		super(value);
	}
	
	@Override
	public String abbreviatedCellText() {
		//Corrects for length while converting value back into percent form
		String s = (int)(super.getDoubleValue()*100)+"%          ";
		return s.substring(0, 10);
	}

}
