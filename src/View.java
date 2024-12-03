import java.util.Random;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

public class View extends BorderPane {

    // member variables
    Stage stage;
    Rectangle doodle;
    Random rand = new Random();
    HBox bottom;
    Pane center;
    VBox top;
    int score = 0;

    public View(Stage stage) {
        this.stage = stage;
        // sets up display
        displaySetup();
    }

    public void displaySetup() {
        // set up doodle and center pane
        center = new Pane();
        doodle = new Rectangle(DoodleJumpConstants.SCENE_WIDTH/2, 700, DoodleJumpConstants.DOODLE_WIDTH, DoodleJumpConstants.DOODLE_HEIGHT);
        doodle.setFill(Color.YELLOW);
        center.getChildren().add(doodle);

        setCenter(center);

        // set up quit button and bottom pane
        bottom = new HBox();

        Button quit = new Button("Quit");
        quit.setOnAction(e -> {
            System.exit(0);
        });
        quit.setMinHeight(30);
        quit.setMinWidth(80);

        bottom.getChildren().add(quit);

        setBottom(bottom);

        // set up score label and top pane
        top = new VBox();

        Label scoreLabel = new Label("Score: " + score);
        top.getChildren().add(scoreLabel);
        top.setAlignment(Pos.CENTER);

        setTop(top);

        // create scene
        Scene scene = new Scene(this, DoodleJumpConstants.SCENE_WIDTH, DoodleJumpConstants.SCENE_HEIGHT);
        stage.setScene(scene);
        stage.setResizable(false);
    }

    public void show() {
        // show scene
        stage.show();
    }

    public void setDoodlePosition(double x, double y){
        // set doodle's visual position
        doodle.setX(x);
        doodle.setY(y);
    }

    public Rectangle getDoodle() {
        return doodle;
    }

    public Pane getCenterPane(){
        return center;
    }
    
    public VBox getTopPane(){
        return top;
    }

    // update score
    public void setLabel(int score){
        Label label = (Label)top.getChildren().get(0);
        label.setText("Score: " + score);
    }

}
