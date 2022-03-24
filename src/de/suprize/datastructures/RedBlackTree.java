package de.suprize.datastructures;

import java.util.Objects;

/**
 *  A red-black tree is a self-balancing binary-searchtree (BST) in respect to black nodes in the tree.
 *
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
 *  The implementation for most functions/methods are attempted in an iterative instead of a recursive matter, because of stack-size limitations
 *  and the possibilities for large number of nodes in the red-black-tree.
 *
 *  TODO: Write tests for each method
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
        R, B    // R stands for Red, B stands for Black
    }

    private class RBNode {
        private final Long val;                         // immutable value, remove node from tree to remove this value
        Color color;
        RBNode left, right, parent;                     // BST property: Every node has two children, a left and a right child; there is also a parent pointer for easier programming

        public RBNode(Long val) {
            if(val == null)                             // for NIL nodes: black color
                this.color = Color.B;
            else {
                left = new RBNode(null);            // if a normal node is created it will have two NIL children
                right = new RBNode(null);
            }

            this.val = val;                             // assign value to node
        }

        public void print_inorder() {
            if(left != null) left.print_inorder();
            if(val != null) System.out.print(val + " ");
            if(right != null) right.print_inorder();
        }

        public void print_preorder() {
            if(val != null) System.out.print(val + " ");
            if(left != null) left.print_preorder();
            if(right != null) right.print_preorder();
        }

        public void print_postorder() {
            if(left != null) left.print_postorder();
            if(right != null) right.print_postorder();
            if(val != null) System.out.print(val + " ");
        }
    }

    /**
     *  rotateLeft() performs a left rotation around the given node, which alters the tree structure.
     *
     *  @param  node a node contained in a red-black-tree
     */
    private void rotateLeft(RBNode node) {

    }

    /**
     *  rotateRight() performs a right rotation around the given node, which alters the tree structure.
     *
     * @param  node a node contained in a red-black-tree
     */
    private void rotateRight(RBNode node) {

    }

    /**
     *  successor() finds the nearest value strictly bigger than the given parameter.
     *
     * @param  val the value for which the procedure searches for the next biggest value in the red-black-tree
     * @return     returns a value, which is strictly bigger than val and contained in the red-black-tree
     */
    public Long successor(Long val) {
        RBNode node = searchNode(val);                                  // search for given value
        if(Objects.equals(node.val, val)) {                             // check if value exists in tree and if node is not null
            RBNode right = node.right;
            if(right != null) return right.val;                         // if the next bigger value is the right child of node -> return right child's value

            Long nodeVal;
            while(node.parent != null) {                                // go up to next parent and check if the parent value is larger than val
                node = node.parent;
                nodeVal = node.val;
                if(Long.compare(nodeVal, val) == 1) return nodeVal;     // return successor's value
            }
        }
        return null;
    }

    /**
     *  predecessor() finds the nearest value strictly smaller than the given parameter.
     *
     * @param  val the value for which the procedure searches for the next smallest value in the red-black-tree
     * @return     returns a value, which is strictly smaller than val and contained in the red-black-tree
     */
    public Long predecessor(Long val) {
        RBNode node = searchNode(val);                                  // search for given value
        if(Objects.equals(node.val, val)) {                             // check if value exists in tree and if node is not null
            RBNode left = node.left;
            if(left != null) return left.val;                           // if the next smaller value is the left child of node -> return left child's value

            Long nodeVal;
            while(node.parent != null) {                                // go up to next parent and check if the parent value is smaller than val
                node = node.parent;
                nodeVal = node.val;
                if(Long.compare(nodeVal, val) == -1) return nodeVal;    // return predecessor's value
            }
        }
        return null;
    }

    /**
     *  successorNode() finds the nearest value strictly bigger than the given parameter.
     *
     * @param  val the value for which the procedure searches for the next biggest value in the red-black-tree
     * @return     returns the node in the red-black-tree that contains a value which is strictly bigger than val
     */
    private RBNode successorNode(Long val) {
        RBNode node = searchNode(val);                                  // search for given value
        if(Objects.equals(node.val, val)) {                             // check if value exists in tree and if node is not null
            RBNode right = node.right;
            if(right != null) return right;                             // if the next bigger value is the right child of node -> return right child

            while(node.parent != null) {                                // go up to next parent and check if the parent value is larger than val
                node = node.parent;
                if(Long.compare(node.val, val) == 1) return node;       // return successor
            }
        }
        return null;
    }

    /**
     *  predecessorNode() finds the nearest value strictly smaller than the given parameter.
     *
     * @param  val the value for which the procedure searches for the next smallest value in the red-black-tree
     * @return     returns the node in the red-black-tree that contains a value which is strictly smaller than val
     */
    private RBNode predecessorNode(Long val) {
        RBNode node = searchNode(val);                                  // search for given value
        if(Objects.equals(node.val, val)) {                             // check if value exists in tree and if node is not null
            RBNode left = node.left;
            if(left != null) return left;                               // if the next smaller value is the left child of node -> return left child

            while(node.parent != null) {                                // go up to next parent and check if the parent value is smaller than val
                node = node.parent;
                if(Long.compare(node.val, val) == -1) return node;      // return predecessor
            }
        }
        return null;
    }

    /**
     *  search() looks for a value in the red-black tree, which can be achieved in log(n) time,
     *  where n is the number of nodes in the tree.
     *
     * @param  val the value that is being searched for
     * @return     returns true if val was found, otherwise false
     */
    public boolean search(long val) {
        RBNode node = root;                                             // start searching from root
        while(node != null && node.val != null) {                       // search as long as node and its value are not null
            if(node.val == val) return true;                            // if node.val and val are equal, return true
            if(node.val < val) node = node.right;                       // if the node value is smaller than val, then search on the right subtree, i.e. node will be the right child in the next iteration
            else               node = node.left;                        // otherwise node will be the left child
        }
        return false;
    }

    /**
     *  searchNode() looks for the node which contains a certain value in the red-black tree, which can be achieved in log(n) time,
     *  where n is the number of nodes in the tree.
     *
     *  In case the value is not found, the last node where the value is supposed to be, will be returned, i.e a null pointer.
     *
     * @param  val the value that is being searched for
     * @return     returns a RBNode object which is located at val's position numerically
     */
    private RBNode searchNode(long val) {
        RBNode node = root;
        while(node != null && node.val != null) {
            if(node.val == val) return node;
            if(node.val < val) node = node.right;
            else               node = node.left;
        }
        return node;
    }

    /**
     *  insertFix() is the sequel to insert(). It fixes remaining invariant issues (see rules above), if there are any.
     *
     * @param node the inserted node that could cause invariant breaches
     */
    private void insertFix(RBNode node) {
        // TODO: balance with rotations, not implemented yet
        throw new RuntimeException("not implemented yet");
    }

    /**
     *  insert() will insert a value under the condition that the value does not
     *  already exist in the red-black tree.
     *
     * @param  val the value that needs to be inserted in the red-black tree
     */
    public void insert(long val) {
        RBNode node = new RBNode(val);
        node.color = Color.R;                   // when inserting, color the inserted node red first, color will be changed if any invariants are violated

        RBNode gparent, parent, temp = root;
        while(temp != null) {
            parent = temp;
            gparent = parent.parent;

            if(temp.val < val) {  // insert on right side of the current node in the red-black tree
                temp = temp.right;
                if (temp == null) {
                    parent.right = node;
                    node.parent = parent;
                }
            } else if(temp.val > val) {   // insert on left side of the current node in the red-black tree
                temp = temp.left;
                if (temp == null) {
                    parent.left = node;
                    node.parent = parent;
                }
            } else
                // value already exists, do not insert duplicates
                return;

            insertFix(node);
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
        /*
            Case 1: Sibling of val is red
            Case 2: Sibling is black with two black children
            Case 3: Sibling black with one right black child
            Case 4: Sibling is black with red right child
        */

        // TODO
        return false;
    }

    /**
     *  print_inorder() prints the red-black tree by traversing in in-order.
     */
    public void print_inorder() {
        root.print_inorder();
    }

    /**
     *  print_preorder() prints the red-black tree by traversing in pre-order.
     */
    public void print_preorder() {
        root.print_preorder();
    }

    /**
     *  print_postorder() prints the red-black tree by traversing in post-order.
     */
    public void print_postorder() {
        root.print_postorder();
    }
}
