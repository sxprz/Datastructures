package de.suprize.datastructures;

/**
 *  This is a custom implementation of the red-black tree data structure.
 *  A red-black tree follows these rules, when inserting a node:
 *
 *      1. A node is either red or black
 *      2. The root and leaves (NIL) are black
 *      3. If a node is red, then its children are black
 *      4. All paths from a node to its NIL descendants contain the same number of black nodes
 *
 *  The following youtube link is the source for the above mentioned rules: https://youtu.be/5IBxA-bZZH8
 *
 * @author  suprize
 * @since   2022-03-03
 */
public class RedBlackTree {
    private RBNode root;

    public RedBlackTree() {}
    public RedBlackTree(long val) {
        this.root = new RBNode(val);
    }

    /*private long countBlackNodes(RBNode node) {
        if(node == null) return 0;
        return countBlackNodes(node.left) + countBlackNodes(node.right) + (node.color == Color.BLACK ? 1 : 0);
    }

    private boolean isValidTree() { // enforcing rule 2 and others in the isValid() call
        return (root.color != Color.RED) && isValid(root);
    }

    private boolean isValid(RBNode node) {
        return node == null || (isValid(node.left) && isValid(node.right) && (countBlackNodes(node.left) == countBlackNodes(node.right)));
    }*/

    private enum Color {
        RED,
        BLACK
    }

    private class RBNode {
        private final Long val;
        Color color;
        RBNode left, right, parent;

        public RBNode(Long val) {
            if(val == null) this.color = Color.BLACK;
            this.val = val;
        }

        public long getVal() {
            return val;
        }
    }

    /**
     *  successor() finds the nearest value strictly bigger than the given parameter.
     *
     * @param  val the value for which the procedure searches for the next biggest value in the red-black-tree
     * @return     returns a value, which is strictly bigger than val and contained in the red-black-tree
     */
    public Long successor(Long val) {
        return -1L;
    }

    /**
     *  predecessor() finds the nearest value strictly smaller than the given parameter.
     *
     * @param  val the value for which the procedure searches for the next smallest value in the red-black-tree
     * @return     returns a value, which is strictly smaller than val and contained in the red-black-tree
     */
    public Long predecessor(Long val) {
        return -1L;
    }

    /**
     *  search() looks for a value in the red-black tree, which can be achieved in log(n) time,
     *  where n is the number of nodes in the tree.
     *
     * @param  val the value that is being searched for
     * @return     returns a boolean value, whether the specified long value is found
     */
    public boolean search(long val) {
        // TODO
        return false;
    }

    /**
     *  insert() will insert a value under the condition that the value does not
     *  already exist in the red-black tree.
     *
     * @param  val the value that needs to be inserted in the red-black tree
     */
    public void insert(long val) {
        RBNode node = new RBNode(val);
        node.color = Color.RED;
        //node.left = new RBNode(null);
        //node.right = new RBNode(null);

        RBNode gparent, parent, temp = root;
        while(temp != null) {
            parent = temp;
            gparent = parent.parent;

            if(temp.val < val)  // insert on right side of the current node in the red-black tree
                temp = temp.right;
                if(temp == null) {
                    parent.right = node;
                    node.parent = parent;
                }

                // TODO: balance with rotations

            else if(temp.val > val)   // insert on left side of the current node in the red-black tree
                temp = temp.left;
                if(temp == null) {
                    parent.left = node;
                    node.parent = parent;
                }

                // TODO: balance with rotations

            else {
                // value already exists, do not insert duplicates
                return;
            }
        }
    }

    /**
     *  insertAll() does the same as insert(), but with the difference that it will insert
     *  multiple values "at once". This method only exists for the user's comfort and will probably not lead
     *  to performance improvements over single calls of insert().
     *
     * @param  vals a list of values that need to be inserted in the red-black tree
     */
    public void insertAll(long[] vals) {
        // TODO
    }

    /**
     *  delete() attempts to remove a node with a specific value in the red-black tree.
     *
     * @param  val the value that is being searched for
     * @return     returns whether the node with the value 'val' in the red-black tree was removed successfully.
     */
    public boolean delete(long val) {
        // TODO
        return false;
    }
}
