package lab1;

public abstract class ErrorPascal implements Pascal{

	public abstract void printPascal (int n, boolean b);
	public abstract int binom (int n, int b);

	public static void verifyPascal (int n, boolean b)
	{
		if (n < 0)
		{
			throw new IllegalArgumentException ("n has to be => 0");
		}
	}

	public static void verifyBinom (int n, int k)
	{
		if (n < 0)
		{
			throw new IllegalArgumentException ("n has to be => 0");
		}
		if (k < 0)
		{
			throw new IllegalArgumentException ("k has to be => 0");
		}
		if (k > n)
		{
			throw new IllegalArgumentException ("k has to be <= n");
		}
	}
}
