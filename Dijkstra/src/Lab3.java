import java.io.IOException;
import java.util.*;
import Lab3Help.*;


public class Lab3 {

	public static void main(String[] args) throws MalformedData, IOException{
		
		Lab3File l3f = new Lab3File();
        ArrayList<BStop>      bstops = new ArrayList<BStop>(l3f.readStops(args[0]));
        ArrayList<BLineTable> btable = new ArrayList<BLineTable>(l3f.readLines(args[1]));
        
        DijkstraStringPath p = new DijkstraStringPath(bstops, btable);
        
        p.computePath(args[2], args[3]);
        int pathLength = p.getPathLength();
        System.out.println(pathLength);
        
        Iterator<String> sitr = p.getPath();
        
        while(sitr.hasNext()) System.out.println(sitr.next());
	}
}