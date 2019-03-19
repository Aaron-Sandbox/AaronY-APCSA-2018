package textExcel;

import java.io.FileNotFoundException;
import java.util.Scanner;

// Update this file with your own code.

public class TextExcel
{

	public static void main(String[] args)
	{
		Spreadsheet spreadsheet = new Spreadsheet();
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
