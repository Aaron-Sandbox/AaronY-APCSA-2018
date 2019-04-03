package textExcel;
import java.util.ArrayList;
/*
 * @author Aaron Yu
 * @version March 2019
 * Client code to run spreadsheet program
 */
//import java.io.FileNotFoundException;
import java.util.Scanner;

// Update this file with your own code.

public class TextExcel
{
	public static Spreadsheet spreadsheet;

	public static void main(String[] args)
	{
		spreadsheet = new Spreadsheet();
		Scanner sc = new Scanner(System.in);
		    
		//Asks for input, continues to ask and print the answer until 'quit' is entered
		String input = sc.nextLine();
		while(!input.equalsIgnoreCase("quit")) {
			System.out.println(spreadsheet.processCommand(input));
			input = sc.nextLine();
		}
		    	
		sc.close();
		
		ArrayList<Cell> range = Spreadsheet.rangeBetween(new SpreadsheetLocation("A9"), new SpreadsheetLocation("B10"), spreadsheet);
		String s = "";
		for(Cell c : range) {
			s += c.fullCellText() + ", ";
		}
		System.out.println(s);
		
	}
}
