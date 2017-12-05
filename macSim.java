import javax.swing.Timer;
import java.awt.event.*;

public class MacSim implements ActionListener {

    Country country;
    long tick = 0;

    public static void main(String[] args) {
        MacSim sim = new MacSim();

        sim.startSimulation();
    }

    public void startSimulation() {
        // Setup instant vars
        log("Starting Simulation...");
        log("");
        country = createCountry();

        Timer timer = new Timer(100, this);
        timer.setInitialDelay(10);
        timer.start();
    }

    Country createCountry() {

        return new Country(150, 10);
    }

    void sim(Country country) {
        tick++; // advance simulation by 1 tick
        p(".");

        if(tick%100 == 0) {
            // One year
            log("");
            log("A year has passed");
            log("");
        } else if(tick%25 == 0) {
            // One quarter
            log("");
            log("A quarter has passed");
        }

        country.tick(tick);
    }

    // simloop scheduler
    public void actionPerformed(ActionEvent e) {
        sim(country);
    }

    public static void log(String s) {
        System.out.println(s);
    }

    public static void p(String s) {
        System.out.print(s);
    }
}
