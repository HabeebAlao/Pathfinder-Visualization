package ie.visualization;



public class ControlPanel {

    Grid G;
    float x;
    float y;

    public ControlPanel(Grid G) {

        this.G = G;
        this.x = G.displayWidth;
        this.y = G.displayHeight;

    }

    public void diplayControlPanel() {
        G.fill(240, 250, 250, 125);
        G.rect(75, this.y - 500, 475, this.y - 350);

    }

}
