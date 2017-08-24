/*
 * Edward Bersin
 * tuf18309@temple.edu
 * Last edited September 6, 2015
 */

package image_pixelization;

import java.awt.Color;
import simplegui.DrwImage;
import simplegui.RGB;
import simplegui.SimpleGUI;

public class Image_Pixelization {

    public static void main(String[] args) {

        DrwImage im = new DrwImage("cat.jpg");

        SimpleGUI sg = new SimpleGUI(im.getWidth(), im.getHeight());

        pixelateImage(im, sg);

    }

    private static void pixelateImage(DrwImage im, SimpleGUI sg) {

        int gridSize = 4;

        for (int rows = 0; rows < im.getHeight(); rows += gridSize) {
            for (int cols = 0; cols < im.getWidth(); cols += gridSize) {
                Color color = determineAverageColor(im, cols, rows, gridSize);
                sg.setColorAndTransparency(color, 1.0);
                sg.drawFilledBox(cols, rows, gridSize, gridSize);
            }
        }
    }

    private static Color determineAverageColor(DrwImage im, int cols, int rows, int gridSize) {

        double sum_r = 0, sum_g = 0, sum_b = 0;

        int average_r = 0, average_g = 0, average_b = 0;

        for (int brows = rows; brows < (rows + gridSize - 1); brows++) {
            for (int bcols = cols; bcols < (cols + gridSize - 1); bcols++) {
                RGB rgb = im.getPixelRGB(bcols, brows);
                int r = rgb.r;
                int g = rgb.g;
                int b = rgb.b;

                sum_r += r;
                sum_g += g;
                sum_b += b;
            }
        }

        average_r = (int) (sum_r / (gridSize * gridSize));
        average_g = (int) (sum_g / (gridSize * gridSize));
        average_b = (int) (sum_b / (gridSize * gridSize));

        Color color = new Color(average_r, average_g, average_b);

        return (color);
    }
}
