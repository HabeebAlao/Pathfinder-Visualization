package ie.visualization;



import processing.core.PApplet;

public class ControlPanel {

    Grid G;
    float x1;
    float y1;
    float x2;
    float y2;

    public ControlPanel(Grid G) {

        this.G = G;
        this.x1 = 75;
        this.y1 = G.displayHeight - 500;
        this.x2 = 440;
        this.y2 = G.displayHeight - 350;

    }

    public void diplayControlPanel() {

        // control panel
        G.fill(40, 40, 40, 125);
        G.rect(this.x1, this.y1, this.x2, this.y2);

        // buttons
        G.fill(210, 250, 250, 125);

        // G.rect(map(1, 0, 8, this.x1, this.x2), map(1, 0, 8, this.y1,
        // this.y2),map(1,0, 8, this.x1, this.x2)+30, map(1, 0, 8, this.y1, this.y2)+15
        // );

        // set target button
        if ((G.mousePressed) && (G.mouseX > 125) && (G.mouseX < 200) && (G.mouseY > 680) && (G.mouseY < 700)) {
            this.SetTargetButtonClicked();
        } else {
            G.fill(210, 250, 250, 125);
        }
        G.rect(125, 680, 200, 700);

        // start button
        if ((G.mousePressed) && (G.mouseX > 220) && (G.mouseX < 295) && (G.mouseY > 680) && (G.mouseY < 700)) {
            this.StartButtonClicked();
        } else {
            G.fill(210, 250, 250, 125);
        }
        G.rect(220, 680, 295, 700);

        // reset button
        if ((G.mousePressed) && (G.mouseX > 315) && (G.mouseX < 390) && (G.mouseY > 680) && (G.mouseY < 700)) {
            this.resetButtonClicked();
        } else {
            G.fill(210, 250, 250, 125);
        }
        G.rect(315, 680, 390, 700);

        String status = "Idle";
        int steps = 0;
        int open = 0;
        int closed = 0;

        G.textSize(15);
        G.textAlign(PApplet.LEFT);
        G.fill(255);

        G.text("Status: " + status, 125, 615);
        G.text("Steps: " + steps, 125, 633);
        G.text("Open: " + open, 125, 650);
        G.text("Closed: " + closed, 125, 667);

        G.textSize(10);
        G.textAlign(PApplet.CENTER);
        G.text("Set Target", 159, 694);
        G.text("Start", 255, 694);
        G.text("Reset", 353, 694);

        // zoom slider

        G.stroke(255);
        G.textSize(15);
        G.text("Zoom", 318, 618);
        G.line(248, 638, 388, 638);



        if ((G.mouseX < cx + radius && G.mouseX > cx - radius) && (G.mouseY < cy + radius && G.mouseY > cy - radius)
                && (G.mouseX > 248 && G.mouseX < 388) && G.mousePressed) {
            this.cx = G.mouseX;
        }

        G.circle(this.cx, cy, radius);

    }

    int cx = 248;
    int cy = 638;
    int radius = 15;

    public float map(float i, float a, float b, float c, float d) {
        return c + ((1 / (b - a)) * (d - c));
    }

    public void resetButtonClicked() {
        G.fill(108, 0, 70, 125);
        System.out.println("reset");
        for (Frame frame : G.FrameList) {

            frame.unsetObstacle();
            frame.unsetStart();
            frame.unsetTarget();
            frame.unsetisActive();
            G.states = 0;

        }

    }

    public void StartButtonClicked() {
        G.fill(108, 0, 70, 125);
        System.out.println("start");
        
        //test for path finding vizualiszation
        /* 
        int s = 55;
        while (s<199) {
            G.findPath(s);
            s+=16;

        }
       */
        

    }

    public void SetTargetButtonClicked() {
        G.fill(108, 0, 70, 125);
        


    }

    

    public int frameCount(int zoom) {
        return zoom;
    }

}
