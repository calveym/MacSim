public abstract class Ticker {
    int startTime, length, startDelay;

    public Ticker(int tick, int len, int sDelay) {
        startTime = tick;
        length = len;
        startDelay = sDelay;
    }

    public void register(TickController controller) {
        controller.registerTicker(this);
    }

    public void update(int newTick) {
        if()
    }

    public abstract void CompleteAction();
}
