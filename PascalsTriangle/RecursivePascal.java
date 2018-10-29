package lab1;

import java.util.Scanner;

import edu.princeton.cs.algs4.Stopwatch;

public class RecursivePascal implements Pascal{

	public static void main(String[] args) {

		Scanner in = new Scanner(System.in);

		System.out.println("Enter row number: ");
		int row = in.nextInt();

		System.out.println("Enter 'true' or 'false': ");
		boolean b = in.nextBoolean();

		RecursivePascal pascal = new RecursivePascal();
		Stopwatch stopwatch = new Stopwatch();
		pascal.printPascal(row, b);
		double time = stopwatch.elapsedTime();
		System.out.println("");
		System.out.println("Runtime: " + time);

		in.close();
	}

	public void printPascal(int row, boolean b)
	{

		if (row == 0)
		{
			System.out.println("\t1");
			return;
		}
		else
		{
			if(b)
				printPascal(row-1, b);

			//Calculate every entry in the row by calling binom
			for (int i = 0; i <= row; i++)
			{
				System.out.print(" " + binom(row, i));
			}
			System.out.println("");

			if(!b)
				printPascal(row-1, b);
		}

	}

	public int binom (int n, int k)
	{
		if (k < 0 || k > n)
		{
			System.out.println("Not defined for 0 > k > n");
			return 0;
		}
		if (k==0 || k==n)
			return 1;
		else 
			//n is the current row an k is the current number. 
			//recursivly add the previous entry from the previous row with the current entry in the previous row
			return binom(n-1, k-1) + binom(n-1, k);
	}

}
