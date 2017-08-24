package LinkedList;

import java.awt.Color;
import java.util.Random;
import simplegui.SimpleGUI;

public class House {
    House next;

    int index;
    double x, y;
    Random r = new Random();

    public House(int index, SimpleGUI sg) {
        this.index = index;
        this.x = 600 * r.nextDouble();
        this.y = 400 * r.nextDouble();
        drawHouse(sg, Color.RED);
    }

    public void drawHouse(SimpleGUI sg, Color color) {
        sg.drawFilledBox(x, y, 15, 15, color, 1.0, null);
    }

    public void connectHouses(SimpleGUI sg) {
        if (next != null) {
            sg.drawLine(this.x, this.y, next.x, next.y, Color.YELLOW, 1.0, 1, null);
        }
    }
}
