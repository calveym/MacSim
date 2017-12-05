import java.util.ArrayList;

public class Country {

    // Politics
    Government government;

    // Economy
    double confidence; // -1 < x < 1
    ArrayList<Company> companies = new ArrayList<Company>();
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
        government = new Government();
        population = newPop;
        confidence = 0.5;
        generateCompanies(numCo);
    }


    // Simulation Loop

    public void tick() {
        for(Company co : companies) {

        }
    }


    // Politics

        // govt setup


    // Economy

        // do updates:
        // work tick for all companies
        // recalc gdp
    void generateCompanies(int num) {
        for(int i = 0; i < num; i++) {
            generateCompany();
        }
        tryHire();
    }

    void generateCompany() {
        Company newCo = new Company();
        companies.add(newCo);
    }


    // Social

    public void checkUnemployment() {
        if(unemployed > 1)
            jobSearch();
    }

    // search for jobs for unemployed population
    public void jobSearch() {

    }

    // only used when generating new economy
    void tryHire() {

    }
}
