public class DivisionExpression extends ArithmeticExpression {
    Expression e1;
    Expression e2;

    public DivisionExpression(Expression e1, Expression e2) {

        if ((e1 == null) | (e2 == null)) {
            throw new IllegalArgumentException("DivisionExpression: e1 and e2 must not be null.");
        }
        this.e1 = e1;
        this.e2 = e2;
    }

    @Override
    public Expression execute() {
        Object value1 = e1.getValue();
        Object value2 = e2.getValue();

        double dividend = ((Number) value1).doubleValue();
        double divisor = ((Number) value2).doubleValue();

        if (divisor == 0) {
            return new ExceptionExpression("DivisionExpression: Division by zero.");
        }

        OrExpression areAnyDouble = new OrExpression(new BooleanLiteral(value1 instanceof Double),
                new BooleanLiteral(value2 instanceof Double));

        IfExpression ifAnyDouble = new IfExpression(areAnyDouble,
                new DoubleLiteral(dividend / divisor),
                new IntegerLiteral((int) (dividend / divisor)));
        return ifAnyDouble.execute();
        /*
         * if ((boolean) (areAnyDouble.getValue())) {
         * return DoubleLiteral.create(dividend / divisor);
         * } else {
         * return IntegerLiteral.create((int) (dividend / divisor));
         * }
         */
    }

    @Override
    Object getValue() {
        return execute().getValue();
    }

    public String toString() {
        return e1 + " / " + e2;
    }
}