package visualisation;

import ie.tudublin.*;
import java.util.ArrayList;

public class MyVisual extends Visual {
    private ArrayList<Layer> layers;
    Sun sun;
    Bird flock[];
    River river;

    int currentBackgroundColor;

    float elapsedTime;
    float sunsetStartTime = 126.0f;
    float sunsetDuration = 27.0f;

    public void settings() {
        size(1024, 500);

        fullScreen();
    }

    public void setup() {
        startMinim();

        // Call loadAudio to load an audio file to process
        loadAudio("primavera_short.mp3");
        getAudioPlayer().cue(0);
        getAudioPlayer().play();

        colorMode(HSB, 360, 100, 100);
        currentBackgroundColor = color(200, 60, 100);

        layers = new ArrayList<Layer>();

        layers.add(new Layer(this));
        layers.get(0).renderObjects.add(new Land(this, 0, 13 * (height / 20), new Color(130, 68, 70), 0));
        layers.add(new Layer(this));
        layers.get(1).renderObjects.add(new Land(this, 0, 14 * (height / 20), new Color(130, 71, 60), 1));
        layers.add(new Layer(this));
        layers.get(2).renderObjects.add(new Land(this, 0, 15 * (height / 20), new Color(130, 74, 50), 2));
        layers.add(new Layer(this));
        layers.get(3).renderObjects.add(new Land(this, 0, 16 * (height / 20), new Color(130, 77, 40), 3));
        layers.add(new Layer(this));
        layers.get(4).renderObjects.add(new Land(this, 0, 17 * (height / 20), new Color(130, 80, 30), 4));

        sun = new Sun(this, width - 300, height / 10, 80, 24);

        river = new River(this, 0, 18 * (height / 19), 40, 50);
    }

    public void keyPressed() {
    }

    public void updateBackgroundColor(float elapsedTime) {
        if (elapsedTime >= sunsetStartTime && elapsedTime <= sunsetStartTime + sunsetDuration) {
            float progress = (elapsedTime - sunsetStartTime) / sunsetDuration;
            int startColor = color(200, 60, 100);
            int endColor = color(340, 10, 10);
            currentBackgroundColor = lerpColor(startColor, endColor, progress);
        }
    }

    public void updateSunPosition(float elapsedTime) {
        if (elapsedTime >= sunsetStartTime && elapsedTime <= sunsetStartTime + sunsetDuration) {
            float progress = (elapsedTime - sunsetStartTime) / sunsetDuration;
            int startY = height / 10;
            int endY = height - 80 * 2;
            sun.y = (int) lerp(startY, endY, progress);
        }
    }

    public void spawnBirds(float elapsedTime) {
        if (elapsedTime > 0f && flock == null) {
            flock = new Bird[5];
            flock[0] = new Bird(this, -10, 200);
            flock[1] = new Bird(this, -40, 175);
            flock[2] = new Bird(this, -40, 225);
            flock[3] = new Bird(this, -70, 150);
            flock[4] = new Bird(this, -70, 250);
        }
    }

    public void renderBirds(float elapsedTime) {
        if (elapsedTime > 0f) {
            for (Bird bird : flock) {
                bird.render();
                bird.update();
            }
        }
    }

    public void draw() {
        elapsedTime = getAudioPlayer().position() / 1000.0f; // Get elapsed time in seconds

        spawnBirds(elapsedTime);
        updateBackgroundColor(elapsedTime);
        background(currentBackgroundColor);
        try {
            // Call this if you want to use FFT data
            calculateFFT();
        } catch (VisualException e) {
            e.printStackTrace();
        }
        // Call this is you want to use frequency bands
        calculateFrequencyBands();

        // Call this is you want to get the average amplitude
        calculateAverageAmplitude();

        updateSunPosition(elapsedTime);
        sun.render();

        for (Layer layer : layers) {
            layer.draw();
            layer.update();
        }

        renderBirds(elapsedTime);

        river.render();
        river.update();
    }
}
