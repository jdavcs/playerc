package compiler.output;

public class MyFirstTest
{
	public static void main( String[] args )
	{
		MyFirstTest MyFirstTest = new MyFirstTest();
		MyFirstTest.run();
	}

	private String[] arr1;
	private double[] arr2;
	private int[] arr3;
	private boolean v1, v2 = true;
	private int v3 = 1;
	private double v4 = 2.1;
	private String v5 = "test";
	private boolean v6 = false;
	private int v7 = 3;
	private double v8 = 4.7;
	private double v9 = v8;
	private double v10 = 3;
	private String[] v21 = {"abc", "abc", "abc", "x", "y", "zzz"};
	private double[] v22 = {5.4, 5.4, 5.4, 6};
	private int[] v23 = {1, 24, 24, 24, 62};
	private rec1 v31 = new rec1("sergei", 100, true);
	private rec1 v32 = null;

	private proc1 proc1_instance = new proc1();

	private class proc1
	{
		void run()
		{
		}
	}

	private proc3 proc3_instance = new proc3();

	private class proc3
	{
		int run(int a)
		{
			if (a > 0)
			{
				return a + proc3_instance.run(a - 1);
			}
			else
			{
				return -1;
			}
		}
	}

	private proc4 proc4_instance = new proc4();

	private class proc4
	{
		private int b = 3;
		private int c = 2;
		int run(int a)
		{
			return a + b * proc3_instance.run(c);
		}
	}

	private proc5 proc5_instance = new proc5();

	private class proc5
	{
		private int a1 = 1;

		private proc5a proc5a_instance = new proc5a();

		private class proc5a
		{
			private int a1 = 1;

			private proc5b proc5b_instance = new proc5b();

			private class proc5b
			{
				private int a1 = 1;
				int run(int a)
				{
					return a1;
				}
			}
			int run(int a)
			{
				return a1 + proc5b_instance.run(a);
			}
		}
		int run(int a)
		{
			return a1 + proc5a_instance.run(a);
		}
	}

	public MyFirstTest() { }

	public void run()
	{
		v4 = +3.2 + (4 - 5) * -2 / 10;
		v6 = !v6;
		v6 = v6 && true || v1;
		v6 = 1 < 2;
		v6 = 1.2 <= 2;
		v6 = 1 > 2;
		v6 = 1 >= 2;
		v6 = 1 != 2;
		v6 = 1 == 2;
		v5 = v21[1];
		v5 = v21[v23[0]];
		v5 = v31.name;
		v3 = v31.age;
		v6 = v31.cool;
		v31.name = v5;
		v31.age = v3;
		v31.cool = v6;
		if (1 == 2)
		{
			v5 = "a";
		}
		else if ((4 == 5) && (3 < 1 + 1))
		{
			v5 = "a";
		}
		else
		{
			v5 = "a";
		}
		while (true)
		{
			v5 = "a";
			System.out.print(v5);
			System.out.print(v7);
			System.out.println();
			break;
		}
		for (int i = 0; i < 10 - 1; i+=1)
		{
			v5 = "a";
			System.out.print(v5);
			System.out.println();
			break;
		}
		java.io.BufferedReader in = new java.io.BufferedReader(new java.io.InputStreamReader(System.in));
		try {
			v5 = in.readLine();
			v3 = Integer.parseInt(in.readLine());
			v8 = Double.parseDouble(in.readLine());
		} catch (Exception e) { e.printStackTrace(); }
		v3 = proc4_instance.run(5);
	}
}
