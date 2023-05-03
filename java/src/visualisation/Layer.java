package visualisation;

import java.util.ArrayList;

public class Layer {
    MyVisual mv;
    public ArrayList<RenderObject> renderObjects = new ArrayList<RenderObject>();

    public Layer(MyVisual mv) {
        this.mv = mv;
    }

    public void draw() {
        for (int i = 0; i < renderObjects.size(); i++) {
            mv.pushStyle();
            RenderObject ro = renderObjects.get(i);
            ro.render();
            mv.popStyle();
        }
    }

    public void update() {
        for (int i = renderObjects.size() - 1; i >= 0; i--) {
            RenderObject ro = renderObjects.get(i);
            ro.update();
        }
    }
}
