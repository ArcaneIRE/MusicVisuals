package visualisation;

public class Bird extends RenderObject {
    boolean isFlapping;

    public Bird(MyVisual mv, int x, int y) {
        super(mv, x, y);
        isFlapping = false;
    }

    public void render() {
        mv.pushMatrix();
        mv.translate(pos.x, pos.y);
        mv.color(255);
        mv.fill(0, 255, 0);
        mv.strokeWeight(5);
        mv.line(-10, 0, 10, 0);
        if (isFlapping) {
            mv.quad(-5, 0, 5, 0, -3, 10, -7, 10);
        } else {
            mv.quad(-5, 0, 5, 0, -3, -10, -7, -10);
        }
        mv.popMatrix();
    }

    float previousBandValue = 0;

    public void update() {
        pos.x += 1;
        // int bands = mv.getBands().length;
        // int bandIndex = (int) mv.map(i, 0, rayCount, 0, bands);
        // float bandValue = mv.getSmoothedBands()[bandIndex];

        float currentBandValue = mv.getSmoothedBands()[5];
        if (currentBandValue > 6 && previousBandValue > 6) {
            isFlapping = true;
        } else {
            isFlapping = false;
        }
        previousBandValue = currentBandValue;
    }
}
