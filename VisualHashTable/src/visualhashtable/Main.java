package visualhashtable;

import java.awt.Color;
import simplegui.SimpleGUI;
import simplegui.GUIListener;

public class Main implements GUIListener {

    private static final int BOXSIZE = 50;
    private static SimpleGUI sg = new SimpleGUI(BOXSIZE * 10, BOXSIZE * 10);

    public static void main(String[] args) {
        Main main = new Main();
        sg.registerToGUI(main);
        
        while (true) {
            double[] hashTable = new double[100];
            int[] collisionTable = new int[100];

            for (int i = 0; i < hashTable.length; i++) {
                hashTable[i] = -1;
                collisionTable[i] = -1;
            }

            sg.setBackgroundColor(Color.DARK_GRAY);
            sg.labelButton1("Generate 10 Numbers");
            sg.labelButton2("Reset Table");
            sg.labelSwitch(null);
            for (int i = 0; i < 10; i++) {
                for (int j = 0; j < 10; j++) {
                    sg.drawBox(i * BOXSIZE, j * BOXSIZE, BOXSIZE, BOXSIZE);
                }
            }
            
            int count = 0;
            while (count < 10) {
                sg.waitForButton1();
                hashFunction(hashTable, collisionTable);
                visualizeTable(collisionTable, sg);
                count++;
            }
            
            sg.waitForButton2();
        }
    }

    public Main() {
        sg = new SimpleGUI(BOXSIZE * 10, BOXSIZE * 10);
        sg.registerToGUI(this);
        sg.setBackgroundColor(Color.DARK_GRAY);
        sg.labelButton1("Generate 10 Numbers");
        sg.labelButton2("Reset Table");
        sg.labelSwitch(null);
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                sg.drawBox(i * BOXSIZE, j * BOXSIZE, BOXSIZE, BOXSIZE);
            }
        }
    }

    public static void hashFunction(double[] hashTable, int[] collisionTable) {

        int arrayIndex, collisionCount = 0;

        for (int i = 0; i < 10; i++) {
            collisionCount = 0;
            double value = Math.random() * 1000;
            value = Math.round(value);
            arrayIndex = (int) (value % 100);

            while (hashTable[arrayIndex] != -1) {
                arrayIndex++;
                collisionCount++;
                //System.out.println("Collision Try Again");
                arrayIndex %= hashTable.length;
            }

            hashTable[arrayIndex] = value;
            collisionTable[arrayIndex] = collisionCount;
        }
    }

    public static void visualizeTable(int[] collisionTable, SimpleGUI sg) {
        for (int i = 0; i < collisionTable.length; i++) {
            if (collisionTable[i] != -1) {
                Color c = computeCellColor(collisionTable[i]);
                int column = (i % 10);
                int row = (i / 10);
                sg.drawFilledBox(column * BOXSIZE, row * BOXSIZE, BOXSIZE, BOXSIZE, c, 1.0, null);
                sg.drawBox(column * BOXSIZE, row * BOXSIZE, BOXSIZE, BOXSIZE);
            }
        }
    }

    public static Color computeCellColor(int collisions) {
        int r = (int) (255.0 / 7.0 * collisions);
        r = (int) Math.min(r, 255);
        int g = 255 - r;
        int b = 0;
        return (new Color(r, g, b));
    }

    @Override
    public void reactToButton1() {
        //throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void reactToButton2() {
        sg.eraseAllDrawables();
        for (int i = 0; i < 10; i++) {
                for (int j = 0; j < 10; j++) {
                    sg.drawBox(i * BOXSIZE, j * BOXSIZE, BOXSIZE, BOXSIZE);
                }
            }
    }

    @Override
    public void reactToSwitch(boolean bln) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void reactToSlider(int i) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
