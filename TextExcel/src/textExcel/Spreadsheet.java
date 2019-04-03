package textExcel;
/*
 * @author Aaron Yu
 * @version March 2019
 * Subclass to handle modifying and accessing the spreadsheet
 */

import java.util.ArrayList;
import java.util.Collections;

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
		// Unit testing requires that empty commands be returned empty
		if(command.isEmpty()) return ""; 
		
		// If assign cell
		if(command.indexOf('=') != -1) { 
			Cell cell;
			
			// Splits by space (returning only 2 arguments)
			String[] split = command.split("=", 2); 
			
			// Removes spaces (for parsing)
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
			
		// Non assignment commands
		} else {
			
			// If command is a clear command
			if(command.toLowerCase().indexOf("clear") != -1) {
				if(command.equalsIgnoreCase("clear")) { // Clears spreadsheet
					resetSpreadsheet();
				} else { //Clears cell
					String s = command.toLowerCase().replaceAll("clear", "");
					setCell(new SpreadsheetLocation(s.replaceAll("\\s+", "")), new EmptyCell());
				}
				
			// If command is a sort command
			} else if (command.toLowerCase().contains("sort")) {
				String range = command.split(" ")[1];
				SpreadsheetLocation start = new SpreadsheetLocation(range.split("-")[0]);
				SpreadsheetLocation end = new SpreadsheetLocation(range.split("-")[1]);
				ArrayList<Cell> rangeArr = rangeBetween(start, end, this);
				
				ArrayList<Cell> sortedArr;
				sortedArr = command.toLowerCase().charAt(4) == 'a' ? sort(rangeArr, true) : sort(rangeArr, false);
				
				replaceRange(sortedArr, start, end, this);
				
			// If command is an inspect command
			} else {
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
	
	// Returns all cells in a rectangular area between a start and end cell location
	public static ArrayList<Cell> rangeBetween(SpreadsheetLocation a, SpreadsheetLocation b, Spreadsheet s) {
		ArrayList<Cell> range = new ArrayList<Cell>();
		
		// Iterates through all the rows and columns within the target range
		for(int i = a.getRow(); i <= b.getRow(); i++) {
			for(int j = a.getCol(); j <= b.getCol(); j++) {
				range.add(s.getCell(RCtoSL(i, j))); 
			}
		}

		return range;
		
	}
	
	public static SpreadsheetLocation RCtoSL(int row, int col) {
		char column = (char)(col+65); // Converts column number into a column letter
		String str = String.format("%1$s%2$s", Character.toString(column), row+1); // Obtaining string version of cell location
		
		return new SpreadsheetLocation(str);
	}
	
	public static ArrayList<Cell> sort(ArrayList<Cell> arr, boolean ascending) {
		ArrayList<Cell> returnArray = (ArrayList<Cell>) arr.clone();
		
		int swaps;
		do {
			swaps = 0;

			for(int i = 1; i < returnArray.size(); i++) {
				if(ascending) {
					if(returnArray.get(i).compareTo(returnArray.get(i-1))==-1) {
						Collections.swap(returnArray, i, i-1);
						swaps++;
					}
				} else {
					if(returnArray.get(i).compareTo(returnArray.get(i-1))==1) {
						Collections.swap(returnArray, i, i-1);
						swaps++;
					}
				}
			}
		} while(swaps != 0);
		
		
		return returnArray;
	} 
	
	public static void replaceRange(ArrayList<Cell> arr, SpreadsheetLocation start, SpreadsheetLocation end, Spreadsheet s) {
		// Iterates through all the rows and columns within the target range
		for(int i = start.getRow(); i <= end.getRow(); i++) {
			for(int j = start.getCol(); j <= end.getCol(); j++) {
				int arrayIndex = (i-start.getRow())*(end.getCol()-start.getCol()+1)+(j-start.getCol());
				s.setCell(RCtoSL(i, j), arr.get(arrayIndex));
			}
		}
	}

}
