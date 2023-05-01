package visualisation;

import example.AudioBandsVisual;
import example.WaveForm;
import ie.tudublin.*;
import java.util.ArrayList;

public class MyVisual extends Visual {
    WaveForm wf;
    AudioBandsVisual abv;
    Layers layers;
    ArrayList<PineTree> pineTrees;

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
        loadAudio("heroplanet.mp3");

        // Call this instead to read audio from the microphone
        startListening();

        wf = new WaveForm(this);
        abv = new AudioBandsVisual(this);

        layers = new Layers(this);
        pineTrees = new ArrayList<>();
    }

    public void keyPressed() {
        if (key == ' ') {
            getAudioPlayer().cue(0);
            getAudioPlayer().play();
        } else if (key == '+') {
            int x = (int) random(width);
            int y = (int) random(height - 200) + 100;
            int maxTriangles = (int) random(5, 10);
            int treeWidth = 100;
            int triangleHeight = 12;
            pineTrees.add(new PineTree(this, x, y, maxTriangles, treeWidth, triangleHeight));
        }
    }

    public void draw() {
        background(0);
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
        wf.render();
        abv.render();
        for (PineTree pineTree : pineTrees) {
            pineTree.grow();
            pineTree.render();
        }
    }
}
