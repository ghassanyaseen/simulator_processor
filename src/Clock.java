public class Clock {
    private int currentCycle;

    public Clock() {
        this.currentCycle = 0;
    }

    public void tick() {
        currentCycle++;
        try {
            Thread.sleep(1000); // 1 second per cycle
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    public String getCycleId() {
        return "C" + currentCycle;
    }
}