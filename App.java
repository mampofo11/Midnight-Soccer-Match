import java.util.*;

public class App {
    static Scanner in = new Scanner(System.in);

    static GameState state;
    static Gamemanager game;
    static CommandSystem commandSystem;

    public static void main(String[] args) throws Exception {

        // Store the command system for easy reference in the client code.
        game = new Gamemanager();
        state = new GameState(game);
        commandSystem = new CommandSystem(state, game);

        game.print("\nWelcome to the midnight soccer match where your goal is to figure out a way to beat the opponent in a game of Penalty shots!! you must find out how to do this by traversing the map and gaining new abilities!! GOOD LUCK!(hint: enter '?' to see your list of commands)");

        game.print("You are on the Field practicing your shots with your ball when you see some figures in the distance and one of them seems to be an opponent.\n\nuse the talk command to find out what they want.");
        


        // The main game loop.
        while (state.gameRunning) {

            
            // Gets input from the user in an array of strings that they typed in.
            String[] command = getCommand();

            // if the game knows about the verb and all the nouns, we should process that
            // command!
            // The validCommand method will output errors if ti is not valid.
            if (game.validCommand(command)) {
                commandSystem.processCommand(command);
            } else {   
                game.print("Invalid. Type 'quit' to exit.");
            }

        }

    }

    // Gets input from the user
    // seperates the input into each word (determined by whitespace)
    // returns an array with each word an element of the array.
    public static String[] getCommand() {

        game.print(Gamemanager.lineBreak, false, false);
        System.out.print("What would you like to do?\n> ");
        String input = in.nextLine();
        game.print("");

        return input.toLowerCase().split("\\s+");
    }

}