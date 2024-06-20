public class OrExpression extends LogicalExpression {
    Expression e1;
    Expression e2;

    OrExpression(Expression e1, Expression e2) {
        this.e1 = e1;
        this.e2 = e2;
    }

    @Override
    public Expression execute() {
        Object value1 = e1.getValue();
        Object value2 = e2.getValue();

        AndExpression areBothBoolean = new AndExpression(new BooleanLiteral(value1 instanceof Boolean),
                new BooleanLiteral(value2 instanceof Boolean));

        AndExpression areBothFalse = new AndExpression(new BooleanLiteral((boolean) value1 == false),
                new BooleanLiteral((boolean) value2 == false));

        if ((boolean) (areBothBoolean.getValue())) {
            if ((boolean) (areBothFalse.getValue())) {
                return BooleanLiteral.create(false);
            }
            return BooleanLiteral.create(true);
        }
        return new ExceptionExpression("OrExpression:execute: At least one of the values is not boolean");
    }

    @Override
    Object getValue() {
        return execute().getValue();
    }

    public String toString() {
        return e1 + " | " + e2;
    }
}
