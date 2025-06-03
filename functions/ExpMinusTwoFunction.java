package functions;

public class ExpMinusTwoFunction implements MathFunction {
    @Override
    public double evaluate(double x) { return Math.exp(x) - 2; }
    @Override public String toString() { return "e^x - 2"; }
}
