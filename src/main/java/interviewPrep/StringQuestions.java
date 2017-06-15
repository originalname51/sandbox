package interviewPrep;

import java.util.Hashtable;

public class StringQuestions {

    StringQuestions() {
    }


    public char nonRepeater(String inputVal) {
        Hashtable<Character, Integer> inputHash = new Hashtable<Character, Integer>();

        for (int i = 0; i < inputVal.length(); i++) {
            if (!inputHash.containsKey(Character.valueOf(inputVal.charAt(i)))) {
                inputHash.put(Character.valueOf(inputVal.charAt(i)), 1);
            } else {
                inputHash.put(Character.valueOf(inputVal.charAt(i)), inputHash.get(Character.valueOf(inputVal.charAt(i))) + 1);
            }
        }

        for (int i = 0; i < inputVal.length(); i++) {

            int number = inputHash.get(Character.valueOf(inputVal.charAt(i)));
            if (number == 1) {
                return inputVal.charAt(i);
            }
        }
        return 'z';
    }

    public String reverseIterative(String inputVal) {
        String returnVal = "";

        for (int i = inputVal.length() - 1; i > -1; i--) {
            returnVal += inputVal.charAt(i);
        }
        return returnVal;
    }


    public String reverseRecursionString(String inputVal) {
        int length = inputVal.length();
        return (length <= 1) ? inputVal : (reverseRecursionString(inputVal.substring(length / 2)) + reverseRecursionString(inputVal.substring(0, length / 2)));
    }

    public boolean anagram(String inputOne, String inputTwo) {
        Hashtable<Character, Integer> inputHash = new Hashtable<Character, Integer>();
        inputOne = inputOne.toLowerCase();
        inputTwo = inputTwo.toLowerCase();
        if (inputOne.length() != inputTwo.length()) {
            return false;
        }
        for (int i = 0; i < inputOne.length(); i++) {
            if (!inputHash.containsKey(Character.valueOf(inputOne.charAt(i)))) {
                inputHash.put(Character.valueOf(inputOne.charAt(i)), 1);
            } else {
                inputHash.put(Character.valueOf(inputOne.charAt(i)), inputHash.get(Character.valueOf(inputOne.charAt(i))) + 1);
            }
        }
        for (int i = 0; i < inputTwo.length(); i++) {
            int number = inputHash.get(Character.valueOf(inputTwo.charAt(i)));
            if (number <= 0) {
                return false;
            } else {
                inputHash.put(Character.valueOf(inputTwo.charAt(i)), inputHash.get(Character.valueOf(inputTwo.charAt(i))) - 1);
            }
        }
        return true;
    }

    /*
     * Initial way I came up with was to go through chararray.
     * */
    public boolean palindrome(String inputVal) {
        for (int i = 0; i < inputVal.length(); i++) {
            if (inputVal.charAt(i) != inputVal.charAt(inputVal.length() - i - 1)) {
                return false;
            }
        }
        return true;
    }

    /*
     * This is a much slicker way. Found on stack overflow
     *
     * http://stackoverflow.com/questions/4138827/check-string-for-palindrome
     * */
    public boolean palindrome_slick(String inputVal) {
        return inputVal.equals(new StringBuilder(inputVal).reverse().toString());
    }

    public boolean uniqueChars(String inputVal) {
        Hashtable<Character, Integer> hashedInputVal = new Hashtable<Character, Integer>();

        for (int i = 0; i < inputVal.length(); i++) {
            if (!hashedInputVal.containsKey(Character.valueOf(inputVal.charAt(i)))) {
                hashedInputVal.put(Character.valueOf(inputVal.charAt(i)), i);
            } else {
                return false;
            }
        }
        return true;
    }

    /*
     * Shortest palindrome is being skipped in favor of longest palindrome using dynamic programming.
     * */
    public int Palindrome(String inputVal) {
        int[][] dynamicPalindrome = new int[inputVal.length()][inputVal.length()];

        for (int i = 0; i < inputVal.length(); i++) {
            dynamicPalindrome[i][i] = 1;
        }
        int testChar;
        for (int i = 2; i <= inputVal.length(); i++) {
            for (int j = 0; j < inputVal.length() - i + 1; j++) {
                testChar = i + j - 1;
                if (inputVal.charAt(j) == inputVal.charAt(testChar) && i == 2) {
                    dynamicPalindrome[j][testChar] = 2;
                } else if (inputVal.charAt(j) == inputVal.charAt(testChar)) {
                    dynamicPalindrome[j][testChar] = dynamicPalindrome[j + 1][testChar - 1] + 2;
                } else {
                    dynamicPalindrome[j][testChar] = Math.max(dynamicPalindrome[j][testChar - 1], dynamicPalindrome[j + 1][testChar]);
                }
            }
        }
        return dynamicPalindrome[0][inputVal.length() - 1];
    }

}
