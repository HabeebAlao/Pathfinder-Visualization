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
        G.rect(125, 680, 200, 700);
        G.rect(220, 680, 295, 700);
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
        G.circle(248, 638, 10);

    }

    public float map(float i, float a, float b, float c, float d) {
        return c + ((1 / (b - a)) * (d - c));
    }

    public int frameCount(int zoom) {
        return zoom;
    }

}
