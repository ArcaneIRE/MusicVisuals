package visualisation;

import processing.core.PApplet;

public class Sun {
    MyVisual mv;
    int x, y;
    int size;
    int rayCount;
    float minRayLength;
    float maxRayLength;

    public Sun(MyVisual mv, int x, int y, int size, int rayCount) {
        this.mv = mv;
        this.x = x;
        this.y = y;
        this.size = size;
        this.rayCount = rayCount;
        this.minRayLength = 0;
        this.maxRayLength = 100;
    }

    public void render() {
        // Draw sun
        mv.fill(60, 80, 100);
        mv.noStroke();
        mv.ellipse(x, y, size, size);

        // Draw rays
        mv.pushMatrix();
        mv.translate(x, y);
        int bands = mv.getBands().length;
        for (int i = 0; i < rayCount; i++) {
            float angle = PApplet.TWO_PI * i / rayCount;
            int bandIndex = (int) PApplet.map(i, 0, rayCount, 0, bands);
            float bandValue = mv.getSmoothedBands()[bandIndex];
            float rayLength = size / 2 + bandValue * 0.5f;

            // Constrain ray length between minimum and maximum values
            rayLength = PApplet.constrain(rayLength, minRayLength, maxRayLength);

            float rayX = PApplet.cos(angle) * rayLength;
            float rayY = PApplet.sin(angle) * rayLength;

            mv.stroke(60, 80, 100);
            mv.strokeWeight(2);
            mv.line(0, 0, rayX, rayY);
        }
        mv.popMatrix();
    }
}
