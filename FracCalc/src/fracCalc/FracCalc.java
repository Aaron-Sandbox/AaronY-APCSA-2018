package fracCalc;

import java.util.*;

public class FracCalc {

    public static void main(String[] args) {
    	Scanner sc = new Scanner(System.in);
    	
    	while(true) {
    		
    		String input = sc.nextLine();
    	
    		if(input.equals("quit")) {
    			break;
    		} else {
    			System.out.println(produceAnswer(input));
    		}
    		
    	}
    	
    	sc.close();
 
    }
    
    public static String produceAnswer(String input) { 
    	
    	//Splits the string into all elements by the space string
    	String[] elements = input.split(" ");
    	
    	//Creates an array of strings called operands that will contain the operands without the operators
    	String[] operands = new String[round2(elements.length/2.0 + 0.5)];
    	int curOperand = 0;
    	char[] allowedOperandValues = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', '-', '_', '/'};
    	for(int i = 0; i < operands.length; i++) {
    		int countMinus = 0, countUnderscore = 0, countSlash = 0;
    		//Error checking loop, iterates through the string and makes sure all the characters within are allowed as per the allowed characters array
    		for(int j = 0; j < elements[curOperand].length(); j++) {
    			char character = elements[curOperand].charAt(j);
    			if(!contains(allowedOperandValues, character)) {
    				return "ERROR: Input is in an invalid format";
    			} else if (character == '-'){
    				countMinus++;
    			} else if (character == '_'){
    				countUnderscore++;
    			} else if (character == '/'){
    				countSlash++;
    			}
    		}
    		
    		if(countMinus > 1 || countUnderscore > 1 || countSlash > 1
    				|| (countUnderscore == 1 && countSlash == 0)){
    			return "ERROR: Input is in an invalid format";
    		}
    		
    		//If there are no errors in the string, it will be added to the array of operands
    		operands[i] = elements[curOperand];
    		curOperand+=2;
    	}
    	
    	
    	//Creates an array of strings called operators that will contain the operators without the operands
    	String[] operators = new String[round2(elements.length/2.0 - 0.5)];
    	int curOperator = 1;
    	char[] allowedOperatorValues = {'+', '-', '*', '/'};
    	for(int i = 0; i < operators.length; i++) {
    		//Error checking loop, iterates through the string and makes sure all the characters within are allowed as per the allowed characters array
    		for(int j = 0; j < elements[curOperator].length(); j++) {
    			char character = elements[curOperator].charAt(j);
    			if(!contains(allowedOperatorValues, character) || elements[curOperator].length() > 1) {
    				return "ERROR: Input is in an invalid format";
    			}
    		}
    		//If there are no errors in the string, it will be added to the array of operators
			operators[i] = elements[curOperator];
			curOperator += 2;
		}
    	
    	//Check to fix issues with only a single operator
    	if(operators.length < 1){
    		return "ERROR: Input is in an invalid format";
    	}
		
    	//An array of all the operands in improper fraction form
    	//[[numerator_one, denominator_one], [numerator_two, denominator_two], [numerator_three, denominator_three]], etc.
    	int[][] improperOperand = toImproperFraction(operands);
    	
    	int[] running_total = operate(improperOperand[0], improperOperand[1], operators[0]);
    	
    	for(int i = 2; i < improperOperand.length; i++){
    		running_total = operate(running_total, improperOperand[i], operators[i-1]);
    	}
    	
    	
    	int[] ans = simplify(running_total);
    	
    	if(ans[2] != 0){

    		if(ans[1] == 0){ 
    			return ans[0] + "";
    		} else if(ans[0] == 0 && ans[1] != 0){ 
    			return ans[1]+"/"+ans[2];
    		} else {
    			return ans[0]+"_"+ans[1]+"/"+ans[2];
    		}
    		
    	} else {
    		return "ERROR: Cannot divide by zero";
    	}
    	//12457 / -1 + 12457
    
    }
    
    /* 
     * Converts an array of numbers (fractions, mixed fractions, whole numbers) to improper fraction form
     * Ex. [[numerator_one, denominator_one], [numerator_two, denominator_two], [numerator_three, denominator_three]]
     */
    public static int[][] toImproperFraction(String[] fractionArray){
    	int[][] operandImproper = new int[fractionArray.length][2];
    	int counter = 0;
    	
    	//Iterates through every operand in the fractionArray
    	for(String mixedFraction : fractionArray) { 
    		
    		//Checks if there is a fraction in each operand in the array 
    		if(mixedFraction.indexOf("/") != -1) {
    			String[] arr = mixedFraction.split("_");	
    			
    			int wholeNum = 0;
    	    	int idx = 0;
    	    	
    	    	//wholeNum and idx default to 0, in the case that there are no underscores and the length stays 1 (just a fraction)
    			if(arr.length == 2) {
	    			wholeNum = Integer.parseInt(arr[0]);
	    			idx = 1;
    			}
	    			
    			//Splits the item at index idx by "/", dividing it into an array of [numerator, denominator]
    			String[] ndArray = arr[idx].split("/");
    			int numerator = Integer.parseInt(ndArray[0]);
    			int denominator = Integer.parseInt(ndArray[1]);
    		
    			
    			//Adds arrays to an array
    			int[] improperFraction = {wholeNum*denominator+(sign(wholeNum)*numerator), denominator};
    			operandImproper[counter] = improperFraction;
    			
    			//Iterates counter to be adding on to a new position in the array
    			counter++;
    			
    		//When the number is whole
    		} else if(mixedFraction.indexOf("/") == -1 || mixedFraction.indexOf("_") == -1){
    			
    			//Parses number (which is whole) to convert to int data type
    			int numerator = Integer.parseInt(mixedFraction);
    			
    			//Add whole number to the array of improper fractions in the form [number, 1]
    			int[] improperFraction = {numerator, 1};
    			operandImproper[counter] = improperFraction;
    			
    			
    			//Iterates counter to be adding on to a new position in the array
    			counter++;
    			
    		} 
    	}
    	//Returns an array of arrays containing the numerator and denominator
    	return operandImproper;
    }
    
    //Simplifies improper fractions into mixed fraction form
    public static int[] simplify(int[] improperFraction) {
    	int[] simplifiedFraction = new int[3];
    	int num, denom;
    	
    	//Checks if there is a divide by zero to avoid throwing an exception
    	if(improperFraction[1] == 0){
    		simplifiedFraction[2] = 0;
    		return simplifiedFraction;
    		
    	} else {
    		//Gets the whole number by dividing the numerator by the denominator and truncating 
    		simplifiedFraction[0] = improperFraction[0]/improperFraction[1];
    		if(simplifiedFraction[0] != 0){
    			num = Math.abs(improperFraction[0]%improperFraction[1]);
    		} else { 
    			num = improperFraction[0]%improperFraction[1];
    		}
    	
    		denom = improperFraction[1];
    		
    		//If denominator is negative and whole number is 0, move negative sign to numerator
    		if(denom < 0 && simplifiedFraction[0] == 0){
    			num *= -1;
    		}
    		
    		denom = Math.abs(denom);
    		
    		simplifiedFraction[1] = num/gcd(num, denom);
        	simplifiedFraction[2] = denom/gcd(num, denom);
    		
    	}

    	//Returns an array in the form [whole_num, numerator, denominator]
    	return simplifiedFraction;
    }
    
    //Reduces fractions without whole numbers by their greatest common denominator
    public static int[] reduce(int[] improperFraction) {
    	int[] reducedFraction = new int[2];

    	int num = improperFraction[0];
    	int denom = improperFraction[1];
    	
    	//Simplifies the new numerator/denominator and places them into an array
    	reducedFraction[0] = num/gcd(num, denom);
    	reducedFraction[1] = denom/gcd(num, denom);
    	
    	//Returns an array in the form [numerator, denominator]
    	return reducedFraction;
    }
    
    //Returns the greatest common denominator of two ints
    public static int gcd(int numOne, int numTwo){
    	for(int i = min(Math.abs(numOne), Math.abs(numTwo)); i > 0; i--){
    		if(numOne%i==0 && numTwo%i==0){
    			return i;
    		}
    	}
    	return 1;
    }
    
    //Returns the larger of two ints
    public static int max(int numOne, int numTwo){
    	if(numOne > numTwo){
    		return numOne;
    	}
    	return numTwo;
    }
    
    //Returns the smaller of two ints
    public static int min(int numOne, int numTwo){
    	if(numOne > numTwo){
    		return numTwo;
    	}
    	return numOne;
    }
    
    //Returns the sign of an int input as either -1 or 1
    public static int sign(int num){
    	if(num < 0){
    		return -1;
    	}
    	return 1;
    }
    
    
    //Operates on two improper fractions based on operator
    public static int[] operate(int[] operand_one, int[] operand_two, String operation) {
    	
    	int fraction[] = new int[2];
    	
    	int n1 = operand_one[0];
    	int d1 = operand_one[1];
    	
    	int n2 = operand_two[0];
    	int d2 = operand_two[1];
    	
    	
    	
    	if(operation.equals("+") || operation.equals("-")) {
    	
	    	//Makes the denominator the same by multiplying by 1/1 equivalent
	    	if(d1 != d2) {
	    		
	    		int denominatorOne = d1;
	    		int denominatorTwo = d2;
	    		
	    		n1 *= denominatorTwo;
	    		d1 *= denominatorTwo;
	    		n2 *= denominatorOne;
	    		d2 *= denominatorOne;
	    		
	    		
	    	}
	    	
	    	if(operation.equals("+")) {
	    		fraction[0] = n1 + n2;
	    		fraction[1] = d1;
	    		
	    	} else {
	    		fraction[0] = n1 - n2;
	    		fraction[1] = d1;
	    	}
	    	
    	} else {
    		if(operation.equals("/")) {
    			int numerator = n2;
    			int denominator = d2;
    			
    			n2 = denominator;
    			d2 = numerator;
    		}
    		
    		fraction[0] = n1*n2;
    		fraction[1] = d1*d2;
    		
    	} 

    	return fraction;
    }
    
    public static int round2(double num) {
    	return (int)(num+0.5);
    }
    
    public static boolean contains(char[] arr, char target) {
    	for(char curChar : arr) {
    		if(curChar == target) {
    			return true;
    		} 
    	}
    	
    	return false;
    }
    
    
}
