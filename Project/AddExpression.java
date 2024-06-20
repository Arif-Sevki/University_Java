public class AddExpression extends ArithmeticExpression {
    Expression e1;
    Expression e2;

    AddExpression(Expression e1, Expression e2) {
        this.e1 = e1;
        this.e2 = e2;
    }

    @Override
    public Expression execute() {
        Object value1 = e1.getValue();
        Object value2 = e2.getValue();

        OrExpression areAnyString = new OrExpression(new BooleanLiteral(value1 instanceof String),
                new BooleanLiteral(value2 instanceof String));

        OrExpression areAnyDouble = new OrExpression(new BooleanLiteral(value1 instanceof Double),
                new BooleanLiteral(value2 instanceof Double));

        if ((boolean) (areAnyString.getValue())) {
            return StringLiteral.create(String.valueOf(value1) + String.valueOf(value2));
        } else if ((boolean) (areAnyDouble.getValue())) {
            double result = ((Number) value1).doubleValue() + ((Number) value2).doubleValue();
            return DoubleLiteral.create(result);
        } else {
            return IntegerLiteral.create((Integer) value1 + (Integer) value2);
        }

    }

    @Override
    Object getValue() {
        return execute().getValue();
    }

    public String toString() {
        return e1 + " + " + e2;
    }
}