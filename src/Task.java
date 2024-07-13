import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Task implements Comparable<Task>, ITask {
    private final int creationTime;
    private final int executionTime;
    private final int priority;
    private final String taskId;

    public Task(int creationTime, int executionTime, int priority, String taskId) {
        this.creationTime = creationTime;
        this.executionTime = executionTime;
        this.priority = priority;
        this.taskId = taskId;
    }


    @Override
    public int getCreationTime() {
        return creationTime;
    }

    @Override
    public int getExecutionTime() {
        return executionTime;
    }

    @Override
    public int getPriority() {
        return priority;
    }

    @Override
    public String getTaskId() {
        return taskId;
    }

    @Override
    public int compareTo(Task other) {
        if (this.priority != other.priority) {
            return other.priority - this.priority;
        }
        if (this.executionTime != other.executionTime) {
            return other.executionTime - this.executionTime;
        }
        return this.taskId.compareTo(other.taskId);
    }


    public static List<Task> readTasks(String filePath) throws IOException {
        List<Task> tasks = new ArrayList<>();
        BufferedReader reader = new BufferedReader(new FileReader(filePath));
        String line;
        int taskId = 1;
        int previousCreationTime = -1; // Initialize with an invalid creation time

        while ((line = reader.readLine()) != null) {
            String[] parts = line.split("\\s+");
            int creationTime = Integer.parseInt(parts[0]);
            int executionTime = Integer.parseInt(parts[1]);
            int priority = Integer.parseInt(parts[2]);

            // Check if the current creation time is in order
            if (creationTime < previousCreationTime) {
                reader.close();
                throw new IOException("Error: Tasks are not in the correct order of creation time.");
            }
            previousCreationTime = creationTime;

            tasks.add(new Task(creationTime, executionTime, priority, "T" + taskId++));
        }

        reader.close();
        return tasks;
    }
}