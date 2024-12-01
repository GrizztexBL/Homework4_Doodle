import javafx.scene.paint.Color;

public class BouncyPlatform extends Platform {
    
    public BouncyPlatform(Model model) {
        super(model);
        this.setFill(Color.GREEN);
    }

    @Override
    public void jump() {
        model.setCurrentVelocity(DoodleJumpConstants.REBOUND_VELOCITY * 1.5);
    }

}
