
/**
 * Door.java. Stores information and provides locking
 * and unlocking of doors, lockable exits in Zuul.
 * 
 * @author William S. Judd
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
     * Locks this door.
     */
    public void lock()
    {
        locked = true;
        return;
    }
    
    /**
     * Unlocks this door.
     */
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
