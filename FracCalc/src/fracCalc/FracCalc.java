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
    
    // ** IMPORTANT ** DO NOT DELETE THIS FUNCTION.  This function will be used to test your code
    // This function takes a String 'input' and produces the result
    //
    // input is a fraction string that needs to be evaluated.  For your program, this will be the user input.
    //      e.g. input ==> "1/2 + 3/4"
    //        
    // The function should return the result of the fraction after it has been calculated
    //      e.g. return ==> "1_1/4"
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
    		curOperator+=2;
    	}
    	 
    	
    	//Checkpoint 1
    	return operands[1];

    }
    
    //Converts an array of numbers (fractions, mixed fractions, whole numbers) to improper fraction form
    public static int[][] toImproperFraction(String[] fractionArray){
    	int[][] operandImproper = new int[fractionArray.length][2];
    	int counter = 0;
    	
    	//Iterates through every operand in the fractionArray
    	for(String mixedFraction : fractionArray) { 
    		//If there is a fraction in the operand
    		if(mixedFraction.indexOf("/") != -1) {
    			//Split by underscore
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
    			int[] improperFraction = {wholeNum*denominator+numerator, denominator};
    			operandImproper[counter] = improperFraction;
    			
    			//Iterates counter to be adding on to a new position in the array
    			counter++;
    			
    		//When the number is whole
    		} else if(mixedFraction.indexOf("/") == -1 || mixedFraction.indexOf("_") == -1){
    			char[] allowedValues = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9'};
    			
    			for(int i = 0; i < mixedFraction.length(); i++) {
    				char character = mixedFraction.charAt(i);
    				if(!contains(allowedValues, character)) {
    					System.out.println("Character is not allowed within array");
    					//TODO: Character is not allowed within array
    				}
    				
    			}
    			
    			int numerator = Integer.parseInt(mixedFraction);
    			
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
    	
    	simplifiedFraction[0] = improperFraction[0]/improperFraction[1];
    	simplifiedFraction[1] = improperFraction[0]%improperFraction[1];
    	simplifiedFraction[2] = improperFraction[1];
    	
    	return simplifiedFraction;
    }
    
    //Operates on two improper fractions based on operator
    public static int[] operate(int[] operand_one, int[] operand_two, String operation) {
    	
    	int fraction[] = new int[2];
    	
    	if(operation.equals("+") || operation.equals("-")) {
    	
	    	//Makes the denominator the same by multiplying by 1/1 equivalent
	    	if(operand_one[1] != operand_two[1]) {
	    		
	    		operand_one[0] *= operand_two[1];
	    		operand_one[1] *= operand_two[1];
	    		
	    		operand_two[0] *= operand_one[1];
	    		operand_two[1] *= operand_one[1];
	    		
	    	}
	    	
	    	if(operation.equals("+")) {
	    		fraction[0] = operand_one[0]+operand_two[0];
	    		fraction[1] = operand_one[1];
	    		
	    	} else {
	    		fraction[0] = operand_one[0]-operand_two[0];
	    		fraction[1] = operand_one[1];
	    	}
	    	
    	} else if(operation.equals("*") || operation.equals("/")){
    		if(operation.equals("/")) {
    			int numerator = operand_two[0];
    			int denominator = operand_two[1];
    			
    			operand_two[0] = denominator;
    			operand_two[1] = numerator;
    		}
    		
    		fraction[0] = operand_one[0]*operand_two[0];
    		fraction[1] = operand_one[1]*operand_one[1];
    		
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
