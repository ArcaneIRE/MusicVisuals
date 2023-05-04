package visualisation;

import processing.core.PApplet;
import processing.core.PConstants;

public class Star {
    private MyVisual mv;
    private float x, y;
    private float speed;
    private float angle;

    public Star(MyVisual mv, float x, float y) {
        this.mv = mv;
        this.x = x;
        this.y = y;
        this.speed = mv.random(0.5f, 2.5f);
        this.angle = mv.random(PApplet.TWO_PI);
    }

    public void render() {
        mv.hint(PConstants.DISABLE_DEPTH_TEST);
        mv.pushMatrix();
        mv.translate(x, y);
        mv.stroke(255);
        mv.point(0, 0);
        mv.popMatrix();
        mv.hint(PConstants.ENABLE_DEPTH_TEST);
    }

    public void update() {
        float amp = mv.getAmplitude() * 50;
        x += PApplet.cos(angle) * (speed + amp);
        y += PApplet.sin(angle) * (speed + amp);

        // Reset the position of the star when it goes off-screen
        if (x < 0 || x > mv.width || y < 0 || y > mv.height) {
            x = mv.width / 2;
            y = mv.height / 2;
            speed = mv.random(0.5f, 2.5f);
            angle = mv.random(PApplet.TWO_PI);
        }
    }
}
