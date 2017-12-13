public class TickerController {
    Vector<Ticker> tickers;

    public void registerTicker(Ticker ticker) {
        tickers.add(ticker);
    }

    public void tick () {
        updateTickers();
    }

    void updateTickers() {
        for(Ticker t : tickers) {
            t.update()
        }
    }
}
