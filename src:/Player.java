/**
 * Class Player - represents the user playing the game.
 *
 * This class manages the player's inventory system, tracking collected items,
 * calculating total weight against limits, and handling logic for picking up or dropping objects.
 *
 * @author  Renata Rusly
 * @version 2025.12.02
 */

import java.util.ArrayList;
public class Player
{
    // instance variables - replace the example below with your own
    private ArrayList<Item>inventory;

    /**
     * Constructor for items of class Player
     */
    public Player()
    {
        inventory = new ArrayList<Item>();
    }

    /**
     * Adds an item to the player's inventory list.
     * @param item the item object to be added.
     */
    public void pickItem(Item item)
    {
        inventory.add(item);
    }
    
    /**
     * Remove an item to the player's inventory list.
     * @param item the item object to be remove.
     */
    public void dropItem(Item item)
    {
        inventory.remove(item);
    }
    
    /**
     * Creates a String listing all items in inventory.
     * @return A string containing names and emojis of all inventory items.
     */
    public String getInventoryString() 
    {
        if (inventory.isEmpty()) 
        {
            return "your inventory is still empty!";
        }
        
        String s = "";
        for (Item item : inventory)
        {
            s += " " + item.getName() + item.getEmoji();
        }
        return "Inventory:" + s;
    }
    
    /**
     * Checks if an item with the given name exists in the inventory.
     * @param itemName of the item to look for.
     * @return true if the item is found, false otherwise.
     */
    public boolean hasItem (String itemName) 
    {
        return getItemString(itemName) != null;
    }
    
    /**
     * Retrieves the actual Item object from the inventory by its name.
     * @param name of the item to retrieve.
     * @return the Item object if found, or null if it doesn't exist.
     */
    public Item getItemString(String name) 
    {
        for (Item item : inventory)
        {
            if (item.getName().equalsIgnoreCase(name)) 
            {
                return item;
            }
        }
        return null;
    }
    
    /**
     * Calculates the combined weight of all items in the inventory.
     * @return the integer sum of all item weights.
     */
    public int getTotalWeight() 
    {
        int totalWeight = 0;
        for (Item item : inventory)
        {
            totalWeight += item.getWeight(); 
        }
          return totalWeight;
    }
}