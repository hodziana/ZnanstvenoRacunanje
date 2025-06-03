package functions;

public class CubicFunction implements MathFunction {
    @Override
    public double evaluate(double x) { return x * x * x - x - 2; }
    @Override public String toString() { return "x^3 - x - 2"; }
}
