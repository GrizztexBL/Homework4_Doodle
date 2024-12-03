import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.util.Duration;

public class Controller {
    // member variables
    Timeline doodleAnimation;
    Model model;
    View view;
    Random rand = new Random();
    ArrayList<Platform> platforms = new ArrayList<>();
    Platform currentTopPlatform;
    Platform currentBottomPlatform;
    IntegerProperty score = new SimpleIntegerProperty(0);
    int platLimit = 10;

    // constructor
    public Controller(Model model, View view){
        // set variables from parameters
        this.model = model;
        this.view = view;
        // call methods to set up game
        setupControls();
        setupPlatforms();
        setupScore();
    }

    public void setupControls(){
        // start animation
        animate();
        // set up key presses
        view.requestFocus();
        view.setOnKeyPressed(e -> setKeyControls(e));
        view.setFocusTraversable(true);
    }

    public void animate(){
        // set up animation for doodle and 
        doodleAnimation = new Timeline(new KeyFrame(Duration.millis(50), e -> updateDoodle()));
        doodleAnimation.setCycleCount(Timeline.INDEFINITE);
        // play animation
        doodleAnimation.play();
    }

    public void setKeyControls(KeyEvent e){
        // if left key pressed, move doodle left
        if (e.getCode() == KeyCode.LEFT){
            model.moveLeft();
        }
        // if right key pressed, move doodle right
        if (e.getCode() == KeyCode.RIGHT){
            model.moveRight();
        }
    }

    public void updateDoodle(){
        // move doodle
        model.moveDoodle();
        // when doodle reaches middle of the screen
        if(model.getDoodleY() < DoodleJumpConstants.SCENE_HEIGHT / 2){
            // move the platforms instead of doodle
            int distance = (DoodleJumpConstants.SCENE_HEIGHT / 2) - (int)model.getDoodleY();
            model.setDoodleY(DoodleJumpConstants.SCENE_HEIGHT / 2);
            movePlatforms(distance);
            // when the bottom platform leaves the screen
            if(currentBottomPlatform.getY() > DoodleJumpConstants.SCENE_HEIGHT){
                // remove the platform
                platforms.remove(currentBottomPlatform);
                view.getCenterPane().getChildren().remove(currentBottomPlatform);
                // increment score
                score.setValue(score.getValue() + 1);
                // set new bottom platform
                currentBottomPlatform = platforms.get(0);
                // create new platform
                Platform tempPlatform = getNextPlatform();
                platforms.add(tempPlatform);
                view.getCenterPane().getChildren().add(tempPlatform);
                currentTopPlatform = tempPlatform;
            }
        }
        // if the doodle is falling, check for intersections
        if(model.getCurrentVelocity() > 0){
            checkIntersections();
        }
        // if the doodle falls below screen
        if(model.getDoodleY() > DoodleJumpConstants.SCENE_HEIGHT){
            // remove controls
            view.setOnKeyPressed(null);
            // stop animation
            doodleAnimation.stop();
            // display game over
            Label gameOver = new Label("Game Over");
            gameOver.setStyle("-fx-font-size: 50");
            view.getTopPane().getChildren().add(gameOver);
        }
        // update doodle's visual position
        view.setDoodlePosition(model.getDoodleX(), model.getDoodleY());
    }

    public void setupPlatforms(){
        // create initial platform below doodle
        currentBottomPlatform = new RegularPlatform(model);
        currentTopPlatform = currentBottomPlatform;
        currentBottomPlatform.setX(view.getDoodle().getX() - 20);
        currentBottomPlatform.setY(view.getDoodle().getY() + 80);
        platforms.add(currentBottomPlatform);
        view.getChildren().add(currentBottomPlatform);
        // add remaining platforms "randomly" above initial platform
        while(platforms.size() < platLimit){
            Platform tempPlatform = getNextPlatform();
            platforms.add(tempPlatform);
            view.getCenterPane().getChildren().add(tempPlatform);
            currentTopPlatform = tempPlatform;
        }
    }

    public Platform getNextPlatform() {
        // get random platform type
        Platform randPlatform;
        int randNum = rand.nextInt(4);
        switch (randNum) {
            case 0:
                randPlatform = new RegularPlatform(model);
                break;
            case 1:
                randPlatform = new DisappearingPlatform(this);
                break;
            case 2:
                randPlatform = new BouncyPlatform(model);
                break;
            default:
                randPlatform = new MovingPlatform(model);
                break;
        }
        // set X and Y of new platform based on constraints
        randPlatform.setX(getNextPlatformX());
        randPlatform.setY(getNextPlatformY());
        
        return randPlatform;
    }

    private double getNextPlatformX() {
        // get min and max based on constraints
        int min = (int)Math.max(0, currentTopPlatform.getX() - 100);
        int max = (int)Math.min(DoodleJumpConstants.SCENE_WIDTH - DoodleJumpConstants.PLAT_WIDTH, currentTopPlatform.getX() + 100);
        // return random number between min and max
        return rand.nextInt(min, max);
    }

    private double getNextPlatformY() {
        // get min and max based on constraints
        int max = (int)currentTopPlatform.getY() - 50;
        int min = (int)currentTopPlatform.getY() - 175;
        // return random number between min and max
        return rand.nextInt(min, max);
    }

    public void movePlatforms(int distance){
        // move platforms based on doodle
        for(Platform p : platforms){
            p.setY(p.getY() + distance);
        }
    }

    public void checkIntersections(){
        Iterator<Platform> iter = platforms.iterator();
        // check if doodle intersects with any platform
        while(iter.hasNext()){
            Platform p = iter.next();
            if (p.intersects(model.getDoodleX(), model.getDoodleY() + DoodleJumpConstants.DOODLE_HEIGHT, DoodleJumpConstants.DOODLE_WIDTH, 1)){
                // make doodle jump based on platform
                p.jump();
            }
        }
    }

    public View getView(){
        return view;
    }

    public Model getModel(){
        return model;
    }

    public void setupScore(){
        // set up score to change
        score.addListener(ov -> {
            view.setLabel(score.getValue());
        });
    }

}   
