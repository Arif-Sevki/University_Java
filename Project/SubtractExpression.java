public class SubtractExpression extends ArithmeticExpression {
    Expression e1;
    Expression e2;

    SubtractExpression(Expression e1, Expression e2) {
        this.e1 = e1;
        this.e2 = e2;
    }

    @Override
    public Expression execute() {
        Object value1 = e1.getValue();
        Object value2 = e2.getValue();

        OrExpression areAnyDouble = new OrExpression(new BooleanLiteral(value1 instanceof Double),
                new BooleanLiteral(value2 instanceof Double));

        if ((boolean) (areAnyDouble.getValue())) {
            double result = ((Number) value1).doubleValue() - ((Number) value2).doubleValue();
            return DoubleLiteral.create(result);
        } else {
            return IntegerLiteral.create((Integer) value1 - (Integer) value2);
        }

    }

    @Override
    Object getValue() {
        return execute().getValue();
    }
}