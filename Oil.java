public class Oil extends Resource{

    public Oil(GoodsMarket mkt, int timePeriod, long diff) {
        market = mkt;
        demandTimePeriod = timePeriod;
        difficulty = 10;
        demand = 1;
        price = 10;
    }
}