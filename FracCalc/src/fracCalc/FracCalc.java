package fracCalc;

import java.util.Scanner;

public class FracCalc {

	public static void main(String[] args) {
		//Creates scanner object
    	Scanner sc = new Scanner(System.in);
	    
    	//Asks for input, continues to ask and print the answer until 'quit' is entered
	   	String input = sc.nextLine();
	   	while(!input.equals("quit")) {
	   		//Calls produceAnswer to obtain the answer to the problem and print it out
	   		System.out.println(produceAnswer(input));
	   		input = sc.nextLine();
	    }
	    	
	    sc.close();
	 
	}
	
	public static String produceAnswer(String input) { 
		//Splits elements by space to get an array of Strings
		String[] elements = input.split(" ");
		
		//Creates two new arrays to contain the operators and the operands
		//Length is a fencepost problem, solved by dividing by 2, adding +-0.5, and rounding
		ImproperFraction[] operands = new ImproperFraction[round2(elements.length/2.0 + 0.5)];
		String[] operators = new String[round2(elements.length/2.0 - 0.5)];
		
		//Creates char arrays containing all possible characters to check each input against
		char[] allowedOperands = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', '-', '_', '/'};
		char[] allowedOperators = {'+', '-', '*', '/'};
		
		//Iterates through the whole elements array
		for(int i = 0; i < elements.length; i++) {
			//If it is even (in an operand slot), check if all characters are valid
			if(i%2==0) { 
				
				//If all characters are valid, put it into the array of operands
				if(checkValid(elements[i], allowedOperands)) { 
					operands[i/2] = ImproperFraction.toImproperFraction(elements[i]);
					
				//If any characters are invalid, return an error immediately
				} else { 
					return "ERROR: \"" + elements[i] + "\" is not an appropriate operand.";
				}
				
			//If it is odd (in an operator slot), check if all characters are valid
			} else { 
				
				//If all characters are valid, put it into the array of operators
				if(checkValid(elements[i], allowedOperators) && elements[i].split("").length < 2) {
					operators[i/2] = elements[i];
				
				//If any are invalid, return an error immediately
				} else {
					return "ERROR: \"" + elements[i] + "\" is not an appropriate operator.";
				}
				
			}
		}
		
		//Gets the first operand and performs all operations one by one to completion
		ImproperFraction running_total = operands[0];
    	for(int i = 1; i < operands.length; i++){
    		running_total = running_total.operate(operands[i], operators[i-1]);
    	}
    	
    	//Simplifies the final fraction and then converts it to a MixedFraction
    	MixedFraction final_ans = running_total.toSimple().toMixedFraction();
    	
    	//Decides which values to return as a String based on the final simplified fraction
		if(final_ans.numerator == 0){ 
			return final_ans.whole + "";
		} else if(final_ans.whole == 0 && final_ans.numerator != 0){ 
			return final_ans.numerator+"/"+final_ans.denominator;
		} else {
			return final_ans.whole+"_"+final_ans.numerator+"/"+final_ans.denominator;
		}
  	
	}
	
	//Rounds a double to the nearest integer and returns as type int
	public static int round2(double num) {
    	return (int)(num+0.5);
    }	
	
	//Checks if a String[] is valid based on an array of allowed characters
	public static boolean checkValid(String check, char[] regex) {
		char[] str = check.toCharArray();
		
		for(char c : str) {
			if(!contains(regex, c)) {
				return false;
			}
		}
		
		return true;
	}
	
	//Returns if a target char is contained within an array 
	public static boolean contains(char[] arr, char target) {
    	for(char curChar : arr) {
    		if(curChar == target) {
    			return true;
    		} 
    	}
    	
    	return false;
    }

}