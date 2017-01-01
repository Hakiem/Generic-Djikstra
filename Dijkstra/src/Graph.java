import java.util.*;

public class Graph<T> {

	private Hashtable<T, ArrayList<Edge<T>>> nodesAndTheirNeighbours;
	
    public Graph(){
    	nodesAndTheirNeighbours = new Hashtable<T, ArrayList<Edge<T>>>();
    }
    
    public void addNodes(T node){
    	if(!containsNode(node))
    		nodesAndTheirNeighbours.put(node, new ArrayList<Edge<T>>());
    }
    
    public void addNeighbourNodes(T key,  Edge<T> newEdge){
    	ArrayList<Edge<T>> edges = nodesAndTheirNeighbours.get(key);
    	if(edges != null){

    		for (Edge<T> edgeNeighbour : edges) {
				if(edgeNeighbour.getTargetNode().getNodeName().equals(newEdge.getTargetNode().getNodeName())){
					if(newEdge.getTravelTime() < edgeNeighbour.getTravelTime())
						edgeNeighbour.setEdgeTravelTime(newEdge.getTravelTime());
					else return;
				}
			}
    		
	    	edges.add(newEdge);
	    	nodesAndTheirNeighbours.put(key, edges);
    	}
    }
    
    public ArrayList<Edge<T>> getNeighbours(T key){
    	return (nodesAndTheirNeighbours.containsKey(key) || nodesAndTheirNeighbours.get(key) != null) ? 
    			nodesAndTheirNeighbours.get(key) :  null;
    }
    
    public boolean containsNode(T nodeName){
    	return nodesAndTheirNeighbours.containsKey(nodeName);
    }
    
    public Set<T> nodeKeys(){
        return nodesAndTheirNeighbours.keySet();
    }
}
