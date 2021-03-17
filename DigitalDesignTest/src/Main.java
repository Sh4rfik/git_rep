import java.util.Arrays;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        System.out.print("Enter a string to unpack : ");
        Scanner scanner = new Scanner(System.in);
        String s = scanner.nextLine();

        System.out.println("Specified string: " + s);
        if (stringIsValid(s))
            System.out.println("Unpacked string: " + unpackingString(s));
        else
            System.out.println("Not valid string");
    }

    public static String unpackingString(String str) {

        String unpackedString = "";
        int countOfBrackets = 0;
        int indexOfOpeningSquareBracket = 0;
        String number = "";

        char[] ch = new char[str.length()];
        for (int i = 0; i < str.length(); i++) {
            ch[i] = str.charAt(i);
        }

        for (int i = 0; i < ch.length; i++) {
            if (Character.isLetter(ch[i]) && countOfBrackets == 0)
                unpackedString += ch[i];
            else if (Character.isDigit(ch[i]) && countOfBrackets == 0) {
                number += ch[i];
            } else if (ch[i] == '[') {
                countOfBrackets++;
                if (countOfBrackets == 1) {
                    indexOfOpeningSquareBracket = i;
                }
            } else if (ch[i] == ']') {
                countOfBrackets--;
                if (countOfBrackets == 0) {
                    unpackedString += unpackingString(String
                            .valueOf(Arrays.copyOfRange(ch, indexOfOpeningSquareBracket + 1, i))
                            .repeat(Integer.parseInt(String.valueOf(number))));
                    indexOfOpeningSquareBracket = 0;
                    number = "";
                }
            }
        }
        return unpackedString;
    }

    public static boolean stringIsValid(String str) {

        int countOfBrackets = 0;

        for (int i = 0; i < str.length(); i++) {
            if (str.toCharArray()[i] == '[') {
                countOfBrackets++;
            }
            if (str.toCharArray()[i] == ']') {
                countOfBrackets--;
                if (countOfBrackets < 0) {
                    return false;
                }
            }
        }
        if (countOfBrackets != 0) {
            return false;
        }
        return str.matches("[a-zA-Z\\[\\]0-9]+");
    }
}
