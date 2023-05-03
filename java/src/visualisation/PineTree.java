package visualisation;

public class PineTree {
    MyVisual mv;
    int x, y;
    int numTriangles;
    int maxTriangles;
    int treeWidth;
    int triangleHeight;
    int overlap;
    Color color;

    public PineTree(MyVisual mv, int x, int y, int size, int height, Color baseColor) {
        this.mv = mv;
        this.x = x;
        this.y = y;
        this.numTriangles = 0;
        this.maxTriangles = size;
        this.treeWidth = size * 10;
        this.triangleHeight = height;
        this.overlap = 4;
        this.color = new Color(baseColor.hue, baseColor.sat, baseColor.bri);
    }

    public void grow() {
        if (numTriangles < maxTriangles) {
            numTriangles += 1;
            if (numTriangles > maxTriangles) {
                numTriangles = maxTriangles;
            }
        }
    }

    public void render() {
        mv.pushStyle();
        mv.pushMatrix();
        mv.translate(x, y);
        mv.fill(color.hue, color.sat, color.bri);
        mv.noStroke();

        for (int i = 0; i < numTriangles; i++) {
            int offsetY = (maxTriangles * (triangleHeight - overlap)) - (i * (triangleHeight - overlap));
            int offsetX = (treeWidth / 2) * i / numTriangles;
            int currentWidth = treeWidth - offsetX * 2;

            mv.triangle(offsetX, offsetY, offsetX + currentWidth, offsetY, offsetX + currentWidth / 2,
                    offsetY - triangleHeight);
        }

        mv.pushStyle();
        mv.popMatrix();
    }
}
