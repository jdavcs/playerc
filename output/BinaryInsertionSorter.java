package compiler.output;

public class BinaryInsertionSorter
{
	public static void main( String[] args )
	{
		BinaryInsertionSorter BinaryInsertionSorter = new BinaryInsertionSorter();
		BinaryInsertionSorter.run();
	}

	private int[] arrNum;
	private int[] numbers = {0, 0, 0, 0, 0, 0};

	private sort sort_instance = new sort();

	private class sort
	{
		private int v = 0;
		private int insertPosition = 0;
		int[] run(int[] input, int length)
		{
			for (int i = 1; i < length; i+=1)
			{
				v = input[i];
				insertPosition = findInsertPosition_instance.run(input, 0, i, input[i]);
				for (int j = i - 1; j > insertPosition - 1; j+=-1)
				{
					input[j + 1] = input[j];
				}
				input[insertPosition] = v;
			}
			return input;
		}
	}

	private findInsertPosition findInsertPosition_instance = new findInsertPosition();

	private class findInsertPosition
	{
		private int midpoint = 0;
		int run(int[] input, int left, int right, int value)
		{
			if (left == right)
			{
				return left;
			}
			midpoint = (left + right) / 2;
			if (value > input[midpoint])
			{
				return findInsertPosition_instance.run(input, midpoint + 1, right, value);
			}
			else if (value < input[midpoint])
			{
				return findInsertPosition_instance.run(input, left, midpoint, value);
			}
			return midpoint;
		}
	}

	public BinaryInsertionSorter() { }

	public void run()
	{
		System.out.print("enter 6 numbers:");
		System.out.println();
		for (int i = 0; i < 6; i+=1)
		{
			java.io.BufferedReader in = new java.io.BufferedReader(new java.io.InputStreamReader(System.in));
			try {
				numbers[i] = Integer.parseInt(in.readLine());
			} catch (Exception e) { e.printStackTrace(); }
		}
		sort_instance.run(numbers, 6);
		for (int i = 0; i < 6; i+=1)
		{
			System.out.print(numbers[i]);
			System.out.println();
		}
	}
}
