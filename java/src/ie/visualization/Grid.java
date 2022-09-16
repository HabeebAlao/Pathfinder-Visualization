package ie.visualization;

import java.util.ArrayList;


import processing.core.PApplet;

public class Grid extends PApplet {

    int windowDimension = 800;
    ArrayList<Frame> FrameList = new ArrayList<Frame>();
    int frameIncrement = 50;
    int states = 0;

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



        for (Frame f : FrameList) {
            for (Frame n : FrameList) {
                //top left neighbour
                if (n.getX() == f.getX() - frameIncrement && n.getY() == f.getY() - frameIncrement){
                    f.adjacentFrameLists.add(n);
                }
                // top middle 
                if (n.getX() == f.getX() && n.getY() == f.getY() - frameIncrement){
                    f.adjacentFrameLists.add(n);
                }
                //top right
                if (n.getX() == f.getX() + frameIncrement && n.getY() == f.getY() - frameIncrement){
                    f.adjacentFrameLists.add(n);
                }
                //left middle
                if (n.getX() == f.getX() - frameIncrement && n.getY() == f.getY()){
                    f.adjacentFrameLists.add(n);
                }
                //middle middle (parent node)
                if (n.getX() == f.getX() && n.getY() == f.getY()){
                    f.adjacentFrameLists.add(n);
                }
                //right middle
                if (n.getX() == f.getX() + frameIncrement && n.getY() == f.getY()){
                    f.adjacentFrameLists.add(n);
                }
                //left bottom
                if (n.getX() == f.getX() - frameIncrement && n.getY() == f.getY() + frameIncrement){
                    f.adjacentFrameLists.add(n);
                }
                // middle bottom
                if (n.getX() == f.getX() && n.getY() == f.getY() + frameIncrement){
                    f.adjacentFrameLists.add(n);
                }
                // right bottom
                if (n.getX() == f.getX() + frameIncrement && n.getY() == f.getY() + frameIncrement){
                    f.adjacentFrameLists.add(n);
                }

               
                
                
            }
            
        }


    }

    

    ControlPanel controlPanel;

    public void generateControlPanel() {
        // control panel class
        controlPanel = new ControlPanel(this);
    }

    public void mousePressed() {

        if (mousePressed) {

            for (Frame frame : FrameList) {
                if ((mouseX >= frame.getX()) && (mouseX <= frame.getX() + frameIncrement)
                        && (mouseY >= frame.getY()) && (mouseY <= frame.getY() + frameIncrement)
                /*
                 * && ((mouseX > (controlPanel.cx + controlPanel.radius))/
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

                    if (states == 0) {
                        frame.setStart();
                        states += 1;
                    } else if (states == 1) {
                        frame.setTarget();
                        states += 1;
                    }

                }

            }



        }
    }

    

    public void keyPressed() {
        switch (key) {
            case '1':
                if (states == 2) {
                    System.out.println("start");
                    controlPanel.StartButtonClicked();
                } else {
                    System.out.println("not ready to start");

                }
                break;

            case '2':
                System.out.println("reset");
                controlPanel.resetButtonClicked();
                break;

            default:
                break;
        }

    }

    Frame f;

    public void drawGrid() {
        frameIncrement = 50;

        for (Frame f : FrameList) {
            f.drawFrame();
        }

        if (mousePressed) {

            for (Frame frame : FrameList) {
                if ((mouseX >= frame.getX()) && (mouseX <= frame.getX() + frameIncrement)
                        && (mouseY >= frame.getY()) && (mouseY <= frame.getY() + frameIncrement)) {

                    if (states == 2) {
                        frame.setObstacle();
                    }

                }

            }

            // System.out.println(mouseX );
            // System.out.println(mouseY );

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

        // findPath(55);

        drawGrid();
        
        controlPanel.diplayControlPanel();

    }

}
