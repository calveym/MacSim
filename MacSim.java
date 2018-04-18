import java.util.*;

public class MacSim {

    static int SUPPRESS = 2; // Debugging system controller. Has 5 states
                     // for 5 levels of Debugging detail and cycle style.
                     // 1: Verbose
                     // 2: Quarterly reviews
                     // 3: Yearly reviews and errors/ messages
                     // 4: Cycle completions
                     // 5: Quiet mode

    static Random rand;

	int simLength = 10000; // how long the simulation lasts for
    boolean restart = false;

    // App variables
    TickerController preController;
    TickerController postController;

    // Simulation variables
    Country country;
    long tick;

    public static void main(String[] args) throws InterruptedException {
		MacSim sim = new MacSim();
		rand = new Random();
		sim.startSimulation();
	}

    public void startSimulation() throws InterruptedException {
        // Setup instant vars
        log(4, "Starting Simulation...");
        log(4, "");
        reset(20);

    	while(tick < simLength) {
			Thread.sleep(20);
			sim();
		}
	}

    Country createCountry(double rate) {
        return new Country(20, 2, rate, SUPPRESS);
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

