import java.applet.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.Timer;

public class MacSim {

    static int SUPPRESS = 2; // Debugging system controller. Has 5 states
                     // for 5 levels of Debugging detail and cycle style.
                     // 1: Verbose
                     // 2: Quarterly reviews
                     // 3: Yearly reviews and errors/ messages
                     // 4: Cycle completions
                     // 5: Quiet mode

    int simLength = 10000; // how long the simulation lasts for
    boolean restart = false;




    // App variables
    InputManager input;
    TickerController preController;
    TickerController postController;

    // Simulation variables
    Country country;
    long tick;

    public static void main(String[] args) throws InterruptedException {
        if(args.length == 1)
			SUPPRESS = (int)args[0];
		MacSim sim = new MacSim();
		sim.startSimulation();
	}

    public void startSimulation() throws InterruptedException {
        // Setup instant vars
        log("Starting Simulation...");
        log("");
        reset(20);

    	while(tick < simLength) {
			Thread.sleep(20);
			sim();
		}
	}

    Country createCountry(double rate) {
        return new Country(15000, 500, rate, SUPPRESS);
    }

    void sim(Country country) {
        tick++; // advance simulation by 1 tick
        updatePreController();
        logTick();
        if(tick%100 == 0 && tick > 0) {
            // One year
            logYear();
        } else if(tick%25 == 0 && tick > 25) {
            // One quarter
            logQuarter();
        }
        country.tick(tick);

        updatePostController();
	}

    void logCycle() {
        if(SUPPRESS > 4) return;
        // log("\n\n\n");
        log("Total GDP at start: " + country.startGdp);
        log("Total GDP at finish: " + country.yGdp);
        log("Total growth rate since start: " + country.getTotalGrowthRate());
        log("Annualized growth since start: " + country.getAnnualizedGrowthRate());
        log("   or: " + (float)(int)(country.getAnnualizedGrowthRate() * 10000)/100.0 + "%");
        log(" \n ");
    }

    void logYear() {
        if(SUPPRESS > 3) return;
        log(" ");
        log(" ");
        log("A year has passed");
        log("Year: " + tick/100);
        log(" ");
    }

    void logQuarter() {
        if(SUPPRESS > 2) return;
        log(" ");
        log("A quarter has passed");
        country.quarterlyReport();
    }

    void logTick() {
        if(SUPPRESS > 3) return;
        p(".");
        if(SUPPRESS > 1) return;
        p("GDP change from last tick: " + 100 * country.dGdp);
    }

    void updatePreController() {
        preController.tick(tick);
    }

    void updatePostController() {
        postController.tick(tick);
    }

    void reset(double rate) {
        country = createCountry(rate);
        tick = 0;
        preController = new TickerController();
        postController = new TickerController();
    }

    // simloop scheduler
    public void sim() {
        if(tick > simLength) {
            logCycle();
            if(restart)
            reset(20);
            return;
        }
        sim(country);
    }

    public static void log(int logLevel, String s) {
        if(logLevel < SUPPRESS) return;
		System.out.println(s);
    }

    public static void p(int logLevel, String s) {
        if(logLevel < SUPPRESS) return;
		System.out.print(s);
    }
}

