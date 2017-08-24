/*
 * Edward Bersin
 * tuf18309@temple.edu
 * Last modified November 8, 2015
 */
package visualheap;

public class Main {

    public static void main(String[] args) {

        Heap h = new Heap();

        boolean insert = true;
        while (insert) {
            int i = (int) (h.sg.keyReadDouble());
            insert = h.insert(i);
            h.visualize();
        }

    }
}