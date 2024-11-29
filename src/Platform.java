import javafx.scene.shape.Rectangle;

public abstract class Platform extends Rectangle{
    double speed;
    double xLocation, yLocation;

    public Platform() {
        super(DoodleJumpConstants.PLAT_WIDTH, DoodleJumpConstants.PLAT_HEIGHT);
        this.speed = 1;
    }

    public abstract void jump();

}
