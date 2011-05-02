
/**
 * Write a description of class NPC here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class NPC extends Player
{
    // instance variables - replace the example below with your own
    private String name;
    private String description;

    /**
     * Constructor for objects of class NPC
     */
    public NPC(String name, String description, String itemname, String itemdesc)
    {
        // initialise instance variables
        this.description = description;
        this.name = name;
        addInventory(itemname, itemdesc);
        
    }

    /**
     * An example of a method - replace this comment with your own
     * 
     * @param  y   a sample parameter for a method
     * @return     the sum of x and y 
     */
    public String getDescription()
    {
        return description;
    }
    
    public String getName()
    {
        return name;
    }
    
    // TODO: Make sure NPCs can only hold one item at a time
}
