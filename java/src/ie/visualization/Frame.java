package ie.visualization;

import processing.core.PApplet;

public class Frame extends PApplet{
    
    Grid G;
    float x;
    float y;
    float w;
    

    int framePosition ;
    int isObstacle;

    

    
    public Frame(float x, float y, float w, int framePosition, Grid G){
        this.G = G;
        this.x = x;
        this.y = y;
        this.w = w;
        this.framePosition = framePosition;
        this.isObstacle = 0;


        this.drawFrame();
    }


    public void drawFrame(){
        G.colorMode(RGB);
        G.rectMode(LEFT);
        G.stroke(1);
        
        if(isObstacle==1){
            G.fill(204, 204, 204);
        }
        else{
            G.fill(255, 185, 20);
        }

        // frame drawn using given parameters
        G.rect(this.x, this.y, this.x + this.w, this.y + this.w);

        // centre frame text & top left quadrant text
        G.textSize(10);
        G.textAlign(CENTER);
        G.fill(1);
        G.text(this.framePosition, this.x + this.w/2, this.y + this.w/2);
        G.text(this.framePosition, this.x + this.w/4, this.y + this.w/4);

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
        this.isObstacle = 1;
        drawFrame();
    }

    public void unsetObstacle() {
        this.isObstacle = 0;
        drawFrame();
    }


    
    


}
