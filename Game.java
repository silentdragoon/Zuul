/**
 *  This class is the main class of the "World of Zuul" application. 
 *  "World of Zuul" is a very simple, text based adventure game.  Users 
 *  can walk around some scenery. That's all. It should really be extended 
 *  to make it more interesting!
 * 
 *  To play this game, create an instance of this class and call the "play"
 *  method.
 * 
 *  This main class creates and initialises all the others: it creates all
 *  rooms, creates the parser and starts the game.  It also evaluates and
 *  executes the commands that the parser returns.
 * 
 * @author  Michael Kolling and David J. Barnes
 * @version 1.0 (February 2002)
 */

public class Game 
{
    private Parser parser;
    private Player player;
    private NPC porter;
        
    /**
     * Create the game and initialise its internal map.
     */
    public Game() 
    {
        player = new Player();
        createRooms();
        parser = new Parser();

    }

    /**
     * Create all the rooms and link their exits together.
     */
    private void createRooms()
    {
        Room outside, theatre, pub, lab, office;
      
        // create the rooms
        outside = new Room("outside the main entrance of the university");
        theatre = new Room("in a lecture theatre");
        pub = new Room("in the campus pub");
        lab = new Room("in a computing lab");
        office = new Room("in the computing admin office");
        
        // initialise room exits
        outside.setExit("east", theatre);
        outside.setExit("south", lab);
        outside.setExit("dennis", pub);

        theatre.setExit("west", outside);

        pub.setExit("undennis", outside);

        lab.setExit("north", outside);
        lab.setExit("east", office);

        office.setExit("west", lab);
        
        // initialise room items
        outside.setItem("flask", "pretty spangly. It looks like you've got ye flask");
        theatre.setItem("sandwich", "a delicious sandwich");
        lab.setItem("gold", "just some gold, sitting around");
        
        player.addInventory("bagel", "half a blueberry bagel");

        outside.addNPC("Dwarf", "A helpful Dwarf", "key", "a sweet key");

        player.setCurrentRoom(outside);  // start game outside
    }

    /**
     *  Main play routine.  Loops until end of play.
     */
    public void play() 
    {            
        printWelcome();

        // Enter the main command loop.  Here we repeatedly read commands and
        // execute them until the game is over.
                
        boolean finished = false;
        while (! finished) {
            Command command = parser.getCommand();
            finished = processCommand(command);
        }
        System.out.println("Thank you for playing.  Goodbye.");
    }

    /**
     * Print out the opening message for the player.
     */
    private void printWelcome()
    {
        System.out.println();
        System.out.println("Welcome to the World of Zuul!");
        System.out.println("World of Zuul is a new, incredibly boring adventure game.");
        System.out.println("Type 'help' if you need help.");
        System.out.println();
        System.out.println(player.getCurrentRoom().getLongDescription());
        System.out.println(player.getInventoryString());
    }

    /**
     * Given a command, process (that is: execute) the command.
     * If this command ends the game, true is returned, otherwise false is
     * returned.
     */
    private boolean processCommand(Command command) 
    {
        boolean wantToQuit = false;

        if(command.isUnknown()) {
            System.out.println("I don't know what you mean...");
            return false;
        }

        String commandWord = command.getCommandWord();
        if (commandWord.equals("help"))
            printHelp();
        else if (commandWord.equals("go"))
            goRoom(command);
        else if (commandWord.equals("drop"))
            dropItem(command);
        else if (commandWord.equals("take"))
            takeItem(command);
        else if (commandWord.equals("trade"))
            tradeItem(command);
        else if (commandWord.equals("examine"))
            examineItem(command);
        else if (commandWord.equals("quit")) {
            wantToQuit = quit(command);
        }
        return wantToQuit;
    }

    // implementations of user commands:

    /**
     * Print out some help information.
     * Here we print some stupid, cryptic message and a list of the 
     * command words.
     */
    private void printHelp() 
    {
        System.out.println("You are lost. You are alone. You wander");
        System.out.println("around at the university.");
        System.out.println();
        System.out.println("Your command words are:");
        parser.showCommands();
    }

    /** 
     * Try to go to one direction. If there is an exit, enter the new
     * room, otherwise print an error message.
     */
    private void goRoom(Command command) 
    {
        if(!command.hasSecondWord()) {
            // if there is no second word, we don't know where to go...
            System.out.println("Go where?");
            return;
        }

        String direction = command.getSecondWord();

        // Try to leave current room.
        Room nextRoom = player.getCurrentRoom().getExit(direction);

        if (nextRoom == null)
            System.out.println("There is no door!");
        else {
            player.setCurrentRoom(nextRoom);
            System.out.println(player.getCurrentRoom().getLongDescription());
            System.out.println(player.getInventoryString());
        }
    }
    
    private void dropItem(Command command)
    {
        if (!command.hasSecondWord()) {
            // if there is no second word, we don't know what to drop...
            System.out.println("Drop what?");
            return;
        }
        
        String droppedItem = command.getSecondWord();
        
        // Drop it
        
        Item temp = player.dropInventory(droppedItem);
        if (temp != null)
        {
            
            // Add it to the room's items
            player.getCurrentRoom().setItem(temp.getName(), temp.getDescription());
            
            // Refresh inventory
            System.out.println(player.getCurrentRoom().getLongDescription());
            System.out.println(player.getInventoryString());
        }      
        
    }
    
    private void takeItem(Command command)
    {
       if (!command.hasSecondWord()) {
            // if there is no second word, we don't know what to drop...
            System.out.println("Take what?");
            return;
        }
        
        String desiredItem = command.getSecondWord();
              
        // Remove it from the room's items
        Item temp = player.getCurrentRoom().delItem(desiredItem);
        if (temp != null)
        {     
            // Add it to player's inventory
            player.addInventory(temp.getName(), temp.getDescription());
            
            // Refresh inventory
            System.out.println(player.getCurrentRoom().getLongDescription());
            System.out.println(player.getInventoryString());
        }
    }
    
    private void tradeItem(Command command)
    {
       if (!command.hasSecondWord()) {
            // if there is no second word, we don't know what to drop...
            System.out.println("Trade what?");
            return;
        }
        
        String tradingItem = command.getSecondWord();
        
        // find player's current room
        
        NPC npc = player.getCurrentRoom().getNPC();
        
        // get NPCs in room
        
        // remove NPC item
        
        Item tempItem = npc.dropInventory();
        
        // add NPC item to player inventory
        
        player.addInventory(tempItem);
        
        // remove player item
        
        Item tempPlayerItem = player.dropInventory(tradingItem);
        
        // add player item to NPC inventory
        
        npc.addInventory(tempPlayerItem);
        
        // print out inventory
        
        System.out.println(player.getInventoryString());
    }
    
    private void examineItem(Command command)
    {
       if (!command.hasSecondWord()) {
            // if there is no second word, we don't know what to drop...
            System.out.println("Examine what?");
            return;
        }
            
        System.out.println(player.getExamineString(command.getSecondWord()));
        return;
    }

    /** 
     * "Quit" was entered. Check the rest of the command to see
     * whether we really quit the game. Return true, if this command
     * quits the game, false otherwise.
     */
    private boolean quit(Command command) 
    {
        if(command.hasSecondWord()) {
            System.out.println("Quit what?");
            return false;
        }
        else
            return true;  // signal that we want to quit
    }
}
