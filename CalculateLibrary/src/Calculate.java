


public class Calculate {
	
	public static int square(int input) {
		return input*input;
	}
	
	public static int cube(int input) {
		return input*input*input;
	}
	
	public static double average(double x1, double x2) {
		return (x1+x2)/2;
	}
	
	public static double average(double x1, double x2, double x3) {
		return (x1+x2+x3)/3;
	}
	
	public static double average(double ... x) {
		int total = 0;
		for(double num : x) {
			total+= num;
		}
		
		return total/x.length;
	}
	
	public static double toDegrees(double radianMeasure) {
		return (180*radianMeasure)/3.14159;
	}
	
	public static double toRadians(double degreeMeasure) {
		return (3.14159*degreeMeasure)/180;
	}
	
	public static double discriminant(double a, double b, double c) {
		return (b*b)-4*a*c; 
	}
	
	public static String toImproperFrac(double wholeNum, double numerator, double denominator) {
		return (wholeNum*denominator+numerator) + "/" + denominator;
	}
	
	public static String toMixedNum(int numerator, int denominator) {
		return numerator/denominator + "/" + numerator%denominator;
	}
	
	public static String foil(int a, int b, int c, int d, String var) {
		int ax, bx, cx;
		
		ax = a*c;
		bx = a*d + b*c;
		cx = b*d;
		
		return ax + var + "^2 + " + bx + var + " + " + cx;
	}
}
