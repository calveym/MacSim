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
import java.util.HashMap;

public class WindowController extends Application {

    MacSim sim;
    Parent root;

    @FXML
    private Label gdpLabel;
    @FXML
    private Label curTime;

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
    }

    // ______________________________________________________
    // UI Updates

    public void update(HashMap<String, String> values) {
        if(root == null) return;

        updateGdp(values.get("gdp"));
        updateTime(values.get("tick"), values.get("year"), values.get("quarter"));
    }


    @FXML
    private void updateGdp(String gdp) {
        if(gdpLabel == null)
            gdpLabel = (Label) root.lookup("#gdp");
        gdpLabel.setText(gdp);
    }

    @FXML
    private void updateTime(String tick, String year, String quarter) {
        if(curTime == null)
            curTime = (Label) root.lookup("#curTime");
        curTime.setText(year + " | " + quarter + " | " + tick);
    }

    public void test(ActionEvent actionEvent) {
        MacSim.log(5, "TESTING 123");
    }

    public void sim(ActionEvent actionEvent) throws InterruptedException {
        MacSim.sim.sim();
    }
}
