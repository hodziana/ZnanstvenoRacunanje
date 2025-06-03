package functions;

public class SinFunction implements MathFunction {
    @Override
    public double evaluate(double x) { return Math.sin(x); }
    @Override public String toString() { return "sin(x)"; }
}