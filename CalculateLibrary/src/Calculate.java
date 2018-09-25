
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
		try {
			return dividend % divisor == 0;
		} catch(ArithmeticException e) {
			return false;
		}
				
	}
	
	public static double max(double x1, double x2) {
		if(x1 > x2) return x1;
		return x2;
	}
	
	public static double max(double x1, double x2, double x3) {
		return max(x1, max(x2, x3));
	}
	
	public static int min(int x1, int x2) {
		if(x1<x2) return x1;
		return x2;
	}
	
	public static double round2(double num) {
		
		if(num == 0) {
			return 0;
		}
		
		int sign = (int) (num / absValue(num));
		int answer = absValue((int)(num*1000));
		if(answer%10>=5) {
			return sign*((int)((answer+10)/10))/100.0;
		}else{
			return sign*(answer/10)/100.0;
		}
		
	}
	
	public static double exponent(double base, int power) {
		
		if(power == 0) {
			return 1;
		}
		
		boolean negativeExponent = power < 0;
		
		double result = base;
		for(int i = 0; i < absValue(power) - 1; i++) {
			result *= base;
		}
		
		if(negativeExponent) {
			result = 1/result;
		}
		
		return result;
	}
	
	public static int factorial(int num) {
		
		if(num == 1) {
			return 1;
		}
		
		int answer = num*factorial(num-1);
		return answer;
		
	}
	
	public static boolean isPrime(int num) {
		for(int i = 2; i < num; i++) {
			if(isDivisibleBy(num, i)) {
				return false;
			}
		}
		return true;
	}
	
	public static int gcf(int num1, int num2) {
		int answer = 1;
		for(int i = 2; i < num1; i++) {
			if(isDivisibleBy(num1, i) && isDivisibleBy(num2, i)) {
				answer =  i;
			}
		}
		return answer;
	}
	
}
