import javax.swing.Timer;
import java.awt.event.*;

public class MacSim implements ActionListener {

    Country country;

    public static void main(String[] args) {
        MacSim sim = new MacSim();

        sim.startSimulation();
    }

    public void startSimulation() {
        // Setup instant vars
        country = createCountry();



        Timer timer = new Timer(100, this);
        timer.setInitialDelay(500);
        timer.start();
    }

    Country createCountry() {
        return new Country(15, 3);
    }

    void sim(Country country) {
        country.tick();
    }

    // simloop scheduler
    public void actionPerformed(ActionEvent e) {
        sim(country);
    }
}
