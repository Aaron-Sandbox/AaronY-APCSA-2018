package fracCalc;

public class ImproperFraction {
	
	public int numerator, denominator;
	
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
	
	public int[] toArray() {
		int[] fraction = {numerator, denominator};
		return fraction;
	}
	
	public String toString() {
		return numerator+"/"+denominator;
	}
	
	public ImproperFraction toSimple() {
		int gcd = gcd(this.numerator, this.denominator);
		return new ImproperFraction(this.numerator/gcd, this.denominator/gcd);
	}
	
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
	
    private static int gcd(int numOne, int numTwo){
    	for(int i = min(Math.abs(numOne), Math.abs(numTwo)); i > 0; i--){
    		if(numOne%i==0 && numTwo%i==0){
    			return i;
    		}
    	}
    	return 1;
    }
    
    private static int min(int numOne, int numTwo){
    	if(numOne > numTwo){
    		return numTwo;
    	}
    	return numOne;
    }
}
