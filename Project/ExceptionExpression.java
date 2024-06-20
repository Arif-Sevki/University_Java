public class ExceptionExpression extends Expression {
    String message;

    public ExceptionExpression(String message) {
        throw new RuntimeException(message);
    }

    @Override
    public Expression execute() {
        return new ExceptionExpression(message);
    }

    @Override
    Object getValue() {
        return execute().getValue();
    }

}
