import java.util.*;


public class MacSim {

    WindowController controller;
    static MacSim sim; // this (singleton)

    static int SUPPRESS = 2; // Debugging system controller. Has 5 states
                     // for 5 levels of Debugging detail and cycle style.
                     // 1: Verbose
                     // 2: Quarterly reviews
                     // 3: Yearly reviews and errors/ messages
                     // 4: Cycle completions
                     // 5: Quiet mode

    static Random rand;

	int simLength = 10000; // how long the simulation lasts for
    int simSpeed = 100;
    boolean restart = false;
    boolean autorun = true;

    // App variables
    TickerController preController;
    TickerController postController;

    // Simulation variables
    Country country;

    long tick;
    int year;
    int quarter;


    public MacSim(WindowController newController) {
        if(sim != null) return;
        sim = this;
        controller = newController;
    }

    public void startSimulation() throws InterruptedException, java.io.IOException {

        // Setup instant vars
        log(4, "Starting Simulation...");
        log(4, "");
        rand = new Random();
        reset(20);

        sim();
	}

    Country createCountry(double rate) {
        return new Country(20, 2, rate, SUPPRESS);
    }

    // simloop scheduler
    public void sim() throws InterruptedException {
        if (tick < simLength && autorun) {
            sim(country);
        }
        logCycle();
        if (restart)
            reset(20);
    }

    // country simulator
    void sim(Country country) {
        tick++; // advance simulation by 1 tick
        updatePreController();
        logTick();
        if(tick%100 == 0 && tick > 0) {
            // One year
            quarter = 0;
            year++;
            logYear();
        } else if(tick%25 == 0 && tick > 0) {
            // One quarter
            quarter++;
            logQuarter();
        }
        country.tick(tick);

        updatePostController();

        updateUI();
	}

    void logCycle() {
        // log("\n\n\n");
        // log(3, "Total GDP at start: " + country.startGdp);
        // log(3, "Total GDP at finish: " + country.yGdp);
        // log(3, "Total growth rate since start: " + country.getTotalGrowthRate());
        // log(3, "Annualized growth since start: " + country.getAnnualizedGrowthRate());
        // log(3, "   or: " + (float)(int)(country.getAnnualizedGrowthRate() * 10000)/100.0 + "%");
        // log(3, " \n ");
    }

    void logYear() {
        log(4, " ");
        log(4, " ");
        log(4, "A year has passed");
        log(4, "Year: " + tick/100);
        log(4, " ");
    }

    void logQuarter() {
        log(2, " ");
        log(2, "A quarter has passed");
        country.quarterlyReport();
    }

    void logTick() {
        p(3, ".");
        // p(2, "GDP change from last tick: " + 100 * country.dGdp);
    }

    void updatePreController() {
        preController.tick(tick);
    }

    void updatePostController() {
        postController.tick(tick);
    }

    void updateUI() {
        controller.update(this);
    }

    void reset(double rate) {
        country = createCountry(rate);
        tick = 0;
        preController = new TickerController();
        postController = new TickerController();
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

