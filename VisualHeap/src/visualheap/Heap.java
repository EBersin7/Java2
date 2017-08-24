package visualheap;

import java.awt.Color;
import simplegui.SimpleGUI;

public class Heap {

    private int[] heap;
    private int[] heapCopy;
    private int lastIndex;

    private final int MAXSIZE = 20;
    private final int BOXSIZE = 50;
    private final int POSY = BOXSIZE;

    public SimpleGUI sg = new SimpleGUI(((MAXSIZE + 2) * BOXSIZE), (BOXSIZE * 3), false);

    public Heap() {
        heap = new int[MAXSIZE];
        heapCopy = new int[MAXSIZE];
        lastIndex = 0;
        visualize();
    }

    public boolean insert(int value) {
        if (lastIndex >= heap.length) {
            return false;
        }

        System.arraycopy(heap, 0, heapCopy, 0, heap.length);

        int current = lastIndex;
        lastIndex++;
        heap[current] = value;

        while (current > 0) {
            int pIndex = getParentIndex(current);
            if (heap[pIndex] > heap[current]) {
                int temp = heap[pIndex];
                heap[pIndex] = heap[current];
                heap[current] = temp;
                current = pIndex;
            } else {
                break;
            }
        }
        return (true);
    }

    public void visualize() {

        sg.eraseAllDrawables(null);
        sg.setBackgroundColor(Color.BLUE);

        for (int i = 0; i < heap.length; i++) {
            int posX = BOXSIZE + BOXSIZE * i;
            Color c = Color.GRAY;
            if (i < lastIndex) {
                if (heap[i] == heapCopy[i]) {
                    c = Color.GREEN;
                } else {
                    c = Color.RED;
                }
            }
            sg.drawFilledBox(posX, POSY, BOXSIZE, BOXSIZE, c, 1.0, "");
            sg.drawBox(posX, POSY, BOXSIZE, BOXSIZE, Color.BLACK, 1.0, 2, "");
            if (i < lastIndex) {
                sg.drawText(heap[i] + "", posX + BOXSIZE/2, POSY + BOXSIZE/2);
            }
        }
    }

    private int getParentIndex(int c) {
        return ((c - 1) / 2);
    }
}
