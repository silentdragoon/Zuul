
/**
 * Write a description of class Item here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Item
{
    // instance variables - replace the example below with your own
    private String description;
    private String name;

    /**
     * Constructor for objects of class Item
     */
    public Item(String description, String name)
    {
        // initialise instance variables
        this.description = description;
        this.name = name;
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
}
