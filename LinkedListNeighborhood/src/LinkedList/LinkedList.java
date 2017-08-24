package LinkedList;

import java.awt.Color;
import java.util.Scanner;
import simplegui.SimpleGUI;

public class LinkedList {
    House FIRST;

    Scanner keyboard = new Scanner(System.in);
    String response;

    public LinkedList() {
        FIRST = null;
    }

    public void insertFirst(int i, SimpleGUI sg) {
        House h = new House(i, sg);
        h.next = FIRST;
        FIRST = h;
    }

    public void linkRoad(SimpleGUI sg) {
        House current = FIRST;

        while ( current.next != null) {
            current.connectHouses(sg);
            if(current.index == 1){
                System.out.println("First house built at <" + Math.floor(current.x * 100) / 100
                        + ", " + Math.floor(current.y * 100) / 100 + ">, connected to the sign post.");
            System.out.println("New house #" + current.index + " built at <" + Math.floor(current.x * 100) / 100
                        + ", " + Math.floor(current.y * 100) / 100 + ">, connected to the house at <"
                        + Math.floor(current.next.x * 100) / 100 + ", " + Math.floor(current.next.y * 100) / 100 + ">");
            }else if (current.index == 9) {
                System.out.println("New house #" + current.index + " built at <" + Math.floor(current.x * 100) / 100
                        + ", " + Math.floor(current.y * 100) / 100 + ">, connected to the house at <"
                        + Math.floor(current.next.x * 100) / 100 + ", " + Math.floor(current.next.y * 100) / 100 + ">");
                System.out.println("Final house built at <" + Math.floor(current.next.x * 100) / 100 + ", "
                        + Math.floor(current.next.y * 100) / 100 + ">");
            } else {
                System.out.println("New house #" + current.index + " built at <" + Math.floor(current.x * 100) / 100
                        + ", " + Math.floor(current.y * 100) / 100 + ">, connected to the house at <"
                        + Math.floor(current.next.x * 100) / 100 + ", " + Math.floor(current.next.y * 100) / 100 + ">");
            }
            current = current.next;
        }
    }

    public void traverseList(SimpleGUI sg) {
        House current = FIRST;

        while (current != null) {
            current.drawHouse(sg, Color.GREEN);
            System.out.println("Please, enter \"t\" to move to the next house or enter any other key to exit.");
            response = keyboard.nextLine();
            if (response.equalsIgnoreCase("t")) {
                current.drawHouse(sg, Color.BLUE);
                current = current.next;
            } else {
                System.out.println("Thanks for stopping by!");
                System.exit(0);
            }
        }
    }
}
