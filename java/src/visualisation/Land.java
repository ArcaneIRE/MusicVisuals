package visualisation;

import processing.core.PApplet;

public class Land extends RenderObject {
    float heightMap[];
    Color color;

    public Land(PApplet p, float x, float y, Color color) {
        super(p, x, y);
        this.color = color;
        this.heightMap = new float[p.width];

        generateHeightMap();
    }

    public void render() {
        p.stroke(color.hue, color.sat, color.bri);
        p.strokeWeight(1);
        for (int i = 0; i < heightMap.length; i++) {
            p.line(i, pos.y + heightMap[i], i, p.height);
        }
    }

    public void update() {
    }

    float noiseOffset = p.random(0, 100);

    public void generateHeightMap() {
        float degreeOfChange = 0.0012f;
        float heightVariation = p.height / 4;

        for (int i = 0; i < heightMap.length; i++) {
            float noiseValue = p.noise(noiseOffset);
            float height = PApplet.map(noiseValue, 0, 1, 0, heightVariation);
            heightMap[i] = height;
            noiseOffset += degreeOfChange;
        }
    }

}
