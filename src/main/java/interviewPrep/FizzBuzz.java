package interviewPrep;

public class FizzBuzz {

    public static void main(String[] args) {
        FizzBuzz.recursiveFizzBuzz(100);
    }

    private static void recursiveFizzBuzz(int number) {
        if (number > 0) {
            recursiveFizzBuzz(number - 1);
            if (number % 15 == 0) {
                System.out.println("FizzBuzz " + number);
            } else if (number % 3 == 0) {
                System.out.println("Fizz " + number);
            } else if (number % 5 == 0) {
                System.out.println("Buzz " + number);
            } else {
                System.out.println(number);
            }

        }

    }
}
