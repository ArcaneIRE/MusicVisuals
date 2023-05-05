package visualisation;

import processing.core.PVector;

public class ShootingStar {
    private MyVisual mv;
    private PVector position, velocity;
    private float timeToLive;

    public ShootingStar(MyVisual mv, float x, float y, float vx, float vy) {
        this.mv = mv;
        this.position = new PVector(x, y);
        this.velocity = new PVector(vx, vy);
        this.timeToLive = mv.random(40, 80); // Randomized time to live (in frames)

    }

    public void render() {
        mv.pushStyle();
        mv.stroke(60, 100, 100); // yellow
        mv.line(position.x, position.y, position.x - velocity.x, position.y - velocity.y);
        mv.popStyle();
    }

    public void update() {
        position.add(velocity);
        timeToLive--;

        // Remove the shooting star if it goes off-screen or its timeToLive reaches 0
        if (position.x < 0 || position.x > mv.width || position.y < 0 || position.y > mv.height || timeToLive <= 0) {
            mv.nightScene.removeShootingStar(this);
        }
    }
}
