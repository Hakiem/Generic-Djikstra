import java.util.*;
import Lab3Help.*;

public class Dijkstra<T> implements Path<T> {
	
	public  Graph<T> graph;
    private HashMap<T, T> previousNodes;
    private HashMap<T, Integer> summationTime; 
    private PriorityQueue<Node<T>> availableNodes;
    private HashSet<Node<T>> visitedNodes; 
    
    private T startNode, destinationNode, destinationNodePathLength;

	@Override
	public void computePath(T from, T to) {
		startNode = from;
		destinationNode = to;
		destinationNodePathLength = destinationNode;
		
		Set<T> nodeKeys = graph.nodeKeys();
		
		if(!nodeKeys.contains(startNode))
			throw new IllegalArgumentException("The graph must contain the initial node.");
		
        previousNodes 	= new HashMap<T, T>();
        summationTime 	= new HashMap<T, Integer>();
        
        availableNodes  = new PriorityQueue<Node<T>>(nodeKeys.size(), new Comparator<Node<T>>(){
            
            public int compare(Node<T> one, Node<T> two){
                int timeOne = Dijkstra.this.summationTime.get(one.getNodeName()); 
                int timeTwo = Dijkstra.this.summationTime.get(two.getNodeName());
                return timeOne - timeTwo;
            }
        });
        
        visitedNodes = new HashSet<Node<T>>();
		
        //for each Node in the graph
        //assume it has a travel time of infinity denoted by Integer.MAX_VALUE
        for(T key: nodeKeys){
            previousNodes.put(key, null);
            summationTime.put(key, Integer.MAX_VALUE);
        }
        
        //the travel time from the initial node to itself is 0
        summationTime.put(startNode, 0);
        
        // and seed initialNode's neighbors
        ArrayList<Edge<T>> initialNodeNeighbors = graph.getNeighbours(startNode);
        
        for (Edge<T> edge : initialNodeNeighbors) {
			Node<T> immediateNeighbor = edge.getTargetNode();
			previousNodes.put(immediateNeighbor.getNodeName(), startNode);
			summationTime.put(immediateNeighbor.getNodeName(), edge.getTravelTime());
            availableNodes.add((Node<T>) immediateNeighbor);
		}
        
        visitedNodes.add(new Node<T>(startNode));
		
        //now apply Dijkstra's algorithm to the Graph
        processGraph();
	}

    /**
     * This method applies Dijkstra's algorithm to the graph using the Node
     * specified by initialNodeLabel as the starting point.
     * 
     * @post The shortest a - b paths as specified by Dijkstra's algorithm and 
     *       their distances are available 
     */
	private void processGraph(){
		
		// as long as there are Edges to process
		while(availableNodes.size() > 0){
			
			// pick the cheapest node
			Node<T> next = availableNodes.poll();
			int travelTimeToNext = summationTime.get(next.getNodeName());
			
			// and for-each of the available neighbors of the chosen Node
			ArrayList<Edge<T>> nextNeighbors = graph.getNeighbours(next.getNodeName());
			
			for (Edge<T> edg : nextNeighbors) {
				Node<T> other = edg.getTargetNode();
				
				if(visitedNodes.contains(other)) continue;
				
				// check to see if a shorter path exists
				// and update to indicate a new shortest found path
				// in the graph
				int currentTime = summationTime.get(other.getNodeName());
				int newTime		= travelTimeToNext + edg.getTravelTime();
				
				if(newTime < currentTime){
					previousNodes.put(other.getNodeName(), next.getNodeName());
					summationTime.put(other.getNodeName(), newTime);
					availableNodes.remove(other);
					availableNodes.add(other);
				}
			}
			
            // finally, mark the selected vertex as visited 
            // so we don't revisit it
            visitedNodes.add(next);
		}
	}
	
	@Override
	public Iterator<T> getPath() {
		ArrayList<T> path = new ArrayList<T>();
		path.add(destinationNode);
		
		while(!destinationNode.equals(startNode)){
			Node<T> predecessor = new Node<T>(previousNodes.get(destinationNode));
			destinationNode = predecessor.getNodeName();
			path.add(0, predecessor.getNodeName());
		}
		
		return path.iterator();
	}

	@Override
	public int getPathLength() {
		return summationTime.get(destinationNodePathLength);
	}
}
