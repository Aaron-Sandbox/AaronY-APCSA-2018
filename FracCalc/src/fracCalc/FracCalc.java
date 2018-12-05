package fracCalc;

import java.util.Scanner;

public class FracCalc {

	public static void main(String[] args) {
    	Scanner sc = new Scanner(System.in);
	    	
	   	String input = sc.nextLine();
	   	while(!input.equals("quit")) {
	   		System.out.println(produceAnswer(input));
	   		input = sc.nextLine();
	    }
	    	
	    sc.close();
	 
	}
	
	public static String produceAnswer(String input) { 
		String[] elements = input.split(" ");
		
		String[] operandsString = new String[round2(elements.length/2.0 + 0.5)];
		String[] operators = new String[round2(elements.length/2.0 - 0.5)];
		
		ImproperFraction[] operands = new ImproperFraction[operandsString.length];
		
		//Checks each operand to make sure it is valid, and then adds it to an array of ImproperFractions
		char[] allowedOperands = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', '-', '_', '/'};
		char[] allowedOperators = {'+', '-', '*', '/'};
		
		for(int i = 0; i < elements.length; i++) {
			if(i%2==0) {
				if(checkValid(elements[i], allowedOperands)) {
					add(operandsString, elements[i]);
					if(operandsString[i/2] != null) {
						operands[i/2] = ImproperFraction.toImproperFraction(elements[i]);
					}
				} else {
					return "ERROR: \"" + elements[i] + "\" is not an appropriate operand.";
				}
			} else {
				if(checkValid(elements[i], allowedOperators) && elements[i].split("").length < 2) {
					add(operators, elements[i]);
					
				} else {
					return "ERROR: \"" + elements[i] + "\" is not an appropriate operator.";
				}
			}
		}
		
		ImproperFraction running_total = operands[0];
    	for(int i = 1; i < operands.length; i++){
    		running_total = running_total.operate(operands[i], operators[i-1]);
    	}
    	
    	MixedFraction final_ans = running_total.toSimple().toMixedFraction();
    	
    	if(final_ans.denominator != 0){
    		if(final_ans.numerator == 0){ 
    			return final_ans.whole + "";
    		} else if(final_ans.whole == 0 && final_ans.numerator != 0){ 
    			return final_ans.numerator+"/"+final_ans.denominator;
    		} else {
    			return final_ans.whole+"_"+final_ans.numerator+"/"+final_ans.denominator;
    		}
    	} else {
    		return "ERROR: Cannot divide by zero";
    	}
		
	}
	
	public static int round2(double num) {
    	return (int)(num+0.5);
    }	
	
	
	public static boolean add(String[] array, String element) {
		for(int i = 0; i < array.length; i++) {
			if(array[i] == null) {
				array[i] = element;
				return true;
			}
		}
		return false;
	}
	
	public static boolean checkValid(String check, char[] regex) {
		char[] str = check.toCharArray();
		
		for(char c : str) {
			if(!contains(regex, c)) {
				return false;
			}
		}
		
		return true;
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