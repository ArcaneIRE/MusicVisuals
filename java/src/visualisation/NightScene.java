package visualisation;

import java.util.ArrayList;

public class NightScene {
    private MyVisual mv;
    private ArrayList<Star> stars;

    public NightScene(MyVisual mv) {
        this.mv = mv;
        setup();
    }

    private void setup() {
        mv.currentBackgroundColor = mv.color(0, 0, 0); // Black background
        stars = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            stars.add(new Star(mv, mv.width / 2, mv.height / 2));
        }
    }

    public void render() {
        mv.background(mv.currentBackgroundColor);
        for (Star star : stars) {
            star.render();
            star.update();
        }
    }
}
