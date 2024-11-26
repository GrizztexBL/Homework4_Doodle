import java.util.Random;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

public class View extends BorderPane {

    Stage stage;
    Rectangle doodle;
    Random rand = new Random();

    public View(Stage stage) {
        this.stage = stage;
        displaySetup();
    }

    public void displaySetup() {
        doodle = new Rectangle(DoodleJumpConstants.SCENE_WIDTH/2, DoodleJumpConstants.SCENE_HEIGHT/2, DoodleJumpConstants.DOODLE_WIDTH, DoodleJumpConstants.DOODLE_HEIGHT);
        doodle.setFill(Color.YELLOW);
        getChildren().add(doodle);

        Button quit = new Button("Quit");
        quit.setOnAction(e -> {
            System.exit(0);
        });
        quit.setMinHeight(30);
        quit.setMinWidth(80);

        setBottom(quit);

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

}
