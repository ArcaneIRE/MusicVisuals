package visualisation;

import processing.core.PApplet;
import processing.core.PVector;

public abstract class RenderObject {
    PVector pos;
    PApplet p;

    public RenderObject(PApplet p, float x, float y) {
        this.pos = new PVector(x, y);
        this.p = p;
    }

    public abstract void update();

    public abstract void render();

}