/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Tree;

import java.lang.reflect.Array;
import java.util.Comparator;

/**
 *
 * @author 216CS2017
 */
public class Tree<T> {

    TreeNode<T> root;
    Comparator<T> comparator;

    public Tree(Comparator<T> comparator) {
        root = null;
        this.comparator = comparator;
    }

    Tree() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public int sumOfTree(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int rootData = (Integer) root.data;
        return rootData + sumOfTree(root.left) + sumOfTree(root.right);// CSE312 de Binary Search TREE yi recursive YAZMA SORUSU KESİN.
    }

    public void printSorted(TreeNode root) {
        if (root != null) {
            printSorted(root.left);
            System.out.println(root.data);
            printSorted(root.right);
        }

    }

    public int numOfChild(TreeNode root) {
        if (root == null) {
            return 0;
        }
        if (root.right != null && root.left != null) {
            return 1 + numOfChild(root.right) + numOfChild(root.left);
        }
        return numOfChild(root.left) + numOfChild(root.right);
    }

    int heightOfTree(TreeNode root) {
        if (root == null) {
            return 0;
        }
        if (heightOfTree(root.left) >= heightOfTree(root.right)) {
            return 1 + heightOfTree(root.left);
        }
        return heightOfTree(root.right);
    }

    int heightOfTree2(TreeNode root) {
        int leftHeight = 0;
        int rightHeight = 0;
        if (root != null) {
            if (root.left != null) {
                leftHeight = heightOfTree2(root.left);
            }

            if (root.right != null) {
                rightHeight = heightOfTree2(root.right);
            }

            return leftHeight > rightHeight ? leftHeight + 1 : rightHeight + 1; //Turner Operator.

        }

        return 0;
    }

    public TreeNode<T> getRoot() {
        return root;
    }

    void swapChilds(TreeNode root) {
        if (root != null) {
            swapChilds(root.left);
            swapChilds(root.right);
            TreeNode temp = root.right;
            root.right = root.left;
            root.left = temp;
        }
    }

    boolean isBinarySearchTree(TreeNode root) {

        if (root == null) {
            return true;
        }
        if (root.left != null && (Integer) root.left.data > (Integer) root.data) {
            return false;
        }
        if (root.right != null && (Integer) root.right.data < (Integer) root.data) {
            return false;
        }
        if (!isBinarySearchTree(root.left) || !isBinarySearchTree(root.right)) {
            return false;
        }
        return true;
        //////////////////////////////////////////////////
//        if (root != null) {
//            if ((Integer) root.right.data >= (Integer) root.data && (Integer) root.left.data <= (Integer) root.data) {
//               return  isBinarySearchTree(root.right)&& isBinarySearchTree(root.left);
//               
//            } else {
//                return false;
//            }
//
//        }
//        return true;
//        

    }

    void deleteKey(int key) {
        root = deleteRec(root, key);

    }

    TreeNode deleteRec(TreeNode<T> root, int key) {
        if (root == null) {
            return root;
        }
        if (key < (Integer) root.data) {
            return root.left = deleteRec(root.left, key);
        } else if (key > (Integer) root.data) {
            return root.right = deleteRec(root.right, key);
        } else if (root.left == null) {
            return root.right;
        } else if (root.right == null) {
            return root.left;
        }
        root.data = root.recursiveMinSearch().data;
        root.right = deleteRec(root.right, (Integer) root.data);
        return root;

    }

    void deleteOddNumbers(TreeNode root) {
        if (root == null) {
            return;
        }
        if ((Integer) root.data % 2 != 0) {
            deleteKey((Integer) root.data);
        }
        deleteOddNumbers(root.right);
        deleteOddNumbers(root.left);

    }

}
