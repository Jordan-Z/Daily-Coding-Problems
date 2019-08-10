/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dailycodingproblems;

import java.io.FileWriter;
import java.io.IOException;

/**
 *
 * @author jzech
 */
public class Node {

    //class variables
    int key;
    Node root;
    Node right;
    Node left;

    //constructor
    Node(int key) {
        this.key = key;
    }

    //added a node to the tree
    public void addNode(int key) {
        //creating new node with key value
        Node newNode = new Node(key);
        //if root is null tree is empty
        if (root == null) {
            root = newNode;
        } else {
            //focusNode is the node we are on as we go through the tree
            Node focusNode = root;
            Node parent;

            while (true) {
                parent = focusNode;
                //if key is less than node goes on left side
                if (key < focusNode.key) {
                    //our focus node is now on left side
                    focusNode = focusNode.left;
                    if (focusNode == null) {
                        //if left child has no children place on left of node
                        parent.left = newNode;
                        return;
                        //done
                    }
                } else {
                    //else node goes on the right side
                    focusNode = focusNode.right;
                    //if node is null than place node right of parent
                    if (focusNode == null) {
                        parent.right = newNode;
                        return;
                    }
                }
            }

        }
    }

    public void preOrderTraverse(Node focusNode, FileWriter treeFile) {
        try {
            
             if (focusNode != null) {
            //if node is not equal to null print value
            System.out.print(focusNode.key + " ");
            //write node key to file 
            treeFile.write(focusNode.key + " ");
            //traverse left side of tree
            preOrderTraverse(focusNode.left, treeFile);
            //traverse right side of tree
            preOrderTraverse(focusNode.right, treeFile);
            
        } else {
            //-1 indicates null value
            System.out.print(" -1 ");
            treeFile.write(-1);
            
        }
        } catch (IOException e) {
            System.out.println(e);
        }
        
        /*
        if (focusNode != null) {
            //if node is not equal to null print value
            System.out.print(focusNode.key + " ");
            //traverse left side of tree
            preOrderTraverse(focusNode.left);
            //traverse right side of tree
            preOrderTraverse(focusNode.right);
        } else {
            //-1 indicates null value
            System.out.print(" -1 ");
        }*/
    }

}
