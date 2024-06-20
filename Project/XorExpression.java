public class XorExpression extends LogicalExpression {
    Expression e1;
    Expression e2;

    XorExpression(Expression e1, Expression e2) {
        this.e1 = e1;
        this.e2 = e2;
    }

    @Override
    public Expression execute() {
        Object value1 = e1.getValue();
        Object value2 = e2.getValue();

        AndExpression areBothBoolean = new AndExpression(new BooleanLiteral(value1 instanceof Boolean),
                new BooleanLiteral(value2 instanceof Boolean));

        ConditionalOperator equalOp = ConditionalOperator.Equal;

        ConditionalExpression areTheySame = new ConditionalExpression(e1, e2, equalOp);

        if ((boolean) (areBothBoolean.getValue())) {
            if ((boolean) (areTheySame.getValue())) {
                return BooleanLiteral.create(false);
            }
            return BooleanLiteral.create(true);
        }
        throw new RuntimeException("XorExpression:execute: At least one of the values is not boolean");
    }

    @Override
    Object getValue() {
        return execute().getValue();
    }

}
