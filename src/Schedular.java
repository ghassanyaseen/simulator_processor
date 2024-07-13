import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

public class Schedular {
    private final List<Processor> processors;
    private final PriorityQueue<Task> taskQueue;
    private final List<Task> tasks;

    public Schedular(int numProcessors, List<Task> tasks) {
        this.processors = new ArrayList<>();
        for (int i = 0; i < numProcessors; i++) {
            processors.add(new Processor("P" + (i + 1)));
        }
        this.tasks = new ArrayList<>(tasks);
        this.taskQueue = new PriorityQueue<>();
    }

    public void scheduleTasks(String cycleId, int currentCycle) {
        System.out.println("--- Cycle " + cycleId + " ---");

        // Add newly created tasks to the task queue
        while (!tasks.isEmpty() && tasks.get(0).getCreationTime() == currentCycle) {
            Task newTask = tasks.remove(0);
            taskQueue.add(newTask);
            System.out.println("Task " + newTask.getTaskId() + " created at cycle " + cycleId + " with priority " + newTask.getPriority());
        }

        // Assign tasks to available processors
        for (Processor processor : processors) {
            if ((processor.isIdle() && !taskQueue.isEmpty())) {
                Task nextTask = taskQueue.poll();
                processor.setCurrentTask(nextTask);
                System.out.println("Task " + nextTask.getTaskId() + " assigned to " + processor.getProcessorId());
            }
        }

        // Update processor states and complete tasks
        for (Processor processor : processors) {
            if (processor.getCurrentTask() != null) {
                processor.dectimeRemaining();
                if (processor.getTimeRemaining() == 0) {
                    System.out.println("Task " + processor.getCurrentTask().getTaskId() + " completed by " + processor.getProcessorId() + " in this Clock ");
                    processor.setCurrentTask(null);
                }
            }
        }

        System.out.println();
        System.out.println("What happened at the end of the Clock:");
        // Print processor states
        for (Processor processor : processors) {
            if (processor.getCurrentTask() != null) {
                System.out.println(processor.getProcessorId() + " is running " + processor.getCurrentTask().getTaskId() + ", remaining time: " + processor.getTimeRemaining());
            } else {
                System.out.println(processor.getProcessorId() + " is idle");
            }
        }
        System.out.println();
    }
}
