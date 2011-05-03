
/**
 * Write a description of class Door here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Door extends Exit
{
    // instance variables - replace the example below with your own
    private boolean locked;

    /**
     * Constructor for objects of class Door
     */
    public Door(String direction, Room neighbor, boolean locked)
    {
        // initialise instance variables
        this.locked = locked;
        this.setDirection(direction);
        this.setNeighbor(neighbor);
        
    }

    /**
     * An example of a method - replace this comment with your own
     * 
     * @param  y   a sample parameter for a method
     * @return     the sum of x and y 
     */
    public void lock()
    {
        locked = true;
        return;
    }
    
    public void unlock()
    {
        locked = false;
        return;
    }
    
    public boolean getLocked()
    {
        return locked;
    }
}
