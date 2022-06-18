package ie.visualization;

import processing.core.PApplet;

public class Frame extends PApplet{
    
    Grid G;
    float x;
    float y;
    float w;
    

    int framePosition ;

    
    public Frame(float x, float y, float w, int framePosition, Grid G){
        this.G = G;
        this.x = x;
        this.y = y;
        this.w = w;
        this.framePosition = framePosition;


        this.drawFrame();
    }

    public void drawFrame(){
        G.colorMode(RGB);
        G.rectMode(LEFT);
        G.stroke(1);
        
        G.fill(255, 185, 20);

        // frame drawn using given parameters
        G.rect(this.x, this.y, this.x + this.w, this.y + this.w);

        // centre frame text & top left quadrant text
        G.textSize(10);
        G.textAlign(CENTER);
        G.fill(1);
        G.text(this.framePosition, this.x + this.w/2, this.y + this.w/2);
        G.text(this.framePosition, this.x + this.w/4, this.y + this.w/4);

    }


}
