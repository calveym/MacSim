public class Resource {

    GoodsMarket market; // Market this item is demanded on

    double demand; // total demand for this demandable over last period
    double demandTime[] = new double[25]; // demand in time array
    int demandTimePeriod; // period over which resource is demanded (25 = quarter default)
    int demandedLastTick; // total demanded last tick

    long difficulty; // cost per hour to produce
    long amount; // total amount of this demandable available

    double price = 10;  // price of this demandable, based on demand / amount


    // Constructors

    public Resource() {}

    public Resource(GoodsMarket mkt, int timePeriod, long diff) {
        market = mkt;
        demandTimePeriod = timePeriod;
        difficulty = diff;
    }

    public void tick() {
        // MacSim.log(5, "Amount of Oil: " + amount);
        advanceDemand();
        calculatePrice();
    }

    void advanceDemand() {
        for(int i = demandTimePeriod - 1; i > 0; i--) {
            demandTime[i] = demandTime[i - 1];
        }
        demandTime[1] = demandedLastTick;
    }

    void calculatePrice() {
        //price = difficulty * demand / amount;
    }

    public void demand(int amount) {
        demand += amount;
    }

    public long price(long amount) {
        return amount * (long)price;
    }

    public boolean produce(long amt) {
        amount += amt;
        calculatePrice();
        return true;
    }

    public boolean trade(long amt) {
        if(amt <= amount) {
            amount -= amt;
            calculatePrice();
            return true;
        } else {
            return false;
        }
    }
}
