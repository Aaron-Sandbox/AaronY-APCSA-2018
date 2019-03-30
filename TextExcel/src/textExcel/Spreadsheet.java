package textExcel;
/*
 * @author Aaron Yu
 * @version March 2019
 * Subclass to handle modifying and accessing the spreadsheet
 */

public class Spreadsheet implements Grid
{
	private Cell[][] cells;
	private int rows = 20;
	private int cols = 12;
	
	public Spreadsheet() {
		resetSpreadsheet();
		
	}
	
	public void resetSpreadsheet() {
		cells = new Cell[getRows()][getCols()];
		for(int i = 0; i < rows; i++) {
			for(int j = 0; j < cols; j++) {
				cells[i][j] = new EmptyCell();
			}
		}
	}

	@Override
	public String processCommand(String command)
	{
		if(command.indexOf('=') != -1) { // If assign cell
			
			Cell cell;
			
			String[] split = command.split("=", 2);
			String assignment = split[1].charAt(0) == ' ' ? split[1].replaceFirst(" ", "") : split[1];
			SpreadsheetLocation loc = new SpreadsheetLocation(split[0].replaceAll("\\s+", ""));
						
			// If assigning a TextCell
			if(assignment.indexOf("\"") != -1 && assignment.lastIndexOf("\"") != -1) {
				cell = new TextCell(assignment.substring(assignment.indexOf("\"")+1, assignment.lastIndexOf("\"")));
				
			// If assigning a FormulaCell
			} else if(assignment.indexOf("(") < assignment.indexOf(")")) { 
				cell = new FormulaCell(assignment.substring(assignment.indexOf("(")+2, assignment.lastIndexOf(")")-1), this);
				
			// If assigning a PercentCell
			} else if(assignment.indexOf("%") != -1){ 
				String percent = Double.parseDouble((assignment.replaceAll("%", "")))/100 + "";
				cell = new PercentCell(percent);
				
			// If assigning a ValueCell
			} else cell = new ValueCell(assignment.replace("%", ""));
			
			setCell(loc, cell);
				
		} else {
			if(command.isEmpty()) return "";
			if(command.toLowerCase().indexOf("clear") != -1) {
				if(command.equalsIgnoreCase("clear")) {
					//If clear all
					resetSpreadsheet();
				} else {
					//If clear cell
					String s = command.toLowerCase().replaceAll("clear", "");
					setCell(new SpreadsheetLocation(s.replaceAll("\\s+", "")), new EmptyCell());
				}
			} else {
				// If inspect cell
				return getCell(new SpreadsheetLocation(command)).fullCellText();
			}
		}
		return getGridText();
	}

	@Override
	public int getRows() {
		return rows;
	}
	
	@Override
	public int getCols()
	{
		return cols;
	}

	@Override
	public Cell getCell(Location loc)
	{
		return cells[loc.getRow()][loc.getCol()];
	}
	
	public void setCell(Location loc, Cell cell) {
		cells[loc.getRow()][loc.getCol()] = cell;
	}

	@Override
	public String getGridText()
	{
		//Creates empty String to assemble spreadsheet in
		String spreadsheet = "";
		
		//Formats first row using String.format (to force 10 character lengths)
		spreadsheet += "   ";
		for(char c = 'A'; c <= 'L'; c++) {
			spreadsheet += "|" + String.format("%-10s", c);
		}
		spreadsheet += "|\n";
		
		//Formats the rest of the rows and adds text
		for(int i = 1; i <= rows; i++) {
			//Formats row numbering, locking it to 3 character length
			spreadsheet += String.format("%-3s", i);
			
			//Adds text into spreadsheet cells
			for(int j = 0; j < cols; j++) {
				spreadsheet += "|" + String.format("%-10s", cells[i-1][j].abbreviatedCellText());

			}
			spreadsheet += "|\n";
		}
		return spreadsheet;
	}

}
