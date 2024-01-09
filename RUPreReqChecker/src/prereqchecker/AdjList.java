package prereqchecker;
import java.util.*;

public class AdjList {
    public static void main(String[] args) {

        if ( args.length < 2 ) {
            StdOut.println("Execute: java -cp bin prereqchecker.AdjList <adjacency list INput file> <adjacency list OUTput file>");
            return;
        }

        StdIn.setFile(args[0]);
        
        int a = StdIn.readInt();
        ArrayList<String> list = new ArrayList<String>();
        Node[] adjList = new Node[a];
        
        for (int i=0; i<a; i++)
        {
            list.add(StdIn.readString());
        }
        StdOut.setFile(args[1]);
        int b = StdIn.readInt();
        for (int i=0; i<b; i++)
        {
            String course = StdIn.readString();
            String prereq = StdIn.readString();
            
            int position = list.indexOf(course);

            Node node = new Node(prereq, null, position);
            if(adjList[position] == null)
            {
                adjList[position] = node;
            }
            else
            {
                node.setNext(adjList[position]);
                adjList[position] = node;
            }
        }
        
        for(int i=0; i<a; i++)
        {
            StdOut.print(list.get(i) + " ");
            Node ptr = adjList[i];
            while(ptr!=null)
            {
                StdOut.print(ptr.getCourse() + " ");
                ptr = ptr.getNext();
            }
            if(i!=a-1)
            {
                StdOut.println();
            }
        }
    }
}