public class Government {

    Country country;

    int capital;
    int debt;

    // Fiscal Instruments
    double targetRate;
    SpendingPlan spendingPlan;
    boolean spendingPlanReady;

    // Monetary Policy


    // Rates
    double corporateTax = 12.5;


    public Government(Country c) {
        country = c;
    }

    public void tick() {
        collectTaxes();
        performSpending();
    }


    // Collect taxes

    // tax last tick income
    void collectTaxes() {
        long taxRevenue = 0;
        for(Company co : country.companies) {
            collectTaxableIncome(taxRevenue, co.tax(corporateTax));
        }
        System.out.println("Total tax revenue this quarter: " + taxRevenue);
        capital += taxRevenue;
    }

    // takes money from individual company
    void collectTaxableIncome(long revenue, long added) {
        revenue += added;
    }


    // Perform spending

    // invest in each company
    void performSpending() {
        if(spendingPlanReady) {
            spendingPlan.start();
        }
    }
}
