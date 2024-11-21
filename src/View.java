import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

public class View extends BorderPane {

    Stage stage;
    Rectangle doodle;
    double sceneWidth = 600, sceneHeight = 1000;

    public View(Stage stage) {
        this.stage = stage;
        displaySetup();
    }

    public void displaySetup() {
        doodle = new Rectangle(40, 60);
        doodle.setFill(Color.GREEN);
        getChildren().add(doodle);

        Button quit = new Button("Quit");
        quit.setOnAction(e -> {
            System.exit(0);
        });
        quit.setMinHeight(30);
        quit.setMinWidth(80);

        setBottom(quit);

        Scene scene = new Scene(this, 600, 400);
        stage.setScene(scene);
        stage.setResizable(false);
    }

    public void show() {
        stage.show();
    }
}
