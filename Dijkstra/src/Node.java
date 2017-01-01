public class Node<T> {

	 public T name;
	
	 public Node(T name) { 
		 this.name = name; 
	 }
	 
	 public void setNodeName(T name){
		 this.name = name;
	 }
	 
	 public String toString() { 
		 return (String) name; 
	 }

	 public T getNodeName(){
		 return name;
	 }
}
