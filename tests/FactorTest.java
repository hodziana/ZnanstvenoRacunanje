package tests;

import java.util.List;
import tasks.FactorTask;

public class FactorTest {
    public static void main(String[] args) {
        long number = 13195;
        long from = 2;
        long to = (long) Math.sqrt(number);

        FactorTask task = new FactorTask(number, from, to);
        Object result = task.execute();

        if (result instanceof List<?> list) {
            System.out.println("Factors: " + list);
            boolean passed = list.contains(5L) && list.contains(7L)
                    && list.contains(13L) && list.contains(29L)
                    && list.size() == 4;

            if (passed) {
                System.out.println("✅ Test successful!");
            } else {
                System.out.println("❌ Missing factors.");
            }
        } else {
            System.out.println("❌ Wrong result type.");
        }
    }
}
