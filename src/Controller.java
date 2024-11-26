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
    Platform currentPlatform;
    int platSwitcher;
    int platLimit = 15;

    public Controller(Model model, View view){
        this.model = model;
        this.view = view;
        createStartingPlatforms();
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
        updatePlatforms();
    }

    public void createPlatforms() {
        int platformCounter = 0;
        int counterAdder = rand.nextInt(99);
        platformCounter = platformCounter + counterAdder;
        while (this.platforms.size() < this.platLimit && (platformCounter % 100 == 0)) {
            Platform thisIsANewPlatform = releasePlatforms();
            if (thisIsANewPlatform == null) {
                System.out.println("You are not creating any platforms, null is being returned");
                break;
            }

            double x = this.getRandomInRange(100, 500);
            thisIsANewPlatform.setCoords(x, 0);
            this.addPlatform(thisIsANewPlatform);
            break;
        }
    }

    public Platform releasePlatforms() {
        platSwitcher = rand.nextInt(4);
        switch (platSwitcher) {
            case 1: return new BluePlatform();
            case 2: return new BluePlatform();
            case 3: return new BluePlatform();
            case 4: return new BluePlatform();
            default: return new BluePlatform();
        }
    }

    public double getRandomInRange(double min, double max) {
        return (min + Math.random() * (max - min + 1));
    }

    void addPlatform(Platform obj) {
        if (obj == null) {
           this.doodleAnimation.stop();
        } else {
           view.getPane().getChildren().add(obj.getRectangle());
           this.platforms.add(obj);
        }
  
    }

    public Platform getCurrentPlatform() {
        return this.currentPlatform;
    }

    public void updatePlatforms() {
        if (model.isAtLimit()) {
            createPlatforms();
        }
        for (int i = 0; i < this.platforms.size(); i++) {
            this.currentPlatform = platforms.get(i);
            if (!this.platforms.contains(currentPlatform)) {
                --i;
            }
            this.currentPlatform.moveItem(model.getCurrentVelocity());
        }
    }

    public void createStartingPlatforms() {
        for (int i = 0; i < platLimit; i++) {
            Platform thisIsANewPlatform = releasePlatforms();
            if (thisIsANewPlatform == null) {
                System.out.println("You are not creating any platforms, null is being returned");
                break;
            }

            double x = this.getRandomInRange(20.0, 550.0);
            double y = this.getRandomInRange(20, 700);
            thisIsANewPlatform.setCoords(x, y);
            thisIsANewPlatform.setInitialSpeed(0);
            this.addPlatform(thisIsANewPlatform);
        } 
    }

}   
