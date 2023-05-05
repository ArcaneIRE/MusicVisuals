package visualisation;

import processing.core.PApplet;
import processing.core.PGraphics;
import java.util.ArrayList;

public class Land extends RenderObject {
    float heightMap[];
    PGraphics buffer;
    Color color;
    ArrayList<PineTree> trees;
    int layerNo;

    public Land(MyVisual mv, float x, float y, Color color, int layerNo) {
        super(mv, x, y);
        this.color = color;
        this.heightMap = new float[mv.width];
        this.layerNo = layerNo;
        trees = new ArrayList<>();

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
            buffer.line(i, heightMap[i], i, pos.y);
        }
        buffer.endDraw();
    }

    public void render() {
        mv.image(buffer, 0, 0);

        for (PineTree tree : trees) {
            tree.render();
        }
    }

    public void update() {
        if (mv.random(4) < mv.getSmoothedAmplitude()) {
            spawnTree();
        }
        for (PineTree tree : trees) {
            if (mv.random(19 ) < mv.getSmoothedAmplitude()) {
                tree.grow();
            }
        }
    }

    float noiseOffset = mv.random(0, 100);

    public void generateHeightMap() {
        float degreeOfChange = 0.0015f;
        float heightVariation = mv.height / 5;

        for (int i = 0; i < heightMap.length; i++) {
            float noiseValue = mv.noise(noiseOffset);
            float height = PApplet.map(noiseValue, 0, 1, 0, heightVariation);
            heightMap[i] = pos.y - height;
            noiseOffset += degreeOfChange;
        }
    }

    public void spawnTree() {
        int x = (int) mv.random(mv.width);
        int size = (int) mv.random(0, 6) + 2 * layerNo;
        int randomOffset = (int) mv.random(0, 10);
        int y = (int) heightMap[x] + randomOffset;
        trees.add(new PineTree(mv, x, y, size, 15, this.color));
    }

}
