package binarysearchtreegame;

import java.awt.Color;

public class Node {

    int value;
    double x, y;
    int size = 20;
    Node left, right;

    public Node(int value) {
        this.value = value;
    }

    public void visualize() {
        Main.sg.drawFilledBox(this.x, this.y, size, size, Color.BLUE, 1.0, null);
        Main.sg.drawText(Integer.toString(this.value), this.x, this.y);
    }

    public void correctClick() {
        Main.sg.drawFilledBox(this.x, this.y, size, size, Color.GREEN, 1.0, null);
    }

    public void incorrectClick() {
        Main.sg.drawFilledBox(this.x, this.y, size, size, Color.RED, 1.0, null);
    }

    public boolean isInside(int mx, int my) {
        return ((mx >= this.x && mx <= (this.x + this.size)) && (my >= this.y && my <= (this.y + this.size)));
    }
}
