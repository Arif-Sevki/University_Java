public class NotExpression extends LogicalExpression {
    Expression e1;

    NotExpression(Expression e1) {
        this.e1 = e1;
    }

    @Override
    public Expression execute() {
        Object value = e1.getValue();
        if (value instanceof Boolean) {
            if ((boolean) value == true) {
                return BooleanLiteral.create(false);
            }
            return BooleanLiteral.create(true);
        }
        return new ExceptionExpression("NotExpression:execute: value is not boolean");
    }

    @Override
    Object getValue() {
        return execute().getValue();
    }

    public String toString() {
        return "!" + e1;
    }

}
