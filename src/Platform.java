import javafx.scene.shape.Rectangle;

public abstract class Platform extends Rectangle{
    Model model;

    public Platform(Model model) {
        super(DoodleJumpConstants.PLAT_WIDTH, DoodleJumpConstants.PLAT_HEIGHT);
        this.model = model;
    }

    public abstract void jump();

}
