import java.util.ArrayList;
import java.util.Random;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.util.Duration;

public class Controller {
    Timeline doodleAnimation;
    Model model;
    View view;
    Random rand = new Random();
    ArrayList<Platform> platforms = new ArrayList<>();
    Platform currentTopPlatform;
    Platform currentBottomPlatform;
    int platLimit = 15;

    public Controller(Model model, View view){
        this.model = model;
        this.view = view;
        setupControls();
        setupPlatforms();
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

    public void setupPlatforms(){
        currentBottomPlatform = new RegularPlatform();
        currentTopPlatform = currentBottomPlatform;
        currentBottomPlatform.setX(view.getDoodle().getX() - 20);
        currentBottomPlatform.setY(view.getDoodle().getY() + 80);
        platforms.add(currentBottomPlatform);
        view.getChildren().add(currentBottomPlatform);
        while(platforms.size() < platLimit){
            Platform tempPlatform = getNextPlatform();
            platforms.add(tempPlatform);
            view.getChildren().add(tempPlatform);
            currentTopPlatform = tempPlatform;
        }
    }

    public Platform getNextPlatform() {
        Platform randPlatform;
        int randNum = rand.nextInt(4);
        switch (randNum) {
            case 0:
                randPlatform = new RegularPlatform();
                break;
            case 1:
                randPlatform = new DisappearingPlatform();
                break;
            case 2:
                randPlatform = new BouncyPlatform();
                break;
            default:
                randPlatform = new MovingPlatform();
                break;
        }

        randPlatform.setX(getNextPlatformX());
        randPlatform.setY(getNextPlatformY());
        
        return randPlatform;
    }

    private double getNextPlatformX() {
        int min = (int)Math.max(0, currentTopPlatform.getX() - 50);
        int max = (int)Math.min(DoodleJumpConstants.SCENE_WIDTH - DoodleJumpConstants.PLAT_WIDTH, currentTopPlatform.getX() + 50);
        return rand.nextInt(min, max);
    }

    private double getNextPlatformY() {
        int min = (int)currentTopPlatform.getY() - 20;
        int max = (int)currentTopPlatform.getY() - 100;
        return rand.nextInt(max, min);
    }

}   
