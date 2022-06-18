package ie.visualization;

import processing.core.PApplet;

public class Grid extends PApplet {


    int windowDimension = 800;

    public void settings() {
        size(windowDimension, windowDimension);

    }
   
    public void setup() {
        colorMode(RGB);

    }

    public void keyPressed() {

    }

    Frame f;
    
    public void drawGrid() {
        int frameIncrement = 50;
        int count = 0;


        for (int i = 0; i < this.windowDimension; i++) {
            for (int j = 0; j < this.windowDimension; j++) {
                if (i%frameIncrement == 0 & j%frameIncrement == 0){
                    Frame f = new Frame( i, j, frameIncrement, count, this);
                    ++count;
                }
            }
        }
        
    }
 

    public void draw() {
        background(26, 26, 26); 
        drawGrid();
        
    }

    
}
