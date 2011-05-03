
/**
 * Write a description of class Exit here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Exit
{
    // instance variables - replace the example below with your own
    private String direction;
    private Room neighbor;

    /**
     * Constructor for objects of class Exit
     */
    public Exit(String direction, Room neighbor)
    {
        // initialise instance variables
        this.direction = direction;
        this.neighbor = neighbor;

    }

    /**
     * An example of a method - replace this comment with your own
     * 
     * @param  y   a sample parameter for a method
     * @return     the sum of x and y 
     */
     
    public String getDirection()
    {
        // put your code here
        return direction;
    }
    
    public Room getNeighbor()
    {
        return neighbor;
    }
}
