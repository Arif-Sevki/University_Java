public class NegateExpression extends ArithmeticExpression {
    Expression e1;

    NegateExpression(Expression e1) {
        this.e1 = e1;
    }

    Expression execute() {
        Object value = e1.getValue();
        if (value instanceof Double) {
            return DoubleLiteral.create(-1 * (double) e1.execute().getValue());
        } else {
            return IntegerLiteral.create(-1 * (int) e1.execute().getValue());
        }
    }

    @Override
    Object getValue() {
        return execute().getValue();
    }

    public String toString() {
        return "-" + e1;
    }
}
