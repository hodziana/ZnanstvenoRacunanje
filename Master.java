import java.util.*;
import functions.MathFunction;
import tasks.Task;
import tasks.FactorTask;
import tasks.ZeroTask;

public class Master {
    public static void main(String[] args) throws Exception {
        if (args.length < 2) {
            System.out.println("Usage:");
            System.out.println("  java Master zero <N> <a> <b> <epsilon>");
            System.out.println("  java Master factor <N> <number>");
            return;
        }

        String mode = args[0];
        int N = Integer.parseInt(args[1]);
        Linker linker = new Linker(0, N);

        if (mode.equals("zero")) {
            if (args.length != 6) {
                System.out.println("Expected: zero <N> <a> <b> <epsilon> [sin|exp|cubic]");
                return;
            }

            System.out.println("Running in ZERO mode...");

            double a = Double.parseDouble(args[2]);
            double b = Double.parseDouble(args[3]);
            double epsilon = Double.parseDouble(args[4]);
            String funcName = args[5];

            MathFunction f;
            switch (funcName.toLowerCase()) {
                case "sin":    f = new functions.SinFunction();        break;
                case "exp":    f = new functions.ExpMinusTwoFunction();  break;
                case "cubic":
                default:       f = new functions.CubicFunction();      break;
            }
            int workers = N - 1;

            System.out.println("Interval: [" + a + ", " + b + "]");
            System.out.println("Epsilon: " + epsilon);
            System.out.println("Total workers: " + workers);

            double step = (b - a) / workers;

            List<Task> tasks = new ArrayList<>();
            for (int i = 0; i < workers; i++) {
                double start = a + i * step;
                double end = start + step;
                System.out.printf("Created task %d: [%.6f, %.6f]%n", i + 1, start, end);
                tasks.add(new ZeroTask(start, end, epsilon, f));
            }

            for (int i = 0; i < workers; i++) {
                System.out.println("Sending task to worker " + (i + 1));
                linker.sendMessage(i + 1, "task", tasks.get(i));
            }

            for (int i = 0; i < workers; i++) {
                System.out.println("Waiting result from worker " + (i + 1));
                Object result = linker.receiveMessage(i + 1, "result");
                System.out.println("Result from worker " + (i + 1) + ": " + result);
            }
        }


        else if (mode.equals("factor")) {
            if (args.length != 3) {
                System.out.println("Expected: factor <N> <number>");
                return;
            }

            long numberToFactor = Long.parseLong(args[2]);
            long sqrt = (long) Math.sqrt(numberToFactor);
            int workers = N - 1;
            long step = sqrt / workers;

            List<Task> tasks = new ArrayList<>();
            for (int i = 0; i < workers; i++) {
                long from = 2 + i * step;
                long to = (i == workers - 1) ? sqrt : from + step - 1;
                tasks.add(new FactorTask(numberToFactor, from, to));
            }

            for (int i = 0; i < workers; i++) {
                linker.sendMessage(i + 1, "task", tasks.get(i));
            }

            List<Long> allFactors = new ArrayList<>();
            for (int i = 0; i < workers; i++) {
                Object result = linker.receiveMessage(i + 1, "result");
                if (result instanceof List<?> list) {
                    for (Object o : list) {
                        if (o instanceof Long l) {
                            allFactors.add(l);
                        }
                    }
                }
            }

            System.out.println("Prime factors: " + allFactors);
        }

        for (int i = 1; i < N; i++) {
            linker.sendMessage(i, "task", "terminate");
        }
    }
}
