package visualisation;

import java.util.ArrayList;
import processing.core.*;

public class DayScene {
    private MyVisual mv;
    private ArrayList<Layer> layers;
    private Sun sun;
    private Bird[] flock;
    private River river;

    public DayScene(MyVisual mv) {
        this.mv = mv;
        setup();
    }

    private void setup() {
        layers = new ArrayList<Layer>();

        mv.currentBackgroundColor = mv.color(200, 60, 100);

        layers = new ArrayList<Layer>();

        layers.add(new Layer(mv));
        layers.get(0).renderObjects.add(new Land(mv, 0, 13 * (mv.height / 20), new Color(130, 68, 70), 0));
        layers.add(new Layer(mv));
        layers.get(1).renderObjects.add(new Land(mv, 0, 14 * (mv.height / 20), new Color(130, 71, 60), 1));
        layers.add(new Layer(mv));
        layers.get(2).renderObjects.add(new Land(mv, 0, 15 * (mv.height / 20), new Color(130, 74, 50), 2));
        layers.add(new Layer(mv));
        layers.get(3).renderObjects.add(new Land(mv, 0, 16 * (mv.height / 20), new Color(130, 77, 40), 3));
        layers.add(new Layer(mv));
        layers.get(4).renderObjects.add(new Land(mv, 0, 17 * (mv.height / 20), new Color(130, 80, 30), 4));

        sun = new Sun(mv, mv.width - 300, mv.height / 10, 80, 24);
        river = new River(mv, 0, 18 * (mv.height / 19), 40, 50);
    }

    private void updateBackgroundColor(float elapsedTime) {
        if (elapsedTime >= mv.sunsetStartTime && elapsedTime <= mv.sunsetStartTime + mv.sunsetDuration) {
            float progress = (elapsedTime - mv.sunsetStartTime) / mv.sunsetDuration;
            int startColor = mv.color(200, 60, 100);
            int endColor = mv.color(340, 10, 10);
            mv.currentBackgroundColor = mv.lerpColor(startColor, endColor, progress);
        }
    }

    private void updateSunPosition(float elapsedTime) {
        if (elapsedTime >= mv.sunsetStartTime && elapsedTime <= mv.sunsetStartTime + mv.sunsetDuration) {
            float progress = (elapsedTime - mv.sunsetStartTime) / mv.sunsetDuration;
            int startY = mv.height / 10;
            int endY = mv.height - 80 * 2;
            sun.y = (int) PApplet.lerp(startY, endY, progress);
        }
    }

    public void spawnBirds() {
        if (this.flock != null) {
            return;
        }

        flock = new Bird[5];
        flock[0] = new Bird(mv, -10, 200);
        flock[1] = new Bird(mv, -40, 175);
        flock[2] = new Bird(mv, -40, 225);
        flock[3] = new Bird(mv, -70, 150);
        flock[4] = new Bird(mv, -70, 250);
    }

    private void renderBirds(float elapsedTime) {
        if (elapsedTime > 0f) {
            for (Bird bird : flock) {
                bird.render();
                bird.update();
            }
        }
    }

    public void render(float elapsedTime) {
        updateBackgroundColor(elapsedTime);
        mv.background(mv.currentBackgroundColor);
        updateSunPosition(elapsedTime);
        sun.render();

        for (Layer layer : layers) {
            layer.draw();
            layer.update();
        }

        if (flock != null) {
            renderBirds(elapsedTime);
            if (flock[4].pos.x > mv.width) {
                flock = null;
            }
        }

        river.render();
        river.update();
    }

    public ArrayList<Layer> getLayers() {
        return layers;
    }

    public River getRiver() {
        return river;
    }

}
