import java.util.*;

public class QuadraticClient {

	public static void main(String[] args) {
		
		boolean run = true;
		Scanner scanner = new Scanner(System.in);
		
		System.out.println("Welcome to the Quadratic Describer\n"
				+ "Provide values for coefficients a, b, and c");
		
		while(run) {
		
			System.out.print("\na: ");
			double a = scanner.nextDouble();
			
			System.out.print("b: ");
			double b = scanner.nextDouble();
			
			System.out.print("c: ");
			double c = scanner.nextDouble();
			
			
			System.out.println("\n" +quadrDescriber(a, b, c));
			
			System.out.println("Do you want to keep going? (Type \"quit\" to end)");
			String cont = scanner.next();
			
			if(cont.equals("quit")) run = false;
			
		}
		
		scanner.close();

	}
	
	public static String quadrDescriber(double a, double b, double c) {
		return Quadratic.processQuadratic(a, b, c);
	}

}
