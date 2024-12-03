import javafx.scene.paint.Color;

public class BouncyPlatform extends Platform {
    
    public BouncyPlatform(Model model) {
        // parent constructor
        super(model);
        // set color to green
        this.setFill(Color.GREEN);
    }

    @Override
    public void jump() {
        // make doodle jump higher
        model.setCurrentVelocity(DoodleJumpConstants.REBOUND_VELOCITY * 1.5);
    }

}
