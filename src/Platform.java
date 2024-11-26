import java.util.Random;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public abstract class Platform {
    double speed;
    double xLocation, yLocation;
    Rectangle rectangle;

    public Platform() {
        this.speed = 1;
        this.rectangle = new Rectangle(20, 20);
        randomColor();
    }

    public void setCoords(double x, double y) {
        this.rectangle.setTranslateX(x);
        this.rectangle.setTranslateY(y);
    }

    public void setInitialSpeed(double startSpeed) {
        speed = startSpeed;
    }
    
    public Rectangle getRectangle() {
        return this.rectangle;
    }

    public void moveItem(double doodleSpeed) {
        if (doodleSpeed > 0) {
            this.speed = doodleSpeed;
        } else {
            this.speed = 0;
        }
        this.rectangle.setTranslateY(this.rectangle.getTranslateY() + speed);
    }

    public void randomColor() {
        Random rand = new Random();
        int r = rand.nextInt(255);
        int g = rand.nextInt(255);
        int b = rand.nextInt(255);
        rectangle.setFill(Color.rgb(r, g, b));
    }

}
