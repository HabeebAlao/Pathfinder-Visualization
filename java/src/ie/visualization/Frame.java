package ie.visualization;

import processing.core.PApplet;

import java.util.ArrayList;



public class Frame extends PApplet {

    Grid G;
    float x;
    float y;
    float w;

    int framePosition;
    float Gcost;
    float Hcost;
    float Fcost;
    int isObstacle;
    int isTarget;
    int isStart;
    int isActive;
    int isPath;
    int inThisColumn;
    int inThisRow;


    ArrayList<Frame> adjacentFrameLists = new ArrayList<Frame>();
    


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

    public void calculateCosts(Frame startNode, Frame endNode){
        findGcost(startNode);
        findHcost(endNode);
        findFcost();
    }

    public void unsetCostValues(){
        setGcost(0);
        setHcost(0);
        setFcost(0);

    }

    public void setFcost(float fcost) {
        this.Fcost = fcost;
    }
    public void setGcost(float gcost) {
        this.Gcost = gcost;
    }
    public void setHcost(float hcost) {
        this.Hcost = hcost;
    }
    

    private void findGcost(Frame startNode){
        
        // Gcost is the distance from the starting node
        this.Gcost = sqrt((this.y - startNode.getY()) * (this.y - startNode.getY()) + (this.x - startNode.getX()) * (this.x - startNode.getX()));
    }

    private void findHcost(Frame endNode){

        // Hcost is the distance from the end node
        this.Hcost = sqrt((this.y - endNode.getY()) * (this.y - endNode.getY()) + (this.x - endNode.getX()) * (this.x - endNode.getX()));
        
    }

    private void findFcost(){
        // Fcost is Gcost plus Hcost
        this.Fcost = this.Gcost + this.Hcost;
        
    }

    public float getFcost(){
        return this.Fcost;
    }


    public void drawFrame() {
        G.colorMode(RGB);
        G.rectMode(LEFT);
        G.stroke(1);

        if (this.isObstacle == 1) {
            
            G.fill(204, 204, 204);
        } else if (this.isTarget == 1) {
            
            G.fill(255, 95, 87);
        } else if (this.isStart == 1) {
            
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
        G.text((int)this.Gcost, this.x + this.w / 4, this.y + this.w / 4);
        G.text((int)this.Hcost, this.x + this.w / 4 * 3, this.y + this.w / 4);
        G.text((int)this.Fcost, this.x + this.w / 4, this.y + this.w / 4 * 3);


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
