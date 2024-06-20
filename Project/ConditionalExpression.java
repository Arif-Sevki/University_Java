public class ConditionalExpression extends Expression {
    Expression e1;
    Expression e2;
    ConditionalOperator op;

    public ConditionalExpression(Expression e1, Expression e2, ConditionalOperator op) {
        this.e1 = e1;
        this.e2 = e2;
        this.op = op;
    }

    @Override
    public Expression execute() {
        Object value1 = e1.getValue();
        Object value2 = e2.getValue();

        AndExpression areBothNumber = new AndExpression(new BooleanLiteral(value1 instanceof Number),
                new BooleanLiteral(value2 instanceof Number));

        AndExpression areBothBoolean = new AndExpression(new BooleanLiteral(value1 instanceof Boolean),
                new BooleanLiteral(value2 instanceof Boolean));

        if ((boolean) (areBothNumber.getValue())) {
            double v1 = ((Number) value1).doubleValue();
            double v2 = ((Number) value2).doubleValue();

            switch (op) {
                case Equal:
                    return BooleanLiteral.create(v1 == v2);
                case NotEqual:
                    return BooleanLiteral.create(v1 != v2);
                case Less:
                    return BooleanLiteral.create(v1 < v2);
                case LessEqual:
                    return BooleanLiteral.create(v1 <= v2);
                case Greater:
                    return BooleanLiteral.create(v1 > v2);
                case GreaterEqual:
                    return BooleanLiteral.create(v1 >= v2);
            }
        } else if ((boolean) (areBothBoolean.getValue())) {

            switch (op) {
                case Equal:
                    return BooleanLiteral.create((boolean) value1 == (boolean) value2);
                case NotEqual:
                    return BooleanLiteral.create((boolean) value1 != (boolean) value2);
                default:
                    throw new RuntimeException("ConditionalExpression:execute: Invalid operator");

            }
        }

        throw new RuntimeException("ConditionalExpression:execute: Invalid operator");

    }

    @Override
    public Object getValue() {
        return execute().getValue();
    }

    public String toString() {
        return e1 + " " + op + " " + e2;
    }
}
