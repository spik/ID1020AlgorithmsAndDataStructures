package lab1;

import edu.princeton.cs.algs4.Stopwatch;

public class RecursivePascalMultiArray {

	static int currentRow;
	static long[][] triangle;
	
	static int numberOfRows = 100;
	static boolean isUpsideDown = false;

	public static void main(String[] args) {

		Stopwatch stopwatch = new Stopwatch();
		printPascal(numberOfRows, isUpsideDown);
		double runtime = stopwatch.elapsedTime();
		System.out.println("Runtime: " + runtime);
	}

	public static void printPascal(int n)
	{
		printPascal(n, false);
	}

	public static void printPascal(int enteredNumberOfRows, boolean upsideDown)
	{
		// First iteration
		if (triangle == null)
		{
			currentRow = enteredNumberOfRows;
			enteredNumberOfRows = 0;
			triangle = new long[currentRow][currentRow];
		}

		// When we have reached the last row, we're done
		if (enteredNumberOfRows == currentRow) 
		{ 
			return; 
		}

		// Left and right one's
		triangle[enteredNumberOfRows][0] = 1;
		
		if (enteredNumberOfRows != 0) 
		{ 
			triangle[enteredNumberOfRows][enteredNumberOfRows - 1] = 1; 
		}

		for (int i = 1; i <= enteredNumberOfRows; i++)
		{
			long aboveRowLeft = triangle[enteredNumberOfRows - 1][i - 1];
			long aboveRowRight = triangle[enteredNumberOfRows - 1][i];
			triangle[enteredNumberOfRows][i] = aboveRowLeft + aboveRowRight;
		}

		if (upsideDown) 
		{
			printPascal(enteredNumberOfRows + 1, upsideDown); 
		}

		// Print the current row
		for (int j = 0; j <= enteredNumberOfRows; j++)
		{
			System.out.print(triangle[enteredNumberOfRows][j] + (enteredNumberOfRows == j ? "\n" : " "));
		}

		if (!upsideDown) 
		{
			printPascal(enteredNumberOfRows + 1, upsideDown); 
		}

	}

	public int binom (int n, int k)
	{
		if (k == 0 || n == k) { return 1; }

		return binom(n - 1, k - 1) + binom(n - 1, k);

	}
}
