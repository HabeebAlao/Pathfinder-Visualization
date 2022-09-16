package ie.visualization;

import processing.core.PApplet;

import java.util.ArrayList;


public class Frame extends PApplet {

    Grid G;
    float x;
    float y;
    float w;

    int framePosition;
    int Gcost;
    int Hcost;
    int Fcost;
    int isObstacle;
    int isTarget;
    int isStart;
    int isActive;
    int isPath;
    int inThisColumn;
    int inThisRow;


    ArrayList<Frame> adjacentFrameLists = new ArrayList<Frame>();
    ArrayList<Frame> open = new ArrayList<Frame>();
    ArrayList<Frame> closed = new ArrayList<Frame>();


    public Frame(float x, float y, float w, int framePosition, Grid G) {
        this.G = G;
        this.x = x;
        this.y = y;
        this.w = w;
        this.framePosition = framePosition;
        this.Gcost = 0; 
        this.Hcost = 0;
        this.Fcost = 0;
        this.isObstacle = 0;
        this.isTarget = 0;
        this.isStart = 0;
        this.isActive = 0;

        
        this.drawFrame();
    }

    public void colourNeighbouringNodes(){
        for (Frame neighbouFrame : adjacentFrameLists) {
            neighbouFrame.isActive = 1;
        }
    }

    public void findGcost(Frame startNode){
        
    }

    public void drawFrame() {
        G.colorMode(RGB);
        G.rectMode(LEFT);
        G.stroke(1);

        if (this.isObstacle == 1) {
            colourNeighbouringNodes();
            G.fill(204, 204, 204);
        } else if (this.isTarget == 1) {
            colourNeighbouringNodes();
            G.fill(255, 95, 87);
        } else if (this.isStart == 1) {
            colourNeighbouringNodes();
            G.fill(72, 126, 2);
        } else if (this.isActive == 1) {
            G.fill(112, 212, 234);
        } else {
            G.fill(255, 185, 20);
        }

        // frame drawn using given parameters
        G.rect(this.x, this.y, this.x + this.w, this.y + this.w);

        // centre frame text & top left quadrant text
        G.textSize(10);
        G.textAlign(CENTER);
        G.fill(1);
        G.text(this.framePosition, this.x + this.w / 2, this.y + this.w / 2);
        G.text(this.Gcost, this.x + this.w / 4, this.y + this.w / 4);
        G.text(this.Hcost, this.x + this.w / 4 * 3, this.y + this.w / 4);
        G.text(this.Fcost, this.x + this.w / 4, this.y + this.w / 4 * 3);


    }

    public float getX() {
        return this.x;
    }

    public float getY() {
        return this.y;
    }

    public float getPos() {
        return this.framePosition;
    }

    public void setObstacle() {
        if (isTarget == 0 && isStart == 0) {
            this.isObstacle = 1;
            drawFrame();
        }

    }

    public void unsetObstacle() {
        this.isObstacle = 0;
        drawFrame();
    }

    public void setTarget() {
        this.isTarget = 1;
        drawFrame();
    }

    public void unsetTarget() {
        this.isTarget = 0;
        drawFrame();
    }

    public void setStart() {
        this.isStart = 1;
        drawFrame();
    }

    public void unsetStart() {
        this.isStart = 0;
        drawFrame();
    }

    public void setisActive() {
        this.isActive = 1;
        drawFrame();
    }

    public void unsetisActive() {
        this.isActive = 0;
        drawFrame();
    }

    public void moveRight() {

        for (Frame f : G.FrameList) {
            if (f.isObstacle != 1 && f.isTarget != 1) {
                for (Frame f2 : G.FrameList) {
                    if (f2.framePosition == this.framePosition + 16) {
                        f2.setisActive();
                    }

                }
            }
        }

    }

    public void moveLeft() {

    }

    public void moveUp() {
  
    }

    public void moveDown() {

    }

}
