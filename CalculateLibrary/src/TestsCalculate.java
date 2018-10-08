import static org.junit.Assert.*;
import org.junit.BeforeClass;
import org.junit.Test;

public class TestsCalculate 
{

	@BeforeClass
	public static void setUpBeforeClass() throws Exception 
	{
	}

	@Test
	public void testSquareIntNonNegative() 
	{
		int[] inputs = {0, 5, 100};
        int[] expecteds = {0, 25, 10000};
        int[] actuals = new int[inputs.length];
        for (int i = 0; i < inputs.length; i++)
        {
        	actuals[i] = Calculate.square(inputs[i]);
        	String failureMsg = String.format("Input %d, expected %d, actual %d\n", inputs[i], 
        																		  expecteds[i], 
        																		  actuals[i]);
        	assertEquals(failureMsg, expecteds[i], actuals[i]);
        }
	}
	
	@Test
	public void testSquareIntNegative() 
	{
		int[] inputs = {-1, -25};
        int[] expecteds = {1, 625};
        int[] actuals = new int[inputs.length];
        for (int i = 0; i < inputs.length; i++)
        {
        	actuals[i] = Calculate.square(inputs[i]);
        	String failureMsg = String.format("Input %d, expected %d, actual %d\n", inputs[i], 
        																		  expecteds[i], 
        																		  actuals[i]);
        	assertEquals(failureMsg, expecteds[i], actuals[i]);
        }
	}

	/*
	@Test
	public void testSquareDouble() 
	{
		fail("Not yet implemented");
	}
	*/

	@Test
	public void testCube() 
	{
		int[] inputs = {0, 5, -5};
        int[] expecteds = {0, 125, -125};
        int[] actuals = new int[inputs.length];
        for (int i = 0; i < inputs.length; i++)
        {
        	actuals[i] = Calculate.cube(inputs[i]);
        	String failureMsg = String.format("Input %d, expected %d, actual %d\n", inputs[i], 
        																		  expecteds[i], 
        																		  actuals[i]);
        	assertEquals(failureMsg, expecteds[i], actuals[i]);
        }
	}

	@Test
	public void testAverageDoubleDouble() 
	{
		double[][] inputs = {{0, 0}, {-5, 5}, {10, 20}, {-10, -20}, {20, -10 }};
        double[] expecteds = {0, 0, 15, -15, 5};
        double[] actuals = new double[inputs.length];
        final double TOL = 0.001;
        for (int i = 0; i < inputs.length; i++)
        {
        	actuals[i] = Calculate.average(inputs[i][0], inputs[i][1]);
        	String failureMsg = String.format("Input (%f, %f), expected %f, actual %f\n", inputs[i][0],
        																				  inputs[i][1],
        																		          expecteds[i], 
        																		          actuals[i]);
        	assertEquals(failureMsg, expecteds[i], actuals[i], TOL);
        }
	}

	@Test
	public void testAverageDoubleDoubleDouble() 
	{
		double[][] inputs = {{0, 0, 0}, {-5, 5, 0}, {10, 20, 30}, {-10, -20, -30}, {20, -10, -5 }};
        double[] expecteds = {0, 0, 20, -20, 5.0/3};
        double[] actuals = new double[inputs.length];
        final double TOL = 0.001;
        for (int i = 0; i < inputs.length; i++)
        {
        	actuals[i] = Calculate.average(inputs[i][0], inputs[i][1], inputs[i][2]);
        	String failureMsg = String.format("Input (%f, %f), expected %f, actual %f\n", inputs[i][0],
        																				  inputs[i][1],
        																				  inputs[i][2],
        																		          expecteds[i], 
        																		          actuals[i]);
        	assertEquals(failureMsg, expecteds[i], actuals[i], TOL);
        }
	}

	@Test
	public void testToDegrees() 
	{
		double[] inputs = {0, 1, -2, 10};
        double[] expecteds = {0, 180.0/Math.PI, -360.0/Math.PI, 1800/Math.PI};
        double[] actuals = new double[inputs.length];
        final double TOL = 0.001;
        for (int i = 0; i < inputs.length; i++)
        {
        	actuals[i] = Calculate.toDegrees(inputs[i]);
        	//String failureMsg = String.format("Input %d, expected %d, actual %d\n", inputs[i], 
        	//																	  expecteds[i], 
        	//																	  actuals[i]);
        	assertEquals(expecteds[i], actuals[i], TOL);
        }
	}

	@Test
	public void testToRadians() 
	{
		double[] inputs = {0, 57.3, 180, 360, -90};
        double[] expecteds = {0, 57.3*(Math.PI/180.0), 180*(Math.PI/180.0), 360*(Math.PI/180.0), -90*(Math.PI/180.0)};
        double[] actuals = new double[inputs.length];
        final double TOL = 0.001;
        for (int i = 0; i < inputs.length; i++)
        {
        	actuals[i] = Calculate.toRadians(inputs[i]);
        	//String failureMsg = String.format("Input %d, expected %d, actual %d\n", inputs[i], 
        	//																	  expecteds[i], 
        	//																	  actuals[i]);
        	assertEquals(expecteds[i], actuals[i], TOL);
        }
	}

	@Test
	public void testDiscriminant() 
	{
		double[][] inputs = {{0, 0, 0}, {1, 2, 3}, {2, 2, -4}, {10, 0, -3}};
        double[] expecteds = {0, -8, 36, 120};
        double[] actuals = new double[inputs.length];
        final double TOL = 0.001;
        for (int i = 0; i < inputs.length; i++)
        {
        	actuals[i] = Calculate.discriminant(inputs[i][0], inputs[i][1], inputs[i][2]);
        	//String failureMsg = String.format("Input (%f, %f), expected %f, actual %f\n", inputs[i][0],
        	//																			  inputs[i][1],
        	//																			  inputs[i][2],
        	//																	          expecteds[i], 
        	//																	          actuals[i]);
        	assertEquals(expecteds[i], actuals[i], TOL);
        }
	}

	@Test
	public void testToImproperFrac() 
	{
		int[][] inputs = {{1, 2, 3}, {0, 2, 3}, {2, 3, 5}};
        String[] expecteds = {"5/3", "2/3", "13/5"};
        String[] actuals = new String[inputs.length];
        for (int i = 0; i < inputs.length; i++)
        {
        	actuals[i] = Calculate.toImproperFrac(inputs[i][0], inputs[i][1], inputs[i][2]);
        	String msg = String.format("Inputs %d, %d, %d\n", inputs[i][0], inputs[i][1], inputs[i][2]);
        	assertEquals(msg, expecteds[i], actuals[i]);
        }
	}

	@Test
	public void testToMixedNum() 
	{
		int[][] inputs = {{5, 3}, {8, 3}, {13, 5}};
        String[] expecteds = {"1_2/3", "2_2/3", "2_3/5"};
        String[] actuals = new String[inputs.length];
        for (int i = 0; i < inputs.length; i++)
        {
        	actuals[i] = Calculate.toMixedNum(inputs[i][0], inputs[i][1]);
        	//String failureMsg = String.format("Input %d, expected %d, actual %d\n", inputs[i], 
        	//																	  expecteds[i], 
        	//																	  actuals[i]);
        	assertEquals(expecteds[i], actuals[i]);
        }
	}

	@Test
	public void testFoil() 
	{
		int[][] inputs = {{2, 3, 6, -7}, {2, 3, 6, -5}, {3, 4, 5, 6}};
		String var = "n";
        String[] expecteds = {"12n^2 + 4n + -21", "12n^2 + 8n + -15", "15n^2 + 38n + 24" };
        String[] actuals = new String[inputs.length];
        for (int i = 0; i < inputs.length; i++)
        {
        	actuals[i] = Calculate.foil(inputs[i][0], inputs[i][1], inputs[i][2], inputs[i][3], var);
        	//String failureMsg = String.format("Input %d, expected %d, actual %d\n", inputs[i], 
        	//																	  expecteds[i], 
        	//																	  actuals[i]);
        	assertEquals(expecteds[i], actuals[i]);
        }
	}

	// Part 2
	@Test
	public void testIsDivisibleBy()
	{
		int[][] inputs = {{5, 1}, {5, 2}, {5, -1}, {5, -2}, {-5, -2}, {10, 5}, {0, 5}};
        boolean[] expecteds = {true, false, true, false, false, true, true};
        boolean[] actuals = new boolean[inputs.length];
        for (int i = 0; i < inputs.length; i++)
        {
        	actuals[i] = Calculate.isDivisibleBy(inputs[i][0], inputs[i][1]);
        	String msg = String.format("Inputs = (%d and %d) ", inputs[i][0], inputs[i][1]);
        	assertEquals(msg, expecteds[i], actuals[i]);
        }
	}
	
	@Test
	public void testAbsValue()
	{
		final double TOL = 0.001;
		double[] inputs = {0, 5.2, -5.1};
        double[] expecteds = {0, 5.2, 5.1};
        double[] actuals = new double[inputs.length];
        for (int i = 0; i < inputs.length; i++)
        {
        	actuals[i] = Calculate.absValue(inputs[i]);
        	String msg = String.format("Input = (%.2f) ", inputs[i]);
        	assertEquals(msg, expecteds[i], actuals[i], TOL);
        }
	}
	
	@Test
	public void testMaxTwoDoubles()
	{
		double[][] inputs = {{1, 2}, {2.01, 2.02}, {4, 4}, {-5.9, -5.8}, {-8, 7}, {-1, 0}, {0.0, 0.00000001}};
        double[] expecteds = {2, 2.02, 4, -5.8, 7, 0, 0 };
        double[] actuals = new double[inputs.length];
        final double TOL = 0.001;
        for (int i = 0; i < inputs.length; i++)
        {
        	actuals[i] = Calculate.max(inputs[i][0], inputs[i][1]);
        	String msg = String.format("Inputs = (%.2f and %.2f) ", inputs[i][0], inputs[i][1]);
        	assertEquals(msg, expecteds[i], actuals[i], TOL);
        }
	}
	
	@Test
	public void testMaxThreeDoubles()
	{
		double[][] inputs = {{1, 2, 2.01}, {2, 3, 2.99}, {4, 4, 4.0}, {5, 6, 4.9}, {-8, 7, 6.99}, {-1, 0, 0.01}, {0, 0, -2.0}};
        double[] expecteds = {2.01, 3, 4, 6, 7, 0.01, 0 };
        double[] actuals = new double[inputs.length];
        final double TOL = 0.001;
        for (int i = 0; i < inputs.length; i++)
        {
        	actuals[i] = Calculate.max(inputs[i][0], inputs[i][1], inputs[i][2]);
        	String msg = String.format("Inputs = (%.2f, %.2f, and %.2f) ", inputs[i][0], inputs[i][1], inputs[i][2]);
        	assertEquals(msg, expecteds[i], actuals[i], TOL);
        }
	}
	
	@Test
	public void testMin()
	{
		int[][] inputs = {{1, 2}, {2, 3}, {4, 4}, {5, 6}, {-8, 7}, {-1, 0}, {0, 0}, {-2, -1}};
        int[] expecteds = {1, 2, 4, 5, -8, -1, 0, -2 };
        int[] actuals = new int[inputs.length];
        for (int i = 0; i < inputs.length; i++)
        {
        	actuals[i] = Calculate.min(inputs[i][0], inputs[i][1]);
        	String msg = String.format("Inputs = (%d and %d)", inputs[i][0], inputs[i][1]);
        	assertEquals(msg, expecteds[i], actuals[i]);
        }
	}
	
	@Test
	public void testRound2()
	{
		final double TOL = 0.001;
		double[] inputs = {0.00, 1234.5750, -1234.5750, 1234.5749, -1234.5749, -987.549, -987.551};
        double[] expecteds = {0.00, 1234.58, -1234.58, 1234.57, -1234.57, -987.55, -987.55};
        double[] actuals = new double[inputs.length];
        for (int i = 0; i < inputs.length; i++)
        {
        	actuals[i] = Calculate.round2(inputs[i]);
        	String msg = String.format("Input = %.5f ", inputs[i]);
        	assertEquals(msg, expecteds[i], actuals[i], TOL);
        }
	}
	

}
