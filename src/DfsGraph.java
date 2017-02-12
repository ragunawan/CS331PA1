// Copyright (c) Mandayam A. Srinivas
// mas 2004-7-29, 2008-11-25, 2013-05-19
/**
 * Implements depth-first search on graphs.
 * Provides hooks preVisit, postVisit, doMarked and doUnmarked
 * for implementing specific applications of dfs.
 *
 * @author M.A.Srinivas
 * @version 2.1
 * 
 */
public class DfsGraph extends Graph {

	public DfsGraph(String name) throws java.io.IOException {
		super(name);
	}

	public DfsGraph(String name, int order, int size, boolean directed, boolean weighted) {
		super(name, order, size, directed, weighted);
	}
	
/**
 * Recursive depth-first search method
 * @param v start vertex
 *
 */
   public void dfs(int v) {
		preVisit(v);
		markVertex(v);
		for (int w:getNeighbors(v)) {
			if (vertexMarked(w)) doMarked(v,w);
			else {
				doUnmarked(v,w);
				dfs(w);
			}
		}
		postVisit(v);
	}
	
/**
 * Defines actions performed while previsting vertex
 * @param v vertex being previsited
 *
 */
	public void preVisit(int v) {
	}
	
/**
 * Defines actions performed while postvisiting vertex
 * @param v vertex being postvisited
 *
 */
	public void postVisit(int v) {
	}

/**
 * Defines actions performed when processing marked vertex w while visiting v
 * @param v vertex being visited
 *	@param w vertex being processed
 *
 */
	public void doMarked(int v, int w) {
	}

/**
 * Defines actions performed when processing unmarked vertex w while visiting v
 * @param v vertex being visited
 *	@param w vertex being processed
 *
 */
	public void doUnmarked(int v, int w) {
	}
}	
