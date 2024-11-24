import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.util.Duration;

public class Controller {
    Timeline doodleAnimation;
    Model model;
    View view;

    public Controller(Model model, View view){
        this.model = model;
        this.view = view;
        setupControls();
    }

    public void setupControls(){
        animate();
        view.requestFocus();
        view.setOnKeyPressed(e -> setKeyControls(e));
        view.setFocusTraversable(true);
    }

    public void animate(){
        doodleAnimation = new Timeline(new KeyFrame(Duration.millis(50), e -> updateDoodle()));
        doodleAnimation.setCycleCount(Timeline.INDEFINITE);
        doodleAnimation.play();
    }

    public void setKeyControls(KeyEvent e){
        if (e.getCode() == KeyCode.LEFT){
            model.moveLeft();
        }
        
        if (e.getCode() == KeyCode.RIGHT){
            model.moveRight();
        }
    }

    public void updateDoodle(){
        model.moveDoodle();
        view.setDoodlePosition(model.getDoodleX(), model.getDoodleY());
    }
}
