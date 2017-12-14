import java.util.*;

public class TickerController {
    Vector<Ticker> tickers;

    public TickerController() {
        tickers = new Vector<Ticker>();
    }

    public void registerTicker(Ticker ticker) {
        tickers.add(ticker);
    }

    public void tick (long newTick) {
        updateTickers(newTick);
    }

    void updateTickers(long newTick) {
        for(Ticker t : tickers) {
            t.update(newTick);
        }
    }
}
