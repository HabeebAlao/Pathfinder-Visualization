package ie.visualization;

import java.util.ArrayList;

import processing.core.PApplet;

public class Grid extends PApplet {

    int windowDimension = 800;
    ArrayList<Frame> FrameList = new ArrayList<Frame>();
    int frameIncrement = 50;
    int states = 0;
    Frame startN;
    Frame endN;
    boolean pathFound;

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
                // top left neighbour
                if (n.getX() == f.getX() - frameIncrement && n.getY() == f.getY() - frameIncrement) {
                    f.adjacentFrameLists.add(n);
                }
                // top middle
                if (n.getX() == f.getX() && n.getY() == f.getY() - frameIncrement) {
                    f.adjacentFrameLists.add(n);
                }
                // top right
                if (n.getX() == f.getX() + frameIncrement && n.getY() == f.getY() - frameIncrement) {
                    f.adjacentFrameLists.add(n);
                }
                // left middle
                if (n.getX() == f.getX() - frameIncrement && n.getY() == f.getY()) {
                    f.adjacentFrameLists.add(n);
                }
                // middle middle (parent node)
                if (n.getX() == f.getX() && n.getY() == f.getY()) {
                    f.adjacentFrameLists.add(n);
                }
                // right middle
                if (n.getX() == f.getX() + frameIncrement && n.getY() == f.getY()) {
                    f.adjacentFrameLists.add(n);
                }
                // left bottom
                if (n.getX() == f.getX() - frameIncrement && n.getY() == f.getY() + frameIncrement) {
                    f.adjacentFrameLists.add(n);
                }
                // middle bottom
                if (n.getX() == f.getX() && n.getY() == f.getY() + frameIncrement) {
                    f.adjacentFrameLists.add(n);
                }
                // right bottom
                if (n.getX() == f.getX() + frameIncrement && n.getY() == f.getY() + frameIncrement) {
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
                        startN = frame;

                    } else if (states == 1) {
                        frame.setTarget();
                        states += 1;
                        endN = frame;
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
                    pathFound = this.findPath();
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

    public Frame MinFcost(ArrayList<Frame> list) {

        Frame min = list.get(0);

        for (Frame frame : list) {
            if (frame.getFcost() <= min.getFcost())
                min = frame;
        }

        return min;

    }

    public boolean findPath() {
        Frame current;
        ArrayList<Frame> open = new ArrayList<Frame>(); // set of nodes to be evaluated
        ArrayList<Frame> closed = new ArrayList<Frame>(); // set of nodes not yet evaluated

        open.add(startN);

        while (open.size() > 0) {

            controlPanel.status = "Running";

            current = MinFcost(open);

            open.remove(current);
            closed.add(current);

            if (current == endN) {
                controlPanel.status = "Path found";

                // color path
                Frame next = endN;
                while (next.parent != null) {
                    
                    next.setIsPath();
                    next = next.parent;

                }
                return true;
            }
            current.colourNeighbouringNodes();

            controlPanel.open = open.size();
            controlPanel.closed = closed.size();

            for (Frame f : current.adjacentFrameLists) {

                if (f.isObstacle == 1 || closed.contains(f)) {
                    continue;
                }

                if (!open.contains(f)) {
                    f.calculateCosts(startN, endN);
                    f.parent = current;
                }
                if (!open.contains(f)) {
                    open.add(f);

                }
            }

            // find neighboring node with min f cost

        }

        controlPanel.status = "No Path found";

        return false;
    }

    private boolean isOutOfBounds(Frame f) {

        if (f.getX() < windowDimension && f.getY() < windowDimension) {
            return false;
        } else {
            return true;
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

    int StartAndTargetSet = 0;

    public void draw() {
        background(26, 26, 26);

        // findPath(55);

        drawGrid();

        controlPanel.diplayControlPanel();

    }

}
