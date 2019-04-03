package textExcel;
/*
 * @author Aaron Yu
 * @version March 2019
 * Class that stores a formula
 */

import java.util.ArrayList;

public class FormulaCell extends RealCell {
	
	private Spreadsheet s;
	
	public FormulaCell(String value, Spreadsheet s) {
		super(value);
		this.s = s;
	}
	
	@Override
	public String abbreviatedCellText() {
		String s = getDoubleValue() + "          ";
		return s.substring(0, 10);
	}
	
	@Override
	public String fullCellText() {
		return "( " + super.fullCellText() + " )";
	}
	
	@Override
	public double getDoubleValue() {
		// Splits formula into operands and operators 
		String[] valArr = super.fullCellText().split(" ");
		ArrayList<Double> operands = new ArrayList<Double>();
		ArrayList<Character> operators = new ArrayList<Character>();
		
		// Determines whether formula is arithmetic or avg/total
		if(valArr[0].equalsIgnoreCase("AVG") || valArr[0].equalsIgnoreCase("SUM")) {
			String[] range = valArr[1].split("-");
			
			// Obtains all cells in the target range
			SpreadsheetLocation start = new SpreadsheetLocation(range[0]);
			SpreadsheetLocation end = new SpreadsheetLocation(range[1]);
			ArrayList<Cell> cellArr = Spreadsheet.rangeBetween(start, end, s);
			
			// Converting cells into RealCells
			ArrayList<RealCell> rangeArr = new ArrayList<RealCell>();
			for(Cell c : cellArr) {
				rangeArr.add((RealCell)c);
			}
			
			// Obtaining total
			double total = 0;
			for(int i = 0; i < rangeArr.size(); i++) {
				total += rangeArr.get(i).getDoubleValue();
			}
			
			// Returns either average or total
			 return valArr[0].equalsIgnoreCase("AVG") ? total/((double)rangeArr.size()) : total;
			
		//Arithmetic formula
		} else {
			// Changes all cell references into values, places all values and operators into one array
			for(int i = 0; i < valArr.length; i++) {
				if(i%2 == 0) {
					if(valArr[i].matches("([A-Za-z])[0-9]*")) {
						operands.add(((RealCell) s.getCell(new SpreadsheetLocation(valArr[i]))).getDoubleValue());
					} else {
						operands.add(Double.parseDouble(valArr[i]));
					}
				} else {
					operators.add(valArr[i].charAt(0));
				}
			}
			
			// Performs operations on formula
			double value = operands.get(0);
			for(int i = 0; i < operators.size(); i++) {
				char c = operators.get(i);
				switch(c) {
					case '+': value += operands.get(i+1);
						break;
					case '-': value -= operands.get(i+1);
						break;
					case '*': value *= operands.get(i+1);
						break;
					case '/': value /= operands.get(i+1);
						break;
				}
			}
			return value;
			
		}
	}
	
}
