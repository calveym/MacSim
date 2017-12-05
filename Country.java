import java.util.ArrayList;

public class Country {

    // Politics
    Government government;

    // Sentiment
    double confidence; // -1 < x < 1


    // Economy
    ArrayList<Company> companies = new ArrayList<Company>();
    int coId = 0;
    double inflation;
    double gdp;

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
        unemployed = newPop;
        government = new Government();
        population = newPop;
        confidence = 0.5;
        generateCompanies(numCo);
    }


    // Simulation Loop

    public void tick(long tick) {
        for(Company co : companies) {
            co.tick(tick);
        }
    }


    // Politics

        // govt setup


    // Economy

        // do updates:
        // work tick for all companies
        // recalc gdp
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
}
