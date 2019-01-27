package fracCalc;

public class Fraction {
	
	private int sign, whole, numerator, denominator;
	
	//Constructor to create Fractions from integers
	public Fraction(int sign, int whole, int numerator, int denominator) {
		
		this.sign = sign;
		this.whole = whole;
		this.numerator = numerator;
		this.denominator = denominator;
		
	}
	
	//Constructor to create Fractions from String inputs
	public Fraction(String s) {
		//Decides what format the String is in, and creates a Fraction object
		if(s.indexOf("/") == -1) { //If fraction is a whole number
			this.whole = Integer.parseInt(s);
			this.sign = this.whole > 0 ? 1 : -1;
			this.whole = Math.abs(this.whole);
			
			this.numerator = 0;
			this.denominator = 1;
			 
		} else if (s.indexOf("_") == -1) { //If fraction is an improper fraction or has no whole
			String[] frac = s.split("/");
			this.numerator = Integer.parseInt(frac[0]);
			this.sign = this.numerator > 0 ? 1 : -1;
			this.numerator = Math.abs(this.numerator);
			this.denominator = Integer.parseInt(frac[1]);
			
			this.whole = 0;
			
		} else { //If fraction is a complete fraction in the form +-a_b/c
			String[] splitWhole = s.split("_");
			String[] splitFrac = splitWhole[1].split("/");
			
			this.whole = Integer.parseInt(splitWhole[0]);
			this.sign = this.whole > 0 ? 1 : -1;
			this.whole = Math.abs(this.whole);
			
			this.numerator = Integer.parseInt(splitFrac[0]);
			this.denominator = Integer.parseInt(splitFrac[1]);
			
		}
	}
	
	public String toString() {
		//Decides how much to return based on the Fraction
		if(this.numerator == 0){ 
			return this.sign*this.whole + "";
		} else if(this.whole == 0 && this.numerator != 0){ 
			return this.sign*this.numerator+"/"+this.denominator;
		} else {
			return this.sign*this.whole+"_"+this.numerator+"/"+this.denominator;
		}
	}
	
	//Simplifies the Fraction using the GCD (converts to improper form)
	public void simplify() {
		this.convertImproperForm();
		
		int gcd = gcd(this.numerator, this.denominator);
		
		this.numerator = this.numerator / gcd;
		this.denominator = this.denominator / gcd;
		
	}
	
	//Converts this fraction to improper form
	public void convertImproperForm() {
		this.numerator = this.whole * this.denominator + this.numerator;
		this.whole = 0;
		
	}
	
	//Static version to convert a Fraction to its improper form
	public static Fraction convertImproperForm(Fraction a) {
		return new Fraction(a.getSign(), 0, a.getWhole()*a.getDenominator()+a.getNumerator(), a.getDenominator());
	}
	
	//Converts fraction to mixed form
	public void convertMixedForm() {
		int mixedWhole = numerator / denominator;
		int mixedNumerator = numerator % denominator;
		
		this.whole = mixedWhole;
		this.numerator = mixedNumerator;
	}
	
	//Static version to convert Fraction to mixed form
	public static Fraction convertMixedForm(Fraction a) {
		return new Fraction(a.getSign(), a.getNumerator()/a.getDenominator(), a.getNumerator()%a.getDenominator(), a.getDenominator());
	}
	
	//Adds two Fractions, returning a Fraction
	public static Fraction add(Fraction a, Fraction b) {
		a = Fraction.convertImproperForm(a);
		b = Fraction.convertImproperForm(b);
		
		int a_num = a.getNumerator()*b.getDenominator();
		int b_num = b.getNumerator()*a.getDenominator();
		int den = a.getDenominator()*b.getDenominator();

		int r_num = a.getSign()*a_num + b.getSign()*b_num;
		int sign = r_num > 0 ? 1 : -1;
		
		return new Fraction(sign, 0, Math.abs(r_num), den);
		
	}
	
	//Subtracts two Fractions, returning a Fraction
	public static Fraction subtract(Fraction a, Fraction b) {
		a = Fraction.convertImproperForm(a);
		b = Fraction.convertImproperForm(b);
		
		int a_num = a.getNumerator()*b.getDenominator();
		int b_num = b.getNumerator()*a.getDenominator();
		int den = a.getDenominator()*b.getDenominator();

		int r_num = a.getSign()*a_num - b.getSign()*b_num;
		int sign = r_num > 0 ? 1 : -1;
		
		return new Fraction(sign, 0, Math.abs(r_num), den);
	}
	
	//Multiplies two Fractions, returning a Fraction
	public static Fraction multiply(Fraction a, Fraction b) {
		a = Fraction.convertImproperForm(a);
		b = Fraction.convertImproperForm(b);
		
		return new Fraction(a.getSign()*b.getSign(), 
				0, 
				a.getNumerator()*b.getNumerator(), 
				a.getDenominator()*b.getDenominator());
	}
	
	//Divides two Fractions, returning a Fraction
	public static Fraction divide(Fraction a, Fraction b) {
		a = Fraction.convertImproperForm(a);
		b = Fraction.convertImproperForm(b);
		
		return new Fraction(a.getSign()*b.getSign(),
				0,
				a.getNumerator()*b.getDenominator(), a.getDenominator()*b.getNumerator());
	}
	
	//Accessors
	public int getWhole() { 
		return this.whole;
	}
	public int getNumerator() {
		return this.numerator;
	}
	public int getDenominator() {
		return this.denominator;
	}
	public int getSign() {
		return this.sign;
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
    
}
