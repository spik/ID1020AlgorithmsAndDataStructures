package lab1;

import java.util.Scanner;

public class Driver {

	public static void main(String[] args) {

		Scanner in = new Scanner(System.in);

		System.out.println("Press 1 to test RecursivePascalFast, press 2 to test IterativePascal");
		int whichClass = in.nextInt();

		System.out.println("Enter row number: ");
		int row = in.nextInt();

		System.out.println("Enter 'true' or 'false': ");
		boolean b = in.nextBoolean();

		if (whichClass == 1)
		{	
			Pascal recursivepascal = new RecursivePascalFastV2();

			System.out.println("binom(4, 2) = " + recursivepascal.binom(4, 2));
			recursivepascal.printPascal(row, b);
		}

		else if (whichClass == 2)
		{
			Pascal iterativePascal = new IterativePascal2();

			System.out.println("binom(4, 2) = " + iterativePascal.binom(4, 2));
			iterativePascal.printPascal(row, b);
		}
		else
		{
			System.out.println("Invalid input");
		}
		in.close();
	}

}
