package visualisation;

import ie.tudublin.*;
import java.util.ArrayList;

public class MyVisual extends Visual {
    private ArrayList<Layer> layers;

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
        // loadAudio("heroplanet.mp3");

        // Call this instead to read audio from the microphone
        startListening();

        layers = new ArrayList<Layer>();
    }

    public void keyPressed() {
        if (key == ' ') {
            getAudioPlayer().cue(0);
            getAudioPlayer().play();
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

        for (Layer layer : layers) {
            layer.draw();
            layer.update();
        }
    }
}
