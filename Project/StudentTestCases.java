
public class StudentTestCases {

	private StudentTestCases() {
	}

	static void testCase1() {
		System.out.println("Student Test Case 1:");
		System.out.println("--------------------");

		System.out.print("Absolute value of -5 = ");
		System.out.println((Functions.absolute(new IntegerLiteral(-5)).getValue()));
		System.out.print("Absolute value of -10.0 = ");
		System.out.println((Functions.absolute(new DoubleLiteral(-10.0)).getValue()));
		System.out.print("Absolute value of 100.0 = ");
		System.out.println((Functions.absolute(new DoubleLiteral(100.0)).getValue()));
		System.out.print("Absolute value of 0 = ");
		System.out.println((Functions.absolute(new IntegerLiteral(0)).getValue()));
		System.out.println();

	}

	static void testCase2() {
		System.out.println("Student Test Case 2:");
		System.out.println("--------------------");

		System.out.println("2^5 = " + (Functions.power(new IntegerLiteral(2), new IntegerLiteral(5)).getValue()));
		System.out.println(
				"2^(-5) = " + (Functions.power(new DoubleLiteral(2), new IntegerLiteral(-5)).getValue()));
		System.out
				.println("0^30 = " + (Functions.power(new IntegerLiteral(0), new IntegerLiteral(30)).getValue()));
		System.out.println(
				"0^1 = " + (Functions.power(new DoubleLiteral(0), new IntegerLiteral(1)).getValue()));
		System.out.println();

	}

	static void testCase3() {
		System.out.println("Student Test Case 3:");
		System.out.println("--------------------");

		System.out.println("Factorial of 5 = " + (Functions.factorial(new IntegerLiteral(5)).getValue()));
		System.out.println("Factorial of 0 = " + (Functions.factorial(new IntegerLiteral(0)).getValue()));
		System.out.println();
	}

	static void testCase4() {
		System.out.println("Student Test Case 4:");
		System.out.println("--------------------");

		IfExpression wololo = new IfExpression(new BooleanLiteral(false), new IntegerLiteral(1), new IntegerLiteral(2));
		System.out.print(wololo + " = ");
		System.out.println(wololo.getValue());

		IfExpression mySuperDuperIfExpression = new IfExpression(new OrExpression(new BooleanLiteral(false),
				new BooleanLiteral(true)),
				new IntegerLiteral(1), new IntegerLiteral(0));

		System.out.println(mySuperDuperIfExpression + " = " + mySuperDuperIfExpression.getValue());
	}

}
