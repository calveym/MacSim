import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.StackPane;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.net.URL;

public class WindowController extends Application {

    MacSim sim;
    Parent root;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws InterruptedException, java.io.IOException  {
        URL location = getClass().getResource("WindowController.fxml");
        FXMLLoader fxmlLoader = new FXMLLoader(location);
        root = fxmlLoader.load();
        //System.out.println("NODE:" + root);

        sim = new MacSim(this);
        sim.startSimulation();
        primaryStage.setTitle("MacSim");
        primaryStage.setScene(new Scene(root, 800, 600));
        primaryStage.show();
        updateText();
    }

    @FXML
    private Label gdp;

    @FXML
    private void updateText() {
        if(root == null) return;
        System.out.println("NODE:" + root);

        gdp = (Label) root.lookup("#gdp");
        gdp.setText("WE OUT HERE");
    }

    public void test(ActionEvent actionEvent) {
        MacSim.log(5, "TESTING 123");
    }

}
