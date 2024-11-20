import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class View extends BorderPane {

    Stage stage;

    public View(Stage stage) {
        this.stage = stage;
        displaySetup();
    }

    public void displaySetup() {
        Scene scene = new Scene(this, 600, 400);
        stage.setScene(scene);
        stage.setResizable(isCache());
    }

    public void show() {
        stage.show();
    }

}
