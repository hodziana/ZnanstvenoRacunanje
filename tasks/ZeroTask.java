package tasks;

import java.util.Objects;
import functions.MathFunction;

public class ZeroTask implements Task {
    private final double a, b, epsilon;
    private final MathFunction f;

    public ZeroTask(double a, double b, double epsilon, MathFunction f) {
        this.a = a;  this.b = b;  this.epsilon = epsilon;
        this.f = Objects.requireNonNull(f);
    }

    @Override
    public Object execute() {
        if (f.evaluate(a) * f.evaluate(b) > 0) {
            System.out.println("No sign change in interval [" + a + ", " + b + "].");
            return null;
        }

        double left = a, right = b, mid;

        while ((right - left) > epsilon) {
            mid = (left + right) / 2;
            if (f.evaluate(left) * f.evaluate(mid) <= 0) right = mid;
            else left = mid;
        }
        return (left + right) / 2;
    }
}
