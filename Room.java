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
    private HashMap<String, Exit> exits;        // stores exits of this room.
    private HashMap<String, Item> items;        // stores items of this room.
    private HashMap<String, NPC> npcs;
    private HashMap<String, Door> doors;

    /**
     * Create a room described "description". Initially, it has no exits.
     * "description" is something like "in a kitchen" or "in an open court 
     * yard".
     */
    public Room(String description) 
    {
        this.description = description;
        exits = new HashMap<String, Exit>();
        items = new HashMap<String, Item>();
        npcs = new HashMap<String, NPC>();
        doors = new HashMap<String, Door>();
    }
    
    /**
     * Adds an NPC described by a name/desc and with an item of name/desc to the room.
     */
    public void addNPC(String name, String description, String itemname, String itemdesc)
    {
    
        Set<String> keys = npcs.keySet();
        for(String npc : keys)
            if (npc.equals(name))
                return;
    
        NPC newNPC = new NPC(name, description, itemname, itemdesc);
        npcs.put(name, newNPC);
        
    }
    
    /**
     * Adds an item to the items list, if it's there already just don't add it.
     */
    public void setItem(String name, String description)
    {
        // Check to see if the item already exists in the items list
        // If it is, just don't add it.
               
        Set<String> keys = items.keySet();
        for(String item : keys)
            if (item.equals(name))
                return;
        
        Item newItem = new Item(description, name);
        items.put(name, newItem);
    }
    
    
    /**
     * Removes an item from the room and returns it.
     */
    public Item delItem(String name)
    {
     
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
        Exit temp = new Exit(direction, neighbor);
        exits.put(direction, temp);
    }
    
    /**
     * Defines a lockable exit, or door, for the room.
     */
    public void setDoor(String direction, Room neighbor, boolean locked)
    {
        Door temp = new Door(direction, neighbor, locked);
        doors.put(direction, temp);
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
        return "You are " + description + ".\n" + getExitString() + "\n" + getDoorString() + "\n" + getItemString() + "\n" + getNPCString();
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
    
    /**
     * Return a string containing the room's doors, for example
     * "Doors: portal".
     */
    private String getDoorString()
    {
        String returnString = "Doors:";
        Set<String> keys = doors.keySet();
        for(String door : keys)
            returnString += " " + door;
        return returnString;
    }
    
    /**
     * Return a string containing the room's items.
     */
    private String getItemString()
    {
        String returnString = "Items:";
        Set<String> keys = items.keySet();
        for(String item : keys)
            returnString += " " + item;
        return returnString;
    }
    
    /**
     * Return a string containing the NPCs in a room.
     */
    private String getNPCString()
    {
        String returnString = "NPCS:";
        Set<String> keys = npcs.keySet();
        for(String npc : keys)
            returnString += " " + npc;
        return returnString;
    }

    /**
     * Return the room that is reached if we go from this room in direction
     * "direction". If there is no room in that direction, return null.
     */
    public Room getExit(String direction) 
    {
        Exit tempExit = exits.get(direction);
        if (tempExit != null)
        {
            return tempExit.getNeighbor();
        }
        return null;
    }
    
    /**
     * Return the room reached if we go from this room in direction "direction."
     * If there is no room in that direction, return null.
     */
    public Room getDoor(String direction)
    {
        Door tempDoor = doors.get(direction);
        if (tempDoor != null)
        {
            return tempDoor.getNeighbor();
        }
        return null;
    }
    
    public boolean getLocked(String direction)
    {
        return doors.get(direction).getLocked();
    }
    
    public Door getActualDoor(String direction)
    {
        return doors.get(direction);
    }
    
    public Item getItem(String name)
    {
        return items.get(name);
    }
    
    public NPC getNPC()
    {
        return npcs.entrySet().iterator().next().getValue();
    }    
}

