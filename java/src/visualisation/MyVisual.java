package visualisation;

import ie.tudublin.*;

public class MyVisual extends Visual {
    int currentBackgroundColor;
    DayScene dayScene;

    float elapsedTime;
    float sunsetStartTime = 0.0f;
    float sunsetDuration = 19.0f;

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
        dayScene = new DayScene(this);

    }

    public void keyPresed() {
    }

    public void draw() {
        elapsedTime = getAudioPlayer().position() / 1000.0f; // Get elapsed time in seconds
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

        if (elapsedTime <= sunsetStartTime + sunsetDuration) {
            dayScene.render(elapsedTime);
        }

    }
}
