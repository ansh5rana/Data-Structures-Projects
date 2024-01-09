package prereqchecker;
import java.util.*;

public class ValidPrereq {
    public static void main(String[] args) {

        if ( args.length < 3 ) {
            StdOut.println("Execute: java -cp bin prereqchecker.ValidPrereq <adjacency list INput file> <valid prereq INput file> <valid prereq OUTput file>");
            return;
        }
        
        AdjacentList adj = new AdjacentList(args[0]);
        StdIn.setFile(args[1]);
        String course1 = StdIn.readString();
        String course2 = StdIn.readString();
        adj.addEdge(course1, course2);

        StdOut.setFile(args[2]);
        boolean cyclic = adj.isCyclic();
        if(cyclic)
            StdOut.print("NO");
        else
            StdOut.print("YES");
    }
}

