
public class Calculate {
	
	//Returns the square of a number as a signed integer
	public static int square(int input) {
		return input*input;
	}
	
	//Returns the cube of a number as a signed integer
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
		return (wholeNum*denominator+numerator) + "/" + denominator;
	}
	
	//Takes a numerator and denominator and returns it as a mixed fraction in the form a_b/c
	public static String toMixedNum(int numerator, int denominator) {
		return numerator/denominator + "_" + numerator%denominator+ "/" + denominator; 
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
		
		return ax + var + "^2" + sign(bx)+ abs(bx) + var + sign(cx) + abs(cx);
	}
	
	public static String sign(int signedInt) {
		if(signedInt >= 0) {
			return " + ";
		} else {
			return " - ";
		}
	}
	
	public static int abs(int integer) {
		if(integer < 0) return -1*integer;
		return integer;
	}
	public static double abs(double integer) {
		if(integer < 0) return -integer;
		return integer;
	}
	
	public static boolean isDivisibleBy(double dividend, double divisor) {
		return dividend % divisor == 0;
	}
	
}
