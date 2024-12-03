import javafx.application.Application;
import javafx.stage.Stage;

public class App extends Application{
    public static void main(String[] args) throws Exception {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        // initialize objects required to start game
        View myGameView = new View(primaryStage);
        Model model = new Model(DoodleJumpConstants.SCENE_WIDTH/2, 700);
        Controller controller = new Controller(model, myGameView);
        // show game
        myGameView.show();
    }
}
