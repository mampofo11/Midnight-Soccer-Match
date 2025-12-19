import java.util.*;
public class CommandSystem {
    private GameState state;
    private Gamemanager game;

    /*
     * Constructor here is used to define all verbs that can be used in the commands
     * and (perhaps) all nouns the user can interact with.
     * 
     * Suggestion: These could all be loaded from a file, or have a different
     * location that this happens. A different class or a new method in this class.
     * 
     * Verb descriptions are stored in a parallel Arraylist with the Verbs and are
     * used when printing out the help menu (using the ? command).
     * 
     * NOTEs:
     * 1. All nouns and verbs will be stored as lowercase.
     * 2. If you want a newline in a description string, use [nl] instead of \n
     */

    public CommandSystem(GameState state, Gamemanager game) {
        this.state = state;
        this.game = game;

        // NOTE: Verb descriptions can use [nl] for new lines.
        // using \n will be ignored in them.
        game.addVerb("?", "Show this help screen.");
        game.addVerb("look", "Use the look command by itself to look in your current area. [nl]You can also look at a person or object by typing look and the name of what you want to look at.[nl]Example: look book");
        game.addVerb("l", "Same as the look command.");
        game.addVerb("quit", "Quit the game."); // NOTE: In the starter code, this is handeled by the GameManager
        game.addVerb("q", "This is the same as quit.");
        game.addVerb("talk", "This is how you will talk to the opponent. Type 'talk [entity]' to talk to them.");
        game.addVerb("t", "Same as talk command.");
        game.addVerb("go", "Moves the player to a new location.");
        game.addVerb("retrieve", "Gets the item you name. Enter 'retrieve [item].");
        game.addVerb("inventory", "Shows the user everything in their inventory.");
        game.addVerb("inv", "Same as inventory command.");
        game.addVerb("use", "This action allows you to use an item in hand. Type 'use [item] player' to use an item on yourself.");

    }

    /**
     * Executes a command when it consists of a just verb
     *
     * @param verb The verb representing the action to be performed.
     */
    public void executeVerb(String verb) {

        switch (verb) {
            // REMINDER: All nouns and verbs are stored as lowercase.
            case "l":
            case "look": // will show the description of the current room (stored in the state object)
                game.print("You look around.");
                game.print(state.currentLocation.description);
                System.out.println("Checking items in " + state.currentLocation.name);
                if (state.currentLocation == state.Bleachers){
                    game.print("There are no items here!");
                } 
                if (state.currentLocation.itemsHere != null){
                    for (Item item : state.currentLocation.itemsHere) {
                        System.out.println("Item: " + item.getName());
                    }
                } 

                break;
            case "?":
                game.printHelp();
                break;
            case "talk":
            case "t":
                game.print("You talk");
                game.print("The opponent on the field challeneged you to a penalty shootout that you have to beat him in. now its up to you to figure out how to beat him in this game!! use the command 'go goal' to start the penalty shootout or 'q' to quit and give up ");
                break;
                //currently this is just to get the basis of talk different speech will be added later on
            case "q":
            case "quit":
                System.out.println("Thank You for playing the game Goodbye!!\n");
                state.gameRunning = false;
                break;
            case "go":
                game.print("Where are you trying to go to? ([field], locker, bleachers, or goal to start the game)" );
                break;
            case "inventory":
            case "inv":
                System.out.println(state.player.ShowInv());
                break;
            // case "inventory":
            // for (Item item : inventory) {
            //     System.out.println("Item: " + item.getName());
            // }
            default:
                game.print("Unknown command. Type '?' for a list of commands.");
        }
    }

    /**
     * Executes a command when it consists of a verb followed by a noun.
     *
     * @param verb The verb representing the action to be performed.
     * @param noun The noun representing the target of the action.
     */
    public void executeVerbNoun(String verb, String noun) {

        switch (verb) { // Decides what to do based on each verb
            case "l":
            case "look":
                lookAt(noun); // The lookAt method will deal with what noun to look at
                break;
            case "go":
                game.print("You are trying to go to: " + noun); // Add this to check the input
                game.print(GoTo(noun));
                break;
            case "talk":
            case "t":
                game.print(Talkto(noun));
                break;
           case "retrieve":
                game.print("You grab the item.");
                Item itemToRetrieve = state.currentLocation.getItem(noun); 
                if (itemToRetrieve != null) {
                    state.player.addItem(itemToRetrieve);
                    game.print("You have retrieved the " + itemToRetrieve.getName() + ".");
                } else {
                    game.print("There is no item named '" + noun + "' here.");
                }
                    break;
            default:
                game.print("Unknown command.");
        }
    }

    // Used when command is a Verb followed by two nouns
    public void executeVerbNounNoun(String verb, String noun1, String noun2) {
        switch (verb.toLowerCase()) {
            case "use":
                if ("player".equalsIgnoreCase(noun2)) {
                    System.out.println("Attempting to use: " + noun1 + " on " + noun2);
                    state.player.useItem(noun1); // Ensure "use item" is specifically for the player
                } else {
                    System.out.println("You cannot use the " + noun1 + " on " + noun2 + ".");
                }
                break;
            case "retrieve":
                if("referee".equalsIgnoreCase(noun2)){
                    System.out.println("Attempting to retrieve: " + noun1 + " to " + noun2);
                    boolean itemFound = false;
        for (int i = 0; i < state.player.inventory.length; i++) {
            Item item = state.player.inventory[i];
            if (item != null && item.getName().equalsIgnoreCase(noun1)) {
                itemFound = true;
                // Remove the item from the inventory
                state.player.inventory[i] = null;
                System.out.println("You handed the " + item.getName() + " to the referee.");
                System.out.println("The referee wonders why you handed him the item but takes it anyways.");
                break;
            }
        }

        if (!itemFound) {
            System.out.println("You don't have a " + noun1 + " in your inventory.");
        }
    } else {
        System.out.println("You can only retrieve items to the referee.");
    }
    break;

                
            default:
                System.out.println("Unknown command: " + verb + " " + noun1 + " " + noun2);
        }
    }

    /**
     * Look method for handling the "look" command.
     * 
     * @param noun The noun representing the object to look at.
     * @return A string containing the description of the object being looked at, or
     *         an empty string if the object is not found.
     * 
     *         TODO: Return an error or message that says you don't see that if noun
     *         is not found instead of an empty string.
     */
    public void lookAt(String noun) {
        switch (noun) { // for the given verb, decide what to do based on what noun was entered
            case "ball":
                // get the description from the item you are looking at.
                game.print( state.ball.getDesc());
                break;
            case "cleats":
                game.print( state.cleats.getDesc());
                break;
            case "amulet":
                game.print(state.amulet.getDesc());
                break;
            
            // You cound design a way to look at any item without having to specify how to
            // deal with each of them.
            // That way you can code special cases for some items, and others would just use
            // default behavior.
            // This is HIGHLY encouraged. (It will save time and headaches!)
            default:
        }
    }

    public String Talkto(String noun){
        switch(noun){
            case "opponent":
                return "You don't even have the slightest chance of beating me... unless u obtain some sort of mystical power!";
            case "referee":
                return "I believe you will find what you are looking for somewhere in the locker room, but make sure you keep everything legal in this shootout. I will be watching closely.";
            case "keeper":
                return "Not a single ball will get passed me! I'm #2 in the nation!! But if you haven't already, I would put on some gear so you don't suck!!";
            default:
                return "That is not an entity you can talk to.";

        }
    }

    
    public String GoTo(String noun) {
    noun = noun.toLowerCase();
    switch (noun) {
        case "field":
            state.currentLocation = state.Field;
            return "You moved to the " + noun + ": " + state.currentLocation.description;
        case "bleachers":
            state.currentLocation = state.Bleachers;
            return "You moved to the " + noun + ": " + state.Bleachers.description + "\n The keeper is also waiting here before the shootout. Talk to him to find out what he wants";
        case "locker":
            state.currentLocation = state.Locker_Room;
            return "You moved to the " + noun + ": " + state.Locker_Room.description + "\n The referee is also here. Talk to him to find out what he wants.";

        case "goal":
    state.currentLocation = state.Goal;

    boolean hasBall = false;
    for (Item item : state.player.inventory) {
        if (item != null && item.getName().equalsIgnoreCase("ball")) {
            hasBall = true;
            break;
        }
    }

    if (!hasBall) {
        state.gameRunning = false; // End the game
        return "You moved to the Goal: " + state.Goal.description +
               "\nHowever, you don't have the ball! The game is over. Better luck next time!";
    }

    Scanner input = new Scanner(System.in);
    System.out.println("You moved to the Goal: " + state.Goal.description);
    
    // Calculate skill points based on skill value
    int skillPoints = state.player.getSkillValue();
    System.out.println("Skill Points: " + skillPoints);

    int score = 0; // Successful goals scored
    int shots = 3; // Total shots allowed

    System.out.println("Penalty Shootout Begins!");


    while (shots > 0) {
        System.out.println("\nYou have " + shots + " shots remaining. Choose which area of the goal you want to shoot: top left(TL), top right(TR), middle(M), bottom left(BL), bottom right(BR)");
       
        String action = input.nextLine().toUpperCase();

        //Decide outcome
        boolean scored = false;

        if (skillPoints >= 8) {
            scored = true; // Guaranteed success for 8+ skill points
        } else if (skillPoints == 2) {
            // One successful shot for 2 skill points
            scored = (score == 0);
        } else {
            scored = false; // All shots missed for 0 skill points
        }

        // Print immediately after input
        if (scored) {
            score++;
            System.out.println("You shot " + action + ". The ball flew past the goalkeeper. YOU SCORED!");
        } else {
            System.out.println("You shot " + action + ". The goalkeeper saved it. YOU MISSED!");
        }

        shots--;
    }

    // Determine the result based on the final score
    if (score == 0) {
        System.out.println("\nYou lost the penalty shootout :(. You realized that you're completely washed and you quit the sport. Your dreams are crushed.");
    } else if (score == 1) {
        System.out.println("\nAW MAN... You almost had it. But, that's okay, you did your best. You go home defeated, and decide to get your get back on FIFA like the loser you are.");
    } else if (score == 3) {
        System.out.println("\nCONGRATULATIONS!!! The goalkeeper reveals himself to be Gianluigi Donnarumma, a professional soccer player from the best soccer club in the WORLD, Manchester City. He says he'll contact his manager, Pep Guardiola, to offer you the chance to be placed in their youth academy in Manchester, England. How exciting!!! :D");
    } else {
        System.out.println("\nYou scored " + score +"/3 shots. The opponent walks away, shaking his head.");
        state.gameRunning = false; // Neutral or fallback ending
    }

    state.gameRunning = false; // End the game after the shootout   
    return "";
     default:
            return "That is not a place you can move to!!"; // Handle invalid input
    }
}


    /**
     * Processes the input command and determines the appropriate method to call
     * based on the number of words in the command.
     * 
     * @param command An array of strings representing the words in the command.
     */
    public void processCommand(String[] command) {
        switch (command.length) {
            case 1:
                executeVerb(command[0]);
                break;
            case 2:
                executeVerbNoun(command[0], command[1]);
                break;
            case 3:
                executeVerbNounNoun(command[0], command[1], command[2]);
                break;

        }
    }

}



































































    // public String GoTo(String noun){
    //     noun = noun.toLowerCase();
    //     Location current = state.currentLocation;

    //         for(Location exit : current.exits){
    //             if(exit == state.Field && noun.equals("field")){
    //                 state.currentLocation = state.Field;
    //                 return "You moved to the Field: " + state.Field.description;
    //             } else if (exit == state.Bleachers && noun.equals("bleachers")){
    //                 state.currentLocation= state.Bleachers;
    //                 return "You moved to the bleachers: " + state.Bleachers.description;
    //              } else if (exit == state.Locker_Room && noun.equals("locker")) {
    //                  state.currentLocation = state.Locker_Room;
    //                  return "You moved to the Locker Room: " + state.Locker_Room.description;
    //              } else if (exit == state.Goal && noun.equals("goal")) {
    //                 state.currentLocation = state.Goal;
    //                  return "You moved to the Goal: " + state.Goal.description;
    //             }
    //     }
    //     return "You can't move to the " + noun + " from here.";
    // }