import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.paint.Color;
import javafx.util.Duration;

public class MovingPlatform extends Platform {

    // member variables
    int direction = 1;
    double platformX;
    Timeline platformAnimation;

    public MovingPlatform(Model model) {
        // parent constructor
        super(model);
        // set color to blue
        this.setFill(Color.BLUE);
        // start platform animation
        animate();
    }

    @Override
    public void jump() {
        // doodle jumps normal amount
        model.setCurrentVelocity(DoodleJumpConstants.REBOUND_VELOCITY);
    }

    public void animate(){
        // set up animation for platform
        platformAnimation = new Timeline(new KeyFrame(Duration.millis(50), e -> movePlatform()));
        platformAnimation.setCycleCount(Timeline.INDEFINITE);
        // start animation
        platformAnimation.play();
    }

    public void movePlatform(){
        // move platform until it hits screen edge
        platformX = getX();
        platformX += 5 * direction;
        if (platformX < 0 || platformX + getWidth() > DoodleJumpConstants.SCENE_WIDTH){
            // change direction
            direction *= -1;
        }
        // visually move platform
        setX(platformX);
    }

}
