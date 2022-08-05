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

                    if (f.framePosition == 55) {
                        f.setStart();

                    } else if (f.framePosition == 199) {
                        f.setTarget();

                    }
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
         * unit test for arraylist implementation
         * for (Frame i : FrameList) {
         * System.out.println(i.framePosition);
         * }
         */

    }

    public boolean findPath(int Start) {

        for (Frame f : FrameList) {
            if (f.framePosition == Start) {

                f.moveRight();
                
            }
        }

        return true;
    }

    int StartAndTargetSet = 0;

    public void draw() {
        background(26, 26, 26);

        int frameIncrement = 50;
        if (mousePressed) {
            for (Frame frame : FrameList) {
                if ((mouseX >= frame.getX()) && (mouseX <= frame.getX() + frameIncrement)
                        && (mouseY >= frame.getY()) && (mouseY <= frame.getY() + frameIncrement)
                /*
                 * && ((mouseX > (controlPanel.cx + controlPanel.radius))
                 * && (mouseX < (controlPanel.cx - controlPanel.radius)))
                 * && ((mouseY < (controlPanel.cy - controlPanel.radius))
                 * && (mouseY > (controlPanel.cy + controlPanel.radius)))
                 */) {
                    // set obstacle code here]
                    /*
                     * if(frame.framePosition == 70){
                     * frame.setStart();
                     * 
                     * }
                     * else if(frame.framePosition == 100){
                     * frame.setTarget();
                     * 
                     * }
                     */

                    frame.setObstacle();

                }

            }

            // System.out.println(mouseX );
            // System.out.println(mouseY );

        }

        //findPath(55);

        drawGrid();
        controlPanel.diplayControlPanel();

    }

}
