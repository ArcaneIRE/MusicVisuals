package visualisation;

import java.util.ArrayList;
import java.util.concurrent.CopyOnWriteArrayList;

public class NightScene {
    private MyVisual mv;
    private ArrayList<Star> stars;
    private CopyOnWriteArrayList<ShootingStar> shootingStars;
    private Moon moon;
    private ArrayList<Layer> layers;
    private River river;

    public NightScene(MyVisual mv, ArrayList<Layer> layers, River river) {
        this.mv = mv;
        this.layers = layers;
        this.river = river;
        setup();
    }

    public void removeShootingStar(ShootingStar shootingStar) {
        shootingStars.remove(shootingStar);
    }

    private void setup() {
        mv.currentBackgroundColor = mv.color(0, 0, 0); // Black background
        stars = new ArrayList<>();
        shootingStars = new CopyOnWriteArrayList<>();
        moon = new Moon(mv, mv.width - 300, mv.height / 8, 50);

        for (int i = 0; i < 100; i++) {
            stars.add(new Star(mv, mv.width / 2, mv.height / 2));
        }

    }

    public void spawnShootingStar() {
        float x = mv.random(mv.width);
        float y = 0;
        float vx = mv.random(-2, 2);
        float vy = mv.random(4, 8);
        ShootingStar shootingStar = new ShootingStar(mv, x, y, vx, vy);
        shootingStars.add(shootingStar);
    }

    public void render() {
        mv.background(mv.currentBackgroundColor);

        for (Star star : stars) {
            star.render();
            star.update();
        }
        for (ShootingStar shootingStar : shootingStars) {
            shootingStar.render();
            shootingStar.update();
        }
        moon.update(mv.getAmplitude());
        moon.render();

        for (Layer layer : layers) {
            layer.draw();
            layer.update();
        }
        river.render();
        river.update();
    }
}
