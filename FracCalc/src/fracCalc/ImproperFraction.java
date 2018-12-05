package fracCalc;

public class ImproperFraction {
	
	public int numerator, denominator;
	
	//Converts each numerator and denominator combination to the proper signs, throwing an exception if / by 0
	public ImproperFraction(int numerator, int denominator) {	
		if(denominator < 0){
			this.numerator = -numerator;
			this.denominator = -denominator;
		} else {
			this.numerator = numerator;
			this.denominator = denominator;
		}
		
		
		if(denominator == 0) {
			throw new IllegalArgumentException("Cannot have a denominator of zero");
		}
	}
	
	//Returns if another object is equal to this one
	public boolean equals(Object o) {
		
		if(o == this) {
			return true;
		}
		
		if(o instanceof ImproperFraction) {
			ImproperFraction fraction = (ImproperFraction)o;
			if(this.toSimple().numerator == fraction.toSimple().numerator
					&& this.toSimple().denominator == fraction.toSimple().denominator) return true;
		} 
		
		return false;
	}
	
	//Returns the ImproperFraction as an array of ints
	public int[] toArray() {
		int[] fraction = {numerator, denominator};
		return fraction;
	}
	
	//Returns the ImproperFraction as a String
	public String toString() {
		return numerator + "/" + denominator;
	}
	
	//Returns the ImproperFraction simplified
	public ImproperFraction toSimple() {
		int gcd = gcd(this.numerator, this.denominator);
		return new ImproperFraction(this.numerator/gcd, this.denominator/gcd);
	}
	
	//Returns the ImproperFraction as a MixedFraction
	public MixedFraction toMixedFraction() {
		
		int mixedWhole = numerator/denominator;
		int mixedNumerator = numerator%denominator;
		
		if(mixedWhole != 0) {
			mixedNumerator = Math.abs(mixedNumerator);
		}
		
		int mixedDenominator = denominator;
		if(mixedDenominator < 0 && mixedWhole == 0){
			mixedNumerator *= -1;
		}
		
		mixedDenominator = Math.abs(mixedDenominator);
		
		return new MixedFraction(mixedWhole, mixedNumerator, mixedDenominator);
	}
	
	//Converts a string to an object of type ImproperFraction
	public static ImproperFraction toImproperFraction(String frac) {
		int wholeNum = 0, idx = 0;
		if(frac.indexOf("/") != -1) {
			String[] arr = frac.split("_");
			
			if(arr.length == 2) {
    			wholeNum = Integer.parseInt(arr[0]);
    			idx = 1;
			}
			
			String[] ndArr = arr[idx].split("/");
			int numerator = Integer.parseInt(ndArr[0]);
			int denominator = Integer.parseInt(ndArr[1]);
			
			return new ImproperFraction(wholeNum*denominator+(sign(wholeNum)*(numerator)), denominator);
		} else if (frac.indexOf("_") == -1) {
			return new ImproperFraction(Integer.parseInt(frac), 1);
		}
		
		return new ImproperFraction(0, 0);
	}
	
	//Performs operations on the current ImproperFraction given another operand and an operator
	//Assumes that the order begins with this instance of ImproperFraction
	public ImproperFraction operate(ImproperFraction operand, String operator) {
		ImproperFraction fraction = new ImproperFraction(0, 1);
    	int n1 = this.numerator;
    	int d1 = this.denominator;
    	
    	int n2 = operand.numerator;
    	int d2 = operand.denominator;
    	  	
    	if(operator.equals("+") || operator.equals("-")) {
    	
	    	//If the denominators are different, make them the same
	    	if(d1 != d2) {
	    		int denominatorOne = d1;
	    		int denominatorTwo = d2;
	    		
	    		n1 *= denominatorTwo;
	    		d1 *= denominatorTwo;
	    		n2 *= denominatorOne;
	    		d2 *= denominatorOne;
	    	}
	    	
	    	//Adds or subtracts the numerators 
	    	if(operator.equals("+")) {
	    		fraction.numerator = n1 + n2;
	    		fraction.denominator = d1;
	    	} else {
	    		fraction.numerator = n1 - n2;
	    		fraction.denominator = d1;
	    	}
	    
    	} else {
    		//If the operator is divide, flip the second operand and then multiply, else multiply
    		if(operator.equals("/")) {
    			int numerator = n2;
    			int denominator = d2;
    			
    			n2 = denominator;
    			d2 = numerator;
    		}
    		
    		fraction.numerator = n1*n2;
    		fraction.denominator = d1*d2;
    		
    	} 

    	return fraction.toSimple();
	}
	
	//Returns the greatest common denominator of two ints
    private static int gcd(int numOne, int numTwo){
    	for(int i = min(Math.abs(numOne), Math.abs(numTwo)); i > 0; i--){
    		if(numOne%i==0 && numTwo%i==0){
    			return i;
    		}
    	}
    	return 1;
    }
    
    //Returns the smaller of two ints
    private static int min(int numOne, int numTwo){
    	if(numOne > numTwo){
    		return numTwo;
    	}
    	return numOne;
    }
    
    //Returns the sign of an int as either +-1
    private static int sign(int num){
    	if(num < 0){
    		return -1;
    	}
    	return 1;
    }
}
