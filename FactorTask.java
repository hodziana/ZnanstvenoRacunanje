import java.util.ArrayList;
import java.util.List;

public class FactorTask implements Task {
    private long number;
    private long from, to;

    public FactorTask(long number, long from, long to) {
        this.number = number;
        this.from = from;
        this.to = to;
    }

    @Override
    public Object execute() {
        List<Long> factors = new ArrayList<>();
        for (long i = from; i <= to; i++) {
            if (number % i == 0 && isPrime(i)) {
                factors.add(i);
            }
        }
        return factors;
    }

    private boolean isPrime(long n) {
        if (n <= 1) return false;
        for (long i = 2; i * i <= n; i++) {
            if (n % i == 0) return false;
        }
        return true;
    }
}
