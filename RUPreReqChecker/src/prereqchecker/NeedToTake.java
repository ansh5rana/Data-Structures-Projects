package prereqchecker;

import java.util.*;


public class NeedToTake {
    public static void main(String[] args) {

        if ( args.length < 3 ) {
            StdOut.println("Execute: java NeedToTake <adjacency list INput file> <need to take INput file> <need to take OUTput file>");
            return;
        }

        AdjacentList adj = new AdjacentList(args[0]);
        StdIn.setFile(args[1]);
        String target = StdIn.readString();
        int d = StdIn.readInt();
        ArrayList<String> takenList = new ArrayList<String>();
        for(int i=0; i<d; i++)
        {
            takenList.add(StdIn.readString());
        }
        StdOut.setFile(args[2]);
        
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


        ArrayList<String> requirements = new ArrayList<String>();
        requirements.add(target);
        for(int i=0; i<requirements.size(); i++)
        {
            int index = list.indexOf(requirements.get(i)); //index of course
            Node ptr = adjList[index];
            while(ptr!=null)
            {
                if(!requirements.contains(ptr.getCourse()))
                {
                    requirements.add(ptr.getCourse());
                }
                ptr = ptr.getNext();
            }
        }
        
        for(int i=0; i<takenList.size(); i++)
        {
            requirements.remove(takenList.get(i));

        }
        requirements.remove(target);
        

        for(int i=0; i<requirements.size(); i++)
        {
            StdOut.println(requirements.get(i));
        }
    }
}
