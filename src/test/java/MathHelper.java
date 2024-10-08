public class MathHelper
{
    public int simple_int = 6;
    public static int static_int = 7;


    public int calc(int a, int b, char action) {
        if(action == '+') {
            return this.plus(a,b);
        } else if(action == '-') {
            return this.minus(a,b);
        } else if(action == '*') {
            return this.multiply(a,b);
        } else if(action == '/') {
            return this.divide(a,b);
        } else {
            return this.typeAnErrorAndReturnDefaultValue("Wrong action: " + action);
        }
    }

private int typeAnErrorAndReturnDefaultValue(String error_message) {
        System.out.println(error_message);
        return 0;
}

private int plus(int c, int d) {
     return c + d;
}

private int minus(int a, int b) {
    return a - b;
}

private int multiply(int a, int b) {
    return a * b;
}

private int divide(int number, int divider) {
    if(divider == 0) {
        return this.typeAnErrorAndReturnDefaultValue("Cannot divide by zero");
    } else {
        return number / divider;
    }
}

}
