/**
 * Class Item - an object within the game world.
 *
 * An Item represents a tangible object that can exist in a room or inventory.
 * It encapsulates properties such as weight, description, and whether it can be carried.
 *
 * @author  Renata Rusly
 * @version 2025.12.02
 */
public class Item
{
    private String itemName;
    private String itemEmoji;
    private int itemWeight;
    private boolean isCarryable;
    private String itemHint;

    /**
     * Creates an item with defined properties like name, weight, and description.
     * @param name the name of the item.
     * @param emoji the visual emoji representation.
     * @param weight the weight of the item.
     * @param carryable True if the item can be picked up, false otherwise.
     * @param hint A short textual description or hint about the item.
     */
    public Item(String name,String emoji, int weight, boolean carryable, String hint)
    {
        itemName = name;
        itemEmoji = emoji;
        itemWeight = weight;
        isCarryable = carryable;
        itemHint = hint;
    }
    
    /**
     * Get the name of the item.
     * @return the item's name as a string.
     */
    public String getName() 
    {
        return itemName ;
    }
    
    /**
     * Get the emoji of the item.
     * @return the item's emoji as a string.
     */
    public String getEmoji() 
    {
        return itemEmoji;
    }
    
    /**
     * Get weight of the item.
     * @return the item's name as an int.
     */
    public int getWeight() 
    {
        return itemWeight;
    }

    /**
     * Checks if the item is capable of being picked up.
     * @return true if the item is carryable, false otherwise.
     */
    public boolean canCarry() 
    {
        return isCarryable;
    }
    
    /**
     * Get the hint of the items.
     * @return the item's hint as a string.
     */
    public String getHint()
    {
        return itemHint;
    }
    
}