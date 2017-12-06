import java.util.ArrayList;
import java.util.*;

public class Country {

    // Util
    Random rand;


    // Politics
    Government government;


    // Sentiment
    double confidence; // -100 < x < 100
    double confidenceEarningsMultiplier; // 0 < x < 2
    double dConfidence; // confidence delta, per tick


    // Economy
    ArrayList<Company> companies = new ArrayList<Company>();
    Company mainCo;
    int coId = 0;
    double inflation;
    long gdp; // Gross Domestic Product, or total economic productivity in last tick extrapolated over a quarter
    double dGdp; // gdp delta from last tick
    long lqGdp; // GDP at end of last quarter

    // Social
    int population;
    int employed;
    int unemployed;


    // Calculated
    double unemployment; // percentage


    // Constructors

    public Country(int newPop, int numCo) {
        // Create components

        MacSim.log("Creating Country... ");
        MacSim.log("Population: " + Integer.toString(newPop));
        MacSim.log(Integer.toString(numCo) + " Companies active in economy");
        MacSim.log("");
        rand = new Random();
        unemployed = newPop;
        government = new Government();
        population = newPop;
        confidence = 0;
        dConfidence = 0;
        generateUniqueCompany(30);
        generateCompanies(numCo);
    }


    // Simulation Loop

    public void tick(long tick) {
        if(tick%25==0) {
            quarterlyReport();
        }

        // Update sentiment based on last tick metrics
        updateSentiment();

        // Run simulation
        for(Company co : companies) {
            co.tick(tick);
        }

        // Update future metrics
        calculateGDP();
    }


    // Politics


    // Economy

    void quarterlyReport() {
        log(" ");
        log("Quarterly country status report: ");
        log("GDP in the last quarter: " + lqGdp);
    }

    void updateSentiment() {
        double swing = (double)(rand.nextFloat() - 0.4f) * 4;
        dConfidence = dConfidence += swing;
        dConfidence += rationalizeConfidenceDelta();
        confidence += (dConfidence * 0.2);
        confidenceEarningsMultiplier = (confidence / 100.0) + 1;
    }

    double rationalizeConfidenceDelta() {
        if(confidence <= -50) {
            // increase chance of growth
            return 1.0;
        } else return (rand.nextFloat() - 0.2) * dGdp;
    }

    void calculateGDP() {
        double oldGdp = (double)gdp;
        gdp = 0;
        for(Company co : companies) {
            gdp += co.profit;
        }
        gdp *= 100;

        if(oldGdp != 0) {
            dGdp = (gdp / oldGdp) - (double)1.0;
        } else {
            dGdp = 0;
        }
    }

    void generateUniqueCompany(int numEmp) {
        mainCo = new Company(this, numEmp, 0);
        companies.add(mainCo);
        unemployed -= numEmp;
        coId++;
    }

    void generateCompanies(int num) {
        int each = (int)(unemployed / num);
        int remainder = unemployed % num;
        MacSim.log("Employees per company:  " + each);

        for(int i = 0; i < num; i++) {
            if(i < remainder){
                generateCompany(each + remainder);
            } else {
                generateCompany(each);
            }
        }

        unemployed = 0;
    }

    void generateCompany(int hire) {
        Company newCo = new Company(this, hire, coId);
        companies.add(newCo);
        coId++;
    }


    // Social

    public void checkUnemployment() {
        if(unemployed > 1)
            jobSearch();
    }

    // search for jobs for unemployed population
    public void jobSearch() {

    }

    public static void log(String s) {
        System.out.println(s);
    }

    public static void p(String s) {
        System.out.print(s);
    }
}
