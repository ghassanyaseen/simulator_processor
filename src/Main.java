import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        if (args.length != 3) {
            throw new IOException("Usage: java Main <num_processors> <total_cycles> <task_file_path>");
        }

        int numProcessors = Integer.parseInt(args[0]);
        int totalCycles = Integer.parseInt(args[1]);
        String taskFilePath = args[2];

        if (numProcessors <= 0) {
            throw new IOException("Wrong number of processors");
        }
        if (totalCycles <= 0) {

            throw new IOException("Wrong number of Cycles");
        }

        try {
            Simulator simulator = new Simulator(numProcessors, totalCycles, taskFilePath);
            simulator.run();
        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
        }
    }
}