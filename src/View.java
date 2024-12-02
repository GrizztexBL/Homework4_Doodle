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

    Stage stage;
    Rectangle doodle;
    Random rand = new Random();
    HBox bottom;
    Pane center;
    VBox top;
    int score = 0;

    public View(Stage stage) {
        this.stage = stage;
        displaySetup();
    }

    public void displaySetup() {
        center = new Pane();
        doodle = new Rectangle(DoodleJumpConstants.SCENE_WIDTH/2, 700, DoodleJumpConstants.DOODLE_WIDTH, DoodleJumpConstants.DOODLE_HEIGHT);
        doodle.setFill(Color.YELLOW);
        center.getChildren().add(doodle);

        setCenter(center);

        bottom = new HBox();

        Button quit = new Button("Quit");
        quit.setOnAction(e -> {
            System.exit(0);
        });
        quit.setMinHeight(30);
        quit.setMinWidth(80);

        bottom.getChildren().add(quit);

        //bottom.setAlignment(Pos.CENTER);

        setBottom(bottom);

        top = new VBox();

        Label scoreLabel = new Label("Score: " + score);
        top.getChildren().add(scoreLabel);
        top.setAlignment(Pos.CENTER);

        setTop(top);

        Scene scene = new Scene(this, DoodleJumpConstants.SCENE_WIDTH, DoodleJumpConstants.SCENE_HEIGHT);
        stage.setScene(scene);
        stage.setResizable(false);
    }

    public void show() {
        stage.show();
    }

    public void setDoodlePosition(double x, double y){
        doodle.setX(x);
        doodle.setY(y);
    }

    public BorderPane getPane() {
        return this;
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

    public void setLabel(int score){
        Label label = (Label)top.getChildren().get(0);
        label.setText("Score: " + score);
    }

}
