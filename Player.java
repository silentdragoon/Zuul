import java.util.Set;
import java.util.HashMap;
import java.util.Iterator;

/**
 * Write a description of class Player here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Player
{
    // instance variables - replace the example below with your own
    private Room currentRoom;
    private HashMap<String, Item> inventory;

    /**
     * Constructor for objects of class Player
     */
    public Player()
    {
        currentRoom = new Room("starting room");
        inventory = new HashMap<String, Item>();
        // initialise instance variables
        // x = 0;
    }

    /**
     * An example of a method - replace this comment with your own
     * 
     * @param  y   a sample parameter for a method
     * @return     the sum of x and y 
     */
    public Room getCurrentRoom()
    {
        return currentRoom;
    }
    
    public void setCurrentRoom(Room newRoom)
    {
        currentRoom = newRoom;
    }
    
    public String getInventoryString()
    {
        String returnString = "Inventory:";
        Set<String> keys = inventory.keySet();
        for(String item : keys)
            returnString += " " + item;
        return returnString;
    }
    
    public void addInventory(String name, String description)
    {
        Set<String> keys = inventory.keySet();
        for(String item : keys) {
            if (item.equals(name))
            {
                System.out.println("We've already got one!");
                return;
            }   
        }
        Item newItem = new Item(description, name);
        inventory.put(name, newItem);
    }
    
    public void addInventory(Item item)
    {
        inventory.put(item.getName(), item);
    }
    
    public Item dropInventory(String name)
    {
        Set<String> keys = inventory.keySet();
        for(String item : keys) {
            if (item.equals(name))
            {
                Item temp = inventory.get(name);
                inventory.remove(name);
                return temp;

            }   
        }
        System.out.println("We've haven't got one!");
        return null;

    }
    
    public Item dropInventory()
    {
        Item temp = inventory.entrySet().iterator().next().getValue();
        inventory.clear();
        return temp;
    }
}
