/*
 * Edward Bersin
 * tuf18309@temple.edu
 * Last modified October 4, 2015
 */
package shapeabstraction;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Scanner;
import simplegui.SimpleGUI;

public class ShapeAbstraction {

    public static void main(String[] args) throws FileNotFoundException {

        Scanner textfile = new Scanner(new File("deerpoints.txt"));

        SimpleGUI sg = new SimpleGUI(800, 750);
        LinkedList ll = new LinkedList();

        Point START = null, END = null, previous = null;
        int i = 1;

        while (textfile.hasNext()) {
            Point p = new Point(textfile.nextInt(), -(textfile.nextInt()) + 650, i);

            if (i == 1) {
                START = p;
                p.VI = 10000;
            }

            p.PREVIOUS = previous;
            if (p.PREVIOUS != null) {
                p.PREVIOUS.NEXT = p;
            }
            p.NEXT = END;
            END = p;

            i++;
            previous = p;
            ll.add(p);
        }

        END.VI = 10000;

        printPicture(sg, ll, START, END);

        sg.drawLine(START.x, START.y, END.x, END.y);

        double VI_MIN = findMin(ll);
        while (VI_MIN < 40.0) {
            removeSmallest(ll, START, END, VI_MIN);
            VI_MIN = findMin(ll);
        }

        Scanner keyboard = new Scanner(System.in);
        String response;
        System.out.println("Would you like to abstract the deer? Please enter "
                + "\'yes\' or \'no\': ");
        response = keyboard.nextLine();

        if (response.equalsIgnoreCase("yes")) {
            sg.eraseAllDrawables();
            printPicture(sg, ll, START, END);
            sg.drawLine(START.x, START.y, END.x, END.y);
            System.out.println("The deer has been abstracted!");
        } else {
            System.out.println("Ok then!");
            System.exit(0);
        }
    }

    public static void printPicture(SimpleGUI sg, LinkedList ll, Point START, Point END) {
        System.out.println("");        
        for (Iterator it = ll.iterator(); it.hasNext();) {
            Point current = (Point) it.next();
            sg.drawDot(current.x, current.y, 1.0);
            if (current.NEXT != null) {
                sg.drawLine(current.x, current.y, current.NEXT.x, current.NEXT.y);
            }
            if ((current != START) && (current != END)) {
                current.VI = determineVI(current.PREVIOUS, current, current.NEXT);
            }
        }
    }

    public static double determineVI(Point previous, Point current, Point next) {

        double length1, length2, length3, visualImportance;

        length1 = Math.sqrt(Math.pow((previous.x - current.x), 2) + Math.pow((previous.y - current.y), 2));
        length2 = Math.sqrt(Math.pow((current.x - next.x), 2) + Math.pow((current.y - next.y), 2));
        length3 = Math.sqrt(Math.pow((next.x - previous.x), 2) + Math.pow((next.y - previous.y), 2));
        visualImportance = 10 * (length1 + length2 - length3);

        return visualImportance;
    }

    public static double findMin(LinkedList ll) {
        
        double VI_MIN = Double.MAX_VALUE;
        for (Iterator it = ll.iterator(); it.hasNext();) {
            Point current = (Point) it.next();
            if (current.VI < VI_MIN) {
                VI_MIN = current.VI;
            }
        }
        return VI_MIN;
    }

    public static void removeSmallest(LinkedList ll, Point START, Point END, double VI_MIN) {
        for (Iterator it = ll.iterator(); it.hasNext();) {
            Point current = (Point) it.next();
            if ((current != START) && (current != END) && ((Math.abs(current.VI - VI_MIN) < 0.01))) {
                current.PREVIOUS.NEXT = current.NEXT;
                current.NEXT.PREVIOUS = current.PREVIOUS;
                if ((current.PREVIOUS.PREVIOUS != null) && (current.NEXT.NEXT != null)) {
                    current.PREVIOUS.VI = determineVI(current.PREVIOUS.PREVIOUS, current.PREVIOUS, current.NEXT);
                    current.NEXT.VI = determineVI(current.PREVIOUS, current.NEXT, current.NEXT.NEXT);
                    it.remove();
                }
            }
        }
    }
}
