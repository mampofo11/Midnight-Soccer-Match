
public class GameState {
    public boolean gameRunning = true;
    public Player player;
    Location currentLocation;
    Location Field;
    Location Bleachers;
    Location Locker_Room;
    Location Goal;

    int goals;
  
    Item amulet;
    Item ball;
    Item cleats;

    Entities Referee;
    Entities Opponent;
    Entities Goal_keeper;

    /*
     * GameState Constructor
     * 
     * Ideally, your game will be fully loaded and ready to play once this
     * constructor has finished running.
     * 
     * How things have been done here are just a rudementry setup to link the other
     * classes and have the
     * bare bones example of the command system. This is not a great way to
     * initilize your project.
     */

    public GameState(Gamemanager game) {
        cleats = new Item("cleats", "This item will allow the user to play better increasing their skill level");
        ball = new Item("ball", "ball that you use to shoot around");
        amulet = new Item("amulet", "This amulet causes the user to gain a boost in their soccer playing abilities");
        player = new Player();
        
        Field = new Location();
        Field.exits = new Location[]{Locker_Room, Goal, Bleachers};
        Field.itemsHere = new Item[]{ball, cleats};
        Field.name = "Field";
        Field.description = "You are on the Field.";
        
        Bleachers = new Location();
        Bleachers.exits = new Location[]{Field};
        Bleachers.itemsHere = new Item[]{};
        Bleachers.name = "Bleachers";
        Bleachers.description = "These are the bleachers right next to the field.";
        game.addNoun("bleachers");
        
        Goal = new Location();
        Goal.exits = new Location[]{Field};
        Goal.itemsHere = new Item[]{};
        Goal.name = "Goal";
        Goal.description = "You are right in front of the Goal!! Shoot the ball to try and get a point.";
        game.addNoun("goal");

        Locker_Room = new Location();
        Locker_Room.exits = new Location[]{Field};
        Locker_Room.itemsHere = new Item[]{amulet};
        Locker_Room.name = "Locker Room";
        Locker_Room.description = "You are in the Locker room. You seem to pass by an open locker.";
        game.addNoun("locker");
        

       
        
        // Create first (starting) location
        // (and store it in currentLocation so I can always referece where the player is
        // easily)
        currentLocation = Field;
        currentLocation.name = Field.name;
        currentLocation.description = Field.description;
        game.addNoun("Field");

        // Create a demo item.
       


        // Add item to list of nouns so our command system knows it exists.
        game.addNoun("cleats");
        game.addNoun("ball");
        game.addNoun("opponent");
        game.addNoun("amulet");
        game.addNoun("player");
        game.addNoun("referee");
        game.addNoun("keeper");
        game.addNoun("talk");




        Opponent = new Entities("Opponent", "The opponent that the player is trying to beat.");
        this.player = new Player();
        


        /*
         * Once the commandSystem knows about the item, we need to code what happens
         * with each of the commands that can happen with the item.
         * See CommandSystem line 96 for what happens if you currently "look mat"
         */
    }
}