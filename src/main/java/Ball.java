import processing.core.PApplet;
import processing.core.PVector;

public class Ball {
    static float maxVelocity = 10;
    static int width = 500;
    static int height = 500;
    PVector position;
    PVector velocity;
    public Ball(PVector position, PVector velocity) {
        this.position = position;
        this.velocity = velocity;
    }
    public void update(PVector ACC) {
        velocity.add(ACC);
        velocity.limit(maxVelocity);
        position.add(velocity);
        //modulo position with width and height
        position.x = position.x % width;
        position.y = position.y % height;

    }
    public void draw(PApplet p ) {
        p.ellipse(position.x, position.y, 20, 20);
    }


    public void checkEdges() {
        if (position.x > width || position.x < 0) {
            velocity.x *= -1;
        }
        if (position.y > height || position.y < 0) {
            velocity.y *= -1;
        }
    }
}
