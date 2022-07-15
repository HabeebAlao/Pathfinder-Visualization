package ie.visualization;

import java.util.ArrayList;

import processing.core.PApplet;

public class Grid extends PApplet {

    int windowDimension = 800;
    ArrayList<Frame> FrameList = new ArrayList<Frame>();
    int frameIncrement = 50;

    public void settings() {
        size(windowDimension, windowDimension);

    }

    public void setup() {
        colorMode(RGB);
        generateFrames();
        generateControlPanel();

    }

    public void generateFrames() {
        int count = 0;
        for (int i = 0; i < this.windowDimension; i++) {
            for (int j = 0; j < this.windowDimension; j++) {
                if (i % frameIncrement == 0 & j % frameIncrement == 0) {
                    Frame f = new Frame(i, j, frameIncrement, count, this);
                    ++count;
                    FrameList.add(f);
                }
            }
        }
    }

    ControlPanel controlPanel;

    public void generateControlPanel() {
        // control panel class
        controlPanel = new ControlPanel(this);
    }

    public void keyPressed() {

    }

    Frame f;

    public void drawGrid() {
        frameIncrement = 50;

        for (Frame f : FrameList) {
            f.drawFrame();
        }

        /*
         * test for arraylist implementation
         * for (Frame i : FrameList) {
         * System.out.println(i.framePosition);
         * }
         */

    }

    public void draw() {
        background(26, 26, 26);
        drawGrid();
        

        int frameIncrement = 50;
        if (mousePressed) {
            for (Frame frame : FrameList) {
                if ((mouseX >= frame.getX()) && (mouseX <= frame.getX() + frameIncrement)
                        && (mouseY >= frame.getY()) && (mouseY <= frame.getY() + frameIncrement)) {
                    // set obstacle coe here
                    frame.setObstacle();

                }

            }

            //System.out.println(mouseX );
            //System.out.println(mouseY );
            

        }
        controlPanel.diplayControlPanel();

    }

}
