package fracCalc;

public class MixedFraction {
	
	public int whole, numerator, denominator;
	
	//Checks input to make sure MixedFraction is in correct form and is valid
	public MixedFraction(int whole, int numerator, int denominator) {	
		this.whole = whole;
		
		if(denominator < 0){
			
			this.numerator = -numerator;
			this.denominator = -denominator;
		} else {
			this.numerator = numerator;
			this.denominator = denominator;
		}
		
		if(this.numerator < 0 && this.whole != 0){
			
			this.numerator = -this.numerator;
			this.whole = -this.whole;
		}
		
		if(denominator == 0) {
			throw new IllegalArgumentException("Cannot have a denominator of zero");
		}
	}
	
	//Returns true if the Object argument is equal to this MixedFraction
	public boolean equals(Object o) {
		if(this == o) {
			return true;
		}
		
		if(o instanceof MixedFraction) {
			MixedFraction fraction = (MixedFraction)o;
			
			if(fraction.toSimple().whole == this.toSimple().whole
					&& fraction.toSimple().numerator == this.toSimple().numerator
					&& fraction.toSimple().denominator == this.toSimple().denominator) return true;
		}
		
		return false;
	}
	
	//Returns the MixedFraction as a String
	public String toString() {
		return whole+"_"+numerator+"/"+denominator;
	}
	
	//Returns the MixedFraction as an array of ints
	public int[] toArray() {
		int[] fraction = {whole, numerator, denominator};
		return fraction;
	}
	
	//Returns a simplified version of this MixedFraction (Re-uses the simplification of the ImproperFraction class)
	public MixedFraction toSimple() {
		return this.toImproperFraction().toSimple().toMixedFraction();
	}
	
	//Returns this MixedFraction as an ImproperFraction
	public ImproperFraction toImproperFraction() {
		int improperNumerator = whole*denominator+(int)Math.signum(whole)*numerator;
		return new ImproperFraction(improperNumerator, denominator);
	}
}
