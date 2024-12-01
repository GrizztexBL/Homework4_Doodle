import javafx.scene.paint.Color;

public class DisappearingPlatform extends Platform {
    Controller controller;
    
    public DisappearingPlatform(Controller controller) {
        super(controller.getModel());
        this.controller = controller;
        this.setFill(Color.RED);
    }

    @Override
    public void jump() {
        model.setCurrentVelocity(DoodleJumpConstants.REBOUND_VELOCITY);
        controller.getView().getCenterPane().getChildren().remove(this);
        setX(-200);
    }

}
