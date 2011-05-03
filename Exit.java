
/**
 * Exit.java. Stores information on exits, non-lockable
 * travel avenues in Zuul.
 * 
 * @author William S. Judd
 */
public class Exit
{
    private String direction;
    private Room neighbor;

    /**
     * Constructor for objects of class Exit
     */
     
     public Exit()
     {
     }
     
    public Exit(String direction, Room neighbor)
    {
        // initialise instance variables
        this.direction = direction;
        this.neighbor = neighbor;

    }
    
    public String getDirection()
    {
        // put your code here
        return direction;
    }
    
    public Room getNeighbor()
    {
        return neighbor;
    }
    
    public void setDirection(String direction)
    {
        this.direction = direction;
    }
    
    public void setNeighbor(Room neighbor)
    {
        this.neighbor = neighbor;
    }
    
}
