import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.net.URL;

public class WindowController extends Application {

    MacSim sim;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws InterruptedException, java.io.IOException  {
        URL location = getClass().getResource("WindowController.fxml");
        FXMLLoader fxmlLoader = new FXMLLoader(location);
        Parent root = fxmlLoader.load();

        sim = new MacSim();
        sim.startSimulation();
        primaryStage.setTitle("MacSim");
        primaryStage.setScene(new Scene(root, 800, 600));
        primaryStage.show();
    }

    public void test(ActionEvent actionEvent) {
        MacSim.log(5, "TESTING 123");
    }

    

}
