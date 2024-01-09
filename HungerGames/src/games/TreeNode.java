package games;

public class TreeNode {
    private District district;
    private TreeNode left;
    private TreeNode right;

    
    public TreeNode(District district, TreeNode left, TreeNode right) {
        this.district = district;
        this.left = left;
        this.right = right;
    }

    
    public TreeNode() {
        this(null, null, null);
    }

   
    public District getDistrict() {
        return district;

    }

    
    public void setDistrict(District district) {
        this.district = district;
    }

   
    public TreeNode getLeft() {
        return left;
    }

    public void setLeft(TreeNode node) {
        left = node;
    }

    public TreeNode getRight() {
        return right;
    }

    public void setRight(TreeNode node) {
        right = node;
    }
}
