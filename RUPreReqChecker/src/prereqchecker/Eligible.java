package prereqchecker;

import java.util.*;

public class Eligible {
    public static void main(String[] args) {

        if ( args.length < 3 ) {
            StdOut.println("Execute: java -cp bin prereqchecker.Eligible <adjacency list INput file> <eligible INput file> <eligible OUTput file>");
            return;
        }

	    // WRITE YOUR CODE HERE
        AdjacentList adj = new AdjacentList(args[0]);
        StdIn.setFile(args[1]);
        int c = StdIn.readInt();
        ArrayList<String> takenList = new ArrayList<String>();
        ArrayList<String> eligibleList = new ArrayList<String>();
        for(int i=0; i<c; i++)
        {
            takenList.add(StdIn.readString());
        }

        //for each course, look at all its pre reqs and add them to the list if its not already in there
        Node[] adjList = adj.getAdjList();
        ArrayList<String> list = adj.getList();
        for(int i=0; i<takenList.size(); i++)
        {
            int index = list.indexOf(takenList.get(i)); //index of course
            Node ptr = adjList[index];
            while(ptr!=null)
            {
                if(!takenList.contains(ptr.getCourse()))
                {
                    takenList.add(ptr.getCourse());
                }
                ptr = ptr.getNext();
            }
        }

        //go through enture adjlist and for each position, if all the nodes in the linked list are in the taken list, add that list(posision) to the eligible list
        for(int i=0; i<adjList.length; i++)
        {
            Node ptr = adjList[i];
            boolean eligible = true;
            while(ptr!=null)
            {
                if(!takenList.contains(ptr.getCourse()))
                {
                    eligible = false;
                    break;
                }
                ptr=ptr.getNext();
            }
            if(eligible)
            {
                eligibleList.add(list.get(i));
            }
        }

        StdOut.setFile(args[2]);
        for(String s : eligibleList)
        {
            if(!takenList.contains(s))
                StdOut.println(s);
        }

    }
}
