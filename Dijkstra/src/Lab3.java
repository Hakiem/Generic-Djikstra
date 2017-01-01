import java.io.IOException;
import java.util.*;
import Lab3Help.*;


public class main {
	
	/*
	
	*/
	public static void main(String[] args) throws MalformedData, IOException{
		
		Lab3File l3f = new Lab3File();
        ArrayList<BStop>      bstops = new ArrayList<BStop>(l3f.readStops("stops-gbg.txt"));
        ArrayList<BLineTable> btable = new ArrayList<BLineTable>(l3f.readLines("lines-gbg.txt"));
        
        DijkstraStringPath p = new DijkstraStringPath(bstops, btable);
        
        p.computePath("Angered", "Botaniska");
        int pathLength = p.getPathLength();
        System.out.println(pathLength);
        
        Iterator<String> sitr = p.getPath();
        
        while(sitr.hasNext()) System.out.println(sitr.next());
	}
}
