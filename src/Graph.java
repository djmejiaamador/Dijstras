import java.util.Random;

class Graph 
{
	public int numVertex;
	public Edge edges[];

	public Graph(int numVerteces) 
	{
		int i;
		numVertex = numVerteces;
		edges = new Edge[numVerteces];
		for (i=0; i<numVertex;i++)
		{
			edges[i] = null;
		}
	}


	public void print(String[] meh) 
	{
		int i;
		for (i=0; i<numVertex; i++) 
		{
			System.out.print(meh[i]+" Adj. List:");
			if (edges[i] == null)
			{
				System.out.println("<empty>");
			}
			else 
			{
				for (Edge tmp = edges[i]; tmp != null; tmp = tmp.next) 
				{
					System.out.print(" " + meh[tmp.neighbor] + ": " + tmp.cost);
				}
				System.out.println();
			}
		}
	}
}