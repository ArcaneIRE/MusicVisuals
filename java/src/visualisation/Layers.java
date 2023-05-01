package visualisation;

import java.util.ArrayList;
import processing.core.PApplet;
import processing.core.PGraphics;

public class Layers extends ArrayList<PGraphics> {
    protected PApplet p;

    public Layers(PApplet p) {
        this.p = p;
    }

    public void newLayer() {
        this.add(p.createGraphics(p.width, p.height));
    }

    // Render finalised PGraphics
    public void renderAll() {
        for (PGraphics layer : this) {
            p.image(layer, 0, 0);
        }
    }
}
