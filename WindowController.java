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
    private Label popLabel;
    private Label empLabel;
    private Label comLabel;

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

    public void update(MacSim sim) {
        updateOverview(sim);
        updateTime(sim);
    }


    @FXML
    private void updateOverview(MacSim sim) {
        if(gdpLabel == null)
            gdpLabel = (Label) root.lookup("#gdp");
        if(popLabel == null)
            popLabel = (Label) root.lookup("#pop");
        if(empLabel == null)
            empLabel = (Label) root.lookup("#emp");
        if(comLabel == null)
            comLabel = (Label) root.lookup("#com");

        gdpLabel.setText(Long.toString(sim.country.economy.gdp));
        popLabel.setText(Long.toString(sim.country.pop.totalPop));
        empLabel.setText(Long.toString(sim.country.pop.employed));
        comLabel.setText(Long.toString(sim.country.economy.companies.size()));
    }

    @FXML
    private void updateTime(MacSim sim) {
        if(curTime == null)
            curTime = (Label) root.lookup("#curTime");
        curTime.setText(sim.year + " | " + sim.quarter + " | " + sim.tick%25);
    }

    public void test(ActionEvent actionEvent) {
        MacSim.log(5, "TESTING 123");
    }

    public void sim(ActionEvent actionEvent) throws InterruptedException {
        MacSim.sim.sim();
    }
}
