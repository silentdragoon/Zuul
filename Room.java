import java.util.Set;
import java.util.HashMap;
import java.util.Iterator;

/**
 * Class Room - a room in an adventure game.
 *
 * This class is part of the "World of Zuul" application. 
 * "World of Zuul" is a very simple, text based adventure game.  
 *
 * A "Room" represents one location in the scenery of the game.  It is 
 * connected to other rooms via exits.  For each existing exit, the room 
 * stores a reference to the neighboring room.
 * 
 * @author  Michael Kolling and David J. Barnes
 * @version 1.0 (February 2002)
 */

public class Room 
{
    private String description;
    private HashMap<String, Room> exits;        // stores exits of this room.
    private HashMap<String, Item> items;        // stores items of this room.

    /**
     * Create a room described "description". Initially, it has no exits.
     * "description" is something like "in a kitchen" or "in an open court 
     * yard".
     */
    public Room(String description) 
    {
        this.description = description;
        exits = new HashMap<String, Room>();
        items = new HashMap<String, Item>();
    }
    
    public void setItem(String name, String description)
    {
        // Check to see if the item already exists in the items list
        // If it is, just don't add it.
        
        // TODO: Make sure this is the correct behavior. Also, perhaps
        // make sure that the name is indeed one word?
        
        Set<String> keys = items.keySet();
        for(String item : keys)
            if (item.equals(name))
                return;
        
        Item newItem = new Item(description, name);
        items.put(name, newItem);
    }
    
    public Item delItem(String name)
    {
    
        // TODO: Make this actually check for items, and remove them iff
        // they are found.
    
        Set<String> keys = items.keySet();
        for(String item : keys) {
            if (item.equals(name))
            {
                Item temp = items.get(name);
                items.remove(name);
                return temp;
            }            
        }
        System.out.println("That isn't here.");
        return null;
        
    }

    /**
     * Define an exit from this room.
     */
    public void setExit(String direction, Room neighbor) 
    {
        exits.put(direction, neighbor);
    }

    /**
     * Return the description of the room (the one that was defined in the
     * constructor).
     */
    public String getShortDescription()
    {
        return description;
    }

    /**
     * Return a long description of this room, in the form:
     *     You are in the kitchen.
     *     Exits: north west
     */
    public String getLongDescription()
    {
        return "You are " + description + ".\n" + getExitString() + ".\n" + getItemString();
    }

    /**
     * Return a string describing the room's exits, for example
     * "Exits: north west".
     */
    private String getExitString()
    {
        String returnString = "Exits:";
        Set<String> keys = exits.keySet();
        for(String exit : keys)
            returnString += " " + exit;
        return returnString;
    }
    
    private String getItemString()
    {
        String returnString = "Items:";
        Set<String> keys = items.keySet();
        for(String item : keys)
            returnString += " " + item;
        return returnString;
    }

    /**
     * Return the room that is reached if we go from this room in direction
     * "direction". If there is no room in that direction, return null.
     */
    public Room getExit(String direction) 
    {
        return exits.get(direction);
    }
    
    public Item getItem(String name)
    {
        return items.get(name);
    }
}

