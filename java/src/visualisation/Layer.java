package visualisation;

import java.util.ArrayList;
import processing.core.PApplet;

public class Layer {
    PApplet p;
    public ArrayList<RenderObject> renderObjects = new ArrayList<RenderObject>();

    public Layer(PApplet p) {
        this.p = p;
    }

    public void draw() {
        for (int i = renderObjects.size() - 1; i >= 0; i--) {
            RenderObject ro = renderObjects.get(i);
            ro.render();
        }
    }

    public void update() {
        for (int i = 0; i < renderObjects.size(); i++) {
            RenderObject ro = renderObjects.get(i);
            ro.update();
        }
    }
}
