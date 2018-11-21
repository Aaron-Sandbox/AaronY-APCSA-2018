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
    	
    	int[] fracOne = {-15, 4};
    	int[] fracTwo = {-10, 4};
    	//"-3_3/4 - -2_2/4"
    	
    	System.out.println(Arrays.toString(simplify(operate(fracOne, fracTwo, "+"))));
    	System.out.println(Arrays.toString(simplify(operate(fracOne, fracTwo, "+"))));
    	System.out.println(Arrays.toString(simplify(operate(fracOne, fracTwo, "+"))));
    	System.out.println(Arrays.toString(simplify(operate(fracOne, fracTwo, "+"))));

    }
    
    public static String produceAnswer(String input) { 
    	
    	//Splits the string into all elements by the space string
    	String[] elements = input.split(" ");
    	System.out.println(Arrays.toString(elements));
    	
    	//Creates an array of strings called operands that will contain the operands without the operators
    	String[] operands = new String[round2(elements.length/2.0 + 0.5)];
    	int curOperand = 0;
    	for(int i = 0; i < operands.length; i++) {
    		operands[i] = elements[curOperand];
    		curOperand+=2;
    	}
    	
    	//Creates an array of strings called operators that will contain the operators without the operands
    	String[] operators = new String[round2(elements.length/2.0 - 0.5)];
    	int curOperator = 1;
    	for(int i = 0; i < operators.length; i++) {
			operators[i] = elements[curOperator];
			curOperator += 2;
		}
		
    	//An array of all the operands in improper fraction form
    	//[[numerator_one, denominator_one], [numerator_two, denominator_two], [numerator_three, denominator_three]], etc.
    	int[][] improperOperand = toImproperFraction(operands);
    	for(int i = 0; i < improperOperand.length; i++) {
    		System.out.print(Arrays.toString(improperOperand[i])+ " ");
    	}
    	
    	//Checkpoint 3
    	//Only returns the first two operands after performing the first operation on them
    	int[] ans = simplify(operate(improperOperand[0], improperOperand[1], operators[0]));

    	return ans[0]+"_"+ans[1]+"/"+ans[2];
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
    			
    			//TODO: Divide by 0 error
    			if(denominator == 0) {
    				
    			}
    			
    			//Adds arrays to an array
    			int[] improperFraction = {wholeNum*denominator+(sign(wholeNum)*numerator), denominator};
    			operandImproper[counter] = improperFraction;
    			
    			//Iterates counter to be adding on to a new position in the array
    			counter++;
    			
    		//When the number is whole
    		} else if(mixedFraction.indexOf("/") == -1 || mixedFraction.indexOf("_") == -1){
    			
    			//Creates an array of allowed characters for error checking
    			char[] allowedValues = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', '-'};
    			
    			//Checks each character in the operand against the array of allowed values
    			for(int i = 0; i < mixedFraction.length(); i++) {
    				char character = mixedFraction.charAt(i);
    				if(!contains(allowedValues, character)) {
    					System.out.println("Character is not allowed within array");
    					//TODO: Character is not allowed within array
    				}
    				
    			}
    			
    			//Parses number (which is whole) to convert to int data type
    			int numerator = Integer.parseInt(mixedFraction);
    			
    			//Add whole number to the array of improper fractions in the form [number, 1]
    			int[] improperFraction = {numerator, 1};
    			operandImproper[counter] = improperFraction;
    			
    			
    			//Iterates counter to be adding on to a new position in the array
    			counter++;
    			
    			//TODO: Error checking for improperly formatted numbers
    		}
    	}
    	//Returns an array of arrays containing the numerator and denominator
    	return operandImproper;
    }
    
    //Simplifies improper fractions into mixed fraction form
    public static int[] simplify(int[] improperFraction) {
    	int[] simplifiedFraction = new int[3];
    	
    	//Gets the whole number by dividing the numerator by the denominator and truncating 
    	simplifiedFraction[0] = improperFraction[0]/improperFraction[1];
    	
    	//Stores the numerator and denominator values in ints
    	//Stores them as absolute value forms because  -18/4 simplifies to -4_2/4, not -4_-2/4
    	int num = Math.abs(improperFraction[0]%improperFraction[1]);
    	int denom = Math.abs(improperFraction[1]);
    	
    	//Simplifies the new numerator/denominator and places them into an array with the whole number
    	simplifiedFraction[1] = num/gcd(num, denom);
    	simplifiedFraction[2] = denom/gcd(num, denom);
    	
    	//Returns an array in the form [whole_num, numerator, denominator]
    	return simplifiedFraction;
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
	    	
    	} else if(operation.equals("*") || operation.equals("/")){
    		if(operation.equals("/")) {
    			int numerator = n2;
    			int denominator = d2;
    			
    			n2 = denominator;
    			d2 = numerator;
    		}
    		
    		fraction[0] = n1*n2;
    		fraction[1] = d1*d2;
    		
    	} else {
    		//TODO: Operator is not in the correct form
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
