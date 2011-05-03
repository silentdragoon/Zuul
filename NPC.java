
/**
 * NPC.java. Stores information about NPCs, non player characters.
 * 
 * @author William S. Judd
 */
public class NPC extends Player
{

    private String name;
    private String description;


    public NPC(String name, String description, String itemname, String itemdesc)
    {
        this.description = description;
        this.name = name;
        addInventory(itemname, itemdesc);
        
    }


    public String getDescription()
    {
        return description;
    }
    
    public String getName()
    {
        return name;
    }
    
}
