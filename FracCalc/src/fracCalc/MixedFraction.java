package fracCalc;

public class MixedFraction {
	
	public int whole, numerator, denominator;
	
	public MixedFraction(int whole, int numerator, int denominator) {	
		this.whole = whole;
		
		if(denominator < 0){
			this.numerator = -numerator;
			this.denominator = -denominator;
		} else {
			this.numerator = numerator;
			this.denominator = denominator;
		}
		
		if(this.numerator < 0){
			this.numerator = -this.numerator;
			this.whole = -this.whole;
		}
		
		if(denominator == 0) {
			throw new IllegalArgumentException("Cannot have a denominator of zero");
		}
	}
	
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
	
	public String toString() {
		return whole+"_"+numerator+"/"+denominator;
	}
	
	public int[] toArray() {
		int[] fraction = {whole, numerator, denominator};
		return fraction;
	}
	
	public MixedFraction toSimple() {
		return this.toImproperFraction().toSimple().toMixedFraction();
	}
	
	public ImproperFraction toImproperFraction() {
		int improperNumerator = whole*denominator+(int)Math.signum(numerator)*numerator;
		return new ImproperFraction(improperNumerator, denominator);
	}
}
