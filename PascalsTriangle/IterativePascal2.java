package lab1;

import java.util.HashMap;

public class IterativePascal2 extends ErrorPascal implements Pascal{
	
	//Creating a HashMap "map". (A HashMap within a HashMap, similar to multiarray)
	static HashMap<Integer, HashMap<Integer, Integer>> map = new HashMap<Integer, HashMap<Integer,Integer>>();

	static int rowCount = 0;

	public void printPascal(int numberOfRequestedRows, boolean isUpsideDown) 
	{
		verifyPascal(numberOfRequestedRows, isUpsideDown);

		for(int rowCount = 0; rowCount < numberOfRequestedRows; rowCount++)
		{

			HashMap<Integer, Integer> currentRow = new HashMap<Integer, Integer>();

			if(rowCount == 0)
			{
				currentRow.put(0, 1);
			}
			else
			{
				int previousRowCount = rowCount-1;
				HashMap<Integer, Integer> previousRow = map.get(previousRowCount);

				int numberOfPositions = rowCount+1;

				for(int pos = 0; pos < numberOfPositions; pos++)
				{
					int previousLeftValue = previousRow.containsKey(pos-1) ? previousRow.get(pos-1) : 0;
					int previousRightValue = previousRow.containsKey(pos) ? previousRow.get(pos) : 0;
					int value = previousLeftValue + previousRightValue;
					currentRow.put(pos, value);
				}
			}			
			map.put(rowCount, currentRow);
		}
		printTriangle(isUpsideDown);
	}
	public static void printTriangle(boolean isUpsideDown)
	{
		
		//get number of positions in the row
		int numberOfRows = map.keySet().size();

		if(isUpsideDown)
		{
			//Loop through number of rows
			for(int rowCount = 0; rowCount < numberOfRows; rowCount++)
			{
				//Get value at position rowcount
				HashMap<Integer, Integer> row = map.get(rowCount);

				//Entry is a pair of a key and the value. 
				//entrySet is the number of positions in the row. 
				//Loop through positions at row
				for(HashMap.Entry<Integer, Integer> posPair : row.entrySet())
				{
					System.out.print("\t" + posPair.getValue());
				}
				System.out.println(" ");
			}
		}

		else
		{
			for(int rowCount = numberOfRows-1; rowCount >= 0; rowCount--)
			{
				HashMap<Integer, Integer> row = map.get(rowCount);
				for(HashMap.Entry<Integer, Integer> posPair : row.entrySet())
				{
					System.out.print("\t" + posPair.getValue());	
				}
				System.out.println(" ");
			}
		}

	}

	public int factorial (int n)
	{
		int product = 1;
		for (int i = 2; i <= n; i++) 
		{
			product = product * i;
		}
		return product;
	}

	public int binom(int n, int k)
	{
		//verifyBinom(n, k);
		int result = factorial(n)/(factorial(k)*factorial(n-k));
		return result;
	}
}
