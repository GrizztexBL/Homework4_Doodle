import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.HBox;
import javafx.util.Duration;

public class Controller {
    Timeline doodleAnimation;
    Model model;
    View view;
    Random rand = new Random();
    ArrayList<Platform> platforms = new ArrayList<>();
    Platform currentTopPlatform;
    Platform currentBottomPlatform;
    IntegerProperty score = new SimpleIntegerProperty(0);
    int platLimit = 10;

    public Controller(Model model, View view){
        this.model = model;
        this.view = view;
        setupControls();
        setupPlatforms();
        setupScore();
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
        if(model.getDoodleY() < DoodleJumpConstants.SCENE_HEIGHT / 2){
            int distance = (DoodleJumpConstants.SCENE_HEIGHT / 2) - (int)model.getDoodleY();
            model.setDoodleY(DoodleJumpConstants.SCENE_HEIGHT / 2);
            movePlatforms(distance);
            if(currentBottomPlatform.getY() > DoodleJumpConstants.SCENE_HEIGHT){
                platforms.remove(currentBottomPlatform);
                view.getCenterPane().getChildren().remove(currentBottomPlatform);
                score.setValue(score.getValue() + 1);
                currentBottomPlatform = platforms.get(0);
                Platform tempPlatform = getNextPlatform();
                platforms.add(tempPlatform);
                view.getCenterPane().getChildren().add(tempPlatform);
                currentTopPlatform = tempPlatform;
            }
        }
        if(model.getCurrentVelocity() > 0){
            checkIntersections();
        }
        if(model.getDoodleY() > DoodleJumpConstants.SCENE_HEIGHT){
            view.setOnKeyPressed(null);
            doodleAnimation.stop();
            Label gameOver = new Label("Game Over");
            gameOver.setStyle("-fx-font-size: 50");
            view.getTopPane().getChildren().add(gameOver);
        }
        view.setDoodlePosition(model.getDoodleX(), model.getDoodleY());
    }

    public void setupPlatforms(){
        currentBottomPlatform = new RegularPlatform(model);
        currentTopPlatform = currentBottomPlatform;
        currentBottomPlatform.setX(view.getDoodle().getX() - 20);
        currentBottomPlatform.setY(view.getDoodle().getY() + 80);
        platforms.add(currentBottomPlatform);
        view.getChildren().add(currentBottomPlatform);
        while(platforms.size() < platLimit){
            Platform tempPlatform = getNextPlatform();
            platforms.add(tempPlatform);
            view.getCenterPane().getChildren().add(tempPlatform);
            currentTopPlatform = tempPlatform;
        }
    }

    public Platform getNextPlatform() {
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

        randPlatform.setX(getNextPlatformX());
        randPlatform.setY(getNextPlatformY());
        
        return randPlatform;
    }

    private double getNextPlatformX() {
        int min = (int)Math.max(0, currentTopPlatform.getX() - 100);
        int max = (int)Math.min(DoodleJumpConstants.SCENE_WIDTH - DoodleJumpConstants.PLAT_WIDTH, currentTopPlatform.getX() + 100);
        return rand.nextInt(min, max);
    }

    private double getNextPlatformY() {
        int min = (int)currentTopPlatform.getY() - 50;
        int max = (int)currentTopPlatform.getY() - 200;
        return rand.nextInt(max, min);
    }

    public void movePlatforms(int distance){
        for(Platform p : platforms){
            p.setY(p.getY() + distance);
        }
    }

    public void checkIntersections(){
        Iterator<Platform> iter = platforms.iterator();
        while(iter.hasNext()){
            Platform p = iter.next();
            if (view.getDoodle().intersects(p.getBoundsInLocal())){
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

    public ArrayList<Platform> getPlatforms(){
        return platforms;
    }

    public void setupScore(){
        score.addListener(ov -> {
            view.setLabel(score.getValue());
        });
    }

}   
