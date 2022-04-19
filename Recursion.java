import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * The Recursion program implements an application that
 * uses a recursive function to print an hourglass pattern
 * of asterisks based on the number entered by the user.
 *
 * @author  Ina Tolo
 * @version 1.0
 * @since   2022-4-11
 */

class Recursion {
    /**
     * Declaring constant for console hourglassFormatting.
     */
    private static final String CONSOLE_SEPARATOR =
        "--------------------------";
    /**
     * Declaring constant for counter multiplier.
     */
    private static final int COUNT_MULT = 4;

    /**
     * Uses recursion to display hourglass pattern of stars.
     *
     * @param userNum is accepted
     * @param space is accepted
     */
    public static void recursiveStar(int userNum, int space) {
        System.out.println(hourglassFormat(userNum, space));

        if (userNum > 1) {
            recursiveStar(userNum - 1, space + 1);
            System.out.println(hourglassFormat(userNum, space));
        } else if (userNum == 1) {
            System.out.println(hourglassFormat(userNum, space));
        }
    }

    /**
     * Configures the format for the hourglass pattern.
     *
     * @param userNum is accepted
     * @param space is accepted
     * @return appropriate string
     */
    public static String hourglassFormat(int userNum, int space) {
        return " ".repeat(space) + "* ".repeat(userNum);
    }

    /**
     * Main entry into program.
     *
     * @param args nothing passed in
     */
    public static void main(String[] args) {
        // declaring variables
        final Scanner sc = new Scanner(System.in);
        final String userNumString;
        int userNumInt;
        String starPatternUser = "";
        final List<String> starList = new ArrayList<String>();
        final String[] starArray;
        final BufferedWriter writer;
        final StringBuilder builder;
        int zero = 0;
        int newNum = 1;

        System.out.println("This program will create"
            + "an hourglass pattern of stars based"
            + "on the entered integer.\n");

        // gets input from user
        System.out.print("Enter a positive integer: ");
        userNumString = sc.nextLine();

        try {
            userNumInt = Integer.parseInt(userNumString);

            if (userNumInt > 0) {

                final int temp = userNumInt;

                // displays message that alerts user file is done being altered
                System.out.println("\nDone forming star pattern."
                    + " Look below and check the output file.");
                System.out.println(CONSOLE_SEPARATOR);

                recursiveStar(userNumInt, 0);

                try {

                    // adds top half of hourglass to list
                    for (int topCounter = 0; topCounter
                        <= userNumInt * COUNT_MULT; topCounter++) {
                        starPatternUser =
                            hourglassFormat(userNumInt, zero);
                        starList.add(starPatternUser);
                        userNumInt--;
                        zero++;
                    }

                    // adds bottom half of hourglass to list
                    for (int bottomCounter = 0; bottomCounter
                        < temp; bottomCounter++) {
                        zero--;
                        starPatternUser = hourglassFormat(newNum, zero);
                        starList.add(starPatternUser);
                        newNum++;
                    }

                    // converts list of altered strings to an array
                    starArray = starList.toArray(new String[0]);

                    // adds the hourglass string to the output file
                    builder = new StringBuilder();

                    for (int hourglassFormatCounter = 0; hourglassFormatCounter
                        < starArray.length; ++hourglassFormatCounter) {

                        builder.append(starArray[hourglassFormatCounter]);
                        if (hourglassFormatCounter != starArray.length - 1) {
                            builder.append("\n");
                        }
                    }

                    writer =
                        new BufferedWriter(new FileWriter(
                            "/home/ubuntu/environment"
                            + "/Assign/Assign-03/Assign-03"
                            + "-Java/output.txt"));
                    writer.write(builder.toString());
                    writer.close();

                    System.out.println(CONSOLE_SEPARATOR);
                } catch (IOException exception) {
                    exception.printStackTrace();
                }

            } else {
                // catches negative numbers
                System.out.println("Value must be greater than 0.");
            }
        } catch (IllegalArgumentException exception) {
            // catches invalid strings
            System.out.println("Value is invalid!");
        }
    }
}
