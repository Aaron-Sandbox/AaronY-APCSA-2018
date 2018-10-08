
public class Quadratic {
	public static String processQuadratic(double coeffA, double coeffB, double coeffC) {
		
		//Allocates memory for Strings to be used later
		String opens;
		String symmetryAxis;
		String vertex;
		String xint;
		String yint;
		
		//Checks input to make sure it is a parabola
		if(coeffA == 0) {
			throw new ArithmeticException("a cannot equal 0, that is not a parabola");
		}
		
		/*
		 * Creates ints a, h, and k, representing the variables 
		 * of the vertex form of a parabola: y = a(x-h)^2 + k
		 */
		double a = coeffA;
		double h = (coeffB / coeffA)/2;
		double k = coeffC - square(h)*a;
		
		/* Processes information using vertex form variables and methods from Calculate project */
		vertex = "(" + h + ", " + k + ")";
		if(isPositive(a)) {
			opens = "Up";
		} else {
			opens = "Down";
		}
		symmetryAxis = h +"";
		xint = quadForm(coeffA, coeffB, coeffC);
		yint = coeffC + "";
		
		//Builds a string containing all the information
		String stringBuilder = "Description of the graph of:\n"
							 + "y = " + coeffA + "x^2 + " + coeffB + "x + " + coeffC +"\n\n"
							 + "Opens: " + opens +"\n"
							 + "Axis of Symmetry: " + symmetryAxis + "\n"
							 + "Vertex: " + vertex + "\n"
							 + "x-intercept(s): " + xint + "\n"
							 + "y-intercept: " + yint + "\n";
		
		//returns full string
		return stringBuilder;
		
		
	}
	
	//Takes the three coefficients of a standard quadratic polynomial and returns the roots in String form
	public static String quadForm(double a, double b, double c){
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
	
	//Takes the coefficients of a quadratic equation and returns the output of the discriminant
	public static double discriminant(double a, double b, double c) {
		return (b*b)-4*a*c; 
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
	
	//Returns the larger of two doubles
	public static double max(double x1, double x2) {
		if(x1 > x2) return x1;
		return x2;
	}
	
	//Returns the larger of three doubles, overrides max(double, double)
	public static double max(double x1, double x2, double x3) {
		return max(x1, max(x2, x3));
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
	
	//Takes a double, returns it rounded to the hundreths place
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
	
	//Returns whether a double is positive (>0) or not
	public static boolean isPositive(double a) {
		if(a > 0) {
			return true;
		} 
		return false;
	}
	
	//Returns the square of a number as an double
	public static double square(double input) {
		return input*input;
	}
	
	//Finds the greatest common factor of two numbers
	public static int gcf(double num1, double num2) {
		int answer = 1;
		for(int i = 1; i <= num1; i++) {
			if(isDivisibleBy(num1, i) && isDivisibleBy(num2, i)) {
				answer =  i;

			}
		}
		return answer;
	}
	
	//Returns whether one number is divisible by another
	public static boolean isDivisibleBy(double dividend, double divisor) {
		if(divisor == 0){
			throw new ArithmeticException("Invalid argument");
		}
		
		return dividend % divisor == 0;	
	}
	
}
