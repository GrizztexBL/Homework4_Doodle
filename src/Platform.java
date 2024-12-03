import javafx.scene.shape.Rectangle;

public abstract class Platform extends Rectangle{
    Model model;

    public Platform(Model model) {
        // parent constructor
        super(DoodleJumpConstants.PLAT_WIDTH, DoodleJumpConstants.PLAT_HEIGHT);
        this.model = model;
    }

    // abstract jump method
    public abstract void jump();

}
