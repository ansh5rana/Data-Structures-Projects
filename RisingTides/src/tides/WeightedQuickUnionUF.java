package tides;

public class WeightedQuickUnionUF {

    
    private GridLocation[][] parent;

    private int[][] size;


    public WeightedQuickUnionUF(int rows, int cols) {
        parent = new GridLocation[rows][cols];
        size = new int[rows][cols];

        for (int rr = 0; rr < rows; rr++) {
            for (int cc = 0; cc < cols; cc++) {
                parent[rr][cc] = new GridLocation(rr, cc);
                size[rr][cc] = 1;
            }
        }
    }
    
    public GridLocation find(GridLocation cell) {
        GridLocation currParent = parent[cell.row][cell.col];

        while (cell.hashCode() != currParent.hashCode() || !cell.equals(currParent)) {
            cell = new GridLocation(currParent.row, currParent.col);
            currParent = parent[cell.row][cell.col];
        }

        return currParent;
    }

    public void union(GridLocation cell1, GridLocation cell2) {
        GridLocation root1 = find(cell1);
        GridLocation root2 = find(cell2);
        if (root1 == root2) return;

        if (getSize(root1) >= getSize(root2)) {
            GridLocation temp = root1;
            root1 = root2;
            root2 = temp;
        }

        parent[root1.row][root1.col] = root2;
        size[root2.row][root2.col] += getSize(root1);
    }

    public int getSize(GridLocation cell) {
        return size[cell.row][cell.col];
    }
}