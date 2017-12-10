public class SpendingPlan {

    Country country;

    long totalSpend;
    long minBarrier;
    long maxBarrier;
    int length;

    public SpendingPlan(Country co, long tSpend, long minBar, long maxBar) {
        country = co;
        totalSpend = tSpend;
        minBarrier = minBar;
        maxBarrier = maxBar;
    }

    public void start() {

    }

    public void tick() {
        long perTickSpend = totalSpend / length;
        int numCompanies = country.getNumCompanies();
        for(Company co : country.companies) {
            if(co.value < minBarrier || co.value > maxBarrier) return;
            co.invest(perTickSpend / numCompanies);
        }
    }
}
