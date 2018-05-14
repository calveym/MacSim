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

    @FXML
    private Label gdpLabel;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws InterruptedException, java.io.IOException  {
        URL location = getClass().getResource("WindowController.fxml");
        FXMLLoader fxmlLoader = new FXMLLoader(location);
        root = fxmlLoader.load();
        //System.out.println("NODE:" + root);


        primaryStage.setTitle("MacSim");
        primaryStage.setScene(new Scene(root, 800, 600));
        primaryStage.show();
        sim = new MacSim(this);
        sim.startSimulation();
        MacSim.log(5, "Hey hey: " + sim);

    }

    // ______________________________________________________
    // UI Updates

    public void update(long gdp) {
        updateLabels(gdp);
    }


    @FXML
    private void updateLabels(long gdp) {
        if(root == null) return;
        MacSim.log(1, "Hello we r here: " + gdp);

        gdpLabel = (Label) root.lookup("#gdp");
        gdpLabel.setText(Long.toString(gdp));
    }

    public void test(ActionEvent actionEvent) {
        MacSim.log(5, "TESTING 123");
    }

    public void sim(ActionEvent actionEvent) throws InterruptedException {
        MacSim.log(5, "Hey hey: " + MacSim.sim);
        MacSim.sim.sim();
    }
}
