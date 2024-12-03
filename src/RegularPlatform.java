import javafx.scene.paint.Color;

public class RegularPlatform extends Platform {
    
    public RegularPlatform(Model model) {
        // parent constructor
        super(model);
        // set color to black
        this.setFill(Color.BLACK);
    }

    @Override
    public void jump() {
        // doodle jumps normal amount
        model.setCurrentVelocity(DoodleJumpConstants.REBOUND_VELOCITY);
    }

}
