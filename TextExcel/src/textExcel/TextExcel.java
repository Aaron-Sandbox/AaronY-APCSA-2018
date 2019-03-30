package textExcel;
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
		
	}
}
