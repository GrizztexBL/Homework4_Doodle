import javafx.scene.paint.Color;

public class DisappearingPlatform extends Platform {
    Controller controller;
    
    public DisappearingPlatform(Controller controller) {
        // parent constructor
        super(controller.getModel());
        this.controller = controller;
        // set color to red
        this.setFill(Color.RED);
    }

    @Override
    public void jump() {
        // doodle jumps normal amount
        model.setCurrentVelocity(DoodleJumpConstants.REBOUND_VELOCITY);
        // remove platform visually
        controller.getView().getCenterPane().getChildren().remove(this);
        // ensure doodle will no longer be able to interact with it
        setX(-200);
    }

}
