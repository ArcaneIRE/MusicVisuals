package visualisation;

public class Bird extends RenderObject {
    boolean isFlapping;
    int initialHeight;

    public Bird(MyVisual mv, int x, int y) {
        super(mv, x, y);
        this.initialHeight = y;
    }

    public void render() {
        mv.pushStyle();
        mv.pushMatrix();
        mv.translate(pos.x, pos.y);
        mv.fill(0, 255, 0);
        mv.stroke(0, 255, 0);
        mv.strokeWeight(5);
        mv.line(-10, 0, 10, 0);
        if (isFlapping) {
            mv.quad(-5, 0, 5, 0, -3, 10, -7, 10);
        } else {
            mv.quad(-5, 0, 5, 0, -3, -10, -7, -10);
        }
        mv.popStyle();
        mv.popMatrix();
    }

    float previousBandValue = 0;

    public void update() {
        pos.x += 1;

        float averageAmp = mv.getSmoothedAmplitude();
        float heightChange = MyVisual.map((averageAmp * 100), 1, 10, -25, +25);
        pos.y = initialHeight + heightChange;

        if (((int) (mv.getAudioPlayer().position() / 1000.0f)) % 4 == 0) {
            isFlapping = true;
        } else {
            isFlapping = false;
        }
    }
}
