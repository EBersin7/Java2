package binarysearchtreegame;

import java.util.Random;

public class BST {

    private Node ROOT;

    int offsetX = 150, offsetY = 50;
    int numberOfElements = 15;
    int randIndex = 200;

    double rootX, rootY;

    int[] sequence = new int[numberOfElements];
    int[] shuffledSequence = new int[numberOfElements];

    public BST() {
        ROOT = null;
        rootX = (Main.sg.getWidth() / 2);
        rootY = 50;
    }

    public void add(Node n, Node parent) {
        if (this.ROOT == null) {
            this.ROOT = n;
        } else {
            if (n.value <= parent.value && parent.left == null) {
                parent.left = n;
            } else if (n.value > parent.value && parent.right == null) {
                parent.right = n;
            } else {
                if (n.value < parent.value) {
                    add(n, parent.left);
                } else if (n.value > parent.value) {
                    add(n, parent.right);
                }
            }
        }
    }

    public void generateSequence() {
        Random rand = new Random();
        for (int i = 0; i < this.numberOfElements; i++) {
            this.sequence[i] = rand.nextInt(randIndex) + 1;
            if (i == this.numberOfElements - 1) {
                System.out.print(sequence[i] + ".");
            } else {
                System.out.print(sequence[i] + ", ");
            }
        }

    }

    public void generateTree() {
        for (int i = 0; i < sequence.length; i++) {
            Node n = new Node(sequence[i]);
            add(n, ROOT);
        }
    }

    public void shuffleSequence() {
        System.arraycopy(sequence, 0, shuffledSequence, 0, sequence.length);
        int index, temp;
        Random random = new Random();
        for (int i = shuffledSequence.length - 1; i > 0; i--) {
            index = random.nextInt(i + 1);
            temp = shuffledSequence[index];
            shuffledSequence[index] = shuffledSequence[i];
            shuffledSequence[i] = temp;
        }

    }

    public void printBST(Node n) {

        if (n != null) {
            System.out.println(n.value);
            printBST(n.left);
            printBST(n.right);
        } else {
            System.out.println("null");
        }
    }

    public void assignCoordinates() {
        assignCoordinatesRec(ROOT, rootX, rootY, offsetX, offsetY);
    }

    public void assignCoordinatesRec(Node n, double px, double py, int offx, int offy) {
        if (n != null) {
            n.x = px;
            n.y = py;

            assignCoordinatesRec(n.left, n.x - offx, n.y + offy, offx / 2, offy);
            assignCoordinatesRec(n.right, n.x + offx, n.y + offy, offx / 2, offy);
        }

    }

    void visualizeTree() {
        visTreeRec(ROOT, null);
    }

    private void visTreeRec(Node n, Node parent) {
        if (n != null) {
            int offset = (n.size / 2);
            if (parent != null) {
                Main.sg.drawLine(n.x + offset, n.y, parent.x + offset, parent.y + parent.size);
            }
            n.visualize();
            visTreeRec(n.left, n);
            visTreeRec(n.right, n);
        }
    }

    public void showShuffledText() {
        Main.sg.drawText("Shuffled Sequence:", 250, 15);
        for (int i = 0; i < numberOfElements; i++) {
            Main.sg.drawText(Integer.toString(shuffledSequence[i]), (i * 50) + 2, 30);
        }
    }

}
