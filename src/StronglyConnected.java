import java.io.IOException;
import java.util.Stack;

public class StronglyConnected extends DfsGraph{
	private static boolean start = false;	// accounts for empty stack
	private static Stack<Integer> strongStack = new Stack<>();

	public StronglyConnected(String name) throws IOException {
		super(name);
	}

	public StronglyConnected(String name, int order, int size, boolean directed, boolean weighted){
		super (name, order, size, directed, weighted);
	}
	
	// override preVisit method in DfsGraph (Step 6)
	public void preVisit(int v){
		if (!start){		// only runs at the end to prevent empty stack exception
			System.out.print(v + " ");
			strongStack.pop();
		}
	}
	
	// override postVisit method in DfsGraph (Step 2)
	public void postVisit(int v){
		if (start)			// only runs at the beginning to add to stack
			strongStack.push(v);
	}
	
	public static void main(String[] args) throws IOException {
		StronglyConnected g = new StronglyConnected(args[0]);
		System.out.println(g);
		start = true;
		
		// 1. Perform peristent DFS on g, visiting vertices in nat order		
		for(int v = 0; v < g.getOrder(); v++){
			if (!g.getVertex(v).isMarked())
				g.dfs(v);
		}
		
		// 3. Construct graph h, which is a copy of g with each edge reversed
		StronglyConnected h = new StronglyConnected("reverse", g.getOrder(), g.getSize(), g.isDirected(), g.isWeighted());
		for (int a = 0; a < h.getOrder(); a++) {
			for (Integer b : g.getVertex(a).getNeighbors())
				h.addEdge(b, a);
			strongStack = (Stack<Integer>) g.strongStack.clone();
		}
		
		// 4. Perform persistent depth-first search on h, visiting the verticies in LIFO order using the stack
		int comp = 1;
		start = false;
		while(!strongStack.isEmpty()){
			// 5. New component
			int v = strongStack.peek();
			System.out.print("\nComponent " + comp + ": ");
			if(!h.getVertex(v).isMarked())
				h.dfs(v);
			comp++;
		}		

	}
		
}
