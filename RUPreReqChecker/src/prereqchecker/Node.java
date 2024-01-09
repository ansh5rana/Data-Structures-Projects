package prereqchecker;


public class Node {
    private String course;  // the song contained in this node
    private Node    next;  // reference to the next song in the playlist
    private int position;

    
    public Node(String course, Node next, int position) {
        this.course = course;
        this.next = next;
        this.position = position;
    }

    public Node() {
        this(null, null, -1);
    }

    public String getCourse() { return course; }
    public void setCourse(String c) { course = c; }

    public Node getNext() { return next; }
    public void setNext(Node n) { next = n; }

    public int getPosition() { return position; }
    public void setPosition(int p) { position = p; }
}

