/**
 * @author Aaron Yu
 * @version 9.17.18
 * 
 * A math library containing various functions emulating those
 * of the Java Math class
 */
public class Calculate {
	
	//Returns the square of a number as an integer
	public static int square(int input) {
		return input*input;
	}
	
	//Returns the cube of a number as an integer
	public static int cube(int input) {
		return input*input*input;
	}
	
	//Returns the average of two numbers
	public static double average(double x1, double x2) {
		return (x1+x2)/2;
	}
	
	//Returns the average of three numbers
	public static double average(double x1, double x2, double x3) {
		return (x1+x2+x3)/3;
	}
	
	//Returns the average of any amount of numbers
	public static double average(double ... x) {
		int total = 0;
		for(double num : x) {
			total+= num;
		}
		
		return total/x.length;
	}
	
	//Takes a measure in radians and returns it in degrees
	public static double toDegrees(double radianMeasure) {
		return (180*radianMeasure)/3.14159;
	}
	
	//Takes a measure in degrees and returns it in radians
	public static double toRadians(double degreeMeasure) {
		return (3.14159*degreeMeasure)/180;
	}
	
	//Takes the coefficients of a quadratic equation and returns the output of the discriminant
	public static double discriminant(double a, double b, double c) {
		return (b*b)-4*a*c; 
	}
	
	//Takes a whole number, numerator, and denominator, and converts it to a mixed fraction in the form a/b
	public static String toImproperFrac(double wholeNum, double numerator, double denominator) {
		try {
			return (wholeNum*denominator+numerator) + "/" + denominator;
		} catch(ArithmeticException e) {
			return "Illegal arguments";
		}
	}
	
	//Takes a numerator and denominator and returns it as a mixed fraction in the form a_b/c
	public static String toMixedNum(int numerator, int denominator) {
		try {
			return numerator/denominator + "_" + numerator%denominator+ "/" + denominator; 
		} catch(ArithmeticException e) {
			return "Illegal arguments";
		}
	}
	
	/**
	 * Takes four numbers and a variable in the form (a*var + b)(c*var + d) 
	 * and returns the result in "a*c*var^2 + (a*d + b*c)*var + b*d" form
	 */
	public static String foil(int a, int b, int c, int d, String var) {
		int ax, bx, cx;
		
		ax = a*c;
		bx = a*d + b*c;
		cx = b*d;
		
		return ax + var + "^2" + sign(bx)+ absValue(bx) + var + sign(cx) + absValue(cx);
	}
	
	//The sign method returns a string with the sign of the integer argument
	public static String sign(int signedInt) {
		if(signedInt >= 0) {
			return " + ";
		} else {
			return " - ";
		}
	}
	//Integer version of absValue(double x)
	public static int absValue(int integer) {
		if(integer < 0) return -1*integer;
		return integer;
	}
	
	//Returns the absolute value of a double
	public static double absValue(double integer) {
		if(integer < 0) return -1*integer;
		return integer;
	}
	
	//Returns whether the first argument is divisible by the second
	public static boolean isDivisibleBy(int dividend, int divisor) {
		if(divisor == 0){
			throw new ArithmeticException("Invalid argument");
		}
		
		return dividend % divisor == 0;	
	}
	
	//Returns the larger of two doubles
	public static double max(double x1, double x2) {
		if(x1 > x2) return x1;
		return x2;
	}
	
	//Returns the larger of three doubles, overrides max(double, double)
	public static double max(double x1, double x2, double x3) {
		return max(x1,max(x2, x3));
	}
	
	//Returns the smaller of two ints
	public static int min(int x1, int x2) {
		if(x1<x2) return x1;
		return x2;
	}

	//Returns the smaller of two doubles, overrides min(int,int)
	public static double min(double x1, double x2) {
		if(x1<x2) return x1;
		return x2;
	}
	
	//Takes a double, returns it rounded to the hundreths place
	public static double round2(double num) {
		int answer = (int)(num*1000);
		if(answer%10>=5) {
			return ((int)((answer+10)/10))/100.0;
		}else{
			return (answer/10)/100.0;
		}
		
	}
	
	//Raises an exponent of type double to a whole number power
	public static double exponent(double base, int power) {
		double result = base;
		
		if(power < 0){
			throw new ArithmeticException("Invalid argument");
		}
		
		for(int i = 0; i < power - 1; i++) {
			result *= base;
		}
		return result;
	}
	
	//Returns the factorial of an integer
	public static int factorial(int num) {
		
		if(num == 1) {
			return 1;
		} else if(num < 0){
			throw new ArithmeticException("Invalid argument");
		}
		
		int answer = num*factorial(num-1);
		return answer;
		
	}
	
	//Takes an int, returns true if int is prime, false otherwise
	public static boolean isPrime(int num) {
		for(int i = 2; i < num; i++) {
			if(isDivisibleBy(num, i)) {
				return false;
			}
		}
		return true;
	}
	
	//Returns the greatest common factor of two ints
	public static int gcf(int num1, int num2) {
		int answer = 1;
		for(int i = 1; i <= num1; i++) {
			if(isDivisibleBy(num1, i) && isDivisibleBy(num2, i)) {
				answer =  i;

			}
		}
		return answer;
	}
	
	//Returns the square root of a double rounded to the hundreths
	public static double sqrt(double num){
		if(num < 0){
			throw new ArithmeticException("Invalid argument");
		}
		
		double answer = 1;
		
		while(!(absValue(num - answer*answer) < 0.005)){
			answer = 0.5*(num/answer + answer);
		}
		
		return round2(answer);
	}
	
	//Takes the three coefficients of a standard quadratic polynomial and returns the roots
	public static String quadForm(int a, int b, int c){
		double discriminant = discriminant(a, b, c);
		if(discriminant < 0){
			return "no real roots";
		} else if(discriminant == 0){
			return round2(-b/2*a) + "";
		} else {
			double root1 = round2((-b+sqrt(discriminant))/(2.0*a));
			double root2 = round2((-b-sqrt(discriminant))/(2.0*a));
			
			return min(root1, root2) + " and " + max(root1, root2);
		}
	}
	
}
