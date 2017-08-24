/*
 * Edward Bersin
 * tuf18309@temple.edu
 * Last modified November 1, 2015
 */

package binarysearchtreegame;

import simplegui.SimpleGUI;

public class Main {

    public static SimpleGUI sg = new SimpleGUI();

    public static void main(String[] args) {

        BST bst = new BST();

        System.out.println("Original Sequence: ");
        bst.generateSequence();

        bst.shuffleSequence();
        bst.generateTree();
        bst.assignCoordinates();
        bst.visualizeTree();
        bst.showShuffledText();

    }
}
