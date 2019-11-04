/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Tree;

import java.util.Comparator;

/**
 *
 * @author 216CS2017
 */
public class TestClass {

    public static void main(String[] args) {
        Comparator comparator = new Comparator() {
            @Override
            public int compare(Object o1, Object o2) {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }
        };

        Tree summ = new Tree(comparator);

        TreeNode t1 = new TreeNode(25);
        TreeNode t2 = new TreeNode(22);
        TreeNode t3 = new TreeNode(19);
        TreeNode t4 = new TreeNode(20);
        TreeNode t5 = new TreeNode(26);
        TreeNode t6 = new TreeNode(28);
        TreeNode t7 = new TreeNode(27);
        Tree tree1 = new Tree(comparator);
        tree1.root = t2;
        t2.left = t4;
        t4.left = t3;
        t2.right = t5;
        t5.left = t1;
        t5.right = t7;
        t7.right = t6;

        tree1.printSorted(tree1.root);
        tree1.deleteOddNumbers(tree1.root);
        tree1.printSorted(tree1.root);

    }

}
