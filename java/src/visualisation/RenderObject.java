package visualisation;

import processing.core.PApplet;
import processing.core.PVector;

public abstract class RenderObject {
    PVector pos;
    PApplet p;

    public RenderObject(float x, float y, PApplet p) {
        this.pos = new PVector(x, y);
        this.p = p;
    }

    public abstract void update();

    public abstract void render();

}