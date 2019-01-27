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
	
	//Returns the calculated answer from a String input
	public static String produceAnswer(String input) {
		//Splits into individual operators and operands
		String[] elements = input.split(" ");
		
		//Iterates through the array of elements two elements at a time, evaluating in order
		Fraction result = new Fraction(elements[0]);
		for(int i = 2; i < elements.length; i+=2) {
			String operator = elements[i-1];
			Fraction operand = new Fraction(elements[i]);
			
			result = operate(result, operator.charAt(0), operand);
		}
		
		//Simplifies, converts, and returns result as String
		result.simplify();
		result.convertMixedForm();
		return result.toString();
	}
	
	//Takes an operator char and two Fractions, performing the desired operation and returning a Fraction
	public static Fraction operate(Fraction a, char operator, Fraction b) {
		if(operator == '+') return Fraction.add(a, b);
		if(operator == '-') return Fraction.subtract(a, b);
		if(operator == '/') return Fraction.divide(a, b);
		return Fraction.multiply(a, b);
	}

}
