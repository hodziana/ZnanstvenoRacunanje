package tests;

import tasks.ZeroTask;
import functions.CubicFunction;

public class ZeroTest {
    public static void main(String[] args) {
        ZeroTask task = new ZeroTask(1.0, 2.0, 1e-6, new CubicFunction());
        Object result = task.execute();

        if (result instanceof Double x) {
            System.out.println("Zero of a function: " + x);
            if (Math.abs(x - 1.52138) < 1e-4) {
                System.out.println("✅ Test successful!");
            } else {
                System.out.println("❌ Unexpected value.");
            }
        } else {
            System.out.println("❌ No result.");
        }
    }
}
