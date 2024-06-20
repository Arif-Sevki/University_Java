public class IfExpression extends Expression {
    Expression condition;
    Expression e1;
    Expression e2;

    public IfExpression(ConditionalExpression condition, Expression e1, Expression e2) {
        this.condition = condition;
        this.e1 = e1;
        this.e2 = e2;
    }

    public IfExpression(LogicalExpression condition, Expression e1, Expression e2) {
        this.condition = condition;
        this.e1 = e1;
        this.e2 = e2;
    }

    public IfExpression(BooleanLiteral condition, Expression e1, Expression e2) {
        this.condition = condition;
        this.e1 = e1;
        this.e2 = e2;
    }

    @Override
    public Expression execute() {
        if (condition.getValue() instanceof Boolean) {
            if ((boolean) condition.getValue()) {
                return e1.execute();
            } else {
                return e2.execute();
            }
        }
        return new ExceptionExpression("IfExpression:execute: Invalid condition");
    }

    @Override
    public Object getValue() {
        return execute().getValue();
    }

    public String toString() {
        return "if (" + condition + ") { " + e1 + " } else { " + e2 + " }";
    }

}
