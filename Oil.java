public class Oil extends Resource{

    public Oil(GoodsMarket mkt, int timePeriod, long diff) {
        market = mkt;
        demandTimePeriod = timePeriod;
        difficulty = diff;
        price = 10;
    }
}
