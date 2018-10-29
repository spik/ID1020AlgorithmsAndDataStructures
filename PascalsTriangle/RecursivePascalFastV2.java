package lab1;

import java.util.HashMap;

public class RecursivePascalFastV2 extends ErrorPascal implements Pascal{

	//Creating a HashMap "map". (A HashMap within a HashMap, similar to multiarray)
	static HashMap<Integer, HashMap<Integer, Integer>> map = new HashMap<Integer, HashMap<Integer,Integer>>();

	static int rowCount = 0;


	public void printPascal(int numberOfRequestedRows, boolean isRightSideUp)
	{
		verifyPascal(numberOfRequestedRows, isRightSideUp);
		//When numberOfRequestedRows == 0, every row has been printed and we are done. 
		if (numberOfRequestedRows == 0)
		{
			return;
		}

		//Creating a HashMap for the current row.
		HashMap<Integer, Integer> currentRow = new HashMap<Integer, Integer>();


		//For the first row
		if(rowCount == 0)
		{
			currentRow.put(0, 1);
		}
		else
		{
			//We want to read the previous row.
			int previousRowCount = rowCount-1;

			//Create a hasmap for the previous row. 
			HashMap<Integer, Integer> previousRow = map.get(previousRowCount);

			/* For every position in this row (i.e. k-value), check the previous row and add the value on the
			 * previous position with the value on the same position as our k-value. 
			 * Add the result to currentRow.
			 * When we are done constructing this row, add it to "map" to save it for next "loop".
			 * */

			//The number of positions in a row will always be one more than our row count
			int numberOfPositions = rowCount+1;

			//Loop trough every position on the row. 
			for(int pos = 0; pos < numberOfPositions; pos++)
			{
				//If previousRow contains a key, i.e. has a saved value, get the requested value. 
				//Else return 0 (to avoid nullPointerException).
				
				//Get k-1 on previous row. 
				int previousLeftValue = previousRow.containsKey(pos-1) ? previousRow.get(pos-1) : 0;
				//Get k on previous row
				int previousRightValue = previousRow.containsKey(pos) ? previousRow.get(pos) : 0;
				//Add the values to complete (n-1)(k-1)+(n-1)(k)
				int value = previousLeftValue + previousRightValue;
				//Add the value at position k in currentRow. 
				currentRow.put(pos, value);
			}
		}

		//Save the value of the current row in map. 
		map.put(rowCount, currentRow);

		//Increment rowCount for next loop
		rowCount++;

		//To print upside down, i.e. if isRightSideUp is false.
		if (!isRightSideUp) 
		{
			printPascal(numberOfRequestedRows - 1, isRightSideUp); 
		}

		//Prints the current row
		for(int value : currentRow.values())
		{
			System.out.print("\t" + value);
		}

		System.out.println("");

		//To print right side up, i.e. isRightSideUp is true. 
		if (isRightSideUp) 
		{
			printPascal(numberOfRequestedRows - 1, isRightSideUp); 
		}
	}

	public int binom (int n, int k)
	{
		if (k==0 || k==n)
		{
			return 1;
		}

		verifyBinom(n, k);
		return binom(n-1, k-1) + binom(n-1, k);

	}

}
