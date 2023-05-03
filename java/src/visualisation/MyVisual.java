package visualisation;

import ie.tudublin.*;
import java.util.ArrayList;

public class MyVisual extends Visual {
    private ArrayList<Layer> layers;
    ArrayList<PineTree> pineTrees;
    Sun sun;

    public void settings() {
        size(1024, 500);

        // Use this to make fullscreen
        // fullScreen();

        // Use this to make fullscreen and use P3D for 3D graphics
        // fullScreen(P3D, SPAN);
    }

    public void setup() {
        startMinim();

        // Call loadAudio to load an audio file to process
        loadAudio("primavera_short.mp3");
        getAudioPlayer().cue(0);
        getAudioPlayer().play();

        colorMode(HSB, 360, 100, 100);
        layers = new ArrayList<Layer>();

        layers.add(new Layer(this));
        layers.add(new Layer(this));
        layers.add(new Layer(this));

        layers.get(0).renderObjects.add(new Land(this, 0, 150, new Color(130, 80, 70)));
        layers.get(1).renderObjects.add(new Land(this, 0, 200, new Color(130, 80, 60)));
        layers.get(2).renderObjects.add(new Land(this, 0, 250, new Color(130, 80, 50)));
        pineTrees = new ArrayList<>();
        sun = new Sun(this, width - 100, height / 10, 80, 24);

    }

    public void keyPressed() {
        if (key == '+') {
            int x = (int) random(width);
            int y = (int) random(height - 200) + 100;
            int size = (int) random(5, 10);
            int height = 15;
            pineTrees.add(new PineTree(this, x, y, size, height));
        }
    }

    public void draw() {
        background(200, 60, 100);
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

        for (Layer layer : layers) {
            layer.draw();
            layer.update();
        }

        for (PineTree pineTree : pineTrees) {
            pineTree.grow();
            pineTree.render();
        }

        pushStyle();
        sun.render();
        popStyle();

    }
}
