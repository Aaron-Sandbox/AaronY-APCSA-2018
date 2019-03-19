package textExcel;

// Update this file with your own code.

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
		if(command.indexOf('=') != -1) {
			// If assign cell
			String[] split = command.split("=", 2);
			String cell = split[0];
			String assignment = split[1];
				
			SpreadsheetLocation loc = new SpreadsheetLocation(cell.replaceAll("\\s+", ""));
			assignment = assignment.substring(assignment.indexOf("\"")+1, assignment.lastIndexOf("\""));
			
			TextCell txtcell;
			if(isAllSpaces(assignment)) {
				txtcell = new TextCell("");
			} else {
				txtcell = new TextCell(assignment);
			}
			
			System.out.println(txtcell.fullCellText());
			
			setCell(loc, txtcell);
				
		} else {
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
	public int getRows()
	{
		// TODO Auto-generated method stub
		return rows;
	}

	private boolean isAllSpaces(String str) {
		for(int i = 0; i < str.length(); i++) {
			if(str.charAt(i) != ' ') return false;
		}
		return true;
	}
	@Override
	public int getCols()
	{
		// TODO Auto-generated method stub
		return cols;
	}

	@Override
	public Cell getCell(Location loc)
	{
		// TODO Auto-generated method stub
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
