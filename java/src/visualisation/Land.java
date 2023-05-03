package visualisation;

import processing.core.PApplet;
import processing.core.PGraphics;

public class Land extends RenderObject {
    float heightMap[];
    PGraphics buffer;
    Color color;

    public Land(MyVisual mv, float x, float y, Color color) {
        super(mv, x, y);
        this.color = color;
        this.heightMap = new float[mv.width];

        setup();
    }

    private void setup() {
        generateHeightMap();

        buffer = mv.createGraphics(mv.width, mv.height);
        buffer.beginDraw();
        buffer.colorMode(PApplet.HSB, 360, 100, 100);
        buffer.stroke(color.hue, color.sat, color.bri);
        buffer.fill(color.hue, color.sat, color.bri);
        buffer.rect(0, pos.y, mv.width, mv.height - pos.y);
        for (int i = 0; i < heightMap.length; i++) {
            buffer.line(i, pos.y - heightMap[i], i, pos.y);
        }
        buffer.endDraw();
    }

    public void render() {
        mv.image(buffer, 0, 0);
    }

    public void update() {
    }

    float noiseOffset = mv.random(0, 100);

    public void generateHeightMap() {
        float degreeOfChange = 0.0012f;
        float heightVariation = mv.height / 4;

        for (int i = 0; i < heightMap.length; i++) {
            float noiseValue = mv.noise(noiseOffset);
            float height = PApplet.map(noiseValue, 0, 1, 0, heightVariation);
            heightMap[i] = height;
            noiseOffset += degreeOfChange;
        }
    }

}
