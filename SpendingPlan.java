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
        int numCompanies = country.economy.companies.size();
        for(Company co : country.economy.companies) {
            if(co.value < minBarrier || co.value > maxBarrier) return;
            co.invest(perTickSpend / numCompanies);
        }
    }
}
