public interface IProcessor {
    String getProcessorId();
    Task getCurrentTask();
    void setCurrentTask(Task task);
    void dectimeRemaining();
    boolean isIdle();
    int getTimeRemaining();
}
