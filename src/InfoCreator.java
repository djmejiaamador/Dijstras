import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class InfoCreator {
	String inputFile;
	String all;
	BufferedReader input;
	String nextLine;
	int intvalue;
	
	Graph theGraph;

	InfoCreator(String inputFile){
		this.inputFile=inputFile;
		all = "";
		
	}

	public void readFileNames(){
		try {
			input = new BufferedReader(new FileReader(inputFile));
			nextLine = input.readLine();
			while (nextLine.compareTo(".") != 0) {
				all +=" "+nextLine;
				nextLine = input.readLine();
			}
			all=all.trim();
			System.out.println(all);

		} catch (IOException e) {
			System.out.println("File Error return ");
		}
	}

	public Graph CreateGraph(HashTable t ){
		Graph meh = new Graph(all.split(" ").length);
		System.out.println(meh.numVertex);
		System.out.println();
		try {
			nextLine = input.readLine();
			while (nextLine!=null) {
				//the place vertext im looking at
				int loc = t.find(nextLine);
				//its neighbor vertex
				nextLine= input.readLine();
				int neighborIndex = t.find(nextLine);
				// the cost of neighbor;
				nextLine = input.readLine();
				int intvalue = Integer.valueOf(nextLine).intValue();
				Edge tmpEdge = meh.edges[loc];
				tmpEdge = new Edge(neighborIndex,tmpEdge,intvalue);
				meh.edges[loc] = tmpEdge;
				
				
				nextLine = input.readLine();
			}
		} catch (IOException e) {
			System.out.println("File Error return ");
		}
		return meh;

	}

	public String[] GenerateNameList(){
		return all.split(" ");
	}
	
	public Graph GenerateGraph(){
		return null;
	}

}
