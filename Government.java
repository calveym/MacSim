public class Government {

    Country country;

    int capital;
    int debt;
    long taxRevenue;

    // Fiscal Instruments
    double targetRate;
    SpendingPlan spendingPlan;
    boolean spendingPlanReady;

    // Monetary Policy


    // Rates
    double corporateTax = 0;


    public Government(Country c) {
        country = c;
    }

    public void tick(long tick) {
        if(tick <= 100)
        {
            collectTaxes(0);
        } else {
            collectTaxes(0);
        }
        performAutonomousSpending();
    }


    // Collect taxes

    // tax last tick income
    void collectTaxes(double overrideRate) {
        taxRevenue = 0;
        for(Company co : country.companies) {
            collectTaxableIncome(co.tax(overrideRate));
        }
        // System.out.println("Total tax revenue this quarter: " + taxRevenue);
        capital += taxRevenue;
    }

    // takes money from individual company
    void collectTaxableIncome(long added) {
        taxRevenue += added;
    }


    // Perform spending

    // reinvesting taxes
    void performAutonomousSpending() {

    }

    // Start investments in each company
    void performSpending(long totalSpend, long minBar, long maxBar) {
        if(!spendingPlanReady) {
            spendingPlan = new SpendingPlan(country, totalSpend, minBar, maxBar);
        }
        spendingPlan.start();
    }
}
