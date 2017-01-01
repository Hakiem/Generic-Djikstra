import java.util.*;
import Lab3Help.*;

public class DijkstraStringPath {

	Dijkstra<String> dijkstrasAlgorithm;
	
	public DijkstraStringPath(List<BStop> bStops, List<BLineTable> bTables){
		
		dijkstrasAlgorithm  = new Dijkstra<String>();
		Graph<String> graph = new Graph<String>();
		
		// Create a Node from all the available stops in the helper file
        for(BStop bstop : bStops) 
        	graph.addNodes(bstop.getName().trim());
		
        // For every Node created, create an edge from the existing info
		for(BLineTable table : bTables){
			BLineStop[] stops = table.getStops();
			for(int i = 0; i < stops.length - 1; i++){
				
				graph.addNeighbourNodes(stops[i].getName().trim(), 
						new Edge<String>(
								new Node<String>(stops[i + 1].getName().trim()), 
								stops[i + 1].getTime()));
			}
		}
		
		dijkstrasAlgorithm.graph = graph;
	}
	
	public void computePath(String from, String to){
		dijkstrasAlgorithm.computePath(from, to);
	}
	
	public Iterator<String> getPath() {
		return dijkstrasAlgorithm.getPath();
	}
	
	public int getPathLength() {
		return dijkstrasAlgorithm.getPathLength();
	}
}
