import javafx.application.Application;
import javafx.stage.Stage;

public class App extends Application{
    public static void main(String[] args) throws Exception {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        View myGameView = new View(primaryStage);
        Model model = new Model(DoodleJumpConstants.SCENE_WIDTH/2, DoodleJumpConstants.SCENE_HEIGHT/2);
        Controller controller = new Controller(model, myGameView);
        myGameView.show();
    }
}
