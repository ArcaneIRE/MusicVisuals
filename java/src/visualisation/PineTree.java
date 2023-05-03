package visualisation;

public class PineTree {
    MyVisual mv;
    int x, y;
    int numTriangles;
    int maxTriangles;
    int treeWidth;
    int triangleHeight;
    int overlap;
    int greenShade;

    public PineTree(MyVisual mv, int x, int y, int maxTriangles, int treeWidth, int triangleHeight) {
        this.mv = mv;
        this.x = x;
        this.y = y;
        this.numTriangles = 0;
        this.maxTriangles = maxTriangles;
        this.treeWidth = treeWidth;
        this.triangleHeight = triangleHeight;
        this.overlap = 3;
        this.greenShade = (int) mv.random(35, 55);
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

        mv.pushMatrix();
        mv.translate(x, y);
        mv.colorMode(MyVisual.RGB);
        mv.fill(0, greenShade, 0);
        mv.colorMode(MyVisual.HSB);

        for (int i = 0; i < numTriangles; i++) {
            int offsetY = (maxTriangles * (triangleHeight - overlap)) - (i * (triangleHeight - overlap));
            int offsetX = (treeWidth / 2) * i / numTriangles;
            int currentWidth = treeWidth - offsetX * 2;

            mv.triangle(offsetX, offsetY, offsetX + currentWidth, offsetY, offsetX + currentWidth / 2,
                    offsetY - triangleHeight);
        }

        mv.popMatrix();
    }
}
