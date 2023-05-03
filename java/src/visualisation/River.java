package visualisation;

import processing.core.PApplet;

public class River extends RenderObject {
    int riverHeight;
    int segments;
    float[] waveOffsets;
    float[] waveAmplitudes;

    public River(MyVisual mv, float x, float y, int riverHeight, int segments) {
        super(mv, x, y);
        this.riverHeight = riverHeight;
        this.segments = segments;
        waveOffsets = new float[segments];
        waveAmplitudes = new float[segments];
    }

    public void render() {
        mv.pushStyle();
        mv.fill(200, 100, 255);
        mv.noStroke();
        mv.beginShape();
        mv.vertex(pos.x, pos.y);

        for (int i = 0; i < segments; i++) {
            float x = PApplet.map(i, 0, segments - 1, pos.x, mv.width);
            float waveHeight = waveAmplitudes[i] * mv.getSmoothedAmplitude() * 10;
            float y = pos.y - waveHeight;
            mv.vertex(x, y);
        }

        mv.vertex(mv.width, pos.y);
        mv.vertex(mv.width, mv.height);
        mv.vertex(pos.x, mv.height);
        mv.endShape(PApplet.CLOSE);
        mv.popStyle();
    }

    public void update() {
        float noiseOffset = mv.random(0, 100);

        for (int i = 0; i < segments; i++) {
            float noiseValue = mv.noise(noiseOffset);
            waveOffsets[i] = noiseValue;
            noiseOffset += 0.1;
        }

        for (int i = 0; i < segments; i++) {
            waveAmplitudes[i] = PApplet.map(waveOffsets[i], 0, 1, -riverHeight / 2, riverHeight / 2);
        }
    }
}
