public class AndExpression extends LogicalExpression {
    Expression e1;
    Expression e2;

    AndExpression(Expression e1, Expression e2) {
        this.e1 = e1;
        this.e2 = e2;
    }

    @Override
    public Expression execute() {
        Object value1 = e1.getValue();
        Object value2 = e2.getValue();

        if (value1 instanceof Boolean & value2 instanceof Boolean) {
            if ((boolean) value1 == true & (boolean) value2 == true) {
                return BooleanLiteral.create(true);
            }
            return BooleanLiteral.create(false);
        }
        return new ExceptionExpression("AndExpression:execute: At least one of the values is not boolean!!!");
        // throw new RuntimeException("AndExpression:execute: At least one of the values
        // is not boolean");
    }

    @Override
    Object getValue() {
        return execute().getValue();
    }

    public String toString() {
        return e1 + " & " + e2;
    }
}
