public class ZeroTask implements Task {
    private double a, b, epsilon;

    public ZeroTask(double a, double b, double epsilon) {
        this.a = a;
        this.b = b;
        this.epsilon = epsilon;
    }

    private double f(double x) {
        return x * x * x - x - 2;
    }

    public Object execute() {
        double left = a, right = b, mid;
        while ((right - left) > epsilon) {
            mid = (left + right) / 2;
            if (f(left) * f(mid) <= 0)
                right = mid;
            else
                left = mid;
        }
        return (left + right) / 2;
    }
}
