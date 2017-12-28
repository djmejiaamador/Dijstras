
public class Dijkstra {

	public static void main(String args[] ){

		InfoCreator m = new InfoCreator(args[0]);
		m.readFileNames();
		String[] allNames = m.GenerateNameList();
		for(String i:allNames){	
			//System.out.println("value: "+i);
		}

		HashTable t = new HashTable();
		for(int  i = 0; i<allNames.length;i++){
			t.insert(allNames[i], i);
			//n.insert(i,t.find(allNames[i]) );
		}
		//t.print();
		Graph meh = m.CreateGraph(t);
		meh.print(allNames);
		DijkstraTable Table= new DijkstraTable(meh.edges.length,allNames);
		dijkstra(meh.edges,0,Table);
		Table.printPath();
	}


	public static void dijkstra(Edge G[], int s, DijkstraTable Table) {

		int i, v;
		Edge e;
		BinomialQueue n = new BinomialQueue(G.length);
		Table.t[s].distance = 0;
		n.insert(0, 0);
		for(i=1; i<G.length; i++) {
			n.insert(i, Table.t[i].distance);
		}		
			while( !(n.empty()) ){
				v = n.RemoveMin();
				Table.t[v].known = true;
				for (e = G[v]; e != null; e = e.next) {
					if (Table.t[e.neighbor].distance > Table.t[v].distance + e.cost) {
						Table.t[e.neighbor].distance = Table.t[v].distance + e.cost;
						n.decreaseKey(e.neighbor, Table.t[e.neighbor].distance);
						Table.t[e.neighbor].path = v;
					}
				}
		}
	}

}
