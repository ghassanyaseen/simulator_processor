public class Processor implements IProcessor {
    private final String processorId;
    private Task currentTask;
    private int timeRemaining;

    public Processor(String processorId) {
        this.processorId = processorId;
        this.currentTask = null;
        this.timeRemaining = 0;
    }

    @Override
    public String getProcessorId() {
        return processorId;
    }

    @Override
    public Task getCurrentTask() {
        return currentTask;
    }

    @Override
    public void setCurrentTask(Task task) {
        this.currentTask = task;
        this.timeRemaining = task != null ? task.getExecutionTime() : 0;
    }

    @Override
    public void dectimeRemaining() {
        if (currentTask != null) {
            timeRemaining--;
        }
    }

    @Override
    public boolean isIdle() {
        return currentTask == null;
    }

    @Override
    public int getTimeRemaining() {
        return timeRemaining;
    }
}
