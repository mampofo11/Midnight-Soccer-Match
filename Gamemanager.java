import java.util.*;

public class Gamemanager {
    // Define the size of the console output we are going to work with.
    // Should really be private and have a way to set it if needed.
    public static int DISPLAY_WIDTH = 100;

    // Just a line of dashes that goes across our DISPLAY_WIDTH
    // Stored here so we don't have to create it any time we want to use it.
    public static String lineBreak = "";

    // ArrayList to store all of the verbs that the game knows about.
    // (This is a parallel array with the verbDescription arraylist)
    // These are added to using the addVerb(String, String) method.
    private List<String> verbs = new ArrayList<String>();

    // ArrayList of the descriptions for the verbs the game knows.
    // (This is a parallel array with the verbs arraylist)
    // These are added to using the addVerb(String, String) method.
    private List<String> verbDescription = new ArrayList<String>();

    // ArratList to store all of the nouns the game knows about.
    // These are added to using the addNoun(String) method.
    private List<String> nouns = new ArrayList<String>();

    /*******************************************************************************
     * - - - - - - - - - - - - Constructor - - - - - - - - - - - - - - - - - - - - -
     *******************************************************************************/
    public Gamemanager() {
        createHorzontalRule(); // for formatting.
    }

    /*******************************************************************************
     * - - - - - - - - - - - - Public Methods - - - - - - - - - - - - - - - - - - -
     *******************************************************************************/
    // command format should be verb, noun, noun2
    public boolean validCommand(String[] command) {
        if (command.length < 1 || command.length > 3) {
            print("Unknown command. Type ? for help.");

        } else if (command[0].equalsIgnoreCase("quit")) {
            print("The game should end here. -- Find this line and make your changes to do it!\nStopping the gameRunning loop is the best way!");

        } else {

            // Check verb
            if (verbs.contains(command[0]) == false) {
                reportUnknownCommand(command[0]);
                return false;
            }
            // Check both nouns (start at index 1 because 0 is the verb)
            for (int i = 1; i < command.length; i++) {
                if (nouns.contains(command[i]) == false) {
                    reportUnknownCommand(command[i]);
                    return false;
                }
            }
        }
        return true; // we got here so it must be okay.
    }

    // Adds a noun to the noun list
    // Lets the game know this is something you an interact with.
    public void addNoun(String string) {
        if (!nouns.contains(string.toLowerCase()))
            nouns.add(string.toLowerCase());
    }

    // Adds a verb to the verb list and its description to the parallel description
    // list.
    // This method should be used to register new commands with the command system.
    public void addVerb(String verb, String description) {
        verbs.add(verb.toLowerCase());
        verbDescription.add(description);
    }

    /*
     * Prints out the help menu. Goes through all verbs and verbDescriptions
     * printing a list of all commands the user can use.
     */
    public void printHelp() {
        System.out.printf("\n\n" + lineBreak + "\n%" + ((DISPLAY_WIDTH / 2) + 4) + "s\n" + lineBreak + "\n",
                " Commands ");

        for (String verb : verbs) {
            System.out.printf("%-8s  %s", verb,
                    formatHelpMenuDescriptionString(verbDescription.get(verbs.indexOf(verb))));
        }
    }

    /*
     * Provides a default way to output to the user.
     * It simply calls the other version of print so you don't have to supply the
     * last two parameters.
     */
    public void print(String longstring) {
        print(longstring, true, true);
    }

    /**
     * Prints the specified string with customizable output handling.
     *
     * @param longstring  The string to be printed.
     * @param addBrackets A boolean value indicating whether brackets should be
     *                    added around words that match the projects nouns.
     * @param formatWidth A boolean value indicating whether the string should be
     *                    formatted to a specific width based on the console width.
     * 
     *                    You could specifically call this and set addBrackets or
     *                    formatWidth to false.
     */
    public void print(String longstring, boolean addBrackets, boolean formatWidth) {
        if (addBrackets) {
            longstring = addNounBrackets(longstring);
        }
        if (formatWidth) {
            longstring = formatWidth(longstring);
        }
        System.out.println(longstring);
    }

    /*******************************************************************************
     * - - - - - - - - - - - - Private Methods - - - - - - - - - - - - - - - - - - -
     *******************************************************************************/

    private static void createHorzontalRule() {
        int count = 0;
        while (count++ < DISPLAY_WIDTH) {
            lineBreak += "-";
        }
    }

    // Default way the project reports to the user that the input is not understood.
    private void reportUnknownCommand(String input) {
        if (Math.random() < .3) // A random chance for a silly response - because why not.
            print("Don't be silly. Everyone knows '" + input + "' is not a command! Type ? for help.");
        else
            print("I don't understand '" + input + "'. Type ? for help.");
    }

    /**
     * Adds brackets around whole words that are included in the `nouns` list,
     * ignoring case, and also deals with any that have punctuation after them.
     *
     * @param longString the string to check for nouns
     * @return the modified string with brackets around the nouns
     */
    private String addNounBrackets(String longString) {
        String[] lines = longString.split("\n");
        StringBuilder result = new StringBuilder();

        for (String line : lines) {
            String[] words = line.split("\\s+");
            for (int i = 0; i < words.length; i++) {
                String word = words[i].replaceAll("\\p{Punct}+$", "");
                String punct = words[i].substring(word.length());
                for (String noun : nouns) {
                    if (word.equalsIgnoreCase(noun)) {
                        words[i] = "[" + word + "]" + punct;
                        break;
                    }
                }
            }
            result.append(String.join(" ", words)).append("\n");
        }

        return result.toString();
    }

    /**
     * Formats the given string to fit within the display width.
     *
     * @param longString The string to be formatted.
     * @return The formatted string.
     */
    private String formatWidth(String longString) {
        String[] lines = longString.split("\n");
        StringBuilder result = new StringBuilder();
        int charLength = 0;

        for (String line : lines) {
            Scanner scanner = new Scanner(line);
            while (scanner.hasNext()) {
                String word = scanner.next();
                // Check if adding the word exceeds the display width
                if (charLength + word.length() > DISPLAY_WIDTH - 6) {
                    result.append("\n");
                    charLength = 0;
                }
                // Append the word and a space
                result.append(word).append(" ");
                charLength += word.length() + 1; // Include space character
            }
            scanner.close(); // Close the scanner after each line
            result.append("\n"); // Add newline after each
            charLength = 0; // Reset charLength for the next line
        }

        return result.toString();
    }

    /**
     * Formats the given description string for a menu display, ensuring proper
     * alignment and wrapping. Width is currently based on DISPLAY_WIDTH.
     * 
     * NOTE: It ignores \n in the longString but will replace [nl] with a new line
     * -- You could work to implement it correctly!
     *
     * @param longString The string to be formatted.
     * @return The formatted string suitable for menu display.
     */
    private String formatHelpMenuDescriptionString(String longString) {
        StringBuilder result = new StringBuilder();
        Scanner chop = new Scanner(longString);
        int charLength = 0;

        while (chop.hasNext()) {
            String next = chop.next();
            if (next.contains("[nl]")) { // Check if the token is a newline marker
                // Find the index of the [nl] marker
                int nlIndex = next.indexOf("[nl]");

                // Append the part of the word before the [nl] marker
                result.append(next, 0, nlIndex);

                // Append a newline character
                result.append("\n          ");

                // If there are characters after the [nl] marker, append them
                if (nlIndex + 4 < next.length()) {
                    result.append(next, nlIndex + 4, next.length());
                    result.append(" "); // Add a space if necessary
                }

                // Reset the character length for the new line
                charLength = 0;
            } else {
                charLength += next.length();
                result.append(next).append(" ");
                if (charLength >= (Gamemanager.DISPLAY_WIDTH - 35)) {
                    result.append("\n          ");
                    charLength = 0;
                }
            }
        }
        chop.close();
        return result.append("\n\n").toString();
    }

}