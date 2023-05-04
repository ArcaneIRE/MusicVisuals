package visualisation;

import processing.core.PApplet;

public class Moon {
    private MyVisual mv;
    private float rotation;

    public Moon(MyVisual mv) {
        this.mv = mv;
        this.rotation = 0;
    }

    public void render() {
        mv.pushMatrix();
        mv.translate(mv.width / 2, mv.height / 2, 0);
        mv.rotateX(rotation);
        mv.rotateY(rotation);
        mv.noFill();
        mv.stroke(200);
        mv.sphere(100);
        mv.popMatrix();
    }

    public void update(float amplitude) {
        rotation += PApplet.map(amplitude, 0, 1, 0.01f, 0.03f);
    }
}
