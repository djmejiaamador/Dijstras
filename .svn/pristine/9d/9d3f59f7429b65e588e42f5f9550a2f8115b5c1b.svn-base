

public class DijkstraTable {

	public DijkNode[] t;
	public String[] names;

	public DijkstraTable(int size, String[] allNames){
		t= new DijkNode[size];
		names = allNames;
		for(int i = 0; i<size; i++){
			t[i]= new DijkNode();
		}

	}
	public void printPath(){
		System.out.println("printing path");
		for(int i = 0 ; i<t.length ; i++){
			System.out.print ( names[i] + " "+ t[i].distance +": " );
			Print(i);
		}
	}
	public void Print(int vertex){
		if( t[vertex].path == -1){
			System.out.print(names[vertex] +"\n");
		}else{
			System.out.print(names[vertex] + " ");
			Print(t[vertex].path);
		}
	}

	public class DijkNode {
		public int distance;
		public int path;
		public boolean known;
		DijkNode(){
			distance = Integer.MAX_VALUE;
			path = -1;
			known = false;
		}

	}

}
