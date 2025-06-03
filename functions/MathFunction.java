package functions;

import java.io.Serializable;

@FunctionalInterface
public interface MathFunction extends Serializable {
    double evaluate(double x);
}
