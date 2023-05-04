package visualisation;

public class Moon {
    private MyVisual mv;
    private float rotation;
    private int x, y, r;

    public Moon(MyVisual mv, int x, int y, int r) {
        this.mv = mv;
        this.rotation = 0;
        this.x = x;
        this.y = y;
        this.r = r;
    }

    public void render() {
        mv.pushMatrix();
        // mv.translate(mv.width / 2, mv.height / 2, 0);
        mv.translate(x, y);

        mv.rotateX(rotation);
        mv.rotateY(rotation);
        mv.noFill();
        mv.stroke(200);
        mv.sphere(r);
        mv.popMatrix();
    }

    public void update(float amplitude) {
        rotation += amplitude;
    }
}
