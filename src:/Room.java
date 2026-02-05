/**
 * Class Room - a location in the adventure game.
 *
 * A "Room" represents one location in the scenery of the game. It is connected
 * to other rooms via exits and acts as a container for items and characters.
 * 
 * @author  Renata Rusly
 * @version 2025.12.02
*/

import java.util.Set;
import java.util.HashMap;
import java.util.ArrayList;

public class Room 
{
    private String description;
    private String roomEmoji;
    private HashMap<String, Room> exits;
    private ArrayList<Item> items;
    private ArrayList<Character> characters;

    /**
     * Create a room described "description".
     * @param description The room's description.
     */
    public Room(String description) 
    {
        this.description = description;
        exits = new HashMap<>();
        items = new ArrayList<>();
        characters = new ArrayList<>();
    }

    /**
     * Define an exit from this room.
     * @param direction The direction of the exit.
     * @param neighbor  The room to which the exit leads.
     */
    public void setExit(String direction, Room neighbor) 
    {
        exits.put(direction, neighbor);
    }

    /**
     * @return The short description of the room
     * (the one that was defined in the constructor).
     */
    public String getShortDescription()
    {
        return description;
    }

    /**
     * Return a description of the room in the form:
     *     'You are in the kitchen.
     *     Exits:... '
     * @return A long description of this room
     */
    public String getLongDescription()
    {
        return "You are " + description + ".\n" + getExitString();
    }

    /**
     * Return a string describing the room's exits
     * @return Details of the room's exits.
     */
    private String getExitString()
    {
        String returnString = "Exits:\n";
        String separator = "";
        Set<String> keys = exits.keySet();
        for(String exit : keys) 
        {
            returnString += separator + exit;
            separator = ", ";
        }
        return returnString;
    }

    /**
     * Return the room that is reached if we go from this room in direction
     * "direction". If there is no room in that direction, return null.
     * @param direction The exit's direction.
     * @return The room in the given direction.
     */
    public Room getExit(String direction) 
    {
        return exits.get(direction);
    }
    
    /**
     * @return The set of exit directions from this room.
     */
    public Set<String> getExitDirections() 
    {
        return exits.keySet();
    }
    
    /**
     * Adds an item object to the room's collection.
     * @param item The item to be added to the room.
     */
    public void addItem(Item item) 
    {
        items.add(item);
    }
    
    /**
     * Removes an item object from the room's collection.
     * @param item The item to be removed from the room.
     */
    public void removeItem(Item item) 
    {
        items.remove(item);
    }
    
    /**
     * Searches for a specific item in the room by its name.
     * @param name The name of the item to look for.
     * @return The Item object if found, or null if not present.
     */
    public Item getItemCheck(String name) 
    {
        for (Item item : items) 
        {
            if (item.getName().equalsIgnoreCase(name)) 
            {
                return item;
            }
        }
        return null;
    }
    
    /**
     * Returns a string listing all items in the room.
     * @return A string with all items names and emoji.
     */
    public String getItemString() 
    {
        if (items.isEmpty()) 
        {
            return "there is no items in the room!";
        }
        String s = "Items:\n";
        for (Item item : items) 
        {
            s += item.getName() + item.getEmoji() +  ", \"" + item.getHint() + "\"\n";
        }
        return s.trim();
    }
    
    /**
     * Add a character object into this room.
     * @param character The character to add.
     */
    public void addCharacter(Character character) 
    {
        characters.add(character);
    }
    
    /**
     * Remove a character object into this room.
     * @param character The character to remove.
     */
    public void removeCharacter(Character character) 
    {
        characters.remove(character);
    }
    
    /**
     * Returns a string listing all characters in the room.
     * @return A string with all character names and emoji.
     */
    public String getCharacterString() 
    {
        if (characters.isEmpty()) 
        {
            return "there is no cat in the room!";
        }
        String s = "Characters:\n"; 
        
        for (Character character : characters) 
        {
            s += character.getCharacterName() + character.getCharacterEmoji() +", " + character.getCharacterDescription(); 
        }
        return s.trim();
    }
}

