/*
 * Edward Bersin
 * tuf18309@temple.edu
 * Last modified September 19, 2015
 */

package LinkedList;

import java.util.Scanner;
import simplegui.SimpleGUI;

public class Main {

    public static void main(String[] args) {

        Scanner keyboard = new Scanner(System.in);
        String response;

        LinkedList ll = new LinkedList();

        SimpleGUI sg = new SimpleGUI(600, 400);
        sg.drawFilledBox(0, 0, 600, 400, java.awt.Color.BLACK, 1.0, null);

        for (int i = 10; i > 0; i--) {
            ll.insertFirst(i, sg);
        }

        ll.linkRoad(sg);

        System.out.println("\nPlease, resize your screen so that you may view the neighborhood and the console prompt "
                + "simultaneously.\nIf you would like to travel through the neighborhood, please enter \"s\": ");
        response = keyboard.nextLine();
        while (response.equalsIgnoreCase("s")) {
            System.out.println("\nYou have entered the neighborhood. Your current "
                    + "house will appear in green. Previously visited houses will appear in blue.");
            ll.traverseList(sg);
        }
    }
}
