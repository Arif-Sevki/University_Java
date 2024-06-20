public class Functions {

    public static Expression absolute(Expression e) {
        Object value = e.getValue();

        if (value instanceof Integer) {
            ConditionalExpression isNegative = new ConditionalExpression(e, new IntegerLiteral(0),
                    ConditionalOperator.Less);
            IfExpression ifNegative = new IfExpression(isNegative,
                    new IntegerLiteral((int) (new NegateExpression(e)).getValue()), // if true
                    new IntegerLiteral((int) value)); // else
            return ifNegative.execute();

        }
        if (value instanceof Double) {
            ConditionalExpression isNegative = new ConditionalExpression(e, new DoubleLiteral(0),
                    ConditionalOperator.Less);
            IfExpression ifNegative = new IfExpression(isNegative,
                    new DoubleLiteral((double) (new NegateExpression(e)).getValue()), // if true
                    new DoubleLiteral((double) value)); // else
            return ifNegative.execute();
        }

        return new ExceptionExpression("Invalid type for absolute value");

        /*
         * if ((boolean) isNegative.getValue()) {
         * return new IntegerLiteral((int) (new NegateExpression(e)).getValue());
         * }
         * return new IntegerLiteral((int) value);
         * } else if (value instanceof Double) {
         * if ((double) value < 0) {
         * return new DoubleLiteral(-1 * (double) value);
         * }
         * return new DoubleLiteral((double) value);
         * }
         * throw new RuntimeException("Invalid type for absolute value");
         */

    }

    public static Expression power(IntegerLiteral base, IntegerLiteral pow) {
        IntegerLiteral zero = new IntegerLiteral(0);
        ConditionalExpression isPowerNonPos = new ConditionalExpression(pow, zero, ConditionalOperator.LessEqual);
        ConditionalExpression isBaseZero = new ConditionalExpression(base, zero, ConditionalOperator.Equal);

        AndExpression undefined = new AndExpression(isPowerNonPos, isBaseZero);
        try {
            IfExpression check = new IfExpression(undefined,
                    null, power1(base, pow));
            return check.execute();
        } catch (NullPointerException e) {
            throw new RuntimeException("0^0 or 0^negative is undefined!!!!");
        }

    }

    public static Expression power(DoubleLiteral base, IntegerLiteral pow) {
        IntegerLiteral zero = new IntegerLiteral(0);
        ConditionalExpression isPowerNonPos = new ConditionalExpression(pow, zero, ConditionalOperator.LessEqual);
        ConditionalExpression isBaseZero = new ConditionalExpression(base, zero, ConditionalOperator.Equal);

        AndExpression undefined = new AndExpression(isPowerNonPos, isBaseZero);
        try {
            IfExpression check = new IfExpression(undefined,
                    null, power1(base, pow));
            return check.execute();
        } catch (NullPointerException e) {
            throw new RuntimeException("0^0 or 0^negative is undefined!!!");
        }
    }

    public static Expression power1(IntegerLiteral base, IntegerLiteral pow) {

        IntegerLiteral zero = new IntegerLiteral(0);
        ConditionalExpression isPowerZero = new ConditionalExpression(pow, zero, ConditionalOperator.Equal);

        ConditionalExpression isPowerPositive = new ConditionalExpression(pow, zero, ConditionalOperator.Greater);
        ConditionalExpression isPowerNegative = new ConditionalExpression(pow, zero, ConditionalOperator.Less);

        if ((boolean) isPowerZero.getValue()) {
            return new IntegerLiteral(1);
        } else if ((boolean) isPowerPositive.getValue()) {
            return new MultiplyExpression(base, power1(base, new IntegerLiteral((int) (pow.getValue()) - 1)));
        } else if ((boolean) isPowerNegative.getValue()) {
            double d = (double) ((Integer) base.getValue()).intValue(); // Convert Integer to double
            DoubleLiteral base2 = new DoubleLiteral(d);
            return power1(base2, pow);
        }

        return new ExceptionExpression("power: invalid type");
    }

    public static Expression power1(DoubleLiteral base, IntegerLiteral pow) {
        DoubleLiteral zero = new DoubleLiteral(0);
        ConditionalExpression isPowerZero = new ConditionalExpression(pow, zero, ConditionalOperator.Equal);
        ConditionalExpression isPowerPositive = new ConditionalExpression(pow, zero, ConditionalOperator.Greater);
        ConditionalExpression isPowerNegative = new ConditionalExpression(pow, zero, ConditionalOperator.Less);

        if ((boolean) isPowerZero.getValue()) {
            return new DoubleLiteral(1);
        } else if ((boolean) isPowerPositive.getValue()) {
            return new MultiplyExpression(base, power1(base, new IntegerLiteral((int) (pow.getValue()) - 1)));
        } else if ((boolean) isPowerNegative.getValue()) {
            DoubleLiteral base2 = new DoubleLiteral(1 / ((double) base.getValue()));
            return power1(base2,
                    new IntegerLiteral((int) ((new MultiplyExpression(pow, new IntegerLiteral(-1))).getValue())));
        }

        return new ExceptionExpression("power: invalid type");
    }

    public static Expression factorial(IntegerLiteral n) {
        IntegerLiteral zero = new IntegerLiteral(0);
        ConditionalExpression isNZero = new ConditionalExpression(n, zero, ConditionalOperator.Equal);
        ConditionalExpression isNPositive = new ConditionalExpression(n, zero, ConditionalOperator.Greater);

        if ((boolean) isNZero.getValue()) {
            return new IntegerLiteral(1);
        } else if ((boolean) isNPositive.getValue()) {
            return new MultiplyExpression(n, factorial(new IntegerLiteral((int) (n.getValue()) - 1)));
        }
        return new ExceptionExpression("factorial: invalid type");

    }

}
