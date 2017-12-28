
public class Test {



	public static void testPriorityQueue()
	{

		int size = 31;
		BinomialQueue Q = new BinomialQueue(size);

		for (int i = 0; i < size; i++)
		{
			Q.insert(i, i);

			//          If trees look off, you can uncomment this bit of code to see how
			//	        the trees change as elements are inserted
//
//						Q.PrintQueue();                 
//						System.out.println("------");

		}
		Q.PrintQueue();

		size = 5000;
		Q = new BinomialQueue(size);
		for (int i = 0; i < size; i++)
		{
			Q.insert(i, size-i);
		}
		boolean errorFound = false;
		for (int i = 0; i < size; i++)
		{
			if (size - i - 1 != Q.RemoveMin())
			{
				errorFound = true;

			}
		}
		if (errorFound)
		{
			System.out.println("Error on removeSmallest (and / or insertElem)");
		}
		else
		{ 
//			System.out.println("-----------------------");
//			System.out.println();
			System.out.println("removeSmallest OK");
//			System.out.println();
//			System.out.println("-----------------------");
		}
		size = 10;
		Q = new BinomialQueue(size);
		for (int i = 0; i < size; i++)
		{
			Q.insert(i, 3 * size-i);
		}
		for (int i = 0; i < size; i++)
		{
			Q.decreaseKey(i, i + 1);
		}

		errorFound = false;
		for (int i = 0; i < size; i++)
		{
			int next = Q.RemoveMin();
			if (i !=  next)
			{
				System.out.println(i);
				System.out.println(next);
				errorFound = true;
			}
		}
		if (errorFound)
		{
			System.out.println("Error on decreaseKey (and / or insertElem / removeSmallest)");
		}
		else
		{
			System.out.println("decreaseKey OK");
		}


	}


	public static void testHashTable()
	{
		HashTable H = new HashTable();

		int size = 500;

		for (int i = 0; i < size; i++)
		{
			String key = Integer.toString(i) +"_" +  Integer.toString(i);
			H.insert(key, new Integer(i));
		}
		boolean errorFound = false;
		for (int i = 0; i < size; i++)
		{
			String key = Integer.toString(i) +"_" +  Integer.toString(i);
			if (H.find(key) == null || !H.find(key).equals(new Integer(i)))
			{
				errorFound = true;
			}
		}
		if (errorFound)
		{
			System.out.println("Error in insert / find");
		}
		else
		{
			System.out.println("HashTable insert / find both look good");
		}

		for (int i = 0; i < size; i = i + 2)
		{
			String key = Integer.toString(i) +"_" +  Integer.toString(i);
			H.delete(key);
		}
		errorFound = false;
		for (int i = 0; i < size; i = i + 2)
		{
			String key = Integer.toString(i) +"_" +  Integer.toString(i);
			if (i % 2 == 0)				
			{
				if (H.find(key) != null)
					errorFound = true;
			}

			else if (H.find(key) == null || !H.find(key).equals(new Integer(i)))
			{
				errorFound = true;
			}
		}
		if (errorFound)
		{
			System.out.println("Error in HashTable delete (or insert / find)");
		}
		else
		{
			System.out.println("HashTable delete looks good");
		}

	}

	public static void main(String args[])
	{
		testPriorityQueue();
		testHashTable();
	}
}