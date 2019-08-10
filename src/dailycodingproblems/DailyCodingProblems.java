/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dailycodingproblems;

import java.util.*;
import java.io.FileWriter;
import java.io.IOException;

/**
 *
 * @author jzech
 */
public class DailyCodingProblems {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        //stair case problem x is the allowed steps you can take
        int[] x = new int[3];
        x[0] = 1;
        x[1] = 3;
        x[2] = 5;
        System.out.print("Number of Allowed Steps: ");
        System.out.println(stairs(10, x));

        //checking if two numbers in array add up to equal kValue
        int[] listNums = new int[]{10, 15, 3, 7};
        int kValue = 17;
        System.out.print("Two Numbers Equal kValue: ");
        System.out.println(numbersAddTok(kValue, listNums));

        //given array of integers, return new array that each element at index i
        //of the new array is the product of all the numbers in the original
        //array except the one at i
        int[] intArray = new int[]{10, 3, 5, 6, 2};

        int[] prodArray = productArray(intArray);
        System.out.print("Product of Array except i: ");
        for (int iter : prodArray) {
            System.out.print(iter + " ");
        }
        System.out.println("");
        int[] missingNum = new int[]{3, 4, -1, 1};
        System.out.print("Lowest Possible Missing Integer: ");
        System.out.println(lowPosMissingInt(missingNum));

        //creating a bstree
        Node bstree = new Node(0);
        bstree.addNode(20);
        bstree.addNode(22);
        bstree.addNode(8);
        bstree.addNode(4);
        bstree.addNode(12);
        bstree.addNode(28);
        bstree.addNode(26);
        bstree.addNode(40);
        //traverse through tree print to screen order of nodes
        System.out.print("Binary Search Tree: ");
        try {
            try (FileWriter treeFile = new FileWriter("treeFile.txt")) {
                bstree.preOrderTraverse(bstree.root, treeFile);
                treeFile.close();
            }
        } catch (IOException e) {
            System.out.println(e);
        }
    }

    //fibonacci sequence - finding how many different ways you can climb count # of steps
    //by x allowed steps 1, 3, or 5 in this case
    public static int stairs(int count, int[] x) {
        //return 0 because you can't start from negative steps
        if (count < 0) {
            return 0;
        } //if count == 0 only 1 step required
        else if (count == 0) {
            return 1;
        } //fibonacci formula f(n) = f(n-1) + f(n-2)
        //using allowed steps 1,3,5 f(n) = f(n-1) + f(n-3) + f(n-5)
        else {
            return (count - (x[0])) + (count - (x[1]) + (count - (x[2])));
        }
    }

    //given k value and a list of numbers add numbers together to see if 2 equal k
    public static boolean numbersAddTok(int k, int[] nums) {
        //add first number i in array to all other numbers j than go to next i 
        for (int i = 0; i < nums.length; i++) {
            for (int j = 1; j < nums.length; j++) {
                //if numbers are equal go to next
                if (j == i) {
                    continue;
                }
                int addNums = nums[i] + nums[j];
                if (addNums == k) {
                    return true;
                }
            }
        }
        return false;
    }

    //given array of integers, return new array that each element at index i
    //of the new array is the product of all the numbers in the original
    //array except the one at i
    public static int[] productArray(int[] array) {
        int[] left = new int[array.length];
        int[] right = new int[array.length];
        int[] prod = new int[array.length];

        int i, j;
        //left most element of array
        left[0] = 1;
        //right most element of array
        right[array.length - 1] = 1;
        //create left array
        for (i = 1; i < array.length; i++) {
            left[i] = array[i - 1] * left[i - 1];
        }
        //create right array
        for (j = array.length - 2; j >= 0; j--) {
            right[j] = array[j + 1] * right[j + 1];
        }
        //create product array
        for (i = 0; i < array.length; i++) {
            prod[i] = left[i] * right[i];
        }

        return prod;
    }

    //find the lowest positive missing integer in an array
    //ex [1,-1,3,4] 2 would be the right answer
    //duplicates have not been tested
    public static int lowPosMissingInt(int[] arr) {
        //sort array from least to greatest
        Arrays.sort(arr);
        //create temp array same length as original arr
        int[] countArr = new int[arr.length];
        int missingNum = 0;
        int lowNum = 0;
        //find the lowest postive int greater than 0 in array
        for (int j = 0; j < arr.length; j++) {
            if (arr[j] >= 0) {
                lowNum = arr[j];
                break;
            }
        }
        //fill temp arr with the correct numbers counting from lowest number
        //in original array to length of array
        for (int y = 0; y < countArr.length; y++) {
            countArr[y] = y + lowNum;
        }
        //find the first number that doesn't match original array
        //if all numbers match return last number + 1
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] > 0 && arr[i] != countArr[i]) {
                missingNum = countArr[i];
                break;
            } else {
                missingNum = countArr[i] + 1;
            }
        }
        return missingNum;
    }
}
