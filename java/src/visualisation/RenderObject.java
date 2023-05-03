package visualisation;

import processing.core.PVector;

public abstract class RenderObject {
    PVector pos;
    MyVisual mv;

    public RenderObject(MyVisual mv, float x, float y) {
        this.pos = new PVector(x, y);
        this.mv = mv;
    }

    public abstract void update();

    public abstract void render();

}