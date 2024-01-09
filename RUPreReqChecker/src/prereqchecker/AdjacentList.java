package prereqchecker;

import java.util.ArrayList;

public class AdjacentList {

    private Node[] adjList;
    private ArrayList<String> list;
    
    private boolean[] visited;
    private boolean[] stack;

    
    public AdjacentList(String filename) {
        StdIn.setFile(filename);
        
        int a = StdIn.readInt();
        ArrayList<String> list = new ArrayList<String>();
        Node[] adjList = new Node[a];
        
        for (int i=0; i<a; i++)
        {
            list.add(StdIn.readString());
        }
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
        this.list = list;
        this.adjList = adjList;
    }


    public Node[] getAdjList() { return adjList;}
    public ArrayList<String> getList() {return list;}

    public void addEdge(String course1, String course2)
    {
        int index = list.indexOf(course1);
        int index2 = list.indexOf(course2);
        Node node = new Node(course2, adjList[index], index2);
        adjList[index] = node;
    }


    public boolean isCyclic()
    {
        visited = new boolean[list.size()];
        stack = new boolean[list.size()];
        for(int i=0; i<list.size(); i++)
        {
            visited[i] = false;
            stack[i] = false;
        }

        for(int i=0; i<list.size(); i++)
        {

            if(isCyclicUtil(i))
            {
                return true;
            }
        }
        return false;
    }

    public boolean isCyclicUtil(int index)
    {
        if(stack[index])
            return true;
        if(visited[index])
            return false;

        visited[index] = true;
        stack[index] = true;
        Node ptr = adjList[index];
        while(ptr!=null)
        {
            if(isCyclicUtil(list.indexOf(ptr.getCourse())))
            {
                return true;
            }
            ptr = ptr.getNext();
        }
        stack[index] = false;
        return false;
    }
}

