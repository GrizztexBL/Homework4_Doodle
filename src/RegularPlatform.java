import javafx.scene.paint.Color;

public class RegularPlatform extends Platform {
    
    public RegularPlatform(Model model) {
        super(model);
        this.setFill(Color.BLACK);
    }

    @Override
    public void jump() {
        model.setCurrentVelocity(DoodleJumpConstants.REBOUND_VELOCITY);
    }

}
