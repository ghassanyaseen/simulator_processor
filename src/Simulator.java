import java.io.IOException;
import java.util.List;

public class Simulator {
    private final int numProcessors;
    private final int totalCycles;
    private final String taskFilePath;
    private final Clock clock;
    private final Schedular schedular;

    public Simulator(int numProcessors, int totalCycles, String taskFilePath) throws IOException {
        this.numProcessors = numProcessors;
        this.totalCycles = totalCycles;
        this.taskFilePath = taskFilePath;
        this.clock = new Clock();
        List<Task> tasks = Task.readTasks(taskFilePath);


        this.schedular = new Schedular(numProcessors, tasks);
    }

    public void run() {
        for (int cycle = 1; cycle <= totalCycles; cycle++) {
            clock.tick();
            schedular.scheduleTasks(clock.getCycleId(), cycle);
        }
    }
}