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
    public ArrayList<Company> companies = new ArrayList<Company>();
    Company mainCo;
    int coId = 0;
    double inflation;
    int businessCycle = 0; // 0 - 2
    long gdp; // Gross Domestic Product, or total economic productivity in last tick extrapolated over a quarter
    double dGdp; // gdp delta from last tick
    long qGdp; // Quarterly GDP
    long lqGdp; // GDP at end of last quarter
    long lqdGdp; // Quarterly GDP change

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
        government = new Government(this);
        population = newPop;
        confidence = 0;
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

    public int getNumCompanies() {
        return companies.size();
    }

    void quarterlyReport() {
        if(lqGdp != 0)
            lqdGdp = qGdp - lqGdp;
        log(" ");
        log("Current confidence: " + confidence);
        log("Confidence earnings multiplier: " + confidenceEarningsMultiplier);
        log("Quarterly country status report: ");
        log("GDP in the last quarter: " + qGdp);
        log("GDP change since last quarter: " + lqdGdp);
        lqGdp = qGdp;
        qGdp = 0;
    }

    void updateSentiment() {
        double oldConfidence = confidence;
        businessCycle++;
        if(businessCycle > 12)
            businessCycle = 0;
        confidence = 6.5;
        if(businessCycle > 10)
            confidence -= 5.4;
        confidence += volatility();
        confidence += shock();
        confidenceEarningsMultiplier = (confidence / 100.0);
        // confidenceEarningsMultiplier = 1.00000000000001;
    }

    double volatility() {
        double adjuster = rand.nextDouble();
        adjuster *= 6;
        adjuster -= 3;
        return adjuster;
    }

    double shock() {
        double shock = 0.0;
        int minor = rand.nextInt(10);
        int recession = rand.nextInt(100);
        int crisis = rand.nextInt(1000);
        if(minor == 1) {
            shock += (rand.nextDouble() * 6.0) - 3;
        } if(recession == 1) {
            shock += (rand.nextDouble() * 14.0) - 7;
        } if(crisis == 1) {
            shock += (rand.nextDouble() * 25) - 12.5;
        }
        return shock;
    }

    void calculateGDP() {
        double oldGdp = (double)gdp;
        gdp = 0;
        for(Company co : companies) {
            gdp += co.profit;
        }
        qGdp += gdp;
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
