public abstract class Ticker {
    long startTime, length, startDelay;

    public Ticker(long tick, long len, long sDelay) {
        startTime = tick;
        length = len;
        startDelay = sDelay;
    }

    public void register(TickerController controller) {
        controller.registerTicker(this);
        preAction();
    }

    public void update(long newTick) {
        duringAction();
        if(newTick == length + startTime) {
            System.out.println("Ticker complete");
            postAction();
        }
    }

    public abstract void preAction();
    public abstract void duringAction(); // happens each tick while it runs
    public abstract void postAction(); // happens once after it completes
}
