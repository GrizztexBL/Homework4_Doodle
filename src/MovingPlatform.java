import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.paint.Color;
import javafx.util.Duration;

public class MovingPlatform extends Platform {

    int direction = 1;
    double platformX;
    Timeline platformAnimation;

    public MovingPlatform(Model model) {
        super(model);
        this.setFill(Color.BLUE);
        animate();
    }

    @Override
    public void jump() {
        model.setCurrentVelocity(DoodleJumpConstants.REBOUND_VELOCITY);
    }

    public void animate(){
        platformAnimation = new Timeline(new KeyFrame(Duration.millis(50), e -> movePlatform()));
        platformAnimation.setCycleCount(Timeline.INDEFINITE);
        platformAnimation.play();
    }

    public void movePlatform(){
        platformX = getX();
        platformX += 5 * direction;
        if (platformX < 0 || platformX + getWidth() > DoodleJumpConstants.SCENE_WIDTH){
            direction *= -1;
        }
        setX(platformX);
    }

}
