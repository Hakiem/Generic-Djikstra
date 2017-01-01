
public class Edge<T> {
	
	private Node<T> target;
	private int travelTime;
	    
	public Edge(Node<T> argTarget, int travelTime){ 
		target = argTarget; 
		this.travelTime = travelTime; 
	}
	 
	public Node<T> getTargetNode(){
		return target;
	}
	 
	public int getTravelTime(){
		return travelTime;
	}
	
	public void setEdgeTargetNode(T targetName){
		target.setNodeName(targetName);
	}
	
	public void setEdgeTravelTime(int travelTime){
		this.travelTime = travelTime;
	}
}
